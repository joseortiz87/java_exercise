package mx.com.joortizs.theads;

public class ProducerConsumerThread {

	public static void main(String arg[]){
		IntBuffer buffer = new IntBuffer();
		Producer p = new Producer(buffer);
		Consumer c = new Consumer(buffer);
		p.start();
		c.start();
	}
}
