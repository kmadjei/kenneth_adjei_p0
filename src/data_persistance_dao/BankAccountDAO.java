package data_persistance_dao;

import models.BankAccountPOJO;

public interface BankAccountDAO {

	void createAccount(int bankAccountID);
	void deposit(BankAccountPOJO bankAccount);
	void withdraw(BankAccountPOJO bankAccount);
	void getAccountBalance(BankAccountPOJO bankAccount);
	
	
}
