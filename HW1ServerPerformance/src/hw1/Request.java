/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1;

import java.net.Socket;
/**
 *
 * @author gholness
 */
public class Request {
    private String data;
    private Socket connection;
    
    public Request(Socket connection) {
        this.connection= connection;
    }
    
    
    public Socket getConnection() {
        return connection;
    }
}
