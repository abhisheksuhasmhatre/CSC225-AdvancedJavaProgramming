// File: BankDriver.java
// Due Date: Monday 6/20/16

/********************************************************************************************************
 * ES&L Bank Account Manager Program - CSC 225 Prog1
 * Course Title: Advanced Java Programming
 * Course Number: CSC 225-805
 * Instructors: Professors Christine Forde and Harry Payne
 * @author Anna Ekeren
 * @author Rafael Ferrer
 * @author Abhishek Mhatre
 * @version 1.0, 06/19/16
 * 
 * Description ES&L Bank Account Manager Program Driver Class - CSC 225 Project 1
 * <p>
 * The BankDriver class is used in conjunction with the Customer class in order to simulate a database of 
 * bank customer accounts for the fictional ES&L Bank. This class creates a Customer database (containing
 * Customer types which are managed by the methods in the Customer class), which can be managed by a user 
 * through a GUI, whose methods are provided in this BankDriver class. 
 * 
 ********************************************************************************************************/




/// Imported Packages ///
import java.io.*; //For File, FileNotFoundException, and PrintWriter Classes
import java.text.DecimalFormat;
import java.util.*; //For Scanner and StringTokenizer Classes
import javax.swing.*; //For JOptionPane and JFileChooser Classes


public class BankDriver {
	
	/// Main Method of BankDriver and runBankTest Method ///
	
