package mx.com.joortizs.les16;

public class Bank {

    private Customer[] customers;
    private int numberOfCustomers;
    private static final Bank bank;
    
    static{
    	bank = new Bank();
    }
    
    public static Bank getInstance(){
    	return bank;
    }

    private Bank() {
        customers = new Customer[10];
        numberOfCustomers = 0;
    }

    public void addCustomer(String f, String l) {
        int i = numberOfCustomers++;
        customers[i] = new Customer(f, l);
    }

    public int getNumOfCustomers() {
        return numberOfCustomers;
    }

    public Customer getCustomer(int customer_index) {
        return customers[customer_index];
    }
}