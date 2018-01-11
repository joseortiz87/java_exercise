/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gholness
 */
public class WorkerThread extends Thread {
     private final WorkQueue queue;
     private String scratchSpace;
     private Request req;
     private Response resp;
     private final String name;
     private final Counter counter;
     private static final int IDLE_PAUSE= 1;
     private int queueState;
     private int iteration;
     
     public WorkerThread (WorkQueue queue, String name,Counter counter) {
         super();
         this.queue = queue;
         this.name= name;
         this.counter = counter;
     }
     
     public void run() {
        
         System.out.println("running thread: " + name);
         
         while(true) {
             
            queueState= queue.size();
            req= (Request) queue.getRequest();
            
            if (req == null) {
                continue;
            }
            
            if(queueState >0){
                counter.incrementCount();
            }
            
            doWork();
//            try {
//                Random rand = new Random();
//                Thread.sleep(rand.nextInt(IDLE_PAUSE));
//            } catch (InterruptedException ex) {
//                Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
//            }
         }
         
         
     }
     
     public boolean doWork() {
       boolean result = false;
       
       try {
    
         Socket conn= req.getConnection();
         
                 
         PrintWriter out = new PrintWriter(conn.getOutputStream(),true);
          
         BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            conn.getInputStream()
                    )
         );
          
        String input = in.readLine();
        
        iteration++;
        System.out.println("iteration(" + iteration + "): " + name +  
                           "  received request [" +  input + "]" +
                           "  queue state K= " + queueState);
         
         scratchSpace = input.toUpperCase();
         
         out.println(scratchSpace);
         out.flush();
              
         result= true;
         
       } catch (Exception e) {
         scratchSpace = null;
         result= false;
       }
       
       return result;
     }
     
}
