package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import models.BankAccountPOJO;

public class BankAccountDAOImpl implements BankAccountDAO {

	private static final Logger logger = LogManager.getLogger(BankAccountDAOImpl.class);
	Database db = Database.getInstance();
	BankAccountPOJO bankAccount = new BankAccountPOJO();
	
	HashMap<Integer, BankAccountPOJO> bankAccounts;
	
	//default constructor
	public BankAccountDAOImpl() {}
	
	// initializes bank account details
	public BankAccountDAOImpl(String bankAccountID) {
		logger.info("Starting BankAccountDAOImpl(String bankAccountID) method in dao.");
		bankAccounts = new HashMap<Integer, BankAccountPOJO>();

		  try {			  
			  	
	        	String query = "SELECT * FROM bank_accounts WHERE bank_id = ?";
	        	logger.info("Starting DB connection.");
	        	PreparedStatement preparedStatement = db.connect().prepareStatement(query);
	            preparedStatement.setString(1, bankAccountID);

	            ResultSet result = preparedStatement.executeQuery();
	   
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
	            	logger.info("closing DB connection.");
	            	db.connect().close();
	            }
	        	

	        } catch (SQLException e) {

				System.out.println("Sorry for the system error. Please proceed or try later.");
				logger.warn(e.getMessage());
				System.out.println();
	        }
		  logger.info("Starting BankAccountDAOImpl(String bankAccountID) method in dao.");
	}


	// creates bank accounts associated to a unique bank ID for the DB
	@Override
	public boolean createAccount(String bankAccountID) {
		
		logger.info("Starting createAccount(String bankAccountID) method in dao.");
		bankAccount = new BankAccountPOJO(bankAccountID);
		
		  try {
			         
	        	String query = "INSERT INTO bank_accounts" +
	        	        "  (bank_id, balance) VALUES " +
	        	        " (?, ?);";
	        	
	        	System.out.println(bankAccountID + " ---> " + bankAccount.getBalance());
	        	logger.info("Starting DB connection.");  
	        	PreparedStatement preparedStatement = db.connect().prepareStatement(query);
	            preparedStatement.setString(1, bankAccountID);
	            preparedStatement.setDouble(2, bankAccount.getBalance());
	        	
	            // execute SQL query
	        	int size = preparedStatement.executeUpdate();
	           
	           if (size == 0) {
	        	   logger.info("Closing DB connection.");  
	        	   db.connect().close();
	        	   System.out.println("Fail to update Bank Account DB with Bank INFO :(");
	        	   System.out.println();
	        	   logger.info("Ending createAccount(String bankAccountID) method in dao."); 
	        	   return false;
	           } else {
	        	   logger.info("Closing DB connection.");  
	        	   db.connect().close();
	               System.out.println("Your bank account has been created in the DB :)");
	        	   System.out.println();
	        	   logger.info("Ending createAccount(String bankAccountID) method in dao.");  
	        	   return true;
	           }

	        } catch (SQLException e) {
				System.out.println("Sorry for the system error. Please proceed or try later.");
				logger.warn(e.getMessage());
				System.out.println();
	        }
		  logger.info("Ending createAccount(String bankAccountID) method in dao.");  
		return false;
	}

	// updates client's bank account after deposit
	@Override
	public void deposit(BankAccountPOJO bankAccount) {
		 logger.info("Starting deposit(BankAccountPOJO bankAccount) method in dao."); 
		  try {
		         
	        	String query = "UPDATE bank_accounts SET balance=? WHERE account_id=?";
	        	
	        	
	        	System.out.println("Processing your deposit....");
	        	logger.info("Starting DB connection."); 
	        	PreparedStatement preparedStatement = db.connect().prepareStatement(query);
	        	preparedStatement.setDouble(1, bankAccount.getBalance());
	        	preparedStatement.setInt(2, bankAccount.getAccountNumber());
	        	
	            // execute SQL query
	        	int size = preparedStatement.executeUpdate();
	        	
	        	logger.info("Closing DB connection."); 
	        	db.connect().close();
	        	
	           if (size == 0) {
	        	   System.out.println("Fail to update Bank Account DB with Bank INFO :(");
	        	   System.out.println();
	           } else {
	        	   System.out.println();
	           }

	        } catch (SQLException e) {
				System.out.println("Sorry for the system error. Please proceed or try later.");
				logger.warn(e.getMessage());
				System.out.println();
	        }
		  
		  logger.info("Ending deposit(BankAccountPOJO bankAccount) method in dao."); 
	}

	//Updates account balance upon withdrawals
	@Override
	public void withdraw(BankAccountPOJO bankAccount) {
		
		logger.info("Starting withdraw(BankAccountPOJO bankAccount) method in dao."); 
		
		  try {
		         
	        	String query = "UPDATE bank_accounts SET balance=? WHERE account_id=?";
	        	
	        	
	        	System.out.println("Processing your withdrawal....");
	        	logger.info("Starting DB connection."); 
	        	PreparedStatement preparedStatement = db.connect().prepareStatement(query);
	        	preparedStatement.setDouble(1, bankAccount.getBalance());
	        	preparedStatement.setInt(2, bankAccount.getAccountNumber());
	        	
	            // execute SQL query
	        	int size = preparedStatement.executeUpdate();
	        	
	        	logger.info("Closing DB connection."); 
	        	db.connect().close();

	           
	           if (size == 0) {
	        	   System.out.println("Fail to update Bank Account DB with Bank INFO :(");
	        	   System.out.println();
	           } else {
	        	   System.out.println();
	           }

	        } catch (SQLException e) {
				System.out.println("Sorry for the system error. Please proceed or try later.");
				logger.warn(e.getMessage());
				System.out.println();
	        }
		  
		  logger.info("Ending withdraw(BankAccountPOJO bankAccount) method in dao.");

	}

	// Gets list of bank accounts
	@Override
	public HashMap<Integer, BankAccountPOJO> getAccountBalance() {
		logger.info("Starting getAccountBalance() method in dao.");
		logger.info("Ending getAccountBalance() method in dao.");
		return bankAccounts;
		
	}

}
