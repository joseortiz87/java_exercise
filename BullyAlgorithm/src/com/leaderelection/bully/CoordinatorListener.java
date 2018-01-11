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
public class CoordinatorListener extends Thread{
    private final Node node;
    private boolean isListenerOn;

    public CoordinatorListener(Node node) {
        this.node = node;
        this.isListenerOn = true;
    }
    
    public void setListenerOn(boolean isListenerOn){
        this.isListenerOn = isListenerOn;
    }
    
    @Override
    public void run() {
        while(isListenerOn) {
            doWork();
        } 
        System.out.println(String.format("[%d] End of listener...",node.getUuid()));
    }
     
    public void doWork() {
        try {
            Timing.delay();
            if(!node.testCoordinator()){
                isListenerOn = false;
                node.startElection(); //IF COORDINATOR IS DOWN START A NEW ELECTION
                System.out.println(String.format("[%d] Coordinator is down call for election...",node.getUuid()));
            }else{
                System.out.println(String.format("[%d] Coordinator is up...",node.getUuid()));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
