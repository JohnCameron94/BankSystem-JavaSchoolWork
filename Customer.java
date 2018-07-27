/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.text.DecimalFormat;
/**
 *
 * @author Normac
 */
public class Customer {
   private String firstName;
   private String lastName;
   private long phoneNum;
   private String emailAddress;
   public   DecimalFormat phoneNumber = new DecimalFormat("###-###-####");
   
    // Contructor for customer  Object
   Customer(String firstName, String lastName){
       this.lastName = lastName;
       this.firstName = firstName;
   
   }
   
   //getter for customer name
   public String getName(){
   
       return firstName + "  " + lastName ;
       
   }
   //getter for phone number from customer
   public long getPhoneNum(){
	   
   
       return this.phoneNum;
   }
   //setter for customer phone number
   public void setPhoneNum(long phoneNum){
       this.phoneNum = phoneNum;
   }
   // getter email from customer 
   public String getEmailAddress(){
       return this.emailAddress;
   
   }
   //set customer email 
   public void setEmailAddress(String emailAddress){
       this.emailAddress = emailAddress;
   }
    
}
