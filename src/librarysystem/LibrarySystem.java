/*
 * LibrarySystem LibrarySystem class
 * Developed by: Callum Young
 * B-number: B00834218
 * Created: 15/03/2022
 */
package librarysystem;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class LibrarySystem {

    // Declares the library object arrays
    private static User[] users = new User[0];
    private static Item[] items = new Item[0];
    private static Loan[] loans = new Loan[0];

    // Declares the scanners
    private static Scanner input = new Scanner(System.in);
    private static Scanner fileScan;

    // Declares the file writer
    private static PrintWriter writer;

    // Sets the paths to the storage files
    private static String usersFilePath = "USERS.csv";
    private static String itemsFilePath = "ITEMS.csv";
    private static String loansFilePath = "LOANS.csv";

    // Adds a new User to the users array
    private static void appendToArray(User newUser) {

    }

    // Adds a new Item to the items array
    private static void appendToArray(Item newItem) {

    }

    // Adds a new Loan to the loans array
    private static void appendToArray(Loan newLoan) {

    }

    // Opens the file scanner to the provide path
    private static void setReadingFile(String path)
            throws FileNotFoundException {

    }

    // Adds all the users in file to the users array
    private static void readUsers() throws FileNotFoundException {

    }

    // Adds all the items in file to the items array
    private static void readItems() throws FileNotFoundException {

    }

    // Adds all the loans in file to the loans array
    private static void readLoans() throws FileNotFoundException {

    }

    // Stores all the information in the files in the arrays
    private static void readFiles() {

    }

    // Gets the item from the items array
    private static Item getItem(String barcode) {
        return items[0];
    }

    // Gets the user from the users array
    private static User getUser(String userID) {
        return users[0];
    }

    // Gets the loan from the loans array
    private static Loan getLoan(String barcode) {
        return loans[0];
    }

    // Checks if the loan is in the loans array
    private static boolean hasLoan(String barcode) {
        return false;
    }

    // Removes the loan from the loans array
    private static void removeLoan(String barcode) {

    }

    // Creates and adds a new loan object to the loans array
    private static void issueItem() {

    }

    // Gets and renews a loan
    private static void renewItem() {

    }

    // Gets and removes a loan from the loans array
    private static void returnItem() {

    }

    // Displays info about all the loans
    private static void viewLoans() {

    }

    // Displays info about all the items
    private static void viewItems() {

    }

    // Runs the method for the function the user wants to run
    private static void runOperation() {

    }

    // Opens the file to write to, creating it if it doesn't exist
    private static void openWriter(String path, boolean create)
            throws SecurityException {

    }

    // Writes all the loans to the opened file
    private static void storeLoans() {

    }

    // Opens a file and writes all the loans out to the file
    private static void writeLoans() {

    }

    // Runs when the class is compiled
    public static void main(String[] args) {
        // TODO code application logic here
    }

}
