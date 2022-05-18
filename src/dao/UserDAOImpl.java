package dao;

import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.UserPOJO;


public class UserDAOImpl implements UserDAO {
	
	private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);
	Database db = Database.getInstance();
	
	// Validates email being used for registration
	@Override
	public boolean validateEmail(UserPOJO client) { 
		
		logger.info("Starting validateEmail(UserPOJO client) method in dao.");
		

		System.out.println("validate email.....");
		try {
			
			logger.info("Connecting to DB.");
			Connection conn = db.connect();
			Statement statement = conn.createStatement();
			
			// logger db connection

			String query = "SELECT email FROM bank_clients WHERE email = '" + client.getEmailID() + "'";
			
			//String query = "SELECT * FROM bank_clients";
			
			ResultSet result = statement.executeQuery(query);
			
			if(result.next()) { 
				System.out.println("Email already exist in database!");
				System.out.println("Email - " + result.getString("email"));
				logger.info("Closing DB connection.");
				logger.info("Ending validateEmail(UserPOJO client) method in dao.");
				conn.close();
				return true;
			}

			
		} catch(SQLException e) {
			System.out.println("Sorry, but a system error occured. Please proceed later.");
			logger.warn(e.getMessage());
		} 
		
		logger.info("Ending validateEmail(UserPOJO client) method in dao.");
		return false;
	}

	
	//Uses client object to add new client to DB when called
	@Override
	public boolean register(UserPOJO client) {
		
		logger.info("Starting register(UserPOJO client) method in dao.");
		
        try {
        	
        	logger.info("Connecting to DB");
        	Connection conn = db.connect();
        
        	String query = "INSERT INTO bank_clients" +
        	        "  (bank_id, first_name, last_name, email, password) VALUES " +
        	        " (?, ?, ?, ?, ?);";
        	System.out.println("prepping query...");
        	
        	
        	PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, client.getBankAccountID());
            preparedStatement.setString(2, client.getFirstName());
            preparedStatement.setString(3, client.getLastName());
            preparedStatement.setString(4, client.getEmailID());
            preparedStatement.setString(5, client.getPassword());
            
            
            System.out.println("Registering bankaccount details....");
            BankAccountDAO bankAccount = new BankAccountDAOImpl();
            
            int size = preparedStatement.executeUpdate();
            boolean createAccount = false;
            
            if (size != 0) {
            	createAccount =  bankAccount.createAccount(client.getBankAccountID());
            }
 
           
           if (createAccount == false) {
        	   System.out.println("Fail to update User DB with user INFO :(");
        	   System.out.println();
        	   db.connect().close();
        	   logger.info("Closing DB conncection");
        	   logger.info("Ending register(UserPOJO client) method in dao.");
        	   return false;
        	   
           } else {
        	   logger.info("Closing DB conncection");
        	   db.connect().close();
        	   System.out.println("Completed Your Registeration in the DB :)");
        	   System.out.println();
        	   logger.info("Endingregister(UserPOJO client) method in dao.");
        	   return true;
           }

        } catch (SQLException e) {
        	
        	logger.warn(e.getMessage());
			System.out.println("Sorry, but a system error occured.");
			System.out.println("");
			logger.warn("Closing DB conncectiong");
        }
        logger.info("Ending register(UserPOJO client) method in dao.");
        return false;

	}
	
	
	public UserPOJO login(UserPOJO client) {

		logger.info("Starting login(UserPOJO client) method in dao.");
		
		   try {
	        	
		        
	        	String query = "SELECT * FROM bank_clients" + " WHERE email =?;";
	        	logger.info("Connecting to DB.");
	        	PreparedStatement preparedStatement = db.connect().prepareStatement(query);
	            preparedStatement.setString(1, client.getEmailID());
	            
	            
	            ResultSet result = preparedStatement.executeQuery();
	            UserPOJO validatedUser = new UserPOJO();
	            
	        	System.out.println("Building User POJO...");
                while (result.next()) {

                    validatedUser.setBankAccountID(result.getString("bank_id")); 
                    validatedUser.setFirstName(result.getString("first_name"));
                    validatedUser.setLastName(result.getString("last_name"));
                    validatedUser.setEmailID(result.getString("email"));
                    validatedUser.setPassword(result.getString("password"));
                   
                }
                
                System.out.println("Validated Email " + validatedUser.getEmailID());
	            
	             // Process result from SQL query for login validation     
	            if (validatedUser.getEmailID() != null && client.getPassword().equals(validatedUser.getPassword())) {
	            	logger.info("Closing DB connection.");
	            	db.connect().close();
	                System.out.println("Your Login credential passed...");
	                logger.info("Ending login(UserPOJO client) method in dao.");
                	return validatedUser;

	            } else {
	            	logger.info("Closing DB connection.");
	            	db.connect().close();
	            	System.out.println("No results found.");
	            	logger.info("Ending login(UserPOJO client) method in dao.");
	            	return null;
	            }


	        } catch (SQLException e) {
	        	logger.warn(e.getMessage());
				System.out.println("Sorry a system error occured.");
				System.out.println();
	        }
		  logger.info("Ending login(UserPOJO client) method in dao.");
		return null;
	}
}
