package BankDetails;

//import java.util.Stack;

public class BankDetails {
	String Name;
	String IFSC;
	private int AccountNumber;
	final static String BANK_NAME = "SBI";
	double Balance = 0.0;
	static int numberOfAccounts;
	public BankDetails(String Name, String IFSC) {
		this.Name = Name;
		this.IFSC = IFSC;
		numberOfAccounts++;
		AccountNumber=numberOfAccounts;
	}
//	public BankDetails(String Name, String IFSC, int Balance) {
//		this.Name = Name;
//		this.IFSC = IFSC;
//		this.Balance= Balance;
//		numberOfAccounts++;
//		AccountNumber=numberOfAccounts;
//	}
	public int accNumber() {
		return this.AccountNumber;
	}
	public double checkBalance() {
		return this.Balance;
	}
	public void submitAmount(double amount) {
		this.Balance = this.Balance+amount;
	}
	
}
