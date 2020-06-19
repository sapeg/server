package com.pol.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import static com.pol.server.Main.sct1;
import static com.pol.server.Main.sct2;
import static com.pol.server.Main.serverClient1;
import static com.pol.server.Main.serverClient2;
import static com.pol.server.ServerClientThread2.outStream2;

 public class ServerClientThread1 extends Thread {
    Socket serverClient1;
    int clientNo;
 static DataInputStream inStream1=null;
 static DataOutputStream outStream1=null;
 
    ServerClientThread1(Socket inSocket, int number){
         this.serverClient1 = inSocket;
         this.clientNo=number;
      //  clientNo=counter;
    }
  /*    ServerClientThread1(Mediator mediator) {
        
     }*/


    public void run(){
                  
   try{ 
    //   System.out.println("serverClient1 /sct1/ "+serverClient1);
            inStream1 = new DataInputStream(serverClient1.getInputStream());
            outStream1 = new DataOutputStream(serverClient1.getOutputStream());
        String clientMessage1="", serverMessage="";
            while(!clientMessage1.equals("bye")){
               //приём позиции-хода от игрока
                clientMessage1=inStream1.readUTF();
                System.out.println("From Client-" +clientNo+ ":  :"+clientMessage1);
               //и передача его индекса на устройство соперника
                outStream2.writeUTF(clientMessage1);
                outStream2.flush();
                
                
                
                
//              serverMessage=clientMessage;
 //               outStream.writeUTF(serverMessage);
 //               outStream.flush();
                
            }
       //     System.out.println("serverClient1");
            inStream1.close();
            outStream1.close();
            serverClient1.close();
        }catch(Exception ex){
            System.out.println(ex);
                 System.out.println("catch thread1");
       
        }
   finally{
    //        System.out.println("Client -" + clientNo + " exit!! ");
        }
    }
}
/* if(clientMessage.equals("qwe")) {
                    sct1.send("12345");
                    sct1.notify("notify_from1");
                         serverMessage=" Square of " + clientMessage;
                        
            DataInputStream inStream2 = new DataInputStream(sct2.serverClient2.getInputStream());
            DataOutputStream outStream2 = new DataOutputStream(sct2.serverClient2.getOutputStream());
                outStream2.writeUTF(serverMessage);
                outStream2.flush();
                }*/