// File: InvestmentDriver.java
// Due Date: Monday 7/4/16

/********************************************************************************************************
 * FutureInvestment2 Program - CSC 225 Program 2
 * Course Title: Advanced Java Programming
 * Course Number: CSC 225-805
 * Instructors: Professors Christine Forde and Harry Payne
 * @author Anna Ekeren
 * @author Rafael Ferrer
 * @author Abhishek Mhatre
 * @version 1.0, 06/22/16
 * 
 * Description: FutureInvestment2 Investment GUI Driver - CSC 225 Project 2
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 ********************************************************************************************************/




public class InvestmentDriver{
	
	public static void main(String[] args){
		
		Investment testInvest = new Investment(10000, 1, 6.75);
		Investment testInvest2 = new Investment(10000, 2, 5.5);
		
		System.out.println(testInvest.getMonthlyInterestRate());
		System.out.println(testInvest.caluclateFutureValue());
		System.out.println(testInvest2.getMonthlyInterestRate());
		System.out.println(testInvest2.caluclateFutureValue());
		
	}//End Main Method
	
	
	
	
	
}
