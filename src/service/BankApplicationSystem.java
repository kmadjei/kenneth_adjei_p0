package service;

import java.util.Scanner;
import java.util.UUID;

import data_persistance_dao.UserDAOImpl;
import models.UserPOJO;

public class BankApplicationSystem {
	
	static Scanner scanner = new Scanner(System.in);
	static UserPOJO client = new UserPOJO();
	static UserDAOImpl userDAO = new UserDAOImpl();
	
	
	// Greet client
	static final void introduction() {
		
		System.out.println("==========================================");
		System.out.println("WELCOME TO BANK AWESOME!");
		System.out.println("==========================================");
		System.out.println();
		System.out.println("Please enter an option you would like me to help you with today?");
		System.out.println("1 --> Register A New Account!");
		System.out.println("2 --> Log into Your Bank Account!");
		System.out.println("3 --> End Session!");
		
	}
	
	
	
	// Presents client with menu options
	public static void processClientChoice() {
		
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
				case 3:
					// End Program
					exitSystem();
					System.out.println("2 --> Log into Your Bank Account!");
					break;	
					
				default:
					System.out.println("Please select the corresponsding integer (1 or 2).");
					System.out.println();
					break;
			}
				
			} catch(Exception e) {
				System.out.println("\nPlease select the corresponsding integer (1 or 2).");
			}
			
			
		}
		
	}
	
	
	// Process client registration
	public static void registerClient() {
		
		
		try {
			System.out.println();
			System.out.println("****************************************");
			System.out.println("REGISTERATION");
			System.out.println("****************************************");
			
			System.out.println();
			System.out.println("Please Enter Your First Name: ");
			String firstName = scanner.nextLine();
			firstName = firstName.trim();
			client.setFirstName(firstName);
			
			
			System.out.println("Please Enter Your Last Name: ");
			String lastName = scanner.nextLine();
			lastName = lastName.trim();
			client.setLastName(lastName);
			
			while(true) {
				System.out.println("Please Enter Your Email: ");
				String email = scanner.nextLine();
				email = email.trim();
				client.setEmailID(email);
				
			
				// check with DB if email is unique
				if (userDAO.validateEmail(client)) {
					break;
				} else {
					System.out.println("Please try a different email!");
					System.out.println();
				}
				
				
				
			}
			
			while(true) {
				System.out.println("Please Enter Your Password: ");
				String pwd = scanner.nextLine();
				pwd = pwd.trim();
				client.setPassword(pwd);
				
				System.out.println("Please Confirm Your Password: ");
				String confirmpwd = scanner.nextLine();
				confirmpwd = pwd.trim();
				
				if (pwd.equals(confirmpwd)) {
					break;
				} else {
					System.out.println("The password does not match!");
					System.out.println("Please try again!!");
					System.out.println();
				}
			}

			
		} catch (Exception e){
			// throw custom system error
			System.out.println("\nPlease select rg block.");
			
		}
		
		//generate client Bank ID
		client.setBankAccountID(createBankID());
		System.out.println("Please wait a moment!");
		System.out.println("Your Account is being created....");
		System.out.println();
		
		// Send data OBJECT to DB as OBJECT Connect to DAO methods CRUD 
		userDAO.register(client);

		System.out.println(client.getFirstName() + " You Have Registered Successfully");
		System.out.println(client.toString());
		System.out.println();
		
	}
	
	// process client login
	public static void loginClient() {
		
		System.out.println("****************************************");
		System.out.println("ACCOUNT LOGIN!");
		System.out.println("****************************************");
		
		
		while (true) {
			
			try {
				System.out.println(" Enter user name => ");
	            String email = scanner.nextLine();

	            System.out.println(" Enter password => ");
	            String password = scanner.nextLine();
	            
	            
	            // Login validation
	            if ("ramesh".equals(email) && "password".equals(password)) {
	                System.out.println(" User successfully logged-in.. ");
	                
	                //String firstName; String lastName, String emailID, String password, String bankAccountID;
	                
	                //UserPOJO client = new UserPOJO(firstName, lastName, emailID, password, bankAccountID);
	                
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
	public static final void exitSystem() {
		System.out.println("****************************************");
		System.out.println("Thank you for using Bank Awesome!");
		System.out.println("Have A Wonderful Day :)"); 
		System.out.println("****************************************");
		System.exit(0);
	}
	
	/*
	 * Generate Unique Bank ID when for new users
	 */
	public static String createBankID() {
	    return UUID.randomUUID().toString();
	}
	
	
	// Generates an account menu when user logs in successfully
	public static void openAccountMenu(UserPOJO user) {
		
		while(true) {
			
			System.out.println("\n****************************************");
			System.out.println("Welcome " + user.getFirstName() + " To Your Client Dashboard");
			System.out.println("****************************************");
			System.out.println();
			System.out.println("Please select one of the options below!!");
			System.out.println("1 --> Check All Balance");
			System.out.println("2 --> Withdraw From An Account");
			System.out.println("3 --> Deposit To An Account");
			System.out.println("4 --> Sign Out");
			
			try {
				int option = Integer.parseInt(scanner.nextLine());
				
				switch(option) {
				case 1:
					// Check All balance
					break;
				case 2:
					// Withdraw from accounts
					break;
				case 3:
					// Deposit to accounts
					break;
				case 4:
					// Exit system / Signout
					exitSystem();
					break;
				default:
					System.out.println("\nPlease carefully select the right option.");
					break;
				}
				
				
			} catch (Exception e) {
				System.out.println("\nPlease carefully select the right option.");
				
			}
			

		}

		
		
	}

}
