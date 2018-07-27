/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Normac
 */
public class Account{
    private long accNumber;
    private Customer accHolder;
    private double balance; 
    
    
    
    public Account(long accNumber, Customer accHolder){
        this.accNumber = accNumber;
        this.accHolder = accHolder;
    }
    
    public void deposit(double deposit){
    
        this.balance +=deposit;
    }
    
    
    public double withdraw(double amount){
    
        if(amount > this.balance){
        System.out.println("Insuffisent funds");
        return 0;
        }else{
            this.balance-=amount;
        }
        return balance;
    }

    
    public Customer getAccHolder(){
    
        return accHolder;
    
    }
 
    public long getAccNumber(){
        return accNumber;
    }
    public void setBalance(double balance){
        this.balance = balance;
    
    }
    public double getBalance(){
        return balance;
    
    }
    
}
