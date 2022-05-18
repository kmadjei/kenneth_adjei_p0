package presentation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.BankApplicationSystem;

public class MainBankInterface {
	
	private static final Logger logger = LogManager.getLogger(MainBankInterface.class);
	
	public static void main(String[] args) {
		logger.info("Banking Application System starting...");
		
		// Execute Banking Application system
		BankApplicationSystem.processClientChoice();

		logger.info("Banking Application System ending!");
	}

}
