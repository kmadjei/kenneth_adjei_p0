package data_persistance_dao;

import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.UserPOJO;


public class UserDAOImpl implements UserDAO {
	
	Database db;
	
	public UserDAOImpl () {
		
		// Initialize DB object
		db = new Database();
	}
	
	// Validates email being used for registration
	@Override
	public boolean validateEmail(UserPOJO client)  { 
		//include custom SystemException refer to Teacher Notes
		
		
		try {
			
			Statement statement = db.connect().createStatement();
			
			String query = "SELECT email FROM bank_clients WHERE email = '" + client.getEmailID() + "'";
			
			ResultSet result = statement.executeQuery(query);
			
			if (result.getFetchSize() == 0) {
				db.connect().close();
				return true;
			}
						
			
		} catch(SQLException e) {
			// throw error for custom exception
			System.out.println(e.getMessage());
			System.out.println();
			e.printStackTrace();
			//return false
			
		} 
		
		return false;
	}

	
	/*
	 * Uses client object to add new client to DB when called
	 */
	@Override
	public boolean register(UserPOJO client) {

        try {
        	
        
        	String query = "INSERT INTO bank_clients" +
        	        "  (bank_id, first_name, last_name, email, password) VALUES " +
        	        " (?, ?, ?, ?, ?);";
        	System.out.println("prepping query...");
        	
        	
        	PreparedStatement preparedStatement = db.connect().prepareStatement(query);
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
	
}
