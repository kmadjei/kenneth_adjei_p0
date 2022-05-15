package models;

public class BankAccountPOJO {

	private int accountNumber;
	private String bankAccountID;
	private double balance;
	
	public BankAccountPOJO() {}
	
	public BankAccountPOJO(String bankAccountID) {
		this.bankAccountID = bankAccountID;
		this.balance = 0;
	}
	
	public String getBankAccountID() {
		return bankAccountID;
	}
	public void setBankAccountID(String bankAccountID) {
		this.bankAccountID = bankAccountID;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}
