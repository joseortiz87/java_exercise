/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author JO031U
 */
public class TCPClient {

    private static final int SERVER_TIME_OUT = 1000;
    private static final int DEFAULT_PORT = 6789;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner;
        String hostname = "";
        int port = DEFAULT_PORT;
        int iterations = 10;
        String sentence;
        String modifySentence;
        Socket clientSocket;
        int fails = 0;
        long timeCounter = 0l;
        long startTime;
        try{
            System.out.println("*** Starting TCP Client ***");
            scanner = new Scanner(System.in);
            System.out.println("Enter hostname (default- localhost): ");
            hostname = scanner.next();
            System.out.println("Enter port (default- " + DEFAULT_PORT + "): ");
            port = scanner.nextInt();
            hostname = hostname == null ? "localhost" : hostname;
            port = port <= 0 ? DEFAULT_PORT : port;
            System.out.println("Number of iterations: ");
            iterations = scanner.nextInt();          
        }catch(Exception e){
            e.printStackTrace();
        }
        
        System.out.println("Start sending to " + hostname + ":" + port + "...");
        //Start sending strings to server
            for(int i=0;i<iterations;i++){
                try{
                    startTime = System.currentTimeMillis();
                            
                    //Connection to server
                    SocketAddress sockaddr = new InetSocketAddress(hostname,port);
                    clientSocket = new Socket();
                    clientSocket.setSoTimeout(SERVER_TIME_OUT); //TIMEOUT
                    clientSocket.connect(sockaddr,SERVER_TIME_OUT);

                    //Output stream to server
                    DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

                    //Input stream from server
                    BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                    //Write random string to the server
                    sentence = generateRandomString();
                    System.out.println("[Sending]: " + sentence);
                    outToServer.writeBytes(sentence + "\n");

                    //Read response from server
                    modifySentence = inFromServer.readLine();
                    System.out.println(i + ".: " + sentence + " - " + modifySentence);

                    //Close socket
                    clientSocket.close();
                    
                    startTime = (System.currentTimeMillis()-startTime);
                    timeCounter += startTime;
                }catch(Exception e){
                    fails++;
                }
            }
        System.out.println("Successful request/response interactions: " + (iterations-fails) + " out of " + iterations);
        System.out.println("Average request/response time (round trip): " + (double)(timeCounter/iterations));
    }
    
    /**
     * GENERATE RANDOM STRINGS (a-z) OF RANDOM LENGTH (10-110)
     * @return 
     */
    private static String generateRandomString(){
        Random rand = new Random();
        StringBuilder str = new StringBuilder();
        char randChar;
        for(int i=0;i<rand.nextInt(100)+10;i++){
            randChar = (char)(rand.nextInt(26)+97);
            str.append(randChar); //from a to z
        }
        return str.toString();
    }
}
