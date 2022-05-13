package service;

import java.util.Scanner;
import java.util.UUID;

import models.UserPOJO;

public class BankApplicationSystem {
	
	
	// Greet client
	static final void introduction() {
		
		System.out.println("==========================================");
		System.out.println("WELCOME TO BANK AWESOME!");
		System.out.println("==========================================");
		System.out.println();
		System.out.println("Please enter an option you would like me to help you with today?");
		System.out.println("1 --> Register A New Account!");
		System.out.println("2 --> Log into Your Bank Account!");
		
	}
	
	static Scanner scanner = new Scanner(System.in);
	
	// Presents client with menu options
	static void processClientChoice() {
		
		while(true) {
			
			introduction();
			
			System.out.println();
			System.out.println("Input Choice here: ");
			
			try {
				int clientChoice = Integer.parseInt(scanner.nextLine());
				switch(clientChoice) {
				
				case 1:
					// Register Client
					registerClient();
					
					break;
				case 2:
					// CLient Login
					loginClient();
					System.out.println("2 --> Log into Your Bank Account!");
					break;
					
				default:
					System.out.println("\nPlease select the corresponsding integer (1 or 2).");
					break;
			}
				
			} catch(Exception e) {
				System.out.println("\nPlease select the corresponsding integer (1 or 2).");
			}
			
			
		}
		
	}
	
	
	// Process client registration
	static void registerClient() {
		
		UserPOJO client =  new UserPOJO();
		
		try(Scanner scan = new Scanner(System.in)) {
			
			System.out.println("****************************************");
			System.out.println("REGISTERATION");
			System.out.println("****************************************");
			
			System.out.println();
			System.out.println("Please Enter Your First Name: ");
			String firstName = scan.nextLine();
			firstName = firstName.trim();
			client.setFirstName(firstName);
			
			
			System.out.println("Please Enter Your Last Name: ");
			String lastName = scan.nextLine();
			lastName = lastName.trim();
			client.setLastName(lastName);
			
			System.out.println("Please Enter Your Email: ");
			String email = scan.nextLine();
			email = email.trim();
			// check with DB if email exist
			client.setEmailID(email);
			
			System.out.println("Please Enter Your Password: ");
			String pwd = scan.nextLine();
			pwd = pwd.trim();
			client.setPassword(pwd);
			
			System.out.println("Please Confirm Your Password: ");
			String confirmpwd = scan.nextLine();
			confirmpwd = pwd.trim();

			
			while(true) {
				
				System.out.println("The password does not match!");
				System.out.println("Please try again!!");
				System.out.println();
				
				System.out.println("Please Enter Your Password: ");
				pwd = scan.nextLine();
				client.setPassword(pwd);
				
				System.out.println("Please Confirm Your Password: ");
				confirmpwd = scan.nextLine();


				if (pwd.equals(confirmpwd)) {
					break;
				}

			}	
			
		} catch (Exception e){
			System.out.println("\nPlease select rg block.");
		}
		
		//generate client ID
		// Send data OBJECT to DB as OBJECT Connect to DAO methods CRUD 
		// gene

		System.out.println(client.getFirstName() + " You Have Registered Successfully");
		System.out.println(client.toString());
		//client.toString();
		
	}
	
	// process client login
	static void loginClient() {
		
		System.out.println("****************************************");
		System.out.println("ACCOUNT LOGIN!");
		System.out.println("****************************************");
		
		
		while (true) {
			
			try {
				System.out.println(" Enter user name => ");
	            String userName = scanner.nextLine();

	            System.out.println(" Enter password => ");
	            String password = scanner.nextLine();
	            
	            
	            // Login validation
	            if ("ramesh".equals(userName) && "password".equals(password)) {
	                System.out.println(" User successfully logged-in.. ");
	                
	                //Connect credentials to open account menu
	                break;
	            } else {
	                System.out.println(" In valid userName password or password. Try again!");
	            }
			} catch (Exception e) {
				System.out.println(" Something went wrong. Please Try again!");
			}
		}
		
	}
	
	
	// Terminate program or sign-out
	static final void exitSystem() {
		System.out.println("****************************************");
		System.out.println("You have been signed out successfully");
		System.out.println("Thank you for using Bank Awesome!!");
		System.out.println("****************************************");
		System.exit(0);
	}
	
	/*
	 * Generate Unique Bank ID when for new users
	 */
	public String createUniqueID() {
	    String uniqueID = UUID.randomUUID().toString();
	    return uniqueID;
	}
	
	
	// 
	static 

}
