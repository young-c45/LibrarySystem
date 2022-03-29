/*
 * LibrarySystem Loan class
 * Developed by: Patrick White
 * B-number: B00798718
 * Created: 15/03/2022
 */

package librarysystem;

public class Loan {
    
 private Item item ;
 private User user ;
 private String issueDate ;
 private String dueDate ;
 private int numberOfRenewals ;
 
  public Loan(Item newItem, User newUser, String newIssueDate, String newDueDate, int newNumberOfRenewals){
      item = newItem;
      user = newUser;
      issueDate = newIssueDate;
      dueDate = newDueDate;
      numberOfRenewals = newNumberOfRenewals;
  }
   public String getUserID(){
       return user.getUserID();
   }
   public String getReturnDate(){
       return dueDate;
   }
   public String getBarcode(){
       return item.getBarcode();
   
   }
   
    public void displayItem(){
      System.out.println("Displayed Item");
    }
    
     public void displayUser(){
      System.out.println("Displayed User");
     }
   
}
