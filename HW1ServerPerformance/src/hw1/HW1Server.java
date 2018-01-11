/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author gholness
 * 
 */
public class HW1Server {
    public static final int PORT= 4444;
    public static final int BACKLOG= Integer.MAX_VALUE;
    public static final boolean DEBUG= false;
    
    private Object queueLock;
    private WorkerThreadPool pool;
    private WorkQueue queue;
    
    
    private final int NUM_WORKERS= 4;
    
    private ServerSocket serverSocket;
    private Socket connectionSocket;
    private final Counter counter;
    
    public HW1Server() {
        this.queueLock = new Object();
        this.queue = new WorkQueue();
        this.counter = new Counter();
        this.pool = new WorkerThreadPool(NUM_WORKERS,queue,counter);
    }
     
    public void startServer() {
        pool.create();
        try {
           serverSocket = new ServerSocket(PORT,BACKLOG);
           System.out.println("HW1Server::startServer:  started server socket");
        } catch(IOException e) {
            System.err.println("Couldnt not listen on port " + new Integer(PORT).toString());
        }
        
        while (true) { 
          try {
            connectionSocket = serverSocket.accept();
            connectionSocket.setKeepAlive(true);
            
            if (DEBUG)
              System.out.println("HW1Server::startServer:  accepted connection " +
                                                              connectionSocket);
                  
                       
            Request req= new Request(connectionSocket);
            
            if (DEBUG)
              System.out.println("server adding request to queue " + req);
            
            queue.add(req);
          
          } catch(IOException e) {
            
          }   
        }
    }
    
    public Counter getCounter(){
        return this.counter;
    }
    
    public WorkQueue getWorkQueue(){
        return this.queue;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        try {
           
          HW1Server server = new HW1Server();
          server.startServer();
          
          try {
            Thread.sleep(Integer.MAX_VALUE);
          } catch (Exception e) {
            
          }
        } catch(Exception e) {
           e.printStackTrace();
           System.exit(-1);
        }
        
        System.exit(0);
    }
    
}
