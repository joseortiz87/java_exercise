package mx.com.joortizs.theads;

public class IntBuffer {
	private int index;
	private  int[] buffer = new int[8];
	
	public synchronized void add(int num){
		while(index == buffer.length -1){
			try{
				wait();
			}catch(Exception e){
				
			}
		}
		System.out.println("Added-" + index);
		buffer[index++] = num;
		notifyAll();
	}
	
	public synchronized int remove(){
		while(index == 0){
			try{
				wait();
			}catch(Exception e){
				
			}
		}
		System.out.println("Removed-" + index);
		int ret = buffer[--index];
		notifyAll();
		return ret;
	}
	
}
