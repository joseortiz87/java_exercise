/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1;

import static hw1.HW1Server.PORT;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket; 

/**
 *
 * @author gholness
 */
public class HW1Client {
    public static final String SERVER_HOST="localhost";
    public static final int SERVER_PORT= HW1Server.PORT;
    
    public static final int NUM_REQUESTERS= 10;
    
    private Object queueLock;
    private RequestorThreadPool pool;
    private WorkQueue queue;
    private final Counter counter;
      
    
    public HW1Client() {
        this.queueLock = new Object();
        this.queue = new WorkQueue();
        this.counter = new Counter();
        this.pool = new RequestorThreadPool(NUM_REQUESTERS,SERVER_HOST,SERVER_PORT,this.counter);
    }
    
    public void startClient() {
        pool.create();
    }
    
    public Counter getCounter(){
        return this.counter;
    }
    
    public static void main(String[] args) {
        try {
  
          HW1Client client = new HW1Client();
          client.startClient();
          
          try {
             Thread.sleep(Integer.MAX_VALUE);
          } catch (Exception e) {
            
          }
                  
        } catch(Exception e) {
           System.exit(-1);
        }
        
        System.exit(0);        
    }    
}
    
