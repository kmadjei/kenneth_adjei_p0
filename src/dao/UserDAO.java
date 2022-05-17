package dao;

import models.UserPOJO;

public interface UserDAO {
	
	// Validate unique emailID
	boolean validateEmail(UserPOJO client);
	
	// Bank - Client registration
	boolean register (UserPOJO client);
	
	// Handle client login validations
	UserPOJO login(UserPOJO client);
	 
}
