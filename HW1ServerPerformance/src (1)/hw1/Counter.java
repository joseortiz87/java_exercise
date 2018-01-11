/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1;

/**
 *
 * @author JO031U
 */
public class Counter {
    private Long requestCount;
    private Long proccessCount;

    public Counter() {
        this.requestCount = new Long(0);
        this.proccessCount = new Long(0);
    }
    
    public void addRequestCount() {
        synchronized(this.requestCount){
            this.requestCount ++;
        }
    }
    
    public void addProccessCount() {
        synchronized(this.proccessCount){
            this.proccessCount++;
        }
    }
    
    public Long getRequestCount() {
        return requestCount;
    }

    public Long getProccessCount() {
        return proccessCount;
    }
    
    
}
