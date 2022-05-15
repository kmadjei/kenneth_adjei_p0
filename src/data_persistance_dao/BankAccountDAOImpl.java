package data_persistance_dao;

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
		
		//Initialize BankAccount Object
		bankAccount = new BankAccountPOJO();
	}

	@Override
	public boolean createAccount(String bankAccountID) {
		
		bankAccount = new BankAccountPOJO(bankAccountID);
		
		  try {
	        	
		        
	        	String query = "INSERT INTO bank_Accounts" +
	        	        "  (bank_id, balance) VALUES " +
	        	        " (" + bankAccountID + ',' + bankAccount.getBalance() + ");";
	        	
	        	Statement statement = db.connect().createStatement();
	        	ResultSet result = statement.executeQuery(query);
	            
	           
	           if (result.getFetchSize() == 0) {
	        	   System.out.println("Fail to update Bank Account DB with Bank INFO :(");
	        	   System.out.println();
	        	   return false;
	           } else {
	        	   db.connect().close();
	        	   System.out.println("Completed Your Registeration in the DB :)");
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
