/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Normac
 */
public class SavingsAccount extends Bank {
   
    double interestRate = 0.05;
    protected String accountType = "Savings Account";
    
 
    public SavingsAccount(long accNumber,Customer accHolder,double balance,char accType){
        super(accNumber, accHolder,balance,accType);
        
    }
    //calculating monthly interest rate
    
    public double calculateAndUpdateBalance(){
        double monthlyInterestRate = interestRate * balance;
         return  balance   += monthlyInterestRate;
        
    }
    
}
