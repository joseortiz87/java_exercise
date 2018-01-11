/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpclient;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author JO031U
 */
public class UDPClient {

    private static final int SERVER_TIME_OUT = 1000;
    private static final int DEFAULT_PORT = 6790;
    
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
        DatagramSocket clientSocket;
        DatagramPacket sendPacket;
        DatagramPacket receivePacket;
        int fails = 0;
        long timeCounter = 0l;
        long startTime;
        byte[] sendData;
        byte[] receiveData;
        try{
            System.out.println("*** Starting UDP Client ***");
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
                    clientSocket = new DatagramSocket();
                    clientSocket.setSoTimeout(SERVER_TIME_OUT);
                    InetAddress ipAddress = InetAddress.getByName(hostname);

                    //Write random string to the server
                    sentence = generateRandomString(i);
                    sendData = sentence.getBytes();
                    sendPacket = new DatagramPacket(sendData,sendData.length,ipAddress,port);

                    //Send data
                    System.out.println("[Client OutputData]: " + sentence);
                    clientSocket.send(sendPacket);
                    
                    //Read response from server
                    receiveData = new byte[1024];
                    receivePacket = new DatagramPacket(receiveData,receiveData.length);
                    clientSocket.receive(receivePacket);
                    modifySentence = new String(receivePacket.getData());
                    System.out.println("[Client InputData " + i + "]: " + modifySentence.trim());

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
     * GENERATE RANDOM STRINGS (a-z) OF RANDOM LENGTH (3-53)
     * @return 
     */
    private static String generateRandomString(int messageNumber){
        Random rand = new Random();
        StringBuilder str = new StringBuilder();
        char randChar;
        str.append("[");
        str.append(messageNumber);
        str.append("]");
        for(int i=0;i<rand.nextInt(50)+3;i++){
            randChar = (char)(rand.nextInt(26)+97);
            str.append(randChar); //from a to z
        }
        return str.toString();
    }
}
