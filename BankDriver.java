// File: BankDriver.java
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
 * @author Abhishek (Mhatre?)
 * @version 0, 06/06/16
 ********************************************************************************************************/


import java.util.Scanner;

public class BankDriver
{
	/// Main Method of BankDriver ///
	
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
	public static void main(String[] args)
	{
		//Instance Variables
		Customer[] customerDatabase;
		Customer currentCustomer;
		int choice = -1; //The user's menu selection number
		Scanner keyboard = new Scanner (System.in); //Scanner used to obtain user input from the keyboard
		
		//Startup Message
		
		
		//Display the Main Menu for the user to manage the database
		while (choice != 6){
			
			//Obtain user's Main Menu selection
			choice = mainMenuInput(keyboard);
			
			//Main Menu Switch Board
			switch(choice){
				case 1: //1. Deposit sum to account
					
					break;
				case 2: //2. Withdraw sum from account
					
					break;
				case 3: //3. Create account
					
					break;
				case 4: //4. View all accounts
					
					break;
				case 5: //5. Delete an account
					
					break;
				case 6: //6. Quit
					
					break;
			}//end switch
		}//end while
		
		//Exit Message
		
		
	}//End Main Method
	
	
	/// Additional Methods Used by Main Method ///
	
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
	private static void deleteCustomer()
	{
		
		
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
	private static int mainMenuInput(Scanner keyboard)
	{
		//Instance Variables
		int input = -1; //The user's menu selection number
		
		//Display the Main Menu
		System.out.println("\n\nMain Menu: ");
		System.out.println("  1. Deposit sum to account");
		System.out.println("  2. Withdraw sum from account");
		System.out.println("  3. Create account");
		System.out.println("  4. View all accounts");
		System.out.println("  5. Delete an account");
		System.out.println("  6. Quit");
		System.out.print("\nPlease enter your selection: ");
		
		//Obtain the user's Main Menu selection and catch illegal arguments
		try {
			input = keyboard.nextInt();
			if (input < 1 || input > 7){
				System.out.println("\nError! You must enter a choice between 1 and 6.");
			}
		}//end try
		catch (Exception e){
			System.out.println("\nError! You must enter an integer. Decimal numbers, letters, and characters are not allowed.");
			keyboard.next();
		}
		
		return input;
		
	}//End mainMenuInput(Scanner keyboard) Method
	
	
}//End BankDriver Class
