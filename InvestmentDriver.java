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

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class InvestmentDriver{
	
	public static void main(String[] args){
		
		TextField investmentAmountTF = new TextField();
		TextField yearsInvestedTF = new TextField();
		TextField annualInterestRateTF = new TextField();
		TextField futureValue = new TextField();
		
		GridPane gridPane = new GridPane();
		
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.add(new Label("Investment Amount"), 0, 0);
		gridPane.add(investmentAmountTF, 1, 0);
		gridPane.add(new Label("Years"), 0, 1);
		gridPane.add(yearsInvestedTF, 1, 1);
		gridPane.add(new Label("Annual Interest Rate"), 0, 2);
		gridPane.add(annualInterestRateTF, 1, 2);
		gridPane.add(new Label("Future Value"), 0, 3);
		gridPane.add(futureValue, 1, 3);
		
		
		
	}//End Main Method
	
	
	
	
	
}
