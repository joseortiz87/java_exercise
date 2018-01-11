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
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author JO031U
 */
public class Node {

	private final int uuid;
	private final int port;
	private final int timeout;

	private Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;
        private ServerSocket serverSocket = null;
        private final ServerThread server;
        private HashMap<Integer, Node> nodes = new HashMap<>();
        private Integer coordinatorId;
        private CoordinatorListener listener;

	public Node(int uuid, int port, int timeout) {
            this.port = port;
            this.uuid = uuid;
            this.timeout = timeout;
            try {
                serverSocket = new ServerSocket(this.port,Integer.MAX_VALUE);
                System.out.println("Started server socket " + this.uuid);
             } catch(IOException e) {
                 System.err.println("Couldnt not listen on port " + port);
             }
            server = new ServerThread(this,serverSocket);
	}

	public int getUuid() {
            return uuid;
	}

	public int getPort() {
            return port;
	}
        
        public void startProcess(){
             server.start();
        }

        public int getTimeout() {
            return timeout;
        }

        public void setNodes(HashMap<Integer, Node> nodes) {
            this.nodes = nodes;
        }
        
        /**
         * 
         */
        public void startElection(){
            System.out.println(String.format("[%d] Triggering an election...",getUuid()));
            boolean ok = false;
            Collection<Node> all = nodes.values();

            for (Node n : all) {
                n.clearListener();
		if (n.getUuid() > getUuid()) {
                    ok = elect(n) || ok;
		}
            }
            
	    // No OK responses, become the new leader.
	    if (ok == false) {
		System.out.println(String.format("[%d] No OK responses...",getUuid()));
		sendResult();
            }
        }
        
        public void clearListener(){
            coordinatorId = 0;
            if(listener != null){
                listener.setListenerOn(false);
            }
            listener = null;
        }
    
        /**
         * 
         * @param n
         * @return 
         */
	private boolean elect(Node n) {
            connect(n);
            
            boolean ok = false;
            System.out.println(String.format("[%d] Send Elect %d to %d.",getUuid(), getUuid(), n.getUuid()));
            Timing.delay();

            if (!Omission.omit()) {
                writer.println(getUuid());
                writer.println(Message.ELECT);
            }

            if (getMessage() == Message.OK) {
                ok = true;
                System.out.println(String.format("[%d] Received OKAY from %d.",getUuid(), n.getUuid()));
            }
            
            disconnect();
            return ok;
	}
        
        /**
         * 
         */
        private void sendResult(){
            Collection<Node> all = nodes.values();
            for (Node n : all) {
		// Send result to all except self
		if (n.getUuid() != getUuid()) {
                    result(n);
		}
            }
        }

        /**
         * 
         * @param n 
         */
	private void result(Node n) {
            connect(n);

            System.out.println(String.format("[%d] Send Result %d to %d.",getUuid(), getUuid(), n.getUuid()));
            Timing.delay();

            if (!Omission.omit()) {
                writer.println(getUuid());
                writer.println(Message.COORDINATOR);
            }
            
            disconnect();
	}

        /**
         * 
         * @return 
         */
	private Message getMessage() {
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    return Message.valueOf(line);
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            return null;
	}

        /**
         * CONNECT TO NODE
         * @param n 
         */
	private void connect(Node n) {
		try {
                    socket = new Socket(BullyAlgorithm.SERVER_HOST,n.getPort());
                    socket.setSoTimeout(timeout);
                    writer = new PrintWriter(socket.getOutputStream(), true);
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
                    System.err.println(e.getMessage());
		}
	}

        /**
         * CLOSE CONNECTIONS
         */
	private void disconnect() {
		try {
                    socket.close();
                    socket = null;
                    writer = null;
                    reader = null;
		} catch (IOException e) {
                    System.err.println(e.getMessage());
		}
	}
        
        /**
         * THREAD THAT CHECK COORDINATOR STATUS
         * @param coordinatorId 
         */
        public void startCoordinatorListener(Integer coordinatorId){
            System.out.println(String.format("[%d] Start listener...", getUuid()));
            this.coordinatorId = coordinatorId;
            if(listener != null){
                listener.setListenerOn(false);
            }
            listener = new CoordinatorListener(this);
            listener.start();
        }
        
        /**
         * SEND PING MESSAGE TO COORDINATOR
         * @return 
         */
        public boolean testCoordinator(){
            if(nodes.containsKey(coordinatorId)){
                Node coordinatorNode = nodes.get(coordinatorId);
                System.out.println(String.format("[%d] Test coordinator %d.",getUuid(), coordinatorNode.getUuid()));
                connect(coordinatorNode);
                
                writer.println(getUuid());
                writer.println(Message.PING);
                
                Message msg = getMessage();
                disconnect();
                return msg == Message.OK;
            }
            return false;
        }
}
