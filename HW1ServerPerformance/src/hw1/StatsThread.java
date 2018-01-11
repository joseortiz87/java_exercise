/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JO031U
 */
public class StatsThread extends Thread{
    private final Counter requestCounter;
    private final Counter proccessCounter;
    private final WorkQueue queue;
    private static final int IDLE_PAUSE= 10000;
    private static final int K = 5; //
    private long index = 1;

    public StatsThread(Counter requestCounter, Counter proccessCounter,WorkQueue queue) {
        super();
        this.requestCounter = requestCounter;
        this.proccessCounter = proccessCounter;
        this.queue = queue;
    }
    
     public void run() {
        System.out.println("stats thread");
         
        while(true) {
            try {
                Thread.sleep(IDLE_PAUSE);
                doWork();
            } catch (InterruptedException ex) {
                Logger.getLogger(StatsThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
    public boolean doWork(){
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("ServerStats.txt", true));
            long executionTimeSeconds = (IDLE_PAUSE/1000l)*index;
            double lambda = (requestCounter.getCount()/executionTimeSeconds);
            double miu = (proccessCounter.getCount()/executionTimeSeconds);
            double U = miu/lambda;
            double pk = (1-U)*Math.pow(U,K);
            double N = U/ (1-U);
            writer.append("\n");
            writer.append("\nStats at Time: " + executionTimeSeconds + " seconds...");
            writer.append("\nNumber of request: " + requestCounter.getCount());
            writer.append("\nNumber of proccessed request: " + proccessCounter.getCount());
            writer.append("\nCurrent queue size: " + queue.size());
            writer.append("\nRequest/sec: " + lambda);
            writer.append("\nProccess/sec: " + miu);
            writer.append("\nServer utilization: " + U);
            writer.append("\nProbability of k message in server queue: " + pk);
            writer.append("\nAvarage number of message in the queue: " + N);
            writer.append("\nActual Avarage queue size: " + queue.calculateAvarage());
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(StatsThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(StatsThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            index++;
        }
        return true;
    }
}
