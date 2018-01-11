/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamportclocks;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JO031U
 */
public class ServerThreadPool {
    private final int numThreads;
    private final List<Thread> servers;
    private final List<Thread> clients;
    
    public ServerThreadPool(int numThreads){
        this.numThreads = numThreads;
        this.servers = new ArrayList<>();
        this.clients = new ArrayList<>();
    }
    
    public void create() {
      if (build()) {
         invoke();
      }    
    }
    
    private boolean build() {
       boolean response= false;
       Thread t;
       try {
         for (int i=1; i <= numThreads; i++) { 
           LogicalClock logicalClock = new LogicalClock(i);
           t = new ServerThread(i,numThreads,logicalClock);
           servers.add(t);
           t = new ClientThread(i,numThreads,logicalClock);
           clients.add(t);
         }
         response= true;
       } catch (Exception e) {
         
       }
       return response;
    }
    
    private boolean invoke() {
        try {
            servers.forEach((t) -> {
                t.start();
            }); 
            clients.forEach((t) -> {
                t.start();
            });
        } catch (Exception e) {
           return false;
        }
        return true;
    }
}
