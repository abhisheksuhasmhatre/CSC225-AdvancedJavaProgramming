// File: Customer.java
// Due Date: Monday 6/20/16

/********************************************************************************************************
 * ES&L Bank Account Manager Program - CSC 225 Prog1
 * Course Title: Advanced Java Programming
 * Course Number: CSC 225-805
 * Instructors: Professors Christine Forde and Harry Payne
 * @author Anna (Ekeren?)
 * @author Rafael Ferrer
 * @author Abhishek (Mhatre?)
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
 * @author Abhishek (Mhatre?)
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
	public boolean deposit(double amount)
	{
		boolean depositSuccessful = false;
		
		
		
		return depositSuccessful;
		
	}//End deposit(Long amount) Method
	
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
	public boolean withdraw(double amount)
	{
		boolean withdrawSuccessful = false;
		
		
		
		return withdrawSuccessful;
		
	}//End withdraw(Long amount) Method
	
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
	public void addInterest()
	{
		
		
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
	private static void nameSort()
	{
		
		
	}//End nameSort() Method
	
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
