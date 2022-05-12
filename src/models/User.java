package models;

import java.util.UUID;

public class User {

	private String firstName;
	private String lastName;
	private String emailID;
	private String password;
	

	public String createUniqueID() {
	    String uniqueID = UUID.randomUUID().toString();
	    return uniqueID;
	}
	
}
