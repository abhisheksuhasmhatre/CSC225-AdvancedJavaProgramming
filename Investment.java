// File: Investment.java
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
 * Description: FutureInvestment2 Investment Data Type Class - CSC 225 Project 2
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 ********************************************************************************************************/




public class Investment{
	
	// Invariant of the Investment class:
	//   1. 
	//   2. 
	//   3. 
	//   4. 
	
	//Private Instance Variables
	private double investmentAmount;
	private double yearsInvested;
	private double annualInterestRate;
	private double monthlyInterestRate;
	
	
	
	
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
	public Investment(){
	
		investmentAmount = 0;
		yearsInvested = 0;
		annualInterestRate = 0;
		monthlyInterestRate = annualInterestRate / 12;
		
	}//End FutureInvestmentValue() Method
	
	
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
	public Investment(double amount, double years, double annualInterestPercent){
		
		investmentAmount = amount;
		yearsInvested = years;
		annualInterestRate = annualInterestPercent / 100;
		monthlyInterestRate = annualInterestRate / 12;
		
	}//End FutureInvestmentValue(double InvestmentAmount, int Years, double AnnualInterestRate) Method
	
	
	
	
	/// Accessor Methods for Private Instance Variables ///
	
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
	public void setInvestmentAmount(double amount){
		
		investmentAmount = amount;
		
	}//End setInvestmentAmount(double InvestmentAmount) Method
	
	
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
	public void setYearsInvested(double years){
		
		yearsInvested = years;
		
	}//End setYears(int Years) Method
	
	
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
	public void setAnnualInterestRate(double annualInterestPercent){
	
		annualInterestRate = annualInterestPercent / 100;
		
	}//End setAnnualInterestRate(double AnnualInterestRate) Method
	
	
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
	public void setMonthlyInterestRate(double monthlyInterestPercent){
		
		annualInterestRate = monthlyInterestPercent / 100;
		
	}//End setAnnualInterestRate(double AnnualInterestRate) Method
	
	
	
	
	/// Mutator Methods for Private Instance Variables ///
	
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
	public double getInvestmentAmount(){
		
		return investmentAmount;
		
	}//End setInvestmentAmount(double InvestmentAmount) Method
	
	
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
	public double getYearsInvested(){
		
		return yearsInvested;
		
	}//End setYears(int Years) Method
	
	
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
	public double getAnnualInterestRate(){
	
		return annualInterestRate;
		
	}//End setAnnualInterestRate(double AnnualInterestRate) Method
	
	
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
	public double getMonthlyInterestRate(){
		
		return monthlyInterestRate;
		
	}//End setAnnualInterestRate(double AnnualInterestRate) Method
	
	
	
	
	/// Investment Calculation Methods ///
	
	public double caluclateFutureValue(){
		
		return investmentAmount * (Math.pow((1 + monthlyInterestRate), (yearsInvested * 12)));
		
	}//End caluclateFutureValue() Method

	
}//End FutureInvestment2 Class

