// File: Customer.java
// Due Date: Monday 6/20/16

/********************************************************************************************************
 * ES&L Bank Account Manager Program - CSC 225 Prog1
 * Course Title: Advanced Java Programming
 * Course Number: CSC 225-805
 * Instructors: Professors Christine Forde and Harry Payne
 * 
 * Description: Customer Data Type Class - CSC 225 Prog1
 * The Customer class is used in conjunction with the BankDriver class in order to simulate a database of 
 * bank customer accounts for the fictional ES&L Bank. This class provides constructors and methods for 
 * managing individual Customers as well as an array of Customers (the customer "database"), as a  bank 
 * teller might do in a real bank. All GUI's for this program are located in the BankDriver class. 
 * 
 * @author Anna Ekeren
 * @author Rafael Ferrer
 * @author Abhishek Mhatre
 * @version 1.0, 16/18/16
 ********************************************************************************************************/




/// Imported Packages ///
import java.text.*; //for DecimalFormat Class


public class Customer implements Comparable<Customer> {
	
	// Invariant of the Customer class: 
	//   1. The Customer's last name is stored in the instance variable lastName, 
	//		which must be a single word String with no delimiters. 
	//   2. The Customer's customer ID is stored in the instance variable custNumber, 
	//		which must be a single code String with no delimiters. 
	//   3. The Customer's phone number is stored in the instance variable phoneNumber, 
	//		which must be a single number String (no letters or characters) with no delimiters. 
	//   4. The Customer's current account balance is stored in the instance variable acctBalance, 
	//		which must be nonnegative and less than Double.MAX_VALUE. 
	//	 5. The constant TRANSACTION_FEE stores the price of a single bank transaction. 
	//	 6. The constant INTEREST_RATE stores the interest rate for a single bank transaction.
	
	
	/// Private Instance Variables ///
	
	//Customer Attributes
	private String lastName;
	private String custNumber;
	private String phoneNumber;
	private double acctBalance;
	
	//Bank Constants
	private final double TRANSACTION_FEE = 1.50;
	private final double INTEREST_RATE = 0.045;
	
	
	
	
	/// Constructors for the Customer Object ///
	
	/**
	 * The default constructor that creates a Customer object with a blank last name, Customer ID, and phone number. 
	 * The Customer's account balance starts at 0.00.
	 * @precondition
	 *  None
	 * @postcondition
	 *  A new Customer object with an account balance of 0.00 and a blank last name, Customer ID, and phone number has been created.
	 **/
	public Customer(){
		
		lastName = "";
		custNumber = "";
		acctBalance = 0;
		phoneNumber = "";
		
	}//End Customer() Constructor
	
	
	/**
	 * A constructor that creates a Customer object with a specified last name, Customer ID, phone number, and account balance. 
	 * @param nameLast
	 *   The last name of the new Customer. This should be a single word with no delimiters.
	 * @param custNum
	 *   The Customer ID of the new Customer. This should be a single code with no delimiters.
	 * @param acctBal
	 *   The starting account balance of the new customer. This must be a non-negative value that is less than Double.MAX_VALUE.
	 * @param phoneNum
	 *   The phone number of the new Customer. This should be a single line of numbers with no delimiters.
	 * @precondition
	 *   The starting account balance must be a non-negative value that is less than Double.MAX_VALUE.
	 * @postcondition
	 *   A new Customer object with the last name, Customer ID, phone number, and account balance specified by the user has been created.
	 * @exception IllegalArgumentException
	 *   Occurs if acctBal is less than 0.00.
	 * @exception NumberFormatException
	 *   Occurs if acctBal is greater than Double.MAX_VALUE.
	 **/
	public Customer(String nameLast, String custNum, double acctBal, String phoneNum){
		
		//Create the new Customer if acctBal is a legal argument
		if (acctBal >= 0){
			lastName = nameLast;
			custNumber = custNum;
			acctBalance = acctBal;
			phoneNumber = phoneNum;
		}
		//Otherwise an exception will be thrown
		else {
			throw new IllegalArgumentException("The starting account balance must be 0.00 or greater!");
		}
		
	}//End Customer(String nameLast, String custNum, double acctBal, String phoneNum) Method
	
	
	
	
	/// Accessor Methods for the Private Instance Variables ///
	
