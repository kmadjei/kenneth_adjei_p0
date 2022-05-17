package dao;


import java.util.HashMap;

import models.BankAccountPOJO;

public interface BankAccountDAO {

	public boolean createAccount(String bankAccountID);
	public void deposit(BankAccountPOJO bankAccount, double depositAmount);
	public void withdraw(BankAccountPOJO bankAccount);
	public HashMap<Integer, BankAccountPOJO> getAccountBalance();
	
	
}
