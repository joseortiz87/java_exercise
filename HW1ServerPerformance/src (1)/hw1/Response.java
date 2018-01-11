/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1;

/**
 *
 * @author gholness
 */
public class Response {
   private String payLoad;
   
   public Response(String data) {
     this.payLoad = new String(data);
   } 
   
   public String getPayLoad() {
       return payLoad;
   }
}
