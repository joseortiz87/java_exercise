/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1;

import java.util.ArrayList;


/**
 *
 * @author gholness
 */
public class RequestorThreadPool {   
    private int size;
    private boolean isBbuilt;
    private final ArrayList<Thread> workers;
    private String servHost;
    private int servPort;
    private final Counter counter;
    
    public RequestorThreadPool(int numThreads,
                               String servHost, int servPort,Counter counter) {
      this.size= numThreads;
      this.servHost = servHost;
      this.servPort= servPort;
      this.workers= new ArrayList<>(size);
      this.counter= counter;
    }
    
    
    public void create() {
      if (build()) {
         invoke();
      }    
    }
    
    public boolean build() {
       boolean response= false;
       Thread t= null;
       String name= null;
       
       try {
         for (int i=0; i < size; i++) { 
           name = new String("Requestor ").concat(new Integer(i+1).toString());
           t = new RequestorThread(name,servHost,servPort,counter);
           
           workers.add(t);
           System.out.println("RequestorThreadPool::build:  added " + t);
         }
         response= true;
       } catch (Exception e) {
         
       }
       
       return response;
    }
    
    public boolean invoke() {
        boolean response= false;
        
        Thread t = null;
        
        try {
          
          for (int i=0; i < size; i++) {
              t= workers.get(i);
              t.start();
          }
          response= true;
           
        } catch (Exception e) {
           
        }
        
        return response;
    }
}

