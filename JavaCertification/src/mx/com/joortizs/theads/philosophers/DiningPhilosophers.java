package mx.com.joortizs.theads.philosophers;

public class DiningPhilosophers {

	private Object[] forks;
	private  Philosopher[] philosophers;
	
	private DiningPhilosophers(int num){
		this.forks = new Object[num];
		philosophers = new Philosopher[num];
		for(int i=0;i<num;++i){
			forks[i] = new Object();
			int fork1 = i;
			int fork2 = (i+1)%num;
			if(i == 0){
				philosophers[i] = new Philosopher(i,fork2,fork1);
			}else{
				philosophers[i] = new Philosopher(i,fork1,fork2);	
			}
		}
	}
	
	public void startEating() throws InterruptedException{
		for(int i=0;i<philosophers.length;++i){
			philosophers[i].start();
		}
		philosophers[0].join();
	}
	
	private class Philosopher extends Thread{
		private int id;
		private int fork1;
		private int fork2;
		public Philosopher(int id,int fork1,int fork2){
			this.id = id;
			this.fork1 = fork1;
			this.fork2 = fork2;
		}
		public void run(){
			status("Ready to eat using forks " + fork1 + " and " + fork2);
			pause();
			while(true){
				status("Picking up fork " + fork1);
				synchronized (forks[fork1]) {
					status("Picking up fork " + fork2);
					synchronized (forks[fork2]) {
						status("Eating");
					}
				}
			}
		}
		
		private void pause(){
			try{
				sleep(200);
			}catch(Exception e){
				
			}
		}
		
		private void status(String msg){
			System.out.println("Philosopher " + id + " : " + msg);
		}
	}
	
	/**
	 * MAIN
	 * @param args
	 */
	public static void main(String args[]){
		try{
			DiningPhilosophers d = new DiningPhilosophers(5);
			d.startEating();
		}catch(Exception e){
			
		}
	}
}
