/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Normac
 */
public class CheckingAccount extends Bank {
    private double monthlyFee = 0.35;
    protected String accountType= "Checking Account";
    
    
   CheckingAccount(long accNumber, Customer accHolder,double balance,char accType){
       super(accNumber,accHolder,balance,accType);
       
   }
    
    //calculate monthly fee
    public double calculateAndUpdateBalance(){
       
        return balance-= monthlyFee;
    
    }
    
    
}