	/**
	* An accessor method that returns this Customer's last name.
	* @return
	*   Returns the last name of the invoking Customer.
	**/
	public String getName(){
		
		return lastName;
		
	}//End getName() Method
	
	
	/**
	* An accessor method that returns this Customer's customer ID.
	* @return
	*   Returns the customer ID of the invoking Customer.
	**/
	public String getCustNumber(){
		
		return custNumber;
		
	}//End getCustNumber() Method
	
	
	/**
	* An accessor method that returns this Customer's current account balance.
	* @return
	*   Returns the current account balance of the invoking Customer.
	**/
	public double getAcctBalance(){
		
		return acctBalance;
		
	}//End getAcctBalance() Method
	
	
	/**
	* An accessor method that returns this Customer's phone number.
	* @return
	*   Returns the phone number of the invoking Customer.
	**/
	public String getPhoneNumber(){
		
		return phoneNumber;
		
	}//End getPhoneNumber() Method
	
	
	
	
	/// Mutator Methods for the Private Instance Variables ///
	
	/**
	* A mutator method that allows a user to change this Customer's last name. 
	* @param newLastName
	*   The new last name the user wishes to set for the invoking Customer.
	* @postcondition
	*   The invoking Customer's last name has been changed to newLastName.
	* @note
	*   The String newLastName should be a single word with no delimiters.
	**/
	public void setName(String newLastName){
		
		lastName = newLastName;
		
	}//End setName(String newLastName) Method
	
	
	/**
	* A mutator method that allows a user to change this Customer's Customer ID.
	* @param newCustNum
	*   The new customer ID the user wishes to set for the invoking Customer.
	* @postcondition
	*   The invoking Customer's customer ID has been changed to newCustNum.
	* @note
	*   The String newCustNum should be a single word with no delimiters.
	**/
	public void setCustNum(String newCustNum){
		
		custNumber = newCustNum;
		
	}//End setCustNum(String newCustNum) Method
	
	
	/**
	* A mutator method that allows a user to change this Customer's account balance.
	* @param newAcctBal
	*   The new account balance the user wishes to set for the invoking Customer.
	* @precondition
	*   The double newAcctBal must be a non-negative value that is less than Double.MAX_VALUE.
	* @postcondition
	*   The invoking Customer's account balance has been changed to newAcctBal.
	* @exception IllegalArgumentException
	*   Occurs if newAcctBal is a negative value.
	* @exception NumberFormatException
	*   Occurs if newAcctBal is greater than Double.MAX_VALUE.
	**/
	public void setAcctBal(double newAcctBal){
		
		//Verify that newAcctBal is a legal argument
		if (newAcctBal > 0){
			acctBalance = newAcctBal;
		}
		else {
			throw new IllegalArgumentException("Account balance must be greater than 0!");
		}
		
	}//End setAcctBal(double newAcctBal) Method
	
	
	/**
	* A mutator method that allows a user to change this Customer's phone number.
	* @param newPhoneNum
	*   The new phone number the user wishes to set for the invoking Customer.
	* @postcondition
	*   The invoking Customer's phone number has been changed to newPhoneNum.
	* @note
	*   The String newPhoneNum should be a single line of numbers with no delimiters.
	**/
	public void setPhoneNum(String newPhoneNum){
		
		phoneNumber = newPhoneNum;
		
	}//End setPhoneNum(String newPhoneNum) Method
	
	
	
	
	/// Customer Account Modification Methods ///
	
