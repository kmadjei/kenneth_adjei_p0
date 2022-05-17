package service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;

import dao.BankAccountDAOImpl;
import dao.UserDAOImpl;
import models.BankAccountPOJO;
import models.UserPOJO;

public class BankApplicationSystem {
	
	static Scanner scanner = new Scanner(System.in);
	static UserPOJO client = new UserPOJO();
	static UserDAOImpl userDAO = new UserDAOImpl();
	static BankAccountDAOImpl bankAccountDAO = new BankAccountDAOImpl();
	
	
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
			
			// validate password
			while(true) {
				System.out.println("Please Enter Your Password: ");
				String password = scanner.nextLine();
				password = password.trim();
				client.setPassword(password);
				
				System.out.println("Please Confirm Your Password: ");
				String confirmpwd = scanner.nextLine();
				confirmpwd = confirmpwd.trim();
				
				System.out.println("pwd --> " + password);
				System.out.println("confim --> " + confirmpwd);
				
				if (password.equals(confirmpwd)) {
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
		

		System.out.println("Please wait a moment!");
		System.out.println("Your Account is being created....");
		System.out.println();
		
		//generate client Bank ID
		client.setBankAccountID(createBankID());
		
		// Send data OBJECT to DB as OBJECT Connect to DAO methods CRUD 
		boolean register = userDAO.register(client);

		if(register) {
			System.out.println(client.getFirstName() + " You Have Registered Successfully");
			System.out.println(client.toString());
			System.out.println();
		} else {
			System.out.println("You Have Failed To Register Successfully!");
			System.out.println("Please Try Again!!");
			System.out.println();
		}

		
	}
	
	// process client login
	public static void loginClient() {
		
		System.out.println("****************************************");
		System.out.println("ACCOUNT LOGIN!");
		System.out.println("****************************************");
		
		
		while (true) {
			
			try {
				System.out.println("Enter Email ID => ");
	            String email = scanner.nextLine();
	            email = email.trim();
	            client.setEmailID(email);
	            
	            System.out.println("Enter password => ");
	            String password = scanner.nextLine();
	            password = password.trim();
	            client.setPassword(password);
	            
	            System.out.println("Processing account login...");
	            UserPOJO userLogin = userDAO.login(client);
	            // Login validation
	            if (userLogin != null) {
	                System.out.println("You have been successfully logged-in!");

	                //Connect credentials to open account menu
	                openAccountMenu(userLogin);
	                
	               break;
	            } else {
	                System.out.println("Invalid userName password or password. Try again!");
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
	

	// Generate Unique Bank ID when for new users
	public static String createBankID() {
	    return UUID.randomUUID().toString();
	}
	
	
	// Generates an account menu when user logs in successfully
	public static void openAccountMenu(UserPOJO user) {
		
		while(true) {
			
			System.out.println();
			System.out.println("****************************************");
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
					getAllBalance(user.getBankAccountID());
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
		
		
	} //END OF ACCOUNT MENU

	// Display all client bank accounts
	public static void getAllBalance(String bankAccountID) {
		
		System.out.println();
		System.out.println("****************************************");
		System.out.println("Account Balance");
		System.out.println("****************************************");
		System.out.println();
		
		
		//grab all account info from db
		bankAccountDAO = new BankAccountDAOImpl(bankAccountID);
		HashMap<Integer, BankAccountPOJO> bankAccounts = bankAccountDAO.getAccountBalance();
		
		double totalBalance = 0;
		// Loops through list of bank account objects
		for(Integer accountNum: bankAccounts.keySet()) {
			
			System.out.println("Account " + accountNum +   
					" ---> " + bankAccounts.get(accountNum).getBalance());
			System.out.println();
			totalBalance += bankAccounts.get(accountNum).getBalance();
			
		}
		
		System.out.println("Your Total Account Balance is " + totalBalance);
		System.out.println();
		
		System.out.println("==========================================");
		System.out.println("To Go Back Enter 1 to Sign out Enter 2");
		
		while(true) {
			try {
				int option = Integer.parseInt(scanner.nextLine());
				
				switch(option) {
				case 1:
					break; // Exit out of switch statement
				case 2:
					// Exit system 
					exitSystem();
					break;
				default:
					System.out.println("\nPlease carefully select the right option.");
					break;
				}
				
				break; //Back to client Dashboard
				
			} catch (Exception e) {
				System.out.println("\nPlease carefully select the right option.");
				
			}
			
		}
		
	}
	
	// Gives client access to deposit to an account
	public static void depositToAccount(String bankAccountID) {
		
		System.out.println();
		System.out.println("****************************************");
		System.out.println("Choose An Account For Your Deposit");
		System.out.println("****************************************");
		System.out.println();
		
		//grab all account info from db with total
		bankAccountDAO = new BankAccountDAOImpl(bankAccountID);
		HashMap<Integer, BankAccountPOJO> bankAccounts = bankAccountDAO.getAccountBalance();
		

		// Loops through list of bank account objects
		for(Integer accountNum: bankAccounts.keySet()) {
			
			System.out.println("Account # " + accountNum +   
					" ---> " + bankAccounts.get(accountNum).getBalance());
			System.out.println();
			
		}

		// Checks and validate user input
		while(true) {
			try {
				int accountNumber = Integer.parseInt(scanner.nextLine());
				
				// validate input
				if (bankAccounts.containsKey(accountNumber)) {
					
					System.out.println("How much do you want to deposit into Account #" + accountNumber + " ?");
					double depositAmount = 0;
					
					while(true) {
						try {
							depositAmount = Double.parseDouble(scanner.nextLine());
							
							
							
							break;
						} catch(Exception e) {
							System.out.println("Please enter the correct ammount!");
						}						
					}
					
					
					BankAccountPOJO account = bankAccounts.get(accountNumber);
					double initialAmount = account.getBalance();
					
					account.setBalance(initialAmount + depositAmount);
	
					
					/* CODE Block to deposit into DB
					BankAccountPOJO account = bankAccounts.get(accountNumber);
					bankAccountDAO.deposit(account, depositAmount);
					*/
					
					
					System.out.println("Your have successfully deposited " + depositAmount + " into your account");
					
					System.out.println("==========================================");
					System.out.println("To Go Back Enter 1 to Sign out Enter 2");
					
					while(true) {
						try {
							int option = Integer.parseInt(scanner.nextLine());
							
							switch(option) {
							case 1:		
								break; // Back To Client Dashboard
							case 2:
								// Exit system 
								exitSystem();
								break;
							default:
								System.out.println("\nPlease carefully select the right option.");
								break;
							}
							
							
						} catch (Exception e) {
							System.out.println("\nPlease carefully select the right option.");
							
						}
						
					} // END OF WHILE LOOP
					

				} else {
					System.out.println("No such account number exist please try again.");				
				}
				
			} catch (Exception e) { 
				System.out.println("No such account number exist please try again.");
			}
		} // END OF WHILE LOOP
		

	}
	
	// Gives client access to withdraw from an account
	public static void withdrawFromAccount(String bankAccountID) {}


}
