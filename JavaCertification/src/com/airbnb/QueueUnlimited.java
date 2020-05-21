package com.airbnb;

import java.io.*;
import java.util.*;

/*
 *

 Build a queue class with the enqueue and dequeue methods. The queue can store an *UNLIMITED* number of elements but you are limited to using arrays that can store up to 5 elements max.

 row [ 5, 6, , , -1]
 [ , , , , ]
 [ , , , , ]
 [ , , , , ]
 ...

 */

public class QueueUnlimited {

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
        //strings.add("This pad is running Java " + Runtime.version().feature());

        for (String string : strings) {
            System.out.println(string);
        }

        Queue queue = new Queue();
        for(int i=1;i<12;i++){
            queue.enqueue(i);
        }
        //queue.printQueue();
        for(int i=1;i<13;i++){
            try{
                System.out.println("dequeueing - " + queue.dequeue());
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

    }
    static class Queue{
        List<int []> queue;
        int front;
        int rear;
        int row; // rear
        int rowFront;
        static final int MAX_CHUNCK_SIZE =5;
        public Queue(){
            this.front = -1;
            this.rear = -1;
            this.queue = new ArrayList<>();
            this.queue.add(new int[MAX_CHUNCK_SIZE]);
            this.row = 0;
            this.rowFront = 0;
        }

        public void enqueue(int element){
            if(this.rear == MAX_CHUNCK_SIZE-1){
                this.queue.add(new int[MAX_CHUNCK_SIZE]);
                this.rear = -1;
                this.row++;
            }
            this.rear++;
            this.queue.get(row)[rear] = element;
        }

        public int dequeue() throws Exception{
            // empty
            if(this.row == this.rowFront && this.front == this.rear){
                throw new Exception("Empty queue!");
            }
            //[5, 6, , , -1]
            //[6,7..]
            front++;
            int tmpElement = this.queue.get(this.rowFront)[front];
            if(front == MAX_CHUNCK_SIZE-1){
                this.queue.remove(this.rowFront);
                this.front = -1;
                this.row--;
            }
            return tmpElement;
        }

        public void printQueue(){
            if(this.row == this.rowFront && this.front == this.rear){
                System.out.println("Empty Queue");
                return;
            }
            for(int i=rowFront+1;i<queue.size();i++){
                int index = 0;
                if(i == rowFront){
                    index = this.front;
                }
                int limit = queue.get(i).length-1;
                if(this.row == i){
                    limit = this.rear;
                }
                for(int j=index;j <= limit;j++){
                    System.out.println(queue.get(i)[j]);
                }
            }
        }

    }
}
