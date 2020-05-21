package com.airbnb;

import java.lang.reflect.Array;

public class QueueCircular {
    public static void main(String args []){
        QueueImp<String> queue = new QueueImp(5,String.class);
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
        queue.enqueue("6");
        queue.printQueue(); // 1 2 3 4 5
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.printQueue(); // 4 5
        queue.enqueue("6");
        queue.enqueue("7");
        queue.enqueue("8");
        queue.enqueue("9");
        queue.printQueue(); // 4 5 6 7 8
    }

    static class QueueImp <T> {
        T[] queue;
        int front;
        int rear;
        public QueueImp(int size,Class<T> type){
            this.queue = (T[]) Array.newInstance(type, size);
            this.front = -1;
            this.rear = -1;
        }
        // null null null null
        public void enqueue(T element){
            if((this.front == 0 && this.rear == queue.length-1) ||
                    (this.rear == this.front-1)){
                return;
            }else if(this.front == -1){
                this.front = 0;
                this.rear = 0;
            }else if(this.rear == queue.length-1 && this.front != 0){
                this.rear = 0;
            }else{
                this.rear++;
            }
            this.queue[rear] = element;
        }
        public void dequeue(){
            if(this.front == -1){
               return;
            }
            this.queue[this.front] = null;
            if(this.front == this.rear){
                this.front = -1;
                this.rear = -1;
            }else if(this.front == queue.length-1){
                this.front = 0;
            }else{
                this.front++;
            }
        }
        public void printQueue(){
            if(this.front > -1){
                System.out.println("***Printing queue");
                int tmpFront = this.front;
                while(tmpFront != this.rear){
                    System.out.println(this.queue[tmpFront]);
                    if(tmpFront == this.queue.length-1){
                        tmpFront = 0;
                    }else{
                        tmpFront++;
                    }
                }
                System.out.println(this.queue[this.rear]);
            }else{
                System.out.println("Empty queue");
            }
        }
    }
}
