package mx.com.joortizs.les17.pet;

public class Fish extends Animal implements Pet{

	protected String name;
	
	public Fish(String name) {
		super(0);
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	
	public Fish() {
		this("Fluffly");
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		System.out.println("Swim...");
	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("Pond scum...");
	}
	
	@Override
	public void walk(){
		super.walk();
		System.out.println("Fish dont walk...");
	}

}
