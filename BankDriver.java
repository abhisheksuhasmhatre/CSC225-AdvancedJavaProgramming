// File: BankDriver.java
// Due Date: Monday 6/20/16

/********************************************************************************************************
 * ES&L Bank Account Manager Program - CSC 225 Prog1
 * Course Title: Advanced Java Programming
 * Course Number: CSC 225-805
 * Instructors: Professors Christine Forde and Harry Payne
 * 
 * Description: ES&L Bank Account Manager Program - CSC 225 Prog1
 * 
 * 
 * 
 * 
 * @author Anna Ekeren
 * @author Rafael Ferrer
 * @author Abhishek Mhatre
 * @version 1.0, 06/18/16
 ********************************************************************************************************/




/// Imported Packages ///
import java.io.*; //For File, FileNotFoundException, and PrintWriter Classes
import java.util.*; //For Scanner and StringTokenizer Classes
import javax.swing.*; //For JOptionPane and JFileChooser Classes


public class BankDriver
{
	/// Main Method of BankDriver and runBankTest Method ///
	
	public static void main(String[] args) throws FileNotFoundException {
		
		BankDriver bankTest = new BankDriver();
		bankTest.runBankTest();
	    System.exit(0);

	}//End Main Method
	
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
	public void runBankTest() throws FileNotFoundException {
		
		//Instance Variables
		File databaseFile; //The customer database file selected by the user. 
		Customer[] customerDatabase;
		Customer currentCustomer;// Customer cursor
		Object [] customerInfo; //customerInfo[0] contains a Customer's index in customerDatabase, customerInfo[1] contains the Customer
		double amount = 0;//The amount of money being deposited or withdrawn
		int count = 0; //The number of customers in customerDatabase
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
			
			//
			if (customerDatabase != null){
			
				//
				for (int i = 0; i < customerDatabase.length; i++){
					if (customerDatabase[i] instanceof Customer){
						count++;
					}
				}
				
				//
				JOptionPane.showMessageDialog(null, 
						"State of all customer bank accounts in the database: \n\n" + Customer.databaseToString(customerDatabase) + "\n\n"
						+ "Click 'OK' to continue to the main menu.", 
						"ES&L Bank - Customer Account Management System", JOptionPane.PLAIN_MESSAGE);
				
				//Run the Main Menu GUI
				while (choice != 9){
					
					//Obtain user's Main Menu selection
					choice = mainMenuInput();
					
					//Main Menu Switch Board
					switch(choice){
					
					//-----------------------------------------------------------------------------------------------------------------------------------------------
						case 1: //1. Deposit sum to account
					//-----------------------------------------------------------------------------------------------------------------------------------------------
							customerInfo = customerSearchInput(customerDatabase);
							amount = doubleAmountInput(choice);
							if (customerInfo != null){
								if (((Customer) customerInfo[1]).deposit(amount) == true){
									JOptionPane.showMessageDialog(null, 
											"The deposit has been successful. \n\n" 
											+ "The new balance for this account is below: \n\n" + ((Customer) customerInfo[1]).toString(), 
											"ES&L Bank - Customer Account Management System", JOptionPane.PLAIN_MESSAGE);
								}
							}//end if
							break;
							
					//-----------------------------------------------------------------------------------------------------------------------------------------------
						case 2: //2. Withdraw sum from account
					//-----------------------------------------------------------------------------------------------------------------------------------------------
							customerInfo = customerSearchInput(customerDatabase);
							amount = doubleAmountInput(choice);
							currentCustomer = (Customer) customerInfo[1];
							if (customerInfo != null){
								if (currentCustomer.withdraw(amount) == true){
									JOptionPane.showMessageDialog(null, 
											"The withdrawl has been successful. \n\n" 
											+ "The new balance for this account is below: \n\n" + ((Customer) customerInfo[1]).toString(), 
											"ES&L Bank - Customer Account Management System", JOptionPane.PLAIN_MESSAGE);
								}
								else {
									JOptionPane.showMessageDialog(null, 
											"Error: Insufficient funds \n"
											+ "\nCustomer: " + currentCustomer.getName()
											+ "\nRequested: " + amount
											+ "\nAvailable: " + currentCustomer.getAcctBalance(),
											"ES&L Bank - Customer Account Management System", JOptionPane.ERROR_MESSAGE);
								}
							}//end if
							break;
							
					//-----------------------------------------------------------------------------------------------------------------------------------------------
						case 3: //3. Create account
					//-----------------------------------------------------------------------------------------------------------------------------------------------
							currentCustomer = createCustomer(customerDatabase);
							if (Customer.addNewCustomer(customerDatabase, currentCustomer) == true){
								count++;
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
							
					//-----------------------------------------------------------------------------------------------------------------------------------------------
						case 4: //4. View all accounts
					//-----------------------------------------------------------------------------------------------------------------------------------------------
							JOptionPane.showMessageDialog(null, 
									"All customer bank accounts in the database: \n\n" + Customer.databaseToString(customerDatabase), 
									"ES&L Bank - Customer Account Management System", JOptionPane.PLAIN_MESSAGE);
							break;
							
					//-----------------------------------------------------------------------------------------------------------------------------------------------
						case 5: //5. Delete an account
					//-----------------------------------------------------------------------------------------------------------------------------------------------
							customerInfo = customerSearchInput(customerDatabase);
							if (customerInfo != null){
								if (Customer.deleteCustomer(customerDatabase, ((int) customerInfo[0])) == true){
									count--;
									JOptionPane.showMessageDialog(null, 
											"The following customer bank account has been deleted from the system: \n\n" + ((Customer) customerInfo[1]).toString(), 
											"ES&L Bank - Customer Account Management System", JOptionPane.PLAIN_MESSAGE);
								}
							}//end if
							break;
							
					//-----------------------------------------------------------------------------------------------------------------------------------------------
						case 9: //6. Quit - Save the changes to the database file and display all customer accounts.
					//-----------------------------------------------------------------------------------------------------------------------------------------------
							FileSaver(databaseFile, customerDatabase);
							JOptionPane.showMessageDialog(null, 
									"State of all customer bank accounts in the database after saving: \n\n" + Customer.databaseToString(customerDatabase), 
									"ES&L Bank - Customer Account Management System", JOptionPane.PLAIN_MESSAGE);
							break;
							
					//-----------------------------------------------------------------------------------------------------------------------------------------------
						case -1: //Not a user option. Quit without saving.
					//-----------------------------------------------------------------------------------------------------------------------------------------------
							choice = 9;
							break;
							
					}//end switch
				}//end while
			}//end if
		}//end if
		
		//Exit Message
		JOptionPane.showMessageDialog(null, 
				//These spaces are properly aligned, do not change
				"                              ~~~ ES&L Bank ~~~\n" 
				+ "           Customer Account Managament System\n\n"
				+ "    Customer Account Management System Closed.", 
				"ES&L Bank - Customer Account Management System", JOptionPane.PLAIN_MESSAGE);
		
	}//End runBankTest() Method
	
	
	
	
	/// User Input Methods ///
	
