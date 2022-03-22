/*
 * LibrarySystem LibrarySystem class
 * Developed by: Callum Young
 * B-number: B00834218
 * Created: 15/03/2022
 */
package librarysystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
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
        // Clones users into a new array of 1 element larger size
        User[] newUsers = Arrays.copyOf(users, users.length + 1);
        // Adds the new user to the end of the new array
        newUsers[users.length] = newUser;
        // Sets users to the new users array
        users = newUsers;
    }

    // Adds a new Item to the items array
    private static void appendToArray(Item newItem) {
        // Clones items into a new array of 1 element larger size
        Item[] newItems = Arrays.copyOf(items, items.length + 1);
        // Adds the new item to the end of the new array
        newItems[items.length] = newItem;
        // Sets items to the new items array
        items = newItems;
    }

    // Adds a new Loan to the loans array
    private static void appendToArray(Loan newLoan) {
        // Clones loans into a new array of 1 element larger size
        Loan[] newLoans = Arrays.copyOf(loans, loans.length + 1);
        // Adds the new loan to the end of the new array
        newLoans[loans.length] = newLoan;
        // Sets loans to the new loans array
        loans = newLoans;
    }

    // Opens the file scanner to the provide path
    private static void setReadingFile(String path)
            throws FileNotFoundException {
        // Opens the file scanner to the path
        fileScan = new Scanner(new File(path));
        // Sets the seperation character
        fileScan.useDelimiter("[,\n\r]");
        // Goes to the next line
        fileScan.nextLine();
    }

    // Adds all the users in file to the users array
    private static void readUsers() throws FileNotFoundException, Exception {
        // Opens the users file to read
        setReadingFile(usersFilePath);

        // Loops through each line in the file
        while (fileScan.hasNextLine()) {
            // Creates a user from the line and adds it to the users array
            appendToArray(new User(fileScan.next(), fileScan.next(),
                    fileScan.next(), fileScan.next()));

            // Goes to the next line
            fileScan.nextLine();
        }

        // Closes the file
        fileScan.close();

        // Throws exception if array is still empty
        if (users.length < 1) {
            throw new Exception("No Users");
        }
    }

    // Adds all the items in file to the items array
    private static void readItems() throws FileNotFoundException, Exception {
        // Opens the items file to read
        setReadingFile(itemsFilePath);

        // Loops through each line in the file
        while (fileScan.hasNextLine()) {
            // Creates an item from the line and adds it to the items array
            appendToArray(new Item(fileScan.next(), fileScan.next(),
                    fileScan.next(), fileScan.next(), fileScan.next(),
                    fileScan.next()));

            // Goes to the next line
            fileScan.nextLine();
        }

        // Closes the file
        fileScan.close();

        // Throws exception if array is still empty
        if (items.length < 1) {
            throw new Exception("No Items");
        }
    }

    // Adds all the loans in file to the loans array
    private static void readLoans() throws FileNotFoundException, Exception {
        // Declares the local variables
        String itemBarcode = "", userID = "";
        Item item;
        User user;

        // Opens the items file to read
        setReadingFile(loansFilePath);

        // Loops through each line in the file
        while (fileScan.hasNextLine()) {
            // Trys to get the information
            try {
                // Gets the item's barcode
                itemBarcode = fileScan.next();
                // Runs if the item is already loaned
                if (hasLoan(itemBarcode)) {
                    // Tells the user the loan is skipped
                    System.out.println("Skipping duplicate loan of item "
                            + itemBarcode);
                    // Goes to the next line
                    fileScan.nextLine();
                    // Goes to the next iteration
                    continue;
                }
                // Gets the item
                item = getItem(itemBarcode);

                // Gets the user's ID
                userID = fileScan.next();
                // Gets the user
                user = getUser(userID);

                // Creates a loan from the line and adds it to the loans array
                appendToArray(new Loan(itemBarcode, userID, fileScan.next(),
                        fileScan.next(), fileScan.nextInt()));
            } // Handles exceptions
            catch (Exception e) {
                switch (e.getMessage()) {
                    // Runs if the item could not be found
                    case "No Item":
                        System.out.println("Could not find itme for barcode "
                                + itemBarcode);
                        break;
                    // Runs if the user could not be found
                    case "No User":
                        System.out.println("Could not find user for id "
                                + userID);
                        break;
                    // Runs for any other exception
                    default:
                        System.out.println("Encountered error while reading "
                                + "item");
                        break;
                }
            }

            // Goes to the next line
            fileScan.nextLine();
        }

        // Closes the file
        fileScan.close();

        // Throws exception if array is still empty
        if (loans.length < 1) {
            throw new Exception("No Loans");
        }
    }

    // Stores all the information in the files in the arrays
    private static void readFiles() {
        // Trys to get all the stored users
        try {
            readUsers();
        } // Runs if no file was found
        catch (FileNotFoundException e) {
            // Tells the user
            System.out.println("Could not find users file");
            // Ends to program
            System.exit(1);
        } // Runs for any other exception
        catch (Exception e) {
            // Tells the user
            System.out.println("No users found in file");
            // Ends to program
            System.exit(1);
        }

        // Trys to get all the stored items
        try {
            readItems();
        } // Runs if no file was found
        catch (FileNotFoundException e) {
            // Tells the user
            System.out.println("Could not find items file");
            // Ends to program
            System.exit(2);
        } // Runs for any other exception
        catch (Exception e) {
            // Tells the user
            System.out.println("No items found in file");
            // Ends to program
            System.exit(2);
        }

        // Trys to get all the stored loans
        try {
            readLoans();
        } // Runs if no file was found
        catch (FileNotFoundException e) {
            // Tells the user
            System.out.println("Could not find loans file");
        } // Runs for any other exception
        catch (Exception e) {
            // Tells the user
            System.out.println("No loans found in file");
        }
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
        // Reads in the files
        readFiles();
    }

}
