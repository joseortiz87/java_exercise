/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leaderelection.bully;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 *
 * @author JO031U
 */
public class BullyAlgorithm {

	int timeout;
	boolean isInitiator = true;
	BufferedReader reader;
	PrintWriter writer;

	public static Node self;
	HashMap<Integer, Node> nodes;
        
        public static final int START_UID = 1;
        public static final int START_PORT = 4444;
        public static final int TIMEOUT = 3500;
        public static final int NODE_NUMBER = 5;
        public static final String SERVER_HOST = "localhost";

	public static void main(String[] args) {
            System.out.println("Start Bully Algorithm...");
	    BullyAlgorithm bully = new BullyAlgorithm();
	    bully.configuration();
            bully.startElection();
	}

	private void configuration() {
            try {
                timeout = TIMEOUT;
                BullyAlgorithm.self = new Node(START_UID,START_PORT,this.timeout);
                System.out.println(String.format("Create node: %d, port: %d", BullyAlgorithm.self.getUuid(), BullyAlgorithm.self.getPort()));
                nodes = setUpNodes();
            } catch (Exception e) {
		System.err.println(e); // TODO: DEBUG
		System.exit(-1);
            }
	}

        /**
         * Create nodes for the network
         * @return
         * @throws Exception 
         */
	private HashMap<Integer, Node> setUpNodes() throws Exception {
            HashMap<Integer, Node> nodes = new HashMap<>();
            nodes.put(START_UID, this.self);
            this.self.startProcess();
	    Node newNode;
            for(int i=1;i<NODE_NUMBER;i++){
                newNode = new Node(START_UID+i,START_PORT+i,this.timeout);
		nodes.put(newNode.getUuid(),newNode);
                newNode.startProcess();
		System.out.println(String.format("Create node: %d, port: %d", newNode.getUuid(), newNode.getPort()));
            }
            for(Node n : nodes.values()){
                n.setNodes(nodes);
            }
            return nodes;
	}

	public void startElection() {
            self.startElection();
	}
}
