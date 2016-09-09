package mx.com.joortizs.les16;

import java.util.Date;

public class TimeDepositAccount extends Account {
    
    private double balance;
    private final Date maturityDate;
    
    public TimeDepositAccount(double balance, Date maturityDate) {
    	super(balance);
        this.maturityDate = maturityDate;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void deposit(double amount) {
        balance += amount;
    }
    
    @Override
    public String toString() {
        return getDescription() + ": current balance is " + balance;
    }

    @Override
    public boolean withdraw(double amount) {
        Date today = new Date();
        if(today.after(maturityDate)) {
            if(amount <= balance) {
                balance -= amount;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String getDescription() {
        return "Time Deposit Account " + maturityDate;
    }
    
}