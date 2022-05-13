package models;

public class UserPOJO {
	
	private String firstName;
	private String lastName;
	private String emailID;
	private String password;
	private String bankAccountID;
	
	
	// default constructor
	public UserPOJO() {}
	
	// constructor with parameters
	public UserPOJO( String firstName, String lastName, String emailID, String password, String bankAccountID) {
		//super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailID = emailID;
		this.password = password;
		this.bankAccountID = bankAccountID;
		
	}

	public String getBankAccountID() {
		return bankAccountID;
	}


	public void setBankAccountID(String bankAccountID) {
		this.bankAccountID = bankAccountID;
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

	@Override
	public String toString() {
		return "ClientPOJO [firstName=  " + firstName + ", lastName= " + lastName + ", emailID= " 
				+ emailID + "]";
	}
	
}
