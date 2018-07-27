
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Johnathon Cameron This class implements the user Session and main
 *         menu of the bankSystem, with the bankSystem name.
 * 
 */
public class BankSystem {

	// instance array
	private Bank[] accounts;
	final String bankName;
	public Customer cus;
	final Scanner input = new Scanner(System.in);

	public BankSystem(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * Method mainMenu Displaying the main menu display of the banking system with
	 * various option for the client Create Account, display accounts, Option for
	 * account(deposit/withdraw), Quit, Bank System charges and interest for
	 * different account types
	 */

	public void mainMenu() {

		System.out.println("Welcome to " + bankName + " Banking System");
		System.out.println("=========================================");
		System.out.println(
				"Choose 'C' to create account\nChoose 'Q' to quite\nChoose 'O' Account Options\nChoose 'D' for Banking Account Type Details\nChoose 'P' Print Accounts\nChoose 'T' Transfer Funds");

		boolean q = true;

		do {

			char option = input.next().charAt(0);

			switch (option) {

			case 'C':
				try {
					userSession();
				} catch (Exception e1) {
					System.out.println(e1.toString());
					e1.printStackTrace();
				}
				break;

			case 'P':

				for (int i = 0; i < accounts.length; i++) {
					try {
						printAccountDetails(accounts, i);
					} catch (NullPointerException e) {
						System.out.println("<<Empty Accounts! Create account>>");
					}

				}
				break;

			case 'Q':
				q = false;
				break;

			case 'O':
				availableOptions();
				break;

			case 'D':
				System.out.println(
						"Checking Account offer:\n-Monthly Fee of 0.35 cents\n-Free Withdrawl\n-3.50$ Withdraw from Other Bank System");
				System.out.println(
						"\nSavings Account offers:\n-Interest Rate 5% yearly\n-Money Transfer(No penalty)\n-Signing bonus 10% increase for the first year");
				System.out.println(
						"\nLine of Credit offers:\n-5.4% Interest\n-Miss payments get taking out of savings\n-3 Chances for miss payments\n-Miss payments interest increase of 7%");
			case 'T': 
				transfer();
			
			default:
				System.out.println("Invalid Command");
			}
			if (option == 'Q') {
				System.out.println("System closing.....");
				System.out.println("GoodBye.. Thank you for using " + bankName + " Banking System");
				break;
			} else {
				System.out.println("=========================================");
				System.out.println(
						"Choose 'C' to create account\nChoose 'Q' to quite\nChoose 'O' Account Options\nChoose 'D' for Banking Account Type Details\nChoose 'P' Print Accounts\nChoose 'T' to Transfer Funds");
			}
		} while (q = true);

	}

	public void printAccountDetails(Bank[] accounts, int i) {
		// printing out all account details

		System.out.println("==============================================");
		System.out.printf(
				"Account Type:%c Account Number: %d Name: %s  Phone Number: %s  Email Address: %s Balance: %.2f\n",
				accounts[i].getAccountType(), accounts[i].getAccNumber(), accounts[i].getAccHolder().getName(),
				accounts[i].getAccHolder().getPhoneNum(), accounts[i].getAccHolder().getEmailAddress(),
				accounts[i].getBalance());

	}

	/**
	 * userSession Method Method used to create and account and prompt the
	 * user/client for personal information Email, Name, AccNumber, Phone Number
	 */
	public void userSession() throws Exception {

		// Array Size (amount of accounts user wants to create
		System.out.println("Enter the amount of Accounts you would like to create?:");
		accounts = new Bank[input.nextInt()];

		// initializing array of accounts (customer info)

		for (int i = 0; i < accounts.length; i++) {

			int j = i;
			System.out.println("Enter Account Holder Details " + (j + 1) + "\n============================");

			long accNumber;
			System.out.println("Enter Account Number:");
			accNumber = input.nextLong();

			System.out.println("Enter First Name: ");
			String fName = input.next();

			System.out.println("Enter Last Name: ");
			String lName = input.next();
			cus = new Customer(fName, lName);

			System.out.println("Enter Phone Number: ");
			cus.setPhoneNum(input.nextLong());

			System.out.println("Enter Email Address:");
			cus.setEmailAddress(input.next());

			System.out.println("Enter Starting Balance:");
			double balance = input.nextDouble();
			// double balance = newbank.getBalance();

			System.out.println("Which Type of Account would you like to Create\n"
					+ "Options:\n (S)Savings\n (C)Checkings\n (L)LineOfCredit\n");

			char accType = (input.next().charAt(0));
			switch (accType) {
			case 'C':
				System.out.println("You have created a Checking Account");
				accounts[i] = new CheckingAccount(accNumber, cus, balance, accType);

				break;
			case 'S':
				System.out.println("You have created a Savings Account");
				accounts[i] = new SavingsAccount(accNumber, cus, balance, accType);
				break;

			case 'L':
				System.out.println("You have opened a line of Credit");

				accounts[i] = new LineOfCredit(accNumber, cus, balance, accType);
				System.out.println();
				break;

			default:
				System.out.println("Account Type <<Not Valid>>");
				continue;

			}
		}
	}

	/**
	 * availableOption Method prompting the user to choose between various options
	 * for the user Deposit,Withdraw, Print Account desired, Exit to Main Menu
	 */

	public void availableOptions() {
		boolean options = true;

		while (options) {

			System.out.println("Commands:\n(D)Deposit\n(W)Withdraw\n(P)Print All Accounts\n\n(T) Transfer\n(E)Exit To Main Menu");
			System.out.println("Enter Command");

			char myChar = input.next().charAt(0);
			char cmd = Character.toUpperCase(myChar);
			switch (cmd) {
			case 'D':

				System.out.println("Enter Account you wish to Deposit");
				int index = input.nextInt();

				System.out.println("Enter Deposit Ammount");
				if (index < accounts.length) {
					accounts[index].deposit(input.nextDouble());
					break;

				} else {
					System.out.println("Invalid Index");

				}
			case 'W':
				System.out.println("Enter which account would you like to Withdraw From");
				index = input.nextInt();
				if (index < accounts.length) {
					System.out.println("Enter Withdraw Ammount");
					accounts[index].withdraw(input.nextDouble());
					break;
				} else {
					System.out.println("Invalid Account Number");
					continue;
				}
			case 'P':
				System.out.println("Enter the Account Number You would like to Display");
				index = input.nextInt();
				if (index < accounts.length) {
					printAccountDetails(accounts, index);
					break;
				} else {
					System.out.println("Invalid Account Number");
					continue;

				}
			case 'E':
				System.out.println("You have Now logged out of" + bankName + "  banking system");
				options = false;
				break;
			case 'T':
				transfer();

			default:
				System.out.println("Invalid Command\nPlease Try Again.");

			}

		}

	}

	public void transfer() {
		System.out.println("Enter Account you which to transfer money from");
		int index1 = input.nextInt();
		System.out.println("Enter Account you wish to transfer money to");
		int index2 = input.nextInt();
		System.out.println("Enter amount you wish to transfer");
		double amount = input.nextDouble();

		accounts[index1].withdraw(amount);
		accounts[index2].deposit(amount);

	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter Bank Name: ");
		String bkname = scanner.next();
		BankSystem bank = new BankSystem(bkname);

		bank.mainMenu();

		scanner.close();
	}
}
