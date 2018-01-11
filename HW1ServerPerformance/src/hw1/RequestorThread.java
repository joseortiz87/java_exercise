/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author gholness
 */
public class RequestorThread extends Thread {
     private Request req;
     private Response resp;
     private String name;
     private int serverPort;
     private String serverHost;
     private String scratchSpace;
     private String resultSpace;
     private final Counter counter;
     private int iteration;
     
     public RequestorThread (String name, String serverHost, int serverPort,Counter counter) {
         super();
         this.name= name;
         this.serverHost= serverHost;
         this.serverPort= serverPort;
         this.counter= counter;
     }
     
     public void run() {
        
         System.out.println("running thread: " + name);
         
         while(true) {
            if (doWork()) {

            } else {
                
            }
         }
         
         
     }
     
     public boolean doWork() {
       boolean result = false;
       
       try {
       
         InetAddress iAddr = InetAddress.getByName(serverHost);
     
         
         Socket clientSocket= new Socket(iAddr,serverPort);  
         clientSocket.setKeepAlive(true);
         
         PrintWriter out= new PrintWriter(clientSocket.getOutputStream(),true);
         
         BufferedReader in= new BufferedReader(
                 new InputStreamReader(clientSocket.getInputStream())
         );  
         
         iteration++;
         
         scratchSpace = new String("message from Thread " + name);
         
         out.println(scratchSpace);

         resultSpace = in.readLine();
         clientSocket.close();
         counter.incrementCount();
         
         System.out.println("iteration(" + iteration + ")" + name + 
                           " sentMsg: [" + scratchSpace + "]" +
                           "  rcvMsg [" + resultSpace + "]" );
         
         
         result= true;
         
       } catch (Exception e) {
         scratchSpace = null;
         resultSpace= null;
         result= false;
       }
       
       return result;
     }
     
}