	/**
	 * A private static GUI input method that displays the Main Menu for the ES&L Bank Account Manager program and returns a user input. 
	 * The menu contains the following options:
	 * <p>
	 *    1. Deposit sum to account
	 * <p>
	 *    2. Withdraw sum from account
	 * <p>
	 *    3. Create account
	 * <p>
	 *    4. View all accounts
	 * <p>
	 *    5. Delete an account
	 * <p>
	 *    9. Quit
	 * <p>
	 * The default option of 9 is selected if the user chooses "Cancel" or closes the window.
	 * @precondition
	 *   none
	 * @return
	 *   Returns an integer value between 1-5 or 9 to quit and save the database. If the user chooses 
	 *   "Cancel" or closes the window, then they are given the option of returning to the main menu 
	 *   or closing the program without saving the database. 
	 * @exception HeadlessException
	 *   Uncaught exception that occurs if called in an environment that does not support a keyboard.
	 * @exception NumberFormatException
	 *   Caught exception that occurs if the user inputs anything other than an integer value.
	 **/
	private static int mainMenuInput(){
		
		//Instance Variables
		String menuChoice; //The user's main menu selection as a String
		String exception; //The wrapper for the exception message thrown by NumberFormatException
		int intInput = 9; //The user's main menu selection number, wrapper for menuChoice
		int yesNoInput; //The user's choice to close or return after clicking cancel
		
		//Display the Main Menu and obtain the user's input
		menuChoice = JOptionPane.showInputDialog(null, 
				"Please select an option:\n"
						+ "1. Deposit sum to account\n"
						+ "2. Withdraw sum from account\n"
						+ "3. Create account\n"
						+ "4. View all accounts\n"
						+ "5. Delete an account\n"
						+ "9. Quit\n\n" 
						, "ES&L Bank", JOptionPane.QUESTION_MESSAGE);
		
		//Process the user's input
		try {
			//Wrap the user's input into an int
			intInput = Integer.parseInt(menuChoice);
			//If the int wrapping was successful, then verify the user's choice is legal
			if (intInput < 1 || intInput > 5 && intInput != 9){
				JOptionPane.showMessageDialog(null, 
						"Error! You must enter a choice between 1 and 6.", 
						"ES&L Bank System", JOptionPane.ERROR_MESSAGE);
				//Return to main menu if user input was illegal
				return mainMenuInput();
			}
		}//end try
		//If the user closed the window or input something other than an integer
		catch (Exception NumberFormatException){
			exception = NumberFormatException.toString();
			//The user closed the window
			if (exception.equals("java.lang.NumberFormatException: null")){
				yesNoInput = JOptionPane.showConfirmDialog(null, 
						"WARNING: If you close the system this way, none of the changes you made to the Customer Account Management System will be saved. \n"
						+ "Please return to the Main Menu and select option 6 to quit and save all changes made to the Customer Account Management System. \n\n"
						+ "Are you sure you want to quit without saving?",
						"ES&L Bank System", JOptionPane.YES_NO_OPTION);
				if (yesNoInput == JOptionPane.YES_OPTION){
					//This will halt the Main Menu options and push the exit message, closing the program
					return -1;
				}
				else {
					//Return to main menu if user input was illegal
					return mainMenuInput();
				}
			}//end if
			//The user input something other than an integer
			else { 
				JOptionPane.showMessageDialog(null, 
					"Error! You must enter an integer. \n"
					+ "Decimal numbers, letters, and characters are not allowed.", 
					"ES&L Bank System", JOptionPane.ERROR_MESSAGE);
				//Return to main menu if user input was illegal
				return mainMenuInput();
			}
		}//end catch
		
		return intInput;
		
	}//End mainMenuInput() Method
	
	
	/**
	 * A private static GUI input method that allows the user to input a Customer to search for in 
	 * customerDatabase. If the Customer is found, then an Object array is returned containing the 
	 * Customer's index location in customerDatabase in objectArray[0] and the Customer object 
	 * itself in objectArray[1]. If the Customer is not found, then this method returns null.
	 * @param customerDatabase
	 *   An array of all the Customers in the customer database. 
	 * @return
	 *   If the Customer is found, then an Object array is returned containing the Customer's index 
	 *   location in customerDatabase in objectArray[0] and the Customer object itself in objectArray[1]. 
	 *   If the Customer is not found, then this method returns null.
	 * @exception HeadlessException
	 *   Uncaught exception that occurs if called in an environment that does not support a keyboard.
	 **/
	private static Object[] customerSearchInput(Customer[] customerDatabase){
		
		//Instance Variables
		Object[] custInfo = new Object[2]; //The array that returns the Customer object and their index in customerDatabase
		Customer findCust; //The Customer the user is searching for
		String nameLast; //The last name of the customer the user is searching for
		String custNum; //The Customer ID of the customer the user is searching for
		int index; //The index location in customerDatabase where the Customer is stored
		
		//Obtain the last name of the Customer from the user
		nameLast = JOptionPane.showInputDialog(null, 
				"Enter the Customer's Name: \n", 
				"ES&L Bank System", JOptionPane.QUESTION_MESSAGE);
		
		//Obtain the Customer ID of the Customer from the user
		custNum = JOptionPane.showInputDialog(null, 
				"Enter the Customer's ID: \n", 
				"ES&L Bank System", JOptionPane.QUESTION_MESSAGE);

		//Find the Customer in the customerDatabase and return the Customer and their index location if found
		findCust = new Customer(nameLast, custNum, 0, "");
		index = Customer.findIndex(customerDatabase, findCust);
		//The Customer was not found in customerDatabase
		if (index == -1){
			JOptionPane.showMessageDialog(null, 
					findCust.getName() 
					+ " with the  Customer ID " 
					+ findCust.getCustNumber() 
					+ " could not be found in the database. \n", 
					"ES&L Bank System", JOptionPane.PLAIN_MESSAGE);
			return null;
		}
		//The Customer was found in customerDatabase
		else {
			findCust = customerDatabase[index];
			custInfo[0] = index;
			custInfo[1] = findCust;
		}
		
		return custInfo;
		
	}//End customerSearch(Customer[] customerDatabase) Method
	
	
	/**
	 * A private static GUI input method that obtains double input from the user for Customer account deposits and withdrawals. 
	 * @param choice
	 *   The user's main mainMenuInput when calling this method. 
	 *   This differentiates between a deposit or withdrawal. 
	 * @precondition
	 *   The user must enter am argument of 1 or 2 for choice. Any other choice will cause a runtime error.
	 * @return
	 *   The double amount the user has input to be deposited or withdrawn.
	 * @exception HeadlessException
	 *   Uncaught exception that occurs if called in an environment that does not support a keyboard.
	 * @exception NumberFormatException
	 *   Caught exception that occurs if the user inputs anything other than a double value. 
	 **/
	private static double doubleAmountInput(int choice){
		
		//Instance Variables
		double amount = 0;
		boolean legalDouble;

		//Obtain the user's input and process it
		do {
			legalDouble = true;
			try {
				//Deposit Option
				if (choice == 1){
					amount = Double.parseDouble(JOptionPane.showInputDialog(null, 
							"Enter the deposit, e.g., 10000.00: \n", 
							"ES&L Bank System", JOptionPane.QUESTION_MESSAGE));
				}
				//Withdraw Option
				if (choice == 2){
					amount = Double.parseDouble(JOptionPane.showInputDialog(null, 
							"Enter the withdrawal, e.g., 10.00: \n", 
							"ES&L Bank System", JOptionPane.QUESTION_MESSAGE));
				}
				//Verify legal input if the user input a valid double
				if (amount <= 0){
					legalDouble = false;
					JOptionPane.showMessageDialog(null, 
							"Error! You must enter a decimal amount greater than 0.00. \n"
									+ "Negative amounts, letters, and characters are not allowed.", 
									"ES&L Bank System", JOptionPane.ERROR_MESSAGE);
				}
			}//end try
			//If the user closes the window or inputs something other than a valid double
			catch (Exception NumberFormatException){
				legalDouble = false;
				JOptionPane.showMessageDialog(null, 
						"Error! You must enter a decimal amount greater than 0.00. \n"
								+ "Negative amounts, letters, and characters are not allowed.", 
								"ES&L Bank System", JOptionPane.ERROR_MESSAGE);
			}
		} while (legalDouble == false); //end do-while

		return amount;

	}//End getDoubleAmount(int choice) Method
	
	
	
	
	/// Customer and Customer Database Creation Methods ///
	
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
	private static Customer createCustomer(Customer[] customerDatabase){
		
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
	private static Customer[] createCustomerDatabase(File database) throws FileNotFoundException {
		
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
					try {
						if (Customer.addNewCustomer(customerDatabase, newCustomer) == false){
							fileScanner.close();
							JOptionPane.showMessageDialog(null, 
									"WARNING: The customer database size limit has been reached! \n"
									+ "Only the first 30 accounts listed in this customer account database will be imported. \n"
									+ "If you save this database, all customer accounts that were not imported will be permanently deleted from the database.",
									"ES&L Bank System", JOptionPane.WARNING_MESSAGE);
							return customerDatabase;
						}
					}//end try
					catch (Exception IllegalArgumentException){
						JOptionPane.showMessageDialog(null, 
								"CRITICAL ERROR! CORRUPT CUSTOMER DATABASE! \n"
								+ "This database contains one or more Customers that share the same Customer ID! \n"
								+ "Every Customer in the database must have a unique ID! \n\n"
								+ "Unable to open this corrupt customer database. \n\n",
								"ES&L Bank System", JOptionPane.ERROR_MESSAGE);
						return null;
					}
				}//end if
			}//end try
			catch (Exception multipleExceptions){
				JOptionPane.showMessageDialog(null, 
						"Error! Error in database file input! \n\n"
						+ "Line " + line + " of the database has been ignored and the customer on this line will not be added.", 
						"ES&L Bank System", JOptionPane.ERROR_MESSAGE);
			}
			line++;
		}//end while
		
		fileScanner.close();
		
		return customerDatabase;
		
	}//End createCustomerDatabase(File database) Method
	
	
	
	
	/// Database File Loader and File Saver Methods ///
	
	/**
	 * This method allows the user to select the text file containing the Customer accounts to be managed. 
	 * @precondition
	 *   The file selected by the user must contain a single (one word) name and a single integer key on each line of the text file. 
	 *   The text file selected must contain 241 or fewer elements. 
	 *   The name and key for each element must be be separated by one of the following delimiters: " "   ","   ";"   ":"   "_"   "\t" 
	 * @return
	 *   Returns the user selected text file containing the names and keys to be used.
	 **/
	private static File FileLoader(){
		
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
	private static void FileSaver(File databaseFile, Customer[] customerDatabase) throws FileNotFoundException {
		
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
		
	
}//End BankDriver Class
