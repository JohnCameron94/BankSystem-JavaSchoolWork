/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Johnathon Cameron
 */
public abstract class Bank {

    public long accNumber;
    public Customer accHolder;
    double balance; 
    char accType;
   

       
    // account numbbr and customer constructor
     public Bank(long accNumber, Customer accHolder,double balance,char accType){
        this.accNumber = accNumber;
        this.accHolder = accHolder;
        this.balance = balance;
        this.accType = accType;
        
     }
    
     // getting account balance
    public double getBalance(){
        return balance;
    }
  
    public char getAccountType(){
        return accType;
    }
    
   
 
    
    public void deposit(double deposit){
    
        this.balance +=deposit;
    }
    
    //method for customer to withdraw money from account
    public double withdraw(double amount){
    
        if(amount > this.balance){
        System.out.println("Insuffisent funds");
        return 0;
        }else{
            this.balance-=amount;
        }
        return balance;
    }
    // get Acc holder 
    public Customer getAccHolder(){
    
        return accHolder;
    
    }
    // get Account number
    public long getAccNumber(){
        return accNumber;
    }
    //setter for account balance.
    public void setBalance(double balance){
        this.balance = balance;
    
    }
    public void transaction(Bank accounts,double amount) {
    		
    		accounts.withdraw(amount);
    		this.deposit(amount);
    	
    }
 
    
}
    

