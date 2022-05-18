package dao;

import java.sql.Statement;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.UserPOJO;


public class UserDAOImpl implements UserDAO {

	Database db = Database.getInstance();
	
	// Validates email being used for registration
	@Override
	public boolean validateEmail(UserPOJO client) { 
		//include custom SystemException 
		
		

		System.out.println("validate email.....");
		try {
			
			Connection conn = db.connect();
			Statement statement = conn.createStatement();
			
			// logger db connection

			String query = "SELECT email FROM bank_clients WHERE email = '" + client.getEmailID() + "'";
			
			//String query = "SELECT * FROM bank_clients";
			
			ResultSet result = statement.executeQuery(query);
			
			if(result.next()) { 
				System.out.println("Email already exist in database!");
				System.out.println("Email - " + result.getString("email"));
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

	
	//Uses client object to add new client to DB when called
	@Override
	public boolean register(UserPOJO client) {

        try {
        	
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
        	   //db.connect().close();
        	   return false;
        	   
           } else {
        	   //db.connect().close();
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
	
	
	public UserPOJO login(UserPOJO client) {

		/* SAMPLE login
		UserPOJO validateUser = new UserPOJO("Kenneth", "Adjei", "ka@email.com", "secret", 
				"6ba7b8c1-fd6a-4ac7-a870-da648abecb61");
		
		if (client.getEmailID().equals(validateUser.getEmailID()) &&
				client.getPassword().equals(validateUser.getPassword())) {
			return validateUser;
		}
		*/

		   try {
	        	
		        
	        	String query = "SELECT * FROM bank_clients" + " WHERE email =?;";

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

	                System.out.println("Your Login credential passed...");
                	return validatedUser;

	            } else {
	            	System.out.println("No results found.");
	            	return null;
	            }


	        } catch (SQLException e) {
	        	// throw error for custom exception
				System.out.println(e.getMessage());
				System.out.println("");
				e.printStackTrace();
	        }
		
		return null;
	}
}
