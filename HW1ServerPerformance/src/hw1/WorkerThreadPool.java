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
public class WorkerThreadPool {   
    private int size;
    private final WorkQueue queue;
    private boolean isBbuilt;
    private final ArrayList<Thread> workers;
    private final Counter counter;
    
    public WorkerThreadPool(int numThreads, WorkQueue queue,Counter counter) {
      this.size= numThreads;
      this.queue = queue;
      this.workers= new ArrayList<>(size);
      this.counter = counter;
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
           name = new String("Worker ").concat(new Integer(i+1).toString());
           t = new WorkerThread(queue,name,counter);
           
           workers.add(t);
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
