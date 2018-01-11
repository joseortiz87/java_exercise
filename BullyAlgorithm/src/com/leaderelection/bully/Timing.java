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
public class Timing {

    static final double maxDelay = 800;

    public static void delay() {
	try {
            long delay = (long) (Math.random() * maxDelay);
                System.out.println("Delay: " + delay);
                Thread.sleep(delay);
        } catch (InterruptedException e) {
                System.out.println(e.getMessage());
	}
    }
}
