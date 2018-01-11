/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamportclocks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static lamportclocks.ServerThread.BASE_PORT;
import static lamportclocks.ServerThread.MAX_SLEEP_TIME;
import static lamportclocks.ServerThread.SERVER_HOST;

/**
 *
 * @author JO031U
 */
public class ClientThread extends Thread {
    
    private final int numThreads;
    private final int id;
    private final int port;
    private final LogicalClock logicalClock;
    private final Random rand = new Random();
    
    public ClientThread(int id,int numThreads,LogicalClock logicalClock){
        this.id = id;
        this.port = BASE_PORT + id;
        this.numThreads = numThreads;
        this.logicalClock = logicalClock;
    }
    
    @Override
    public void run() {
        System.out.println( "[ID-" + id + "]Running client thread: " + id);
        while(true) {
            try {
                //SLEEP 
                Thread.sleep(rand.nextInt(MAX_SLEEP_TIME)+1);
            } catch (InterruptedException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            doWork();
        } 
    }

    public void doWork() {
        try {
            //SELECT EVENT
            if(rand.nextBoolean()){
                logicalClock.increment();
                System.out.println("[CLK-" + logicalClock.getClock() + "] Process " + id + " is awake!");
            }else{
                sendMessageRandomServer();
            }
        }catch(Exception e){
        
        }
    }
     
    private void sendMessageRandomServer(){
       try {
            logicalClock.increment();
            logicalClock.increment();
            InetAddress iAddr = InetAddress.getByName(SERVER_HOST);
            int randPort = port;
            while(randPort == port){
                randPort = (rand.nextInt(numThreads)+1) + BASE_PORT;
            }
            //System.out.println("[ID-" + id + " - Sending message to server port " + randPort + " ]");
            try (Socket clientSocket = new Socket(iAddr,randPort)) {
               clientSocket.setKeepAlive(true);
               PrintWriter out= new PrintWriter(clientSocket.getOutputStream(),true);
               BufferedReader in= new BufferedReader(
                       new InputStreamReader(clientSocket.getInputStream())
               );
               out.println(("[CLK-" + logicalClock.getClock() + "] Hello from process " + id));
               //System.out.println(in.readLine());
            }
       } catch (IOException e) {
           
       }
    }
}
