package mx.com.joortizs.theads;

import java.util.Random;

public class Producer extends Thread{
	private IntBuffer buffer;
	
	public Producer(IntBuffer buffer){
		this.buffer = buffer;
	}
	public void run(){
		Random r = new Random();
		while(true){
			int num = r.nextInt();
			buffer.add(num);
			System.out.println("Produced-" + num);
		}
	}
}
