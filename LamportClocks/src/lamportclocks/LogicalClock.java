/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamportclocks;

/**
 *
 * @author JO031U
 */
public class LogicalClock {
    private int clock;
    public LogicalClock(int clock){
        this.clock = clock;
    }
    public int getClock() {
        return clock;
    }
    public void increment(){
       clock = clock +1;
    }
    public void setClock(int clock) {
        this.clock = clock;
    }
}
