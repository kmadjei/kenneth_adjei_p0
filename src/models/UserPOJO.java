package models;

import java.util.UUID;

public class UserPOJO {
	
	private String firstName;
	private String lastName;
	private String emailID;
	private String password;
	private String bankID;

	public String getBankID() {
		return bankID;
	}


	public void setBankID(String bankID) {
		this.bankID = bankID;
	}

	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmailID() {
		return emailID;
	}


	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String createUniqueID() {
	    String uniqueID = UUID.randomUUID().toString();
	    return uniqueID;
	}
	
	@Override
	public String toString() {
		return "ClientPOJO [firstName=  " + firstName + ", lastName= " + lastName + ", emailID=" 
				+ emailID + "]";
	}
	
}
