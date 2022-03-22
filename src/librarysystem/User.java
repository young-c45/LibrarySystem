/*
 * LibrarySystem User class
 * Developed by: Patrick White
 * B-number: B00798718
 * Created: 15/03/2022
 */

package librarysystem;

public class User {

   private String identification ;
   private String firstname ;
   private String lastname ;
   private String email ;
   
   public User(String newIdentification, String newFirstName, String newLastName, String newEmail){
       identification = newIdentification;
       firstname = newFirstName;
       lastname = newLastName;
       email = newEmail;     
   }
    
   public String getUserID(){
       return identification;
   }
   
}
