/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpservice;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JO031U
 */
public class TCPService {
    
    private static final int SERVER_RESPONSE_TIME = 300;
    private static final int SERVER_TIME_OUT = 1000;
    private static final int DEFAULT_PORT = 6789;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner;
        ServerSocket serverSocket = null;
        Socket connectionSocket;
        BufferedReader inFromClient;
        DataOutputStream outToClient;
        int port = 0;
        System.out.println("*** Starting TCP Server ***");
        scanner = new Scanner(System.in);
        System.out.println("Enter server port (default- " + DEFAULT_PORT + "): ");
        try {
            port = scanner.nextInt();
            port = port > 0 ? port : DEFAULT_PORT;
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(SERVER_TIME_OUT);
        } catch (IOException ex) {
            Logger.getLogger(TCPService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Listening on port " + port + "...");
        while(true){
            try {
                //Listens for a connection to be made to this socket and accepts it
                connectionSocket = serverSocket.accept();
                connectionSocket.setKeepAlive(true);
                
                //Read string data from client
                inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                
                                //RANDOM ERROR 6%
                Random rand = new Random();
                if(rand.nextBoolean() && rand.nextBoolean() && rand.nextBoolean() && rand.nextBoolean() )
                    throw new Exception("Simulationg Server error...");
                
                //Output stream from client
                outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                
                //Output reverse + uppercase string to client
                String inStr = inFromClient.readLine();
                String outStr = reverse2UpperCase(inStr);
                System.out.println("[Request]: " + inStr + " - " + outStr);
                Thread.sleep(SERVER_RESPONSE_TIME);
                outToClient.writeBytes(outStr);
                outToClient.flush();
                outToClient.close();
                
            } catch (IOException ex) {
                //Logger.getLogger(TCPService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                //Logger.getLogger(TCPService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                //Logger.getLogger(TCPService.class.getName()).log(Level.SEVERE, null, ex);
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
        return builder.reverse().toString();
    }
}
