// File: Customer.java
// Due Date: Monday 6/20/16

/********************************************************************************************************
 * ES&L Bank Account Manager Program - CSC 225 Prog1
 * Course Title: Advanced Java Programming
 * Course Number: CSC 225-805
 * Instructors: Professors Christine Forde and Harry Payne
 * @author Anna (Ekeren?)
 * @author Rafael Ferrer
 * @author Abhishek Mhatre
 * @version 0, 06/06/16
 * 
 * Description: Customer Data Type Class - CSC 225 Prog1
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


import java.text.DecimalFormat;

public class Customer implements Comparable<Customer>
{
	// Invariant of the Customer class:
	//   1. 
	//   2. 
	//   3. 
	//   4. 
	
	/// Private Instance Variables ///
	
	private String lastName;
	private String custNumber;
	private double acctBalance;
	private String phoneNumber;
	public static final double TRANSACTION_FEE = 1.50; 
	
	
	/// Constructors ///
	
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
	public Customer()
	{
		lastName = "";
		custNumber = ""; // How should we generate customer numbers?
		acctBalance = 0;
		phoneNumber = "";
		
	}//End Customer() Constructor
	
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
	public Customer(String nameLast, String custNum, double acctBal, String phoneNum)
	{
		lastName = nameLast;
		custNumber = custNum;
		acctBalance = acctBal;
		phoneNumber = phoneNum;
		
	}//End Customer(String nameLast, Long custNum, Long acctBal, Long phoneNum) Method
	
	
	/// Accessor Methods for the Private Instance Variables ///
	
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
	public String getName()
	{
		return lastName;
		
	}//End getName() Method
	
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
	public String getCustNumber()
	{
		return custNumber;
		
	}//End getCustNumber() Method
	
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
	public double getAcctBalance()
	{
		return acctBalance;
		
	}//End getAcctBalance() Method
	
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
	public String getPhoneNumber()
	{
		return phoneNumber;
		
	}//End getPhoneNumber() Method
	
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
	public String toString()
	{
		DecimalFormat cashOutput = new DecimalFormat("0.00"); //decimal output format for outputting account balances
		
		return "Last Name: " + lastName + "; Customer Number: " + custNumber + "; Account Balance: " + cashOutput.format(acctBalance/100.0) + "; Phone Number: " + phoneNumber;
		
	}//End toString() Method
	
	
	/// Mutator Methods for the Private Instance Variables ///
	
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
	public void setName(String newLastName)
	{
		lastName = newLastName;
		
	}//End setName(String newLastName) Method
	
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
	public void setCustNum(String newCustNum)
	{
		custNumber = newCustNum;
		
	}//End setCustNum(Long newCustNum) Method
	
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
	public void setAcctBal(double newLAcctBal)
	{
		acctBalance = newLAcctBal;
		
	}//End setAcctBal(Long newLAcctBal) Method
	
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
	public void setPhoneNum(String newPhoneNum)
	{
		phoneNumber = newPhoneNum;
		
	}//End setPhoneNum(Long newPhoneNum) Method
	
	
	/// Customer Account Modification Methods ///
	
	/**
	 * Description
	 * @param amount
	 *   
	 * @precondition
	 *   previous balance
	 * @postcondition / return
	 *   amount to be true or false (deposited amount)
	 * @exception
	 *   amount cannot be deposited if amount is <= 0
	 * @note
	 *   deposits amount if amount is > 0
	 **/
	public boolean deposit(double amount)
	{
		if(amount <= 0)
		{
        		JOptionPane.showMessageDialog(null,("Error: Deposit amount is invalid."
				+ "\nCustomer:" + lastName
				+ "\nRequested:" + amount
				+ "where "  + lastName + "equals the customer's name"
				+ "\nwhere"amount + "equals the amount"), "ES&L Banking System", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else
		{
         		JOptionPane.showMessageDialog(null, "Balance after deposit " + (balance += amount), "ES&L Banking System", JOptionPane.INFORMATION_MESSAGE);
			return true;
		}	
	}//End deposit(Long amount) Method
	
	/**
	 * Description
	 * @param amount
	 *   
	 * @precondition
	 *   previous balance
	 * @postcondition / return
	 *   amount value to be true or false (withdrawed amount)
	 * @exception
	 *   cannot withdraw amount if amount is < 0 or amount is > balance
	 * @note
	 *   withdraws amount of amount is > 0 and amount is < 0
	 **/
	public boolean withdraw(double amount)
	{
		if(amount < 0)
		{
        		JOptionPane.showMessageDialog(null,("Error: Withdraw amount is invalid."
				+ "\nCustomer:" + lastName
				+ "\nRequested:" + amount
				+ "\nwhere " + lastName + "equals the customer's name\n\t"
				+ amount + "equals the amount"), "ES&L Banking System", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else if(amount > balance)
		{
        		JOptionPane.showMessageDialog(null,("Error: Insufficient funds"
				+ "\nCustomer:" + lastName
				+ "\nRequested:" + amount
				+ "\nAvailable: " + balance
				+ "where " + lastName + "equals the customer's name\n\t"
				+ amount + "equals the amount"
				+ balance + "equals the balance in the account"), "ES&L Banking System", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else
		{
         		JOptionPane.showMessageDialog(null,  "Balance after withdrawal " + (balance -= amount + TRANSCATION_FEE), "ES&L Banking System", JOptionPane.INFORMATION_MESSAGE);
			return true;
		}
	}//End withdraw(Long amount) Method
	
	/**
	 * Description
	 * @param rate
	 *   
	 * @precondition
	 *   acctBalance without interest rate
	 * @postcondition / return
	 *   
	 * @exception
	 *   
	 * @note
	 *   sets the acctBalance (with interest rate)
	 **/
	public void addInterest(double rate)
	{
		acctBalance = acctBalance + (acctBalance * rate);
		
	}//End addInterest() Method
	
	
	/// Comparable Interface and Equals Method ///
	
	/**
	 * A method that returns an integer comparison based on the lexicographical order of the customers last names.
	 * The comparison is based on the Unicode value of each character in a customer's last name. 
	 * @param inputCustomer
	 *   The Customer object to compare to other Customer objects.
	 * @return
	 *   Returns a negative integer if inputCustomer's last name precedes this Customer's lastName lexicographically.
	 *   Returns a positive integer if inputCustomer's last name follows this Customer's lastName lexicographically.
	 *   Returns zero if inputCustomer's last name is the same as this Customer's lastName.
	 * @note
	 *   To ensure a pure alphabetical ordering, rather than a lexicographical ordering based on each character's Unicode value, 
	 *   each customer's last name should be created in the following format: 
	 *   The first letter should be upper case, while all other letters should be lower case, with no numbers or symbols.
	 **/
	public int compareTo(Customer inputCustomer)
	{
		int comparison;
		
		comparison = lastName.compareTo(inputCustomer.getName());
		
		return comparison;
		
	}//End compareTo(Customer inputCustomer) Method
	
	/**
	 * Description
	 * @param
	 *   
	 * @precondition
	 *   custNumber must be unique
	 * @postcondition / return
	 *   
	 * @exception
	 *   
	 * @note
	 *   
	 **/
	public boolean equals(Object obj)
	{
		//Instance Variables
		Customer candidate;
		boolean isEqual = false;
		
		//Verify (1) That obj is of the Customer data type.
		if (obj instanceof Customer){
			candidate = (Customer) obj;
			//Verify (2) That candidate has the same lastName and custNumber as the invoked DoubleArraySeq.
			if (this.lastName.equals(candidate.lastName) && this.custNumber.equals(candidate.custNumber)){
				isEqual = true;
			}//end if
		}//end if
		return isEqual;
		
	}//end equals(Object obj) Method
	
	
	/// Driver Methods ///
	
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
	private static void addNewCustomer()
	{
		/// Verify that the customer number is unique!!!! ///
		
	}//End addNewCustomer()v Method
	
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
	private static void deleteCustomer(Customer[] database, Customer newCust)
	{
		for (int i = 0; i < database.length; i++){
			if (newCust.equals(database[i])){
				database[i] = null;
				//should there be a customer count we modify somewhere?
			}//end if
		}//end for
		
	}//End deleteCustomer() Method
	
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
	private static void nameSort(Customer[] customerDatabase, int first, int manyItems)
	{
		//Instance Variables
		int big;
		Customer temp;
		
		//Run a selection sort on customerDatabase
		for (int i = manyItems - 1; i > 0; i--){
			//Calculate big as the index of the largest value in customerDatabase[first] through customerDatabase[manyItems]
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
		
	}//End nameSort(Customer[] customerDatabase, int first, int manyItems) Method
	
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
	public static String databaseToString(Customer[] customerDatabase)
	{
		//Instance Variables
		String databaseString = "Customer Name          Account ID          Phone Number          Account Balance          \n\n";
		
		//Sort the customer database alphabetically by lastName
		nameSort(customerDatabase, 0, 30);
		
		//Add all customer accounts in the database to the databaseString
		for (int i = 0; i < customerDatabase.length; i++){
			if (customerDatabase[i] instanceof Customer){
				databaseString = databaseString 
						+ customerDatabase[i].getName() + "                    "
						+ customerDatabase[i].getCustNumber() + "                    "
						+ customerDatabase[i].getPhoneNumber() + "                    "
						+ customerDatabase[i].getAcctBalance() + "                    \n";
			}
		}//end for
		
		return databaseString;
		
	}//End databaseToString(Customer[] customerDatabase) Method
	
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
	private static void findIndex()
	{
		
		
	}//End findIndex() Method
	
}//End Customer Class
