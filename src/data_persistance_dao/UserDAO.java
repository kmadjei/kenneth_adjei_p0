package data_persistance_dao;

import models.UserPOJO;

public interface UserDAO {
	
	// Validate unique emailID
	boolean validateEmail(UserPOJO client);
	// Bank - Client registration
	//UserPOJO register (UserPOJO client);
	 
}
