/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpservice;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JO031U
 */
public class UDPService {

     private static final int SERVER_RESPONSE_TIME = 250;
     private static final int SERVER_TIME_OUT = 1000;
     private static final int DEFAULT_PORT = 6790;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner;
        DatagramSocket serverSocket = null;
        DatagramPacket receivePacket;
        DatagramPacket sendPacket;
        InetAddress ipAddress;
        int port = 0;
        byte[] sendData;
        byte[] receiveData = new byte[1024];
        System.out.println("*** Starting UDP Server ***");
        scanner = new Scanner(System.in);
        System.out.println("Enter server port (default- " + DEFAULT_PORT + "): ");
        try {
            port = scanner.nextInt();
            port = port > 0 ? port : DEFAULT_PORT;
            serverSocket = new DatagramSocket(port);
            serverSocket.setSoTimeout(SERVER_TIME_OUT);
        } catch (IOException ex) {
            Logger.getLogger(UDPService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Listening on port " + port + "...");
        while(true){
            try {
                //Receive data from server
                receiveData = new byte[1024];
                receivePacket = new DatagramPacket(receiveData,receiveData.length);
                serverSocket.receive(receivePacket);
                
                //Output reverse + uppercase string to client
                String inStr = new String(receivePacket.getData());
                inStr = inStr.trim();
                String outStr = reverse2UpperCase(inStr);
                outStr = outStr.trim();
                System.out.println("[Server InputData]: " + inStr + " - " + outStr);
                
                //RANDOM ERROR 6%
                Random rand = new Random();
                if(rand.nextBoolean() && rand.nextBoolean() && rand.nextBoolean() && rand.nextBoolean() )
                    throw new Exception("Simulationg Server error...");
                        
                //Sleep
                Thread.sleep(SERVER_RESPONSE_TIME);
                
                //Get address from client
                ipAddress = receivePacket.getAddress();
                port = receivePacket.getPort();
                sendData = outStr.getBytes();
                
                //Response to the client
                System.out.println("[Server OutputData]: " + outStr);
                sendPacket = new DatagramPacket(sendData,sendData.length,ipAddress,port);
                serverSocket.send(sendPacket);
                
            } catch (IOException ex) {
                //Logger.getLogger(UDPService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                //Logger.getLogger(UDPService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                //Logger.getLogger(UDPService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    /**
     * REVERSE AND UPPERCASE STRING
     * @param str
     * @return 
     */
    private static String reverse2UpperCase(String str){
        if(str == null || str.isEmpty()){
            return "";
        }
        str = str.toUpperCase();
        StringBuilder builder = new StringBuilder(str);
        return builder.reverse().toString().trim();
    }
}
