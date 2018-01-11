/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
/**
 *
 * @author gholness
 */
public class WorkQueue<Request> extends ArrayList<Request> {
  private static int HEAD= 0;
  
  private Object queueLock;
  private AtomicLong sum = new AtomicLong(0);
  private AtomicLong count = new AtomicLong(0);
  private AtomicLong max = new AtomicLong(0);
          
  /***
   * 
   */
  public WorkQueue() {
      super();
      queueLock = new Object();
      
  }
  
  /***
   * 
   * @param initialCapacity 
   */
  public WorkQueue(int initialCapacity) {
      super(initialCapacity);
      queueLock = new Object();
  }
  
  /***
   * 
   * @param req
   * @return boolean- true: request added  false:  request not added
   */
  public boolean addRequest(Request req) {
      boolean result= false;
      
      synchronized(queueLock) {
        result = super.add(req);
      }
      return result;
  }
  
  
  /***
   * 
   * @return instance of <code>Request</code> object representing
   *         the request at the head of the <code>WOrkQueue</code>
   */
  public Request getRequest() {
      Request req= null;
      if(max.longValue() < size()){
          max.set(size());
      }
      synchronized(queueLock) {
        if (super.size()> 0)
          req= super.remove(HEAD);
      }
      sumToAvarage();
      return req;
  }
  
  public int size() {
      int k= -1;
      
      synchronized(queueLock) {
        k = super.size();
      }
      
      return k;
  }

  public String toString() {
      String msg= "WorkQueue: ";
      
      int k= 0;
      synchronized(queueLock) {
        k = super.size();
      }
  
      msg= msg.concat(new Integer(k).toString());
      
      return msg;
  }
  
  public void sumToAvarage(){
      sum.addAndGet(size());
      count.incrementAndGet();
  }
  
  public double calculateAvarage(){
      System.out.println("sum: " + sum);
      System.out.println("count: " + count);
      Double res = new Double(max.incrementAndGet()/2); //Math.pow(sum.get(),1/count.get());
      System.out.println("avarage: " + res);
      sum.set(0);
      count.set(0);
      max.set(0);
      return res;
  }
  
}
