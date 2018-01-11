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
    
    
    private final int NUM_WORKERS= 1;
    private static final long SERVER_LIVE = 10000l;
    
    private ServerSocket serverSocket;
    private Socket connectionSocket;
    private final Counter counter;
    private final int K = 10; //
    
    public HW1Server() {
        queueLock = new Object();
        queue = new WorkQueue();
        counter = new Counter();
        pool = new WorkerThreadPool(NUM_WORKERS,queue,counter);
    }
     
    public void startServer() {
        long startTime = System.currentTimeMillis();
        pool.create();
        try {
           serverSocket = new ServerSocket(PORT,BACKLOG);
           System.out.println("HW1Server::startServer:  started server socket");
        } catch(IOException e) {
            System.err.println("Couldnt not listen on port " + new Integer(PORT).toString());
        }
        
        while ((System.currentTimeMillis()-startTime) <= SERVER_LIVE) { 
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
            counter.addRequestCount();
          
          } catch(IOException e) {
            
          }   
        }
        
        long executionTimeSeconds = (System.currentTimeMillis() - startTime)/1000l;
        System.out.println("END RUNNING SERVER"); 
        double lambda = (getCounter().getRequestCount()/executionTimeSeconds);
        double miu = (getCounter().getProccessCount()/executionTimeSeconds);
        double U = lambda/miu;
        double pk = (1-U)*Math.pow(U,K);
        double N = U/ (1-U);
        System.out.println("Request/sec: " + lambda);
        System.out.println("Proccess/sec: " + miu);
        System.out.println("Server utilization: " + U);
        System.out.println("Probability of k message in server queue: " + pk);
        System.out.println("Avarage number of message in the queue: " + N);
        System.out.println("Avarage queue size: " + queue.calculateAvarage());
    }
    
    public Counter getCounter(){
        return this.counter;
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