	/**
	* An account modification method that allows the user to deposit money to a Customer's account, 
	* thereby increasing that Customer's account balance. 
	* @param depositAmount
	*   The amount of money to deposit into the invoking Customer's account. 
	* @precondition
	*   The double depositAmount must be a non-negative value that is less than Double.MAX_VALUE.
	* @postcondition
	*   The invoking Customer's account balance has been increased by depositAmount.
	* @return
	*   Returns true if the account deposit was successful.
	* @exception IllegalArgumentException
	*   Occurs if depositAmount is a negative value.
	* @exception NumberFormatException
	*   Occurs if depositAmount is greater than Double.MAX_VALUE.
	* @note
	*   If the sum of the invoking Customer's current account balance and depositAmount is greater than Double.MAX_VALUE, 
	*   then an arithmetic overflow error will occur. 
	**/
	public boolean deposit(double depositAmount){
		
		//Instance Variables
		boolean status = false; //Represents the status of the successful completion of the transaction.
		
		//Verify that depositAmount is a legal argument
		if (depositAmount > 0){
			//First add interest to this Customer's account before depositAmount is added 
			this.addInterest(INTEREST_RATE);
			//Add depositAmount to this Customer's account after adding interest
			acctBalance = acctBalance + depositAmount;
			status = true;
		}
		else {
			throw new IllegalArgumentException("depositAmount must be greater than 0!");
		}
		
		return status;
		
	}//End deposit(double depositAmount) Method
	
	
	/**
	* An account modification method that allows the user to withdraw money from a Customer's account, 
	* thereby decreasing that Customer's account balance. 
	* @param withdrawAmount
	*   The amount of money to withdraw from the invoking Customer's account. 
	* @precondition
	*   The double withdrawAmount must be a non-negative value that is less than Double.MAX_VALUE.
	*   It also may not be greater than the current account balance. 
	* @postcondition
	*   If the transaction is successful, then the invoking Customer's account balance has been decreased 
	*   by the withdrawAmount and a transaction fee. If there are insufficient funds in this Customer's account, 
	*   then the transaction is cancelled and no changes are made to the invoking Customer's account balance.
	* @return
	*   Returns true if the account withdrawal was successful. 
	*   Returns false if there were insufficient funds in this Customer's account. 
	* @exception IllegalArgumentException
	*   Occurs if withdrawAmount is a negative value.
	* @exception NumberFormatException
	*   Occurs if withdrawAmount is greater than Double.MAX_VALUE.
	**/
	public boolean withdraw(double withdrawAmount){
		
		//Instance Variables
		boolean status = false; //Represents the status of the successful completion of the transaction.
		
		//Verify that withdrawAmount is a legal argument
		if (withdrawAmount > 0){
			//Verify that withdrawAmount is available to withdraw from this Customer's account, and that they can afford the fee
			if ((withdrawAmount + TRANSACTION_FEE) <= acctBalance){
				acctBalance = acctBalance - (withdrawAmount + TRANSACTION_FEE);
				status = true;
			}
		}//end if
		else {
			throw new IllegalArgumentException("depositAmount must be greater than 0!");
		}

		return status;

	}//End withdraw(double withdrawAmount) Method
	
	
	/**
	* An account modification method that allows the user to add interest to a Customer's account. 
	* @param interestRate
	*   The decimal rate of the interest to be added.
	* @precondition
	*   The double interestRate must be greater than 0 and less than Double.MAX_VALUE.
	* @postcondition
	*   The invoking Customer's account balance has been increased by the product of interestRate and 
	*   their current account balance. 
	* @exception IllegalArgumentException
	*   Occurs if withdrawAmount is a negative value.
	* @exception NumberFormatException
	*   Occurs if withdrawAmount is greater than Double.MAX_VALUE.
	* @note
	*   If the sum of the invoking Customer's current account balance and the added interest is greater 
	*   than Double.MAX_VALUE, then an arithmetic overflow error will occur. 
	**/
	public void addInterest(double interestRate){
		
		//Verify that interestRate is a legal argument
		if (interestRate > 0){
			acctBalance = acctBalance + (acctBalance * interestRate);
		}
		else {
			throw new IllegalArgumentException("interestRate must be greater than 0!");
		}
		
	}//End addInterest(double interestRate) Method
	
	
	
	
	/// Overridden toString and Equals Methods ///
	
	/**
	* A method that returns the Customer's labeled account information.
	* @return 
	*   Returns a String containing the invoked customer's last name, customer ID, account balance, and phone number.
	**/
	public String toString(){
		
		//Instance Variables
		DecimalFormat cashOutput = new DecimalFormat("0.00"); //decimal output format for outputting account balances
		
		//Return the Customer's labeled instance variables
		return "Last Name: " + lastName + "\nCustomer Number: " + custNumber + "\nAccount Balance: $" + cashOutput.format(acctBalance) + "\nPhone Number: " + phoneNumber;
		
	}//End toString() Method

	
	/**
	 * A method to compare two Customer objects and determine if they are equivalent.
	 * @param obj
	 *   The Object that is being compared to the invoking Customer to test for equivalency.
	 * @postcondition
	 *   If the Customer objects being compared have the same last name and customer ID, 
	 *   then equals will return true. Otherwise equals will return false. 
	 **/
	public boolean equals(Object obj){
		
		//Instance Variables
		Customer candidate; //Cursor that holds obj, assuming obj is an object of the Customer type
		boolean isEqual = false; //Outcome of the equivalency comparison
		
		//Verify that obj is of the Customer data type
		if (obj instanceof Customer){
			candidate = (Customer) obj;
			//Verify that candidate has the same lastName and custNumber as the invoked Customer
			if (this.lastName.equals(candidate.lastName) && this.custNumber.equals(candidate.custNumber)){
				isEqual = true;
			}
		}//end if
		
		return isEqual;
		
	}//end equals(Object obj) Method
	
	
	
	
	/// Comparable Interface ///
	
