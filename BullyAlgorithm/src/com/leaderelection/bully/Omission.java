/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leaderelection.bully;

/**
 *
 * @author JO031U
 */
public class Omission {
	public static final double threashold = 0.25;
        
	public static boolean omit() {
		boolean result = Math.random() < threashold;
		if(result) {
                    System.out.println("Omission");
		}
		return result;
	}
}
