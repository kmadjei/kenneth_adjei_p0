package data_persistance_dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.BankAccountPOJO;

public class BankAccountDAOImpl implements BankAccountDAO {

	Database db;
	BankAccountPOJO bankAccount;

	public BankAccountDAOImpl() {

		// Initialize DB object
		db = new Database();

		// Initialize BankAccount Object
		bankAccount = new BankAccountPOJO();
	}

	// creates bank accounts associated to a unique bank ID
	@Override
	public boolean createAccount(String bankAccountID) {
		
		bankAccount = new BankAccountPOJO(bankAccountID);
		
		  try {
			         
	        	String query = "INSERT INTO bank_accounts" +
	        	        "  (bank_id, balance) VALUES " +
	        	        " (?, ?);";
	        	
	        	System.out.println(bankAccountID + " ---> " + bankAccount.getBalance());
	        	PreparedStatement preparedStatement = db.connect().prepareStatement(query);
	            preparedStatement.setString(1, bankAccountID);
	            preparedStatement.setDouble(2, bankAccount.getBalance());
	        	
	            // execute SQL query
	        	int size = preparedStatement.executeUpdate();
	           
	           if (size == 0) {
	        	   db.connect().close();
	        	   System.out.println("Fail to update Bank Account DB with Bank INFO :(");
	        	   System.out.println();
	        	   return false;
	           } else {
	        	   db.connect().close();
	               System.out.println("Your bank account has been created in the DB :)");
	        	   System.out.println();
	        	   return true;
	           }

	        } catch (SQLException e) {
	        	// throw error for custom exception
				System.out.println(e.getMessage());
				System.out.println("");
				e.printStackTrace();
	        }
		
		return false;
	}

	@Override
	public void deposit(BankAccountPOJO bankAccount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void withdraw(BankAccountPOJO bankAccount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAccountBalance(BankAccountPOJO bankAccount) {
		// TODO Auto-generated method stub

	}

}
