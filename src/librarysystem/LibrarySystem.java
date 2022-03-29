/*
 * LibrarySystem LibrarySystem class
 * Developed by: Callum Young
 * B-number: B00834218
 * Created: 15/03/2022
 */
package librarysystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    private static String loansBackupPath = "LOANS_BACKUP.csv";

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
                            + itemBarcode + ".");
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
                appendToArray(new Loan(item, user, fileScan.next(),
                        fileScan.next(), fileScan.nextInt()));
            } // Handles exceptions
            catch (Exception e) {
                switch (e.getMessage()) {
                    // Runs if the item could not be found
                    case "No Item":
                        System.out.println("Could not find item for barcode "
                                + itemBarcode + ".");
                        break;
                    // Runs if the user could not be found
                    case "No User":
                        System.out.println("Could not find user for id "
                                + userID + ".");
                        break;
                    // Runs for any other exception
                    default:
                        System.out.println("Encountered error while reading "
                                + "item.");
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
            System.out.println("ERROR - Could not find users file, "
                    + "ending program.");
            // Ends to program
            System.exit(1);
        } // Runs for any other exception
        catch (Exception e) {
            // Tells the user
            System.out.println("ERROR - No users found in file, "
                    + "ending program.");
            // Ends to program
            System.exit(1);
        }

        // Trys to get all the stored items
        try {
            readItems();
        } // Runs if no file was found
        catch (FileNotFoundException e) {
            // Tells the user
            System.out.println("ERROR - Could not find items file, "
                    + "ending program");
            // Ends to program
            System.exit(2);
        } // Runs for any other exception
        catch (Exception e) {
            // Tells the user
            System.out.println("ERROR - No items found in file, "
                    + "ending program.");
            // Ends to program
            System.exit(2);
        }

        // Trys to get all the stored loans
        try {
            readLoans();
        } // Runs if no file was found
        catch (FileNotFoundException e) {
            // Tells the user
            System.out.println("Could not find loans file.");
        } // Runs for any other exception
        catch (Exception e) {
            // Tells the user
            System.out.println("No loans found in file.");
        }
    }

    // Gets the item from the items array
    private static Item getItem(String barcode) throws Exception {
        // Runs for each item in items
        for (Item item : items) {
            // Returns the item if it has the provided barcode
            if (item.getBarcode().equals(barcode)) {
                return item;
            }
        }
        // Throws exception if no item is returned
        throw new Exception("No Item");
    }

    // Gets the user from the users array
    private static User getUser(String userID) throws Exception {
        // Runs for each user in users
        for (User user : users) {
            // Returns user if it has the provided id
            if (user.getUserID().equals(userID)) {
                return user;
            }
        }
        // Throws exception if no user is returned
        throw new Exception("No User");
    }

    // Gets the loan from the loans array
    private static Loan getLoan(String barcode) throws Exception {
        // Runs for each loan in loans
        for (Loan loan : loans) {
            // Returns the loan if the item barcode is the provided barcode
            if (loan.getBarcode().equals(barcode)) {
                return loan;
            }
        }
        // Throws exception if no loan is returned
        throw new Exception("No Loan");
    }

    // Checks if the loan is in the loans array
    private static boolean hasLoan(String barcode) {
        // Runs for each loan in loans
        for (Loan loan : loans) {
            // Returns true if the item barcode is the provided barcode
            if (loan.getBarcode().equals(barcode)) {
                return true;
            }
        }
        // Returns false if no loan is found
        return false;
    }

    // Removes the loan from the loans array
    private static void removeLoan(String barcode) throws Exception {
        // Creates a new loans array with 1 less element than loans
        Loan[] newLoans = new Loan[loans.length - 1];
        // Declares the variable to store the index for newLoans
        int newLoansIndex = 0;

        // Runs for each loan in loans
        for (int i = 0; i < loans.length; i++) {
            // Runs if the loan item's barcode is the provided barcode
            if (loans[i].getBarcode().equals(barcode)) {
                // Runs for each loan in loans
                for (int j = 0; j < loans.length; j++) {
                    // Runs if j is not the index of the removed loan (i)
                    if (j != i) {
                        // Stores the loan in the new loans array
                        newLoans[newLoansIndex++] = loans[j];
                    }
                }

                // Stores the new loans array in loans
                loans = newLoans;

                // Tells the user the loan was removed
                System.out.println("Successfully returned item "
                        + barcode + ".");

                // Exits the method
                return;
            }
        }
        // Throws exception if no loan is found
        throw new Exception("No Loan");
    }

    // Creates and adds a new loan object to the loans array
    private static void issueItem() {
        // Declares the local variables
        String itemBarcode = "", userID = "";
        Item item;
        User user;
        Loan loan;

        // Trys to get the information
        try {
            // Asks the user to input the loaned item's barcode
            System.out.println("Please enter the barcode of the item to be "
                    + "loaned...");
            // Gets the item's barcode
            itemBarcode = input.nextLine();
            // Runs if the item is already loaned
            if (hasLoan(itemBarcode)) {
                // Tells the user the loan is skipped
                System.out.println("Item " + itemBarcode
                        + " is already on loan.");
                // Exits the method
                return;
            }
            // Gets the item
            item = getItem(itemBarcode);

            // Asks the user to input the id of the person borrowing the item
            System.out.println("Please enter the ID of the person borrowing "
                    + "the item...");
            // Gets the user's ID
            userID = input.nextLine();
            // Gets the user
            user = getUser(userID);

            // Creates the loan from the inputs
            // TODO change to only passing item and user when constructor is
            //      implemented
            loan = new Loan(item, user, "", "", 0);
            // Adds the loan to the loans array
            appendToArray(loan);

            // Tells the user the loan was issued
            System.out.println("Successfully issued loan:");
            // Outputs the information of the new loan
            // TODO change to displayInfo method when implemented
            loan.displayItem();
        } // Handles exceptions
        catch (Exception e) {
            switch (e.getMessage()) {
                // Runs if the item could not be found
                case "No Item":
                    System.out.println("Could not find item for barcode "
                            + itemBarcode + ".");
                    break;
                // Runs if the user could not be found
                case "No User":
                    System.out.println("Could not find user for id "
                            + userID + ".");
                    break;
                // Runs for any other exception
                default:
                    System.out.println("Encountered error while issuing loan.");
                    break;
            }
        }
    }

    // Gets and renews a loan
    private static void renewItem() {
        // Declares the local variables
        String itemBarcode = "";
        Loan loan;

        // Trys to get and renew the item
        try {
            // Asks the user to input the loaned item's barcode
            System.out.println("Please enter the barcode of the item "
                    + "associated with the loan to be renewed...");
            // Gets the item's barcode
            itemBarcode = input.nextLine();
            // Gets the loan for the barcode
            loan = getLoan(itemBarcode);

            // Renews the loan
            // TODO uncomment when renew() method is implemented
            //loan.renew();
            // Tells the user the loan was updated
            System.out.println("Successfully renewed loan:");
            // Outputs updated information about the loan
            // TODO change to displayInfo() when method is implemented
            loan.displayItem();
        } // Handles exceptions
        catch (Exception e) {
            switch (e.getMessage()) {
                // Runs if the loan cannot be found
                case "No Loan":
                    System.out.println("Item " + itemBarcode
                            + " is not on loan.");
                    break;
                // Runs if the item cannot be renewed more
                case "Max Renewals Reached":
                    System.out.println("This loan has already been renewed the "
                            + "maximum number of times, so cannot be renewed "
                            + "again.");
                // Runs for any other exception
                default:
                    System.out.println("Encountered error while "
                            + "renewing loan.");
                    break;
            }
        }
    }

    // Gets and removes a loan from the loans array
    private static void returnItem() {
        // Declares the local variables
        String itemBarcode = "";

        // Trys to get and return the item
        try {
            // Asks the user to input the loaned item's barcode
            System.out.println("Please enter the barcode of the item "
                    + "to be returned...");
            // Gets the loan item's barcode
            itemBarcode = input.nextLine();
            // Removes the loan from the items array
            removeLoan(itemBarcode);
            // Tells the user the loan was removed
            System.out.println("Successfully returned item " + itemBarcode);
        } // Handles exceptions
        catch (Exception e) {
            // Tells the user the loan couldn't be found
            System.out.println("Item " + itemBarcode + " is not on loan.");
        }
    }

    // Displays info about all the loans
    private static void viewLoans() {
        // Runs if there are no loans
        if (loans.length < 1) {
            // Tells the user there are no loans on file
            System.out.println("No items are currently on loan.");
            // Exits the method
            return;
        }

        // Tells the user how many loans are on file
        System.out.println("There are " + loans.length + " loans on file:");
        // Runs for each loan
        for (Loan loan : loans) {
            // Trys to wait for half a second for better ux
            try {
                Thread.sleep(500);
            } // Handles interupts
            catch (InterruptedException e) {
                // Tells the user the sleep was interrupted
                System.out.println("Continuing...");
            } finally {
                // Displays info about the loan
                // TODO change to displayInfo() when method is implemented
                loan.displayItem();
            }
        }
    }

    // Displays info about all the items
    private static void viewItems() {
        // Tells the user how many items are on file
        System.out.println("There are " + items.length + " items on file:");
        // Runs for each item
        for (Item item : items) {
            // Trys to wait for half a second for better ux
            try {
                Thread.sleep(500);
            } // Handles interupts
            catch (InterruptedException e) {
                // Tells the user the sleep was interrupted
                System.out.println("Continuing...");
            } finally {
                // Displays info about the item
                item.displayItem();
            }
        }
    }

    // Runs the method for the function the user wants to run
    private static boolean runOperation() {
        // Declares the local variables
        String response;

        // Tells the user of the options
        System.out.println("\nWelcome to the Library System! This program can:"
                + "\n\t1. Issue a new loan of an item to a user"
                + "\n\t2. Renew the current loan of an item"
                + "\n\t3. Return an item"
                + "\n\t4. View information about all the currently active loans"
                + "\n\t5. View information about all the items in the library"
                + "\n\t6. Exit the program"
                + "\nPlease enter the number of the function you wish to "
                + "perform...");
        // Gets the option the user wants to run
        response = input.nextLine();

        // Outputs an empty line for better formating
        System.out.println();

        // Runs code depending on the response, taking only the first character
        switch (response.substring(0, 1)) {
            // Runs for option 1
            case "1":
                issueItem();
                break;
            // Runs for option 2
            case "2":
                renewItem();
                break;
            // Runs for option 3
            case "3":
                returnItem();
                break;
            // Runs for option 4
            case "4":
                viewLoans();
                break;
            // Runs for option 5
            case "5":
                viewItems();
                break;
            // Runs for option 6
            case "6":
                // Returns false to not run again
                return false;
            // Runs for any other input
            default:
                // Tells the user the input is invalid
                System.out.println("Invalid input, please only enter the number"
                        + " of the function you wish to perform.");
        }

        // Trys to wait 1 second for better ux
        try {
            Thread.sleep(1000);
        } // Handles interupts
        catch (InterruptedException e) {
            // Tells the user the sleep was interrupted
            System.out.println("Continuing...");
        } finally {
            // Returns true to run again
            return true;
        }
    }

    // Opens the file to write to, creating it if it doesn't exist
    private static void openWriter(String path)
            throws SecurityException, IOException {
        // Opens the file at path
        File outputFile = new File(path);
        // Creates the file if it doesn't exist
        outputFile.createNewFile();
        // Opens the writer to the file
        writer = new PrintWriter(outputFile);
    }

    // Writes all the loans to the opened file
    private static void storeLoans() {
        // Writers the field descriptions to the file
        writer.println("Barcode,User_id,Issue_Date,Due_Date,numRenews");
        // Runs for each loan in the loans array
        for (Loan loan : loans) {
            // Writes the loan info to the file
            writer.println(loan);
        }
    }

    // Opens a file and writes all the loans out to the file
    private static void writeLoans(String path) {
        // Sets the backup path
        String backupPath = System.getProperty("user.home")
                + System.currentTimeMillis() + loansBackupPath;

        // Trys to open and write to the loans file
        try {
            // Open the writer to the loans file
            openWriter(path);
            // Writes the loans to the file
            storeLoans();
            // Closes the writer
            writer.close();
        } // Catches I/O error
        catch (IOException e) {
            // Tells the user there was an error
            System.out.println("There was an error in opening " + path
                    + ", saving to backup file.");
            // Trys to wait 1 second for the error to pass
            try {
                Thread.sleep(1000);
            } // Handles interupts
            catch (InterruptedException ex) {
                // Tells the user the sleep was interrupted
                System.out.println("Continuing...");
            } finally {
                // Saves loans to backup file
                writeLoans(backupPath);
                // Tells the user the path of the backup file
                System.out.println("Backup saved to '" + backupPath + "'.\n");
            }
        } // Catches denied wright access
        catch (SecurityException e) {
            // Tells the user there was an error
            System.out.println("Wright access denied to " + path
                    + ", saving to backup file.\n");
            // Saves loans to backup file
            writeLoans(backupPath);
            // Tells the user the path of the backup file
            System.out.println("Backup saved to '" + backupPath + "'.\n");
        }
    }

    // Runs when the class is compiled
    public static void main(String[] args) {
        // Declares the local variables
        boolean running;

        // Reads in the files
        readFiles();

        // Runs until runOperation() returns false
        do {
            // Runs runOperation and stores the returned value
            running = runOperation();
        } while (running);

        // Saves the loans array to a file
        // TODO remove "../" when finished testing
        writeLoans("../" + loansFilePath);

        // Tells the user the program is exiting
        System.out.println("Thank you for using the Library System, "
                + "goodbye!");
    }

}
