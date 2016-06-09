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
 * @version 0, 06/08/16
 ********************************************************************************************************/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;

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
				case 6: //6. Quit and display accounts
					
					break;
			}//end switch
		}//end while
		
		//Exit Message
		
		
	}//End Main Method
	
	
	/// Additional Methods Used by Main Method ///
	
	/**
	 * This method allows the user to select the text file containing the names and keys to be used. 
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
		File tableFile; //This is the text file where all the names and keys are stored
		JFileChooser fileSelector; //A file selector UI
		
		//Launch a JFileChooser window to select the file to be used
		fileSelector = new JFileChooser();
		int status = fileSelector.showOpenDialog(null);
		
		//Once a file has been selected, return that file
		if (status == JFileChooser.APPROVE_OPTION){
			tableFile = fileSelector.getSelectedFile();
			System.out.println("You have selected the file located at " + tableFile.toString());
			return tableFile;
		}
		//If no file is selected, give the user a second chance to select a file or close the program
		else if (status == JFileChooser.CANCEL_OPTION){
			System.out.println("You must select a file to continue...");
			System.out.println("You may click 'Cancel' again to close the program.");
			//Launch the JFileChooser window to select the file to be used
			status = fileSelector.showOpenDialog(null);
			//Once a file has been selected, return that file
			if (status == JFileChooser.APPROVE_OPTION){
				tableFile = fileSelector.getSelectedFile();
				System.out.println("You have selected the file located at " + tableFile.toString());
				return tableFile;
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
		Customer[] customerDatabase = new Customer[30];
		String readCustomer; //A cursor for each line of text in the database text file
		Customer currentCustomer; //A cursor used to add customers and their account information to the customerDatabase
		
		//Scanner Instantiation
		Scanner fileScanner = new Scanner(database); //Used to read the database text file
		fileScanner.useDelimiter(System.getProperty("line.separator")); //Allows us to read the database text file one line at a time
		
		//String Tokenizer Instantiation
		StringTokenizer tokenizer; 
		String delimeters = " ,;:_\t"; //For each golfer (new line), each piece of information may be separated with any of these delimiters
		
		//Read the database text file one line at a time, construct each golfer, add each golfer's stats, then add the golfer to golferDatabase
		while(fileScanner.hasNextLine()){
			readCustomer = fileScanner.nextLine();
			tokenizer = new StringTokenizer(readCustomer, delimeters);
			//currentCustomer = new Customer(tokenizer.nextToken());
			//currentCustomer.setNumberOfRounds(Integer.parseInt(tokenizer.nextToken()));
			//currentCustomer.setHandicap(Integer.parseInt(tokenizer.nextToken()));
			//currentCustomer.setAverageScore(Double.parseDouble(tokenizer.nextToken()));
			//customerDatabase.add(currentCustomer);
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
		System.out.println("  6. Quit and display accounts");
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
