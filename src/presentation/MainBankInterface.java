package presentation;

import java.util.Scanner;

public class MainBankInterface {
	
	// Execute Menu Interface for Client
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("==========================================");
		System.out.println("WELCOME TO BANK AWESOME!");
		System.out.println("==========================================");
		System.out.println("");
		System.out.println("Please select an option you would like me to help you with today?");
		System.out.println("#1 --> Register A New Account");
		System.out.println("#2 --> Log into Your Bank Account");
		
		int clientInput = input.nextInt();
		
		System.out.println("You choose " + clientInput);
	}

}
