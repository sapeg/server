package com.pol.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import static com.pol.server.Main.sct1;
import static com.pol.server.Main.sct2;
import static com.pol.server.Main.serverClient1;
import static com.pol.server.Main.serverClient2;
import static com.pol.server.ServerClientThread1.outStream1;

public class ServerClientThread2 extends Thread {
    Socket serverClient2;
    int clientNo;
 static DataInputStream inStream2=null;
 static DataOutputStream outStream2=null;
 
    ServerClientThread2(Socket inSocket,int number){
         this.serverClient2 = inSocket;
         this.clientNo=number;
    }
    public void run(){
        try{
            outStream2 = new DataOutputStream(serverClient2.getOutputStream());
            inStream2 = new DataInputStream(serverClient2.getInputStream());
            String clientMessage2="", serverMessage="";
            while(!clientMessage2.equals("bye")){
                clientMessage2=inStream2.readUTF();
                System.out.println("From Client-" +clientNo+ ": Number is :"+clientMessage2);
   /*             serverMessage="From Server to Client-" + clientNo + " Square of " + clientMessage;
                outStream.writeUTF(serverMessage);
                outStream.flush();
   */             
                outStream1.writeUTF(clientMessage2);
                outStream1.flush();
               

            }
            inStream2.close();
            outStream2.close();
            serverClient2.close();
        }catch(Exception ex){
            System.out.println(ex);
        }finally{
      //      System.out.println("Client -" + clientNo + " exit!! ");
        }
    }
/*    public ServerClientThread2() {
       
    }*/

}
