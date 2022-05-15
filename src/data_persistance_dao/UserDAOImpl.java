package data_persistance_dao;

import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

import models.UserPOJO;


public class UserDAOImpl implements UserDAO {
	
	
	/*
	 * Takes in client object to check if emailID is unique for
	 * the database
	 * @param client
	 * 
	 */
	@Override
	public boolean validateEmail(UserPOJO client)  { 
		//include custom SystemException refer to Teacher Notes
		
		Database db = new Database();
		
		try {
			
			Statement statement = db.connect().createStatement();
			 
			if (db.connect() != null) {
				System.out.println("Connected To DB from Validate EMail");
			}
			
			String query = "SELECT email FROM bank_clients WHERE email = '" + client.getEmailID() + "'";

			System.out.println("Validating email in DB.....");
			
			ResultSet result = statement.executeQuery(query);
			//System.out.println("result size --- " + result.getFetchSize());
			
			if (result.getFetchSize() == 0) {
				System.out.println("Yay email is unique");
				db.connect().close();
				return true;
			}
						
			
		} catch(SQLException e) {
			// throw error for custom exception
			System.out.println(e.getMessage());
			System.out.println("");
			e.printStackTrace();
			//return false
			
		} 
		
		return false;
	}
	
}
