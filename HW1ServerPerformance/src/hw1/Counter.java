/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1;

import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author JO031U
 */
public class Counter {
    private final AtomicLong count;

    public Counter() {
        this.count = new AtomicLong(0l);
    }
    
    public void incrementCount(){
        count.incrementAndGet();
    }
    
    public long getCount(){
        return count.get();
    }
}
