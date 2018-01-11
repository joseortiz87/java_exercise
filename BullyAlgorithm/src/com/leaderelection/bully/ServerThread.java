/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leaderelection.bully;

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
public class ServerThread extends Thread{

    private final ServerSocket serverSocket;
    private final Node node;
    private Socket connectionSocket;
    
    public ServerThread(Node node,ServerSocket serverSocket){
        this.node = node;
        this.serverSocket = serverSocket;
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
            connectionSocket.setSoTimeout(node.getTimeout());
            
            PrintWriter out = new PrintWriter(connectionSocket.getOutputStream(),true);
          
            BufferedReader in = new BufferedReader(
                       new InputStreamReader(
                               connectionSocket.getInputStream()
                       )
            );
          
            int senderID = Integer.parseInt(in.readLine());
            Message message = Message.valueOf(in.readLine());

            if (message == Message.ELECT) {
                Timing.delay();
                if (!Omission.omit()){
                    out.println(Message.OK);
                    out.flush();
                    System.out.println(String.format("[" + node.getUuid() + "] Send OKAY to %d.", senderID));
                    node.startElection(); //TRIGGER ELECTION
                }
            } else if (message == Message.PING) {
                Timing.delay();
                if (!Omission.omit()){
                    out.println(Message.OK);
                    out.flush();
                    System.out.println(String.format("[" + node.getUuid() + "] Received Ping from %d.", senderID));
                }
            } else if (message == Message.COORDINATOR){
                System.out.println(String.format("[" + node.getUuid() + "] - New coordinator %d.", senderID));
                //IF THIS NODE ID IS GREATER THAN THE NEW COORDINATOR START ELECTION AGAIN
                if(node.getUuid() > senderID){
                    node.startElection();
                }else{
                    node.startCoordinatorListener(senderID); //ATTACH LISTENER TO PING THE NEW COORDINATOR
                }
            }
        }catch(IOException | NumberFormatException e){
            System.out.println(e.getMessage());
        }
    }
}
