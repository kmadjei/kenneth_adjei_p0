package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import models.BankAccountPOJO;

public class BankAccountDAOImpl implements BankAccountDAO {

	Database db = Database.getInstance();
	BankAccountPOJO bankAccount = new BankAccountPOJO();
	
	HashMap<Integer, BankAccountPOJO> bankAccounts;
	
	//default constructor
	public BankAccountDAOImpl() {}
	
	// initializes bank account details
	public BankAccountDAOImpl(String bankAccountID) {
		
		bankAccounts = new HashMap<Integer, BankAccountPOJO>();

		  try {			  
			  	
	        	String query = "SELECT * FROM bank_accounts WHERE bank_id = ?";
	        
	        	PreparedStatement preparedStatement = db.connect().prepareStatement(query);
	            preparedStatement.setString(1, bankAccountID);

	            ResultSet result = preparedStatement.executeQuery();
	            //System.out.println(result.next());
	            
	            System.out.println(bankAccountID + " ---> " + bankAccount.getBalance());
	            System.out.println("Getting account info from DB...");
	            System.out.println();
	            // getting bank accounts from DB
	            while(result.next()) {
	            	BankAccountPOJO bankAcct = new BankAccountPOJO();  	
	            	
	            	bankAcct.setAccountNumber(result.getInt("account_id"));
	            	bankAcct.setBankAccountID(result.getString("bank_id"));
	            	bankAcct.setBalance(result.getDouble("balance"));
     	
	            	bankAccounts.put(bankAcct.getAccountNumber(), bankAcct); 	
	            	
	            }
	            

	            if(bankAccounts.size() > 0) {
	            	System.out.println("Bank account(s) retrieved successfully...");
	            	System.out.println();
	            }
	        	

	        } catch (SQLException e) {
	        	// throw error for custom exception
				System.out.println(e.getMessage());
				System.out.println("");
				e.printStackTrace();
	        }

	}


	// creates bank accounts associated to a unique bank ID for the DB
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

	// updates client's bank account
	@Override
	public void deposit(BankAccountPOJO bankAccount, double depositAmount) {
		

	}

	@Override
	public void withdraw(BankAccountPOJO bankAccount) {
		// TODO Auto-generated method stub

	}

	// Gets list of bank accounts
	@Override
	public HashMap<Integer, BankAccountPOJO> getAccountBalance() {
		return bankAccounts;
		
	}

}
