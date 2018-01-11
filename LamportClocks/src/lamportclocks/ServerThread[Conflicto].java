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
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author JO031U
 */
public class ServerThread extends Thread {
    
    public static final int BASE_PORT = 8000;
    public static final String SERVER_HOST = "localhost";
    public static final int MAX_SLEEP_TIME = 10;
    
    private final int id;
    private final int port;
    private ServerSocket serverSocket;
    private Socket connectionSocket;
    private LogicalClock logicalClock;
    
    public ServerThread(int id,int numThreads,LogicalClock logicalClock){
        this.id = id;
        this.port = BASE_PORT + id;
        this.logicalClock = logicalClock;
        try {
           serverSocket = new ServerSocket(port,Integer.MAX_VALUE);
           System.out.println("Started server socket " + id + " with logical clock " + logicalClock.getClock());
        } catch(IOException e) {
            System.err.println("Couldnt not listen on port " + port);
        }
    }
 
    @Override
    public void run() {
        //System.out.println("Running server thread: " + id);
        while(true) {
            doWork();
        } 
    }
     
     public void doWork() {
        try {
            //GET REQUEST
            connectionSocket = serverSocket.accept();
            connectionSocket.setKeepAlive(true);
            
            PrintWriter out = new PrintWriter(connectionSocket.getOutputStream(),true);
          
            BufferedReader in = new BufferedReader(
                       new InputStreamReader(
                               connectionSocket.getInputStream()
                       )
            );
          
            String input = in.readLine();

            System.out.println("[SERVER ID-" + id + " CLK-" + logicalClock.getClock() + "]" +
                               "  received request [" +  input + "]");
            
            int startIdex = input.indexOf("-");
            int endIdex = input.indexOf("]");
            input = input.substring(++startIdex, endIdex);
            Integer sendTime = new Integer(input);
            if(logicalClock.getClock() < sendTime){
                System.out.println("[SERVER ID-" + id + "] Synchronize clock to " + (sendTime+1));
                logicalClock.setClock(sendTime+1);
            }
            out.println(input.toUpperCase());
            out.flush();
        }catch(IOException e){
        
        }
    }
}
