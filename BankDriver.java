// File: BankDriver.java
// Due Date: Monday 6/20/16

/********************************************************************************************************
 * ES&L Bank Account Manager Program - CSC 225 Prog1
 * Course Title: Advanced Java Programming
 * Course Number: CSC 225-805
 * Instructors: Professors Christine Forde and Harry Payne
 * @author Anna (Ekeren?)
 * @author Rafael Ferrer
 * @author Abhishek Mhatre
 * @version 0, 06/08/16
 * 
 * Description: ES&L Bank Account Manager Program - CSC 225 Prog1
 * 
 * 
 * Input: 
 * 
 * 
 * Compute:
 * 
 * 
 * @author Anna (Ekeren?)
 * @author Rafael Ferrer
 * @author Abhishek Mhatre
 * @version 0, 06/08/16
 ********************************************************************************************************/


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class BankDriver
{
	/// Main Method of BankDriver ///
	
	/**
	 * Description
	 * @param
	 * @throws FileNotFoundException 
	 *   
	 * @precondition
	 *   
	 * @postcondition / return
	 *   
	 * @exception
	 *   
	 * @note
	 *   
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{
		//Instance Variables
		File databaseFile; //The customer database file selected by the user. 
		Customer[] customerDatabase;
		Customer currentCustomer;// Customer cursor
		Object [] customerInfo; //customerInfo[0] contains a Customer's index in customerDatabase, customerInfo[1] contains the Customer
		double amount = 0;//The amount of money being deposited or withdrawn
		int choice = -1; //The user's menu selection number
		
		//Startup Message
		JOptionPane.showMessageDialog(null, 
				//These spaces are properly aligned, do not change
				"                                                   ~~~ ES&L Bank ~~~\n" 
				+ "                                Customer Account Managament System\n\n"
				+ "Please select a customer account database to manage after clicking 'OK'.", 
				"ES&L Bank - Customer Account Management System", JOptionPane.PLAIN_MESSAGE);
		
		//Have the user select the customer databaseFile to be used
		databaseFile = FileLoader();
		
		//Run the program with the selected databaseFile, or close the program if no file was chosen
		if (databaseFile != null){
			
			//Create the customerDatabase from the databaseFile and display it
			customerDatabase = createCustomerDatabase(databaseFile);
			JOptionPane.showMessageDialog(null, 
					"State of all customer bank accounts in the database: \n\n" + Customer.databaseToString(customerDatabase) + "\n\n"
					+ "Click 'OK' to continue to the main menu.", 
					"ES&L Bank - Customer Account Management System", JOptionPane.PLAIN_MESSAGE);
			
			//Display the Main Menu for the user to manage the database
			while (choice != 6){
				
				//Obtain user's Main Menu selection
				choice = mainMenuInput();
				
				//Main Menu Switch Board
				switch(choice){
					case 1: //1. Deposit money to an account.
						customerInfo = customerSearch(customerDatabase);
						amount = getDoubleAmount();
						if (customerInfo != null){
							if (((Customer) customerInfo[1]).deposit(amount) == true){
								JOptionPane.showMessageDialog(null, 
										"The deposit has been successful. \n\n" 
										+ "The new balance for this account is below: \n\n" + ((Customer) customerInfo[1]).toString(), 
										"ES&L Bank - Customer Account Management System", JOptionPane.PLAIN_MESSAGE);
							}
						}//end if
						break;
					case 2: //2. Withdraw money from an account.
						customerInfo = customerSearch(customerDatabase);
						amount = getDoubleAmount();
						if (customerInfo != null){
							if (((Customer) customerInfo[1]).withdraw(amount) == true){
								JOptionPane.showMessageDialog(null, 
										"The withdrawl has been successful. \n\n" 
										+ "The new balance for this account is below: \n\n" + ((Customer) customerInfo[1]).toString(), 
										"ES&L Bank - Customer Account Management System", JOptionPane.PLAIN_MESSAGE);
							}
						}//end if
						break;
					case 3: //3. Create a new customer account.
						currentCustomer = createCustomer(customerDatabase);
						if (Customer.addNewCustomer(currentCustomer, customerDatabase) == true){
							JOptionPane.showMessageDialog(null, 
									"The following customer bank account has been created successfully: \n\n" + currentCustomer.toString(), 
									"ES&L Bank - Customer Account Management System", JOptionPane.PLAIN_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, 
									"The customer database is full! \n"
									+ "The following customer bank account was NOT created: \n\n" + currentCustomer.toString() + "\n\n"
									+ "Please delete customer bank accounts that are no longer in use to make space available for new accounts.",
									"ES&L Bank - Error!", JOptionPane.ERROR_MESSAGE);
						}
						break;
					case 4: //4. View all customer accounts.
						JOptionPane.showMessageDialog(null, 
								"All customer bank accounts in the database: \n\n" + Customer.databaseToString(customerDatabase), 
								"ES&L Bank - Customer Account Management System", JOptionPane.PLAIN_MESSAGE);
						break;
					case 5: //5. Delete a customer account.
						customerInfo = customerSearch(customerDatabase);
						if (customerInfo != null){
							if (Customer.deleteCustomer(customerDatabase, ((int) customerInfo[0])) == true){
								JOptionPane.showMessageDialog(null, 
										"The following customer bank account has been deleted from the system: \n\n" + ((Customer) customerInfo[1]).toString(), 
										"ES&L Bank - Customer Account Management System", JOptionPane.PLAIN_MESSAGE);
							}
						}//end if
						break;
					case 6: //6. Quit and display all customer accounts.
						FileSaver(databaseFile, customerDatabase);
						JOptionPane.showMessageDialog(null, 
								"State of all customer bank accounts in the database after saving: \n\n" + Customer.databaseToString(customerDatabase), 
								"ES&L Bank - Customer Account Management System", JOptionPane.PLAIN_MESSAGE);
						break;
					case -1: //Not a user option. Quit without saving.
						choice = 6;
						break;
				}//end switch
			}//end while
		}//end if
		
		//Exit Message
		JOptionPane.showMessageDialog(null, 
				//These spaces are properly aligned, do not change
				"                              ~~~ ES&L Bank ~~~\n" 
				+ "           Customer Account Managament System\n\n"
				+ "    Customer Account Management System Closed.", 
				"ES&L Bank - Customer Account Management System", JOptionPane.PLAIN_MESSAGE);
		
	}//End Main Method
	
	
	/// Additional Methods Used by Main Method ///
	
	/**
	 * This method allows the user to select the text file containing the Customer accounts to be managed. 
	 * @precondition
	 *   The file selected by the user must contain a single (one word) name and a single integer key on each line of the text file. 
	 *   The text file selected must contain 241 or fewer elements. 
	 *   The name and key for each element must be be separated by one of the following delimiters: " "   ","   ";"   ":"   "_"   "\t" 
	 * @return
	 *   Returns the user selected text file containing the names and keys to be used.
	 **/
	private static File FileLoader()
	{
		//Instance Variables
		File databaseFile; //This is the text file where all the Customers are stored
		JFileChooser fileSelector; //A file selector UI
		
		//Launch a JFileChooser window to select the file to be used
		fileSelector = new JFileChooser();
		int status = fileSelector.showOpenDialog(null);
		
		//Once a file has been selected, return that file
		if (status == JFileChooser.APPROVE_OPTION){
			databaseFile = fileSelector.getSelectedFile();
			JOptionPane.showMessageDialog(null, 
					"You have selected the file located at: \n" + databaseFile.toString(), 
					"ES&L Bank - Customer Account Management System", JOptionPane.PLAIN_MESSAGE);
			return databaseFile;
		}
		//If no file is selected, give the user a second chance to select a file or close the program
		else if (status == JFileChooser.CANCEL_OPTION){
			JOptionPane.showMessageDialog(null, 
					"Error! You must select a file to continue..."
					+ "You may click 'Cancel' again to close the program.", 
					"ES&L Bank - Error!", JOptionPane.ERROR_MESSAGE);
			//Launch the JFileChooser window to select the file to be used
			status = fileSelector.showOpenDialog(null);
			//Once a file has been selected, return that file
			if (status == JFileChooser.APPROVE_OPTION){
				databaseFile = fileSelector.getSelectedFile();
				JOptionPane.showMessageDialog(null, 
						"You have selected the file located at: \n" + databaseFile.toString(), 
						"ES&L Bank - Customer Account Management System", JOptionPane.PLAIN_MESSAGE);
				return databaseFile;
			}
			//Close the program if no file is selected
			else {
				//The program will halt after returning null
				return null;
			}
		}//end else if
		
		return null;
		
	}//End FileLoader() Method
	
	/**
	 * This method overwrites the golfer "database" text file selected in the FileLoader() method with any changes made while running this program, then saves the file.
	 * @param database
	 *   The golfer "database" text file selected in the FileLoader() method.
	 * @param golferDatabase
	 *   The golfer TreeBag created by the createGolferDatabase() method.
	 * @postcondition / return
	 *   The golfer "database" text file selected in the FileLoader() method has been overwritten with any changes made while running this program and saved.
	 * @throws FileNotFoundException
	 * @Note
	 *   This method uses the writeToDatabase() method to actually write over the previous golfer "database" text file. Then this method saves that file.
	 **/
	private static void FileSaver(File databaseFile, Customer[] customerDatabase) throws FileNotFoundException
	{	
		//Instance Variables
		PrintWriter fileWriter; //Writes a line of text to the text file database
		Customer currentCustomer;// Customer cursor
		
		//Write over the database text file with all changes made to golferDatabase and then save the file
		fileWriter = new PrintWriter(databaseFile);
		for (int i = 0; i < customerDatabase.length; i++){
			if (customerDatabase[i] instanceof Customer){
				currentCustomer = customerDatabase[i];
				fileWriter.println(currentCustomer.getName() + " " + currentCustomer.getCustNumber() + " " + currentCustomer.getAcctBalance() + " " + currentCustomer.getPhoneNumber());
			}
		}//end for
		fileWriter.close();

	}//End FileSaver(File databaseFile, Customer[] customerDatabase) Method
	
	/**
	 * This method reads the golfer "database" text file selected by the FileLoader() method and generates a golfer TreeBag from it to be used in this program.
	 * @param database
	 *   the golfer "database" text file selected by the FileLoader() method
	 * @precondition
	 *   The database text file selected must contain a single golfer's information on each line in the following order: 
	 *   1. Golfer's Last Name (first letter must be upper case, all other letters must be lower case, no numbers or symbols)
	 *   2. Number of Rounds (integer number between 0 and 999)
	 *   3. Handicap (integer number between 0 and 20)
	 *   4. Average Score (decimal number between 0 and 999)
	 *   For each golfer, each piece of information may be separated with any of the following delimiters: " ,;:_\t"
	 *   An empty text file may also be loaded to begin a new empty golfer "database".
	 *   A text file in any format other than the exact formats specified above will NOT work with the GolferScoresTree class.
	 * @throws FileNotFoundException
	 * @note
	 *   To ensure a pure alphabetical ordering of golfers, rather than a lexicographical ordering based on each character's Unicode value, 
	 *   each golfer's last name should be created in the following format: 
	 *   The first letter should be upper case, while all other letters should be lower case, with no numbers or symbols.
	 **/
	private static Customer[] createCustomerDatabase(File database) throws FileNotFoundException
	{
		//Instance Variables
		final int DATABASE_SIZE = 30;
		Customer[] customerDatabase = new Customer[DATABASE_SIZE];
		String readCustomer; //A cursor for each line of text in the database text file
		Customer newCustomer; //A cursor used to add customers and their account information to the customerDatabase
		String nameLast;
		String custNum;
		String phoneNum;
		double acctBal;
		int line = 1;
		
		//Scanner Instantiation
		Scanner fileScanner = new Scanner(database); //Used to read the database text file
		fileScanner.useDelimiter(System.getProperty("line.separator")); //Allows us to read the database text file one line at a time
		
		//String Tokenizer Instantiation
		StringTokenizer tokenizer; 
		String delimeters = " ,;:_\t"; //For each golfer (new line), each piece of information may be separated with any of these delimiters
		
		//Read the database text file one line at a time, construct each golfer, add each golfer's stats, then add the golfer to golferDatabase
		while (fileScanner.hasNextLine()){
			try {
				readCustomer = fileScanner.nextLine();
				tokenizer = new StringTokenizer(readCustomer, delimeters);
				if (tokenizer.hasMoreTokens()){
					nameLast = tokenizer.nextToken();
					custNum = tokenizer.nextToken();
					acctBal = Double.parseDouble(tokenizer.nextToken());
					phoneNum = tokenizer.nextToken();
					newCustomer = new Customer(nameLast, custNum, acctBal, phoneNum);
					if (Customer.addNewCustomer(newCustomer, customerDatabase) == false){
						fileScanner.close();
						JOptionPane.showMessageDialog(null, 
								"WARNING: The customer database size limit has been reached! \n"
								+ "Only the first 30 accounts listed in this customer account database will be imported. \n"
								+ "If you save this database, all customer accounts that were not imported will be permanently deleted from the database.",
								"ES&L Bank - Customer Account Management System", JOptionPane.WARNING_MESSAGE);
						return customerDatabase;
					}
				}//end if
			}//end try
			catch (Exception multipleExceptions){
				JOptionPane.showMessageDialog(null, 
						"Error! Error in database file input! \n\n"
						+ "Line " + line + " of the database has been ignored and the customer on this line will not be added.", 
						"ES&L Bank - Error!", JOptionPane.ERROR_MESSAGE);
			}
			line++;
		}//end while
		
		fileScanner.close();
		
		return customerDatabase;
		
	}//End createCustomerDatabase(File database) Method
	
	/**
	 * Description
	 * @param
	 *   
	 * @precondition
	 *   
	 * @postcondition / return
	 *   
	 * @exception
	 *   
	 * @note
	 *   
	 **/
	private static Customer createCustomer(Customer[] customerDatabase)
	{
		//Instance Variables
		Customer newCustomer;
		String nameLast;
		String custNum;
		String phoneNum;
		double acctBal = 0;
		boolean uniqueID;
		boolean legalDouble;
		
		//Obtain the last name that will be used for this account
		nameLast = JOptionPane.showInputDialog(null, 
				"Add a new customer bank account: Customer Name \n\n"
				+ "Please enter the last name that will be used for this account: \n", 
				"ES&L Bank - Customer Account Management System", JOptionPane.QUESTION_MESSAGE);
		
		//Obtain the customer number that will be used for this account
		do {
			uniqueID = true;
			custNum = JOptionPane.showInputDialog(null, 
					"Add a new customer bank account: Customer ID \n\n"
					+ "Please enter the customer ID that will be used for this account: \n", 
					"ES&L Bank - Customer Account Management System", JOptionPane.QUESTION_MESSAGE);
			//Verify that the custNum is unique before continuing
			for (int i = 0; i < customerDatabase.length; i++){
				if (customerDatabase[i] instanceof Customer){
					if (custNum.equals(customerDatabase[i].getCustNumber())){
						uniqueID = false;
						JOptionPane.showMessageDialog(null, 
								"Error! This customer ID is already in use. \n"
								+ "Please enter a new customer ID after clicking 'OK'.", 
								"ES&L Bank - Error!", JOptionPane.ERROR_MESSAGE);
					}
				}//end if
			}//end for
		} while (uniqueID == false); //end do-while
		
		//Obtain the phone number that will be used for this account
		phoneNum = JOptionPane.showInputDialog(null, 
				"Add a new customer bank account: Customer Phone Number \n\n"
				+ "Please enter the phone number that will be used for this account: \n", 
				"ES&L Bank - Customer Account Management System", JOptionPane.QUESTION_MESSAGE);
		
		//Obtain the account balance of this account
		do {
			legalDouble = true;
			try {
				acctBal = Double.parseDouble(JOptionPane.showInputDialog(null, 
						"Add a new customer bank account: Account Balance \n\n"
						+ "Please enter the current account balance for this account: \n", 
						"ES&L Bank - Customer Account Management System", JOptionPane.QUESTION_MESSAGE));
				if (acctBal < 0){
					legalDouble = false;
					JOptionPane.showMessageDialog(null, 
							"Error! You must enter a decimal amount greater than 0.00. \n"
							+ "Negative amounts, letters, and characters are not allowed.", 
							"ES&L Bank - Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
			catch (Exception NumberFormatException){
				legalDouble = false;
				JOptionPane.showMessageDialog(null, 
						"Error! You must enter a decimal amount greater than 0.00. \n"
						+ "Negative amounts, letters, and characters are not allowed.", 
						"ES&L Bank - Error!", JOptionPane.ERROR_MESSAGE);
			}
		} while (legalDouble == false); //end do-while
		
		//Create the new customer account
		newCustomer = new Customer(nameLast, custNum, acctBal, phoneNum);
		
		return newCustomer;
		
	}//End createCustomer() Method
	
	/**
	 * Description
	 * @param
	 *   
	 * @precondition
	 *   
	 * @postcondition / return
	 *   
	 * @exception
	 *   
	 * @note
	 *   
	 **/
	private static Object[] customerSearch(Customer[] customerDatabase)
	{
		//Instance Variables
		Object[] custInfo = new Object[2];
		Customer findCust;
		String nameLast;
		String custNum;
		int index;
		
		//Obtain the last name for account being searched
		nameLast = JOptionPane.showInputDialog(null, 
				"Search for a customer bank account: Customer Name \n\n"
				+ "Please enter the last name on the account you are searching for: \n", 
				"ES&L Bank - Customer Account Management System", JOptionPane.QUESTION_MESSAGE);
		
		//Obtain the customer number for the account being searched
		custNum = JOptionPane.showInputDialog(null, 
				"Search for a customer bank account: Customer ID \n\n"
				+ "Please enter the customer ID on the account you are searching for: \n", 
				"ES&L Bank - Customer Account Management System", JOptionPane.QUESTION_MESSAGE);

		//Find the customer in the customerDatabase and return the customer and their index if found
		findCust = new Customer(nameLast, custNum, 0, "");
		index = Customer.findIndex(customerDatabase, findCust);
		if (index == -1){
			JOptionPane.showMessageDialog(null, 
					"This customer could not be found in the database. \n\n" 
					+ "Customer Name: " + findCust.getName() 
					+ "\nCustomer ID:" + findCust.getCustNumber(), 
					"ES&L Bank - Customer Account Management System", JOptionPane.PLAIN_MESSAGE);
			return null;
		}
		else {
			findCust = customerDatabase[index];
		}
		custInfo[0] = index;
		custInfo[1] = findCust;
		
		return custInfo;
		
	}//End customerSearch(Customer[] customerDatabase) Method
	
	/**
	 * A private GUI input method that displays the Main Menu for the ES&L Bank Account Manager program and returns a user input. 
	 * The menu contains the following options:
	 * <p>
	 *    1. Deposit money to a customer account.
	 * <p>
	 *    2. Withdraw money from a customer account.
	 * <p>
	 *    3. Create a new customer account.
	 * <p>
	 *    4. View all customer accounts.
	 * <p>
	 *    5. Delete a customer account.
	 * <p>
	 *    6. Quit and display all customer accounts.
	 * <p>
	 * The default option of 6 is selected if the user chooses "Cancel" or closes the window.
	 * @precondition
	 *   none
	 * @return
	 *   Returns an integer value between 1-6. If the user chooses "Cancel" or closes the window, then the default value of 6 is returned.
	 * @exception HeadlessException
	 *   Uncaught exception that occurs if called in an environment that does not support a keyboard.
	 * @exception NumberFormatException
	 *   Caught exception that occurs if the user inputs anything other than an integer value.
	 **/
	private static int mainMenuInput()
	{
		//Instance Variables
		String menuChoice; //The Main Menu GUI
		String exception; //The exception message thrown by NumberFormatException
		int input = 6; //The user's menu selection number, default of 6 is exit
		int dialogButton;
		
		//Display the Main Menu
		menuChoice = JOptionPane.showInputDialog(null, 
				"Customer Bank Accounts Main Menu: \n\n"
				+ "    1. Deposit money to a customer account.\n"
				+ "    2. Withdraw money from a customer account.\n"
				+ "    3. Create a new customer account.\n"
				+ "    4. View all customer accounts.\n"
				+ "    5. Delete a customer account.\n"
				+ "    6. Quit and save changes.\n\n"
				+ "Please enter your selection: \n", 
				"ES&L Bank - Customer Account Management System", JOptionPane.QUESTION_MESSAGE);
		
		//Obtain the user's Main Menu selection and catch illegal arguments
		try {
			input = Integer.parseInt(menuChoice);
			if (input < 1 || input > 7){
				JOptionPane.showMessageDialog(null, 
						"Error! You must enter a choice between 1 and 6.", 
						"ES&L Bank - Error!", JOptionPane.ERROR_MESSAGE);
				return mainMenuInput();
			}
		}//end try
		catch (Exception NumberFormatException){
			exception = NumberFormatException.toString();
			if (exception.equals("java.lang.NumberFormatException: null")){
				dialogButton = JOptionPane.showConfirmDialog(null, 
						"WARNING: If you close the system this way, none of the changes you made to the Customer Account Management System will be saved. \n"
						+ "Please return to the Main Menu and select option 6 to quit and save all changes made to the Customer Account Management System. \n\n"
						+ "Are you sure you want to quit without saving?",
						"ES&L Bank - Customer Account Management System", JOptionPane.YES_NO_OPTION);
				if (dialogButton == JOptionPane.YES_OPTION){
					return -1; //This will halt the Main Menu options and push the exit message, closing the program
				}
				else {
					return mainMenuInput();
				}
			}//end if
			else { 
				JOptionPane.showMessageDialog(null, 
					"Error! You must enter an integer. \n"
					+ "Decimal numbers, letters, and characters are not allowed.", 
					"ES&L Bank - Error!", JOptionPane.ERROR_MESSAGE);
				return mainMenuInput();
			}
		}//end catch
		
		return input;
		
	}//End mainMenuInput() Method
	
	/**
	 * Description
	 * @param
	 *   
	 * @precondition
	 *   
	 * @postcondition / return
	 *   
	 * @exception
	 *   
	 * @note
	 *   
	 **/
	private static double getDoubleAmount()
	{
		//Instance Variables
		double amount = 0;
		boolean legalDouble;

		//
		do {
			legalDouble = true;
			try {
				amount = Double.parseDouble(JOptionPane.showInputDialog(null, 
						"Please enter the amount of money: \n", 
						"ES&L Bank - Customer Account Management System", JOptionPane.QUESTION_MESSAGE));
				if (amount <= 0){
					legalDouble = false;
					JOptionPane.showMessageDialog(null, 
							"Error! You must enter a decimal amount greater than 0.00. \n"
									+ "Negative amounts, letters, and characters are not allowed.", 
									"ES&L Bank - Error!", JOptionPane.ERROR_MESSAGE);
				}
			}//end try
			catch (Exception NumberFormatException){
				legalDouble = false;
				JOptionPane.showMessageDialog(null, 
						"Error! You must enter a decimal amount greater than 0.00. \n"
								+ "Negative amounts, letters, and characters are not allowed.", 
								"ES&L Bank - Error!", JOptionPane.ERROR_MESSAGE);
			}
		} while (legalDouble == false); //end do-while

		return amount;

	}//End getDoubleAmount()Method
	
	
}//End BankDriver Class
