/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1;


public class HW1MainRun {
    
    public static void main(String args[]){
        
        HW1Server server = new HW1Server();
        HW1Client client = new HW1Client();
        
        /*
        * START CLIENTS
        */
        try {
          client.startClient();
        } catch(Exception e) {
           e.printStackTrace();
        }
        
        /**
         * START STATICS THEAD
         */
        try{
            StatsThread statsThread = new StatsThread(client.getCounter(),server.getCounter(),server.getWorkQueue());
            statsThread.start();
            
        } catch(Exception e) {
           e.printStackTrace();
        }
        
                /*
        * START SERVER AND WORKERS
        */
        try {
          server.startServer();
        } catch(Exception e) {
           e.printStackTrace();
        }
        
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            
        }
        
        System.exit(0);
        
    }
    
}
