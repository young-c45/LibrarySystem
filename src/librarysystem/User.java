/*
 * LibrarySystem User class
 * Developed by: Patrick White
 * B-number: B00798718
 * Created: 15/03/2022
 */
package librarysystem;

public class User {

    // Declares the instance variables
    private String identification;
    private String firstname;
    private String lastname;
    private String email;

    // Creates the object
    public User(String newIdentification, String newFirstName,
            String newLastName, String newEmail) {
        // Initialises the variables to those passed to the constructor
        identification = newIdentification;
        firstname = newFirstName;
        lastname = newLastName;
        email = newEmail;
    }

    // Returns the user's ID
    public String getUserID() {
        return identification;
    }

}