	/**
	 * A method that returns an integer comparison based on the lexicographical order of the Customers last names.
	 * The comparison is based on the Unicode value of each character in a Customer's last name. 
	 * @param inputCustomer
	 *   The Customer object to compare to the object calling this method.
	 * @return
	 *   Returns a negative integer if inputCustomer's last name precedes this Customer's lastName lexicographically.
	 *   Returns a positive integer if inputCustomer's last name follows this Customer's lastName lexicographically.
	 *   Returns zero if inputCustomer's last name is the same as this Customer's lastName.
	 **/
	public int compareTo(Customer inputCustomer){
		
		//Instance Variables
		int comparison; //The return value assigned by the lexicographical order of this Customer's lastName
		
		//Use String's compareTo to compare the Customers lastNames
		comparison = lastName.compareTo(inputCustomer.getName());
		
		return comparison;
		
	}//End compareTo(Customer inputCustomer) Method
	
	
	
	
	/// Additional Methods Required for BankDriver ///
	
	/**
	 * A static method that adds a new Customer to the customer database. 
	 * @param customerDatabase
	 *   An array of all the Customers in the customer database. 
	 * @param newCustomer
	 *   The new Customer to be added to customerDatabase. 
	 * @precondition
	 *   The customerDatabase must be non-null and below its maximum capacity. 
	 * @return
	 *   Returns true if newCustomer was successfully added to customerDatabase. 
	 *   Returns false if there was no open index in customerDatabase to add newCustomer to.
	 * @exception NullPointerException
	 *   Occurs if customerDatabase is null. 
	 * @exception IllegalArgumentException
	 *   Occurs if customerDatabase contains one or more Customers that share the same Customer ID.
	 **/
	public static boolean addNewCustomer(Customer[] customerDatabase, Customer newCustomer){
		
		//Instance Variables
		boolean status = false; //Represents the status of the completion of the customer addition
		
		//Verify that each customer ID is unique to customerDatabase
		//If not, then do not add that Customer to customerDatabase
		for (int i = 0; i < customerDatabase.length; i++){
			if (customerDatabase[i] instanceof Customer && customerDatabase[i].getCustNumber().equals(newCustomer.getCustNumber())){
				throw new IllegalArgumentException("This database contains one or more Customers that share the same Customer ID!");
			}
		}//end for
		
		//Add newCustomer to the first open index in customerDatabase, if there is one
		for (int i = 0; i < customerDatabase.length; i++){
			if (customerDatabase[i] == null){
				customerDatabase[i] = newCustomer;
				status = true;
				return status;
			}
		}//end for
		
		return status;
		
	}//End addNewCustomer(Customer[] customerDatabase, Customer newCustomer) Method
	
	
	/**
	 * A static method that deletes a Customer from the customer database. 
	 * @param customerDatabase
	 *   An array of all the Customers in the customer database. 
	 * @param index
	 *   The index location of the Customer to be deleted in customerDatabase. 
	 * @precondition
	 *   The index value must be a valid index of customerDatabase.
	 * @return
	 *   Returns true after removing the customer located at index in customerDatabase.
	 * @exception ArrayIndexOutOfBoundsException
	 *   Occurs if the index value is negative or greater than or equal to the maximum capacity of customerDatabase. 
	 **/
	public static boolean deleteCustomer(Customer[] customerDatabase, int index){
		
		customerDatabase[index] = null;
		
		return true;
		
	}//End deleteCustomer(Customer[] customerDatabase, int index) Method
	
	
	/**
	 * A static method that sorts an array of Customers in alphabetical order by last name.
	 * @param customerDatabase
	 *   An array of all the Customers in the customer database.   
	 * @param first
	 *   The index location of customerDatabase to begin sorting at.
	 * @param size
	 *   The number of indexes to sort after first. 
	 * @precondition
	 *   The sum of first and size is less than the maximum capacity of customerDatabase. 
	 *   The integers first and size are non-negative and less than Integer.MAX_VALUE. 
	 * @postcondition
	 *   The elements of customerDatabase have been rearranged in alphabetical order by last name.
	 * @exception ArrayIndexOutOfBoundsException
	 *   Occurs if first is negative or greater than or equal to the maximum capacity of customerDatabase. 
	 *   Occurs if the sum of first and size is negative or greater than or equal to the maximum capacity of customerDatabase.
	 * @exception NumberFormatException
	 *   Occurs if first or size are greater than Integer.MAX_VALUE.
	 **/
	private static void nameSort(Customer[] customerDatabase, int first, int size){
		
		//Instance Variables
		int big;
		Customer temp;
		
		//Run a selection sort on customerDatabase
		for (int i = size - 1; i > 0; i--){
			//Calculate big as the index of the largest value in customerDatabase[first] through customerDatabase[size]
			big = first;
			if (customerDatabase[big] instanceof Customer){
				for (int j = first + 1; j <= first + i; j++){
						if (customerDatabase[j] instanceof Customer){
							if (customerDatabase[big].compareTo(customerDatabase[j]) < 0){
								big = j;
							}
						}//end if
				}//end for
				//Swap customerDatabase[first + i] with customerDatabase[big]
				temp = customerDatabase[first + i];
				customerDatabase[first + i] = customerDatabase[big];
				customerDatabase[big] = temp;
			}//end if
		}//end for
		
	}//End (Customer[] customerDatabase, int first, int size) Method
	
	
	/**
	 * A static method that returns the index location of a specific Customer in an array of customers.
	 * @param customerDatabase
	 *   An array of all the Customers in the customer database. 
	 * @param findCust
	 *   The specific Customer that the user is searching for in the array.
	 * @return
	 *   If findCust is found in customerDatabase, then the index location that the searched Customer is located at is returned.
	 *   If findCust is not found in customerDatabase, then -1 is returned. 
	 **/
	public static int findIndex(Customer[] customerDatabase, Customer findCust){
		
		//Instance Variables
		int index; //An index cursor for customerDatabase
		
		//Search for the index in customerDatabase that contains this customer and return that index
		for (index = 0; index < customerDatabase.length; index++){
			if (customerDatabase[index] instanceof Customer && customerDatabase[index].equals(findCust)){ 
				return index;
			}
		}//end for
		
		//If the customer is not found in customerDatabase, then return -1
		return -1;
		
	}//End findIndex(Customer[] customerDatabase, Customer findCust) Method
	
	
	/**
	 * A static method that returns a string (in the form of a table with a single customer on each line) 
	 * containing the account information of all the customers in the customer database. 
	 * @param customerDatabase
	 *   An array of all the Customers in the customer database. 
	 * @return
	 *   The String databaseString that contains the Name, Account ID, Phone Number, and Balance
	 *   of each customer in the database, formatted to be printed out.   
	 **/
	public static String databaseToString(Customer[] customerDatabase){
		
		//Instance Variables
		String databaseString = "Customer Name          Account ID          Phone Number          Account Balance          \n\n";
		DecimalFormat cashOutput = new DecimalFormat("0.00"); //decimal output format for outputting account balances
		
		//Sort customerDatabase alphabetically by lastName
		nameSort(customerDatabase, 0, customerDatabase.length);
		
		//Add all customer accounts in the database to databaseString
		for (int i = 0; i < customerDatabase.length; i++){
			if (customerDatabase[i] instanceof Customer){
				databaseString = databaseString 
						+ customerDatabase[i].getName() + "                    "
						+ customerDatabase[i].getCustNumber() + "                    "
						+ customerDatabase[i].getPhoneNumber() + "                    "
						+ "$" + cashOutput.format(customerDatabase[i].getAcctBalance()) + "                    \n";
			}
		}//end for
		
		return databaseString;
		
	}//End databaseToString(Customer[] customerDatabase) Method
	
	
}//End Customer Class

