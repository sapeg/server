package com.pol.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import static com.pol.server.MainActivity.t1;
import static com.pol.server.MainActivity.t2;

//import android.os.StrictMode;

public class Main extends Activity {
    static com.pol.server.ServerClientThread1 sct1;
    static com.pol.server.ServerClientThread2 sct2;
    static Socket serverClient=null;
 static Socket serverClient1=null,serverClient2=null;   
 static ArrayList<String> as=new ArrayList<String>();
 static Boolean f=false;
    static ExecutorService executor;
    public static Handler handler;
    public static ServerSocket server;
    public View t3,t4;
// @TargetApi(Build.VERSION_CODES.GINGERBREAD)
 public static void main(String args) throws IOException {

/*       

    
     //      String address = "192.168.0.91";
  InetAddress ipAddress = InetAddress.getByName(address);   
  
   serverClient1 = new Socket(ipAddress,serverPort); 
    //    Socket serverClient2 = new Socket(ipAddress,serverPort); 
*/     
/*
        ServerClientThread1 c1 = new ServerClientThread1("8888"",m);
        ServerClientThread2 c2 = new ServerClientThread2(m);

        m.setServerClientThread1(c1);
        m.setServerClientThread2(c2);

        c1.start();
        c1.send("How are you?");
        c2.send("Fine, thanks");
*/
try{
    //planshet : 176.59.13.186
    //           176.59.2.138
    //           92.63.194.76
    //           176.59.2.137
    
    ///185.211.159.111
    ///176.59.2.137
as.clear();

    executor = Executors.newSingleThreadExecutor();
    executor.submit(() -> {

        System.out.println("S ....");


            server=new ServerSocket(8777);
      URL url = new URL("http://checkip.amazonaws.com/");
//BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            int counter=0;
   // System.out.println("Server Started ("+br.readLine()+")"+server.getLocalPort()+" ....");
    System.out.println("Server Started ....");
            while(true){
         /*       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

                    StrictMode.setThreadPolicy(policy);
                }*/
                serverClient=server.accept();  // сервер принимает запрос на подключение клиента
   //             System.out.println("S");
            System.out.println("Started "+serverClient.getInetAddress()+" ....");
              if(!as.contains(serverClient.getInetAddress().toString())){
                  counter++;
                System.out.println("counter "+counter);
        //          t1.setVisibility(View.VISIBLE);
      //            t1.invalidate();

                  as.add(serverClient.getInetAddress().toString()); 
                  if(counter==1){
                      System.out.println("Ожидается подключение второго игрока ....");
                    serverClient1=serverClient;
                      System.out.println("S1");
                      handler.sendEmptyMessage(1);
                    DataOutputStream outStream1 = new DataOutputStream(serverClient1.getOutputStream());
                     outStream1.writeUTF(String.valueOf(counter));
                     outStream1.flush();
                              }
                   if(counter==2){
                //       t2.setVisibility(View.VISIBLE);
                //       t2.invalidate();
                       handler.sendEmptyMessage(2);

                       System.out.println("S2");

                       serverClient2=serverClient;
                   sct2 = new ServerClientThread2(serverClient2,2);
                   sct2.start();
                   sct1 = new ServerClientThread1(serverClient1,1);
                   sct1.start();
                   
                   //оповешение первого игрока о начале игры
                     DataOutputStream outStream1 = new DataOutputStream(serverClient1.getOutputStream());
                     outStream1.writeUTF(String.valueOf(11));
                     outStream1.flush();


                     DataOutputStream outStream2 = new DataOutputStream(serverClient2.getOutputStream());
                     outStream2.writeUTF(String.valueOf(counter));
                     outStream2.flush();
                     for(int q=0;q<as.size();q++) System.out.println(as.get(q).toString());  
                           }
             }
 if(counter>2){
  /*            try{
                System.out.println("counter "+counter);
                if(counter==1) {
                   
               } // отправляем запрос в отдельный поток
               if(counter==2 & !serverClient.equals(serverClient1)) {
                    
                    
                    
               } // отправляем запрос в отдельный поток
            }catch(Exception e){
                   System.out.println("op");
                   System.out.println(e);
                   e.printStackTrace();
        }
      */              
    //          System.out.println(" "+counter);

  //     
            
   
 if(as.contains(serverClient.getInetAddress().toString()) & counter>=2){
                    //   System.out.println(serverClient.getInetAddress().equals(serverClient1.getInetAddress()));
          if(serverClient.getInetAddress().equals(serverClient1.getInetAddress())){
             System.out.println("wow1");
              sct1.serverClient1=serverClient;
                   sct1.run();
               }
           if(serverClient.getInetAddress().equals(serverClient2.getInetAddress())){
              System.out.println("wow2");
                  sct2.serverClient2=serverClient;
                   sct2.run();
      
               }
              }
            }     
               if(as.size()>=2) counter=1111; 

    //           if(counter>2)System.out.println("2 потока уже создано");
      
   //     System.out.println("end while Main");
            }
    });
}catch(Exception e){
            System.out.println("catch main "+e);
            e.printStackTrace();

        }finally{
    System.out.println("finally");
 
    }

     }
        //        serverClient.close();
        //        serverClient1.close();
        //        serverClient2.close();

 }
 