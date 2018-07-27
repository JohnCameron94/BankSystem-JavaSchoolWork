/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jon_cameron
 */
public class LineOfCredit extends Bank {
       private double monthlyAmount = 0.5;
       private double creditAmount;
       protected String accountType = "Line Of Credit";
      
       LineOfCredit(long accNumber,Customer accHolder,double balance,char accType){
       
        super(accNumber,accHolder,balance,accType);
        
        
        
       }
 
    // money credit amount
    public double moneyCreditAmount(double amount){
        
        this.creditAmount += super.balance;
        
        return balance;
    
    }
    
    
       // calculate and update balance  monthly payements
    public double calculateAndUpdateBalance(){
  
        double amount;
           amount = monthlyAmount *= balance;
        amount -= balance;
        
        return balance;
    }
    
}