	/**
	 * The Main method calls the runBankTest method to initiate the ES&L Bank Account Manager Program.
	 * @postcondition
	 *   The ES&L Bank Account Manager Program has been run and then closed. 
	 **/
	public static void main(String[] args) throws FileNotFoundException {
		
		BankDriver bankTest = new BankDriver();
		bankTest.runBankTest();
	    System.exit(0);

	}//End Main Method
	
	
	/**
	 * A driver method that initiates the ES&L Bank Account Manager Program. It begins with a startup message, 
	 * followed by a prompt to select a Customer database text file. It then generates a Customer array containing
	 * the database and displays that to the user. After that the user is provided a Main Menu GUI where they can
	 * manage the Customer database array. After the user chooses to close the program it displays the database again 
	 * and then closes with an exit message.
	 * @postcondition
	 *   The ES&L Bank Account Manager Program has been run and then closed. 
	 **/
	public void runBankTest() throws FileNotFoundException {
		
		//Instance Variables
		File databaseFile; //The Customer database text file that was selected in the FileLoader() method after launching the program.
		Customer[] customerDatabase; //An array of all the Customers in the customer database. 
		Object [] customerInfo; //customerInfo[0] contains a Customer's index in customerDatabase, customerInfo[1] contains the Customer
		Customer currentCustomer; //Customer cursor
		double amount = 0;//The amount of money being deposited or withdrawn from a Customer account
		int choice = -1; //The user's menu selection number
		int count = 0; //A counter that keeps track of the number of customers in customerDatabase
		DecimalFormat cashOutput = new DecimalFormat("0.00"); //decimal output format for outputting account balances
		
		//Startup Message
		JOptionPane.showMessageDialog(null, 
				//These spaces are properly aligned, do not change
				"                                                   ~~~ ES&L Bank ~~~\n" 
				+ "                                Customer Account Managament System\n\n"
				+ "Please select a customer account database to manage after clicking 'OK'.", 
				"ES&L Bank System", JOptionPane.PLAIN_MESSAGE);
		
		//Have the user select the Customer databaseFile to be used
		databaseFile = FileLoader();
		
		//Run the program with the selected databaseFile, or close the program if no file was chosen
		if (databaseFile != null){
			
			//Create customerDatabase from databaseFile
			customerDatabase = createCustomerDatabase(databaseFile);
			
			//If a customerDatabase was successfully created, then display the customerDatabase and then run the Main Menu
			if (customerDatabase != null){
			
				//Update count with the number of Customers in customerDatabase
				for (int i = 0; i < customerDatabase.length; i++){
					if (customerDatabase[i] instanceof Customer){
						count++;
					}
				}
				
				//Display customerDatabase
				JOptionPane.showMessageDialog(null, 
						"State of all customer bank accounts in the database: \n\n" + Customer.databaseToString(customerDatabase) + "\n\n"
						+ "Click 'OK' to continue to the Main Menu.", 
						"ES&L Bank System", JOptionPane.PLAIN_MESSAGE);
				
				//Loop the Main Menu GUI and close the program if the user selects option 9
				while (choice != 9){
					//Obtain user's Main Menu selection
					choice = mainMenuInput();
					//Main Menu Switch Board
					switch(choice){
					
					//-----------------------------------------------------------------------------------------------------------------------------------------------
						case 1: //1. Deposit sum to account
					//-----------------------------------------------------------------------------------------------------------------------------------------------
							//Prompt the user to search for the Customer 
							customerInfo = customerSearchInput(customerDatabase);
							//Verify that the Customer was found in customerDatabase
							if (customerInfo != null){
								currentCustomer = (Customer) customerInfo[1];
								//Prompt the user to enter the amount to deposit
								amount = doubleAmountInput(choice);
								//Run the deposit and verify that it was successful
								if (currentCustomer.deposit(amount) == true){
									JOptionPane.showMessageDialog(null, 
											currentCustomer.getName() + " balance after deposit with interest added: $" + cashOutput.format(currentCustomer.getAcctBalance()), 
											"ES&L Bank System", JOptionPane.PLAIN_MESSAGE);
								}
							}//end if
							break;
							
					//-----------------------------------------------------------------------------------------------------------------------------------------------
						case 2: //2. Withdraw sum from account
					//-----------------------------------------------------------------------------------------------------------------------------------------------
							//Prompt the user to search for the Customer
							customerInfo = customerSearchInput(customerDatabase);
							//Verify that the Customer was found in customerDatabase
							if (customerInfo != null){
								currentCustomer = (Customer) customerInfo[1];
								//Prompt the user to enter the amount to withdraw
								amount = doubleAmountInput(choice);
								if (currentCustomer.withdraw(amount) == true){
									JOptionPane.showMessageDialog(null, 
											currentCustomer.getName() + " balance after withdrawal with transaction fee paid: $" + cashOutput.format(currentCustomer.getAcctBalance()), 
											"ES&L Bank System", JOptionPane.PLAIN_MESSAGE);
								}
								else {
									JOptionPane.showMessageDialog(null, 
											"Error: Insufficient funds."
											+ "\nCustomer: " + currentCustomer.getName()
											+ "\nRequested: $" + cashOutput.format(amount)
											+ "\nAvailable: $" + cashOutput.format(currentCustomer.getAcctBalance()),
											"ES&L Bank System", JOptionPane.ERROR_MESSAGE);
								}
							}//end if
							break;
							
					//-----------------------------------------------------------------------------------------------------------------------------------------------
						case 3: //3. Create account
					//-----------------------------------------------------------------------------------------------------------------------------------------------
							//Verify there is room in this customerDatabase first
							if (count < customerDatabase.length){
								//Prompt the user to create the new Customer if there is space available
								currentCustomer = createCustomer(customerDatabase);
								//Add the Customer to customerDatabase and verify it was successful
								if (Customer.addNewCustomer(customerDatabase, currentCustomer) == true){
									count++;
									JOptionPane.showMessageDialog(null, 
											"The following customer bank account has been created successfully: \n\n" + currentCustomer.toString(), 
											"ES&L Bank System", JOptionPane.PLAIN_MESSAGE);
								}
							}//end if
							//There is no more room for new Customers in customerDatabase
							else {
								JOptionPane.showMessageDialog(null, 
										"The customer database is full! \n"
										+ "Please delete customer bank accounts that are no longer in use to make space available for new accounts.",
										"ES&L Bank System", JOptionPane.ERROR_MESSAGE);
							}//end else
							break;
							
					//-----------------------------------------------------------------------------------------------------------------------------------------------
						case 4: //4. View all accounts
					//-----------------------------------------------------------------------------------------------------------------------------------------------
							JOptionPane.showMessageDialog(null, 
									"All customer bank accounts in the database: \n\n" + Customer.databaseToString(customerDatabase), 
									"ES&L Bank System", JOptionPane.PLAIN_MESSAGE);
							break;
							
					//-----------------------------------------------------------------------------------------------------------------------------------------------
						case 5: //5. Delete an account
					//-----------------------------------------------------------------------------------------------------------------------------------------------
							//Prompt the user to search for the Customer
							customerInfo = customerSearchInput(customerDatabase);
							//Verify that the Customer was found in customerDatabase
							if (customerInfo != null){
								currentCustomer = (Customer) customerInfo[1];
								//Delete the Customer from customerDatabase and verify it was successful
								if (Customer.deleteCustomer(customerDatabase, ((int) customerInfo[0])) == true){
									count--;
									JOptionPane.showMessageDialog(null, 
											currentCustomer.getName() + " is deleted.",  
											"ES&L Bank System", JOptionPane.PLAIN_MESSAGE);
								}
							}//end if
							break;
							
					//-----------------------------------------------------------------------------------------------------------------------------------------------
						case 9: //6. Quit - Save the changes to the database file and display all customer accounts.
					//-----------------------------------------------------------------------------------------------------------------------------------------------
							FileSaver(customerDatabase, databaseFile);
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
	 * A private static GUI input method used by runBankTest that displays the Main Menu for the ES&L Bank Account Manager program and returns a user input. 
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
	 * A private static GUI input method used by runBankTest that allows the user to input a Customer to search for in 
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
		
		try {
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
		}//end try
		//If the user closes or cancels any GUI prompt
		catch (Exception multipleExceptions){
			return null;
		}
		
		return custInfo;
		
	}//End customerSearch(Customer[] customerDatabase) Method
	
	
	/**
	 * A private static GUI input method used by runBankTest that obtains double input from the user for Customer account deposits and withdrawals. 
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
	 * A private static GUI method used by runBankTest that allows the user to create a new Customer object
	 * by enter the new Customer's parameters. This method verifies that the user creates a new Customer with
	 * a Customer ID unique to this customerDatabase. Once the Customer has been created it is returned for use.
	 * @param customerDatabase
	 *   An array of all the Customers in the customer database. 
	 * @return
	 *   The new Customer object that the user has created.
	 * @exception HeadlessException
	 *   Uncaught exception that occurs if called in an environment that does not support a keyboard.
	 * @exception NumberFormatException
	 *   Caught exception that occurs if the user inputs anything other than a double value for the Customer's account balance. 
	 **/
	private static Customer createCustomer(Customer[] customerDatabase){
		
		//Instance Variables
		Customer newCustomer; //The new Customer object the user will create and return
		String nameLast; //The new Customer's last name
		String custNum; //The new Customer's Customer ID
		String phoneNum; //The new Customer's phone number
		double acctBal = 0; //The new Customer's account balance
		boolean uniqueID; //False if the user has entered a non-unique Customer ID
		boolean legalDouble; //False if the user has entered illegal input for a double
		
		//Obtain the last name that will be used for this account
		do {
		nameLast = JOptionPane.showInputDialog(null, 
				"Enter Customer's Name: \n", 
				"ES&L Bank System", JOptionPane.QUESTION_MESSAGE);
		} while (nameLast == null  || nameLast.equals(""));
		
		//Obtain the Customer ID that will be used for this account
		do {
			uniqueID = true;
			custNum = JOptionPane.showInputDialog(null, 
					"Enter Customer's Number, e.g., 11111: \n", 
					"ES&L Bank System", JOptionPane.QUESTION_MESSAGE);
			//Verify that custNum is unique to this customerDatabase before continuing
			for (int i = 0; i < customerDatabase.length; i++){
				if (customerDatabase[i] instanceof Customer && custNum != null){
					if (custNum.equals(customerDatabase[i].getCustNumber())){
						uniqueID = false;
						JOptionPane.showMessageDialog(null, 
								"Error! This Customer ID is already in use. \n"
								+ "Please enter a new Customer ID after clicking 'OK'.", 
								"ES&L Bank System", JOptionPane.ERROR_MESSAGE);
					}
				}//end if
			}//end for
		} while (uniqueID == false || custNum == null  || custNum.equals("")); //end do-while
		
		//Obtain the account balance of this account
		do {
			legalDouble = true;
			try {
				acctBal = Double.parseDouble(JOptionPane.showInputDialog(null, 
						"Enter Customer's Balance, e.g., 1000.00: \n", 
						"ES&L Bank System", JOptionPane.QUESTION_MESSAGE));
				if (acctBal < 0){
					legalDouble = false;
					JOptionPane.showMessageDialog(null, 
							"Error! You must enter an account balance greater than 0.00. \n", 
							"ES&L Bank System", JOptionPane.ERROR_MESSAGE);
				}
			}//end try
			catch (Exception NumberFormatException){
				legalDouble = false;
				JOptionPane.showMessageDialog(null, 
						"Error! You must enter a decimal number amount greater than 0.00. \n"
						+ "Negative amounts, letters, and characters are not allowed.", 
						"ES&L Bank System", JOptionPane.ERROR_MESSAGE);
			}
		} while (legalDouble == false); //end do-while
		
		//Obtain the phone number that will be used for this account
		do {
		phoneNum = JOptionPane.showInputDialog(null, 
				"Enter Customer's Phone Number: \n", 
				"ES&L Bank System", JOptionPane.QUESTION_MESSAGE);
		} while (phoneNum == null  || phoneNum.equals(""));
		
		//Create the new Customer object and return it
		newCustomer = new Customer(nameLast, custNum, acctBal, phoneNum);
		
		return newCustomer;
		
	}//End createCustomer(Customer[] customerDatabase) Method
	
	
	/**
	 * This method reads the Customer account database text file selected by the FileLoader() method and generates 
	 * a new Customer object for each valid Customer in databaseFile. It then places these Customer's into an Customer array
	 * where they can be managed by the other methods in the BankDriver class. 
	 * @param databaseFile
	 *   The Customer database text file selected by the FileLoader() method
	 * @precondition
	 *   The databaseFile selected must contain a single Customer's information on each line in the following order: 
	 *   <p>
	 *   1. Customer's Last Name (must be a single word String with no delimiters)
	 *   <p>
	 *   2. Customer's ID (must be a single code String with no delimiters)
	 *   <p>
	 *   3. Customer's Account Balance (must be a nonnegative double less than Double.MAX_VALUE)
	 *   <p>
	 *   4. Customer's Phone Number (must be a single number String (no letters or characters) with no delimiters)
	 *   <p>
	 *   For each Customer, each piece of information may be separated with any of the following delimiters: " "  ","  ";"  ":"  "_"  "\t"
	 *   An empty text file may also be loaded to begin a new empty Customer database.
	 *   Every customer in databaseFile must have a unique Customer ID. If this precondition is not met, then the databaseFile will not open and
	 *   the system will shut down as a security measure. 
	 * @throws FileNotFoundException
	 *   Uncaught exception that occurs if an attempt to open the file denoted by a specified pathname has failed. 
	 * @throws IllegalArgumentException
	 *   Caught exception that occurs if two or more Customers in databaseFile share the same Customer ID. If this occurs, 
	 *   then the databaseFile will not open and the system will shut down as a security measure.
	 * @note
	 *   If a single line of Customer information is corrupt and that Customer cannot be created, then that line will be 
	 *   skipped and the user will be notified that that line of databaseFile has not been included in the Customer database.
	 **/
	private static Customer[] createCustomerDatabase(File databaseFile) throws FileNotFoundException {
		
		//Instance Variables
		final int DATABASE_SIZE = 30; //The constant size of all Customer databases created
		Customer[] customerDatabase = new Customer[DATABASE_SIZE]; //An array of all the Customers in the customer database file. 
		String readCustomer; //A cursor for each line of text in the database text file
		Customer newCustomer; //A newly created Customer object to be added to customerDatabase
		String nameLast; //The last name of a newly created Customer object
		String custNum; //The Customer ID of a newly created Customer object
		String phoneNum; //The phone number of a newly created Customer object
		double acctBal; //The account balance of a newly created Customer object
		int line = 1; //A counter to keep track of the current line number of database being read
		
		//Scanner Instantiation
		Scanner fileScanner = new Scanner(databaseFile); //Used to read the database text file
		fileScanner.useDelimiter(System.getProperty("line.separator")); //Allows us to read the database text file one line at a time
		
		//String Tokenizer Instantiation
		StringTokenizer tokenizer; 
		String delimeters = " ,;:_\t"; //For each Customer (new line), each piece of information may be separated with any of these delimiters
		
		//Read the database text file one line at a time, construct each Customer, then add each Customer to customerDatabase
		while (fileScanner.hasNextLine()){
			try {
				//Read the next line in database
				readCustomer = fileScanner.nextLine();
				tokenizer = new StringTokenizer(readCustomer, delimeters);
				//Verify that the line is not empty
				if (tokenizer.hasMoreTokens()){
					//Obtain Customer parameters from the line and then create the new Customer
					nameLast = tokenizer.nextToken();
					custNum = tokenizer.nextToken();
					acctBal = Double.parseDouble(tokenizer.nextToken());
					phoneNum = tokenizer.nextToken();
					newCustomer = new Customer(nameLast, custNum, acctBal, phoneNum);
					//Add the new Customer to customerDatabase
					try {
						//There is no more room in customerDatabase to add more Customers
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
					//If two or more Customers in database share the same Customer ID
					catch (Exception IllegalArgumentException){
						JOptionPane.showMessageDialog(null, 
								"CRITICAL ERROR! CORRUPT CUSTOMER DATABASE! \n"
								+ "This database contains one or more Customers that share the same Customer ID! \n"
								+ "Every Customer in the database must have a unique ID! \n\n"
								+ "Unable to open this corrupt customer database. \n\n",
								"ES&L Bank System", JOptionPane.ERROR_MESSAGE);
						//Closes the system as a security measure, due to a corrupt database
						return null;
					}
				}//end if
			}//end try
			//If there is a corrupt line in database that prevents that Customer from being created and added to customerDatabase
			catch (Exception multipleExceptions){
				JOptionPane.showMessageDialog(null, 
						"Error in database file line " + line + "! \n\n"
						+ "Line " + line + " of the customer database has been ignored and the customer on this line will not be added.", 
						"ES&L Bank System", JOptionPane.ERROR_MESSAGE);
			}
			line++;
		}//end while
		
		fileScanner.close();
		
		return customerDatabase;
		
	}//End createCustomerDatabase(File databaseFile) Method
	
	
	
	
	/// Database File Loader and File Saver Methods ///
	
	/**
	 * This method allows the user to select the Customer database text file containing the Customer accounts to be managed. 
	 * @precondition
	 *   The Customer database text file selected must contain a single Customer's information on each line in the following order: 
	 *   1. Customer's Last Name (must be a single word String with no delimiters)
	 *   2. Customer's ID (must be a single code String with no delimiters)
	 *   3. Customer's Account Balance (must be a nonnegative double less than Double.MAX_VALUE)
	 *   4. Customer's Phone Number (must be a single number String (no letters or characters) with no delimiters)
	 *   For each Customer, each piece of information may be separated with any of the following delimiters: " "  ","  ";"  ":"  "_"  "\t"
	 *   An empty text file may also be loaded to begin a new empty Customer database.
	 * @return
	 *   Returns the user selected text file database containing the Customer data to be used.
	 **/
	private static File FileLoader(){
		
		//Instance Variables
		File databaseFile; //This is the text file where all the Customers data is stored
		JFileChooser fileSelector; //A file selector UI
		
		//Launch a JFileChooser window to select the file to be used
		fileSelector = new JFileChooser();
		int status = fileSelector.showOpenDialog(null);
		
		//Once a file has been selected, return that file
		if (status == JFileChooser.APPROVE_OPTION){
			databaseFile = fileSelector.getSelectedFile();
			JOptionPane.showMessageDialog(null, 
					"You have selected the customer database file located at: \n" + databaseFile.toString(), 
					"ES&L Bank System", JOptionPane.PLAIN_MESSAGE);
			return databaseFile;
		}
		//If no file is selected, give the user a second chance to select a file or close the program
		else if (status == JFileChooser.CANCEL_OPTION){
			JOptionPane.showMessageDialog(null, 
					"Error! You must select a file to continue.\n\n"
					+ "You may select an empty text file to create a new customer database.\n"
					+ "Or you may click 'Cancel' again to close the program.\n", 
					"ES&L Bank System", JOptionPane.ERROR_MESSAGE);
			//Re-launch the JFileChooser window to select the file to be used 
			status = fileSelector.showOpenDialog(null);
			//Once a file has been selected, return that file
			if (status == JFileChooser.APPROVE_OPTION){
				databaseFile = fileSelector.getSelectedFile();
				JOptionPane.showMessageDialog(null, 
						"You have selected the customer database file located at: \n" + databaseFile.toString(), 
						"ES&L Bank System", JOptionPane.PLAIN_MESSAGE);
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
	 * This method overwrites the Customer databaseFile text file selected in the FileLoader() method with any changes made while running this program.
	 * It then saves databaseFile with those changes.
	 * @param databaseFile
	 *   The Customer database text file that was selected in the FileLoader() method after launching the program. 
	 * @param customerDatabase
	 *   An array of all the Customers in the customer database. 
	 * @postcondition
	 *   The Customer databaseFile text file selected in the FileLoader() method has been overwritten with any changes made while running this program and saved.
	 * @throws FileNotFoundException
	 *   Uncaught exception that occurs if an attempt to open the file denoted by a specified pathname has failed. 
	 **/
	private static void FileSaver(Customer[] customerDatabase, File databaseFile) throws FileNotFoundException {
		
		//Instance Variables
		PrintWriter fileWriter; //Writes a line of text to the text file database
		Customer currentCustomer; //Customer cursor
		
		//Write over the database text file with all changes made to golferDatabase and then save the file
		fileWriter = new PrintWriter(databaseFile);
		for (int i = 0; i < customerDatabase.length; i++){
			if (customerDatabase[i] instanceof Customer){
				currentCustomer = customerDatabase[i];
				fileWriter.println(currentCustomer.getName() + " " + currentCustomer.getCustNumber() + " " + currentCustomer.getAcctBalance() + " " + currentCustomer.getPhoneNumber());
			}
		}//end for
		fileWriter.close();

	}//End FileSaver(Customer[] customerDatabase, File databaseFile) Method
		
	
}//End BankDriver Class
