package com.airbnb;

import java.lang.reflect.Array;

/**
 * Queue
 * implementation using array
 */
public class Queue {

    public static void main(String args []){
        QueueImp<String> queue = new QueueImp(5,String.class);
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
        queue.enqueue("6");
        queue.printQueue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.printQueue();
        queue.dequeue();
        queue.dequeue();
        queue.printQueue();
        queue.printQueue();
    }

    static class QueueImp <T>{
        T [] queue;
        int front;
        int rear;
        public QueueImp(int size,Class<T> type){
            this.queue = (T[]) Array.newInstance(type, size);
            this.front = 0;
            this.rear = 0;
        }
        public void enqueue(T element){
            if(this.rear == this.queue.length){
                return;
            }
            this.queue[rear] = element;
            rear++;
        }
        public void dequeue(){
            if(this.rear > this.front){
                T element = this.queue[this.front];
                // shift array O(N)
                for(int i=0;i<this.rear-1;i++){
                    this.queue[i] = this.queue[i+1];
                }
                if(this.rear < this.queue.length){
                    this.queue[this.rear] = null;
                }
                this.rear--;
            }
        }
        public void printQueue(){
            if(this.rear > this.front){
                System.out.println("***Printing queue");
                for(int i = this.front;i<this.rear;i++){
                    System.out.println(this.queue[i]);
                }
            }else{
                System.out.println("Empty queue");
            }
        }
    }
}
