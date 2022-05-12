package service;

import java.util.Scanner;

public class BankApplicationSystem {
	
	
	Scanner scan = new Scanner(System.in);
	
	public static final void introduction() {
		
		System.out.println("==========================================");
		System.out.println("WELCOME TO BANK AWESOME!");
		System.out.println("==========================================");
		System.out.println();
		System.out.println("Please enter an option you would like me to help you with today?");
		System.out.println("1 --> Register A New Account");
		System.out.println("2 --> Log into Your Bank Account");
		
	}
	
	
	public int getClientChoice(int x) {
		// choice loop
		return x;
	}
	
	
	
	public void executeChoice() {}
	
	public static final void exitSystem() {
		System.out.println("****************************************");
		System.out.println("You have been signed out successfully");
		System.out.println("Thank you for using Bank Awesome!!");
		System.out.println("****************************************");
		System.exit(0);
	}
	
	/*
	 * 
	 * 		
		
		
		int clientInput = input.nextInt();
		
		switch(clientInput) {
		
			case 1:
				// Register New Client 
				
				System.out.println();
				System.out.println("Please Enter Your First Name: ");
				System.out.println("Please Enter Your Last Name: ");
				System.out.println("Please Enter Your Email: ");
				
				System.out.println();
				System.out.println("Please select an account you want to create today: ");
				System.out.println("1 --> Chequing Account");
				System.out.println("2 --> Savings Account");
				
				System.out.println();
				System.out.println("Congratulations!!");
				System.out.println("You have successfuly created an account at Bank Awesome");
				System.out.println("Do You want to ");
				
				
				System.out.println(" ");
				
				break;
			case 2:
				// Client Log In
				//
				break;
			default:
				// code
		}
		
	 * 
	 * 
	 */
}
