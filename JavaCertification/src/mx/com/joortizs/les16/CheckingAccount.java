package mx.com.joortizs.les16;

public class CheckingAccount extends Account{

	private final double overDraftLimit;
	
	public CheckingAccount(double balance,double overDraftLimit) {
		super(balance);
		this.overDraftLimit = overDraftLimit;
	}
	
	public CheckingAccount(double balance){
		this(balance,0);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Checking Account";
	}

	@Override
	public boolean withdraw(double amount) {
		// TODO Auto-generated method stub
		if(amount <= balance + overDraftLimit) {         
			balance -= amount;         
			return true;     
		} else {         
			return false;
		}
	}

}
