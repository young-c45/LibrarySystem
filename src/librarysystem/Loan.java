/*
 * LibrarySystem Loan class
 * Developed by: Patrick White
 * B-number: B00798718
 * Created: 15/03/2022
 */
package librarysystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Loan {

    // Creates an object to convert between the date format used in the loans
    // file, and a java.util Date object
    protected static SimpleDateFormat fileDateFormatter
            = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);

    // Declares the instance variables
    private Item item;
    private User user;
    private Date issueDate;
    private Date dueDate;
    private int numberOfRenewals;
    private int maxRenewals;

    // Creates the object when all the loan information is passed
    // Throws ParseException if the dates cannot be converted to Date objects
    public Loan(Item newItem, User newUser, String newIssueDate,
            String newDueDate, int newNumberOfRenewals) throws ParseException {
        // Initialises the variables to those passed to the constructor
        item = newItem;
        user = newUser;
        // Uses the formatter to convert the strings to Date objects
        issueDate = fileDateFormatter.parse(newIssueDate);
        dueDate = fileDateFormatter.parse(newDueDate);
        numberOfRenewals = newNumberOfRenewals;
        // Sets the maximum number of renewals allowed based on the item type
        maxRenewals = item.getType().equals("Book") ? 3 : 2;
    }

    // Creates the object when only the item and user are passed
    public Loan(Item newItem, User newUser) {
        // Initialises the variables to those passed to the constructor
        item = newItem;
        user = newUser;

        // Sets the dates to the current date
        issueDate = new Date();
        dueDate = new Date();
        // Sets the return date to the correct time
        setReturnDate(false);

        // Initialises the number of renewals to 0
        numberOfRenewals = 0;
        // Sets the maximum number of renewals allowed based on the item type
        maxRenewals = item.getType().equals("Book") ? 3 : 2;
    }

    // Returns a date as a more readable string
    private String getReadableDate(Date date) {
        // Creates the object used to format the date string
        SimpleDateFormat readableDateFormatter
                = new SimpleDateFormat(" 'of' MMMM, yyyy", Locale.UK);

        // Gets a calender for time transformations
        Calendar calendar = Calendar.getInstance();
        // Sets the date for the calendar to the required date
        calendar.setTime(date);

        // Gets the number of the day for the required date
        int dayNum = calendar.get(Calendar.DAY_OF_MONTH);
        // Stores the number of the day as a string
        String dayString = dayNum + "";

        // Uses 'th' as the suffix for all teens (11-19)
        if (dayNum > 10 && dayNum < 20) {
            dayString += "th";
        } else {
            // Uses the correct suffix for the number based on the last digit
            switch (dayNum % 10) {
                case 1:
                    dayString += "st";
                    break;
                case 2:
                    dayString += "nd";
                    break;
                case 3:
                    dayString += "rd";
                    break;
                default:
                    dayString += "th";
            }
        }

        // Returns the date, using the day number string set above,
        // and formating the rest of the date using the formatter
        return dayString + readableDateFormatter.format(date);
    }

    // Returns the loaned item's barcode
    public String getBarcode() {
        return item.getBarcode();
    }

    // Outputs the loan's information to the standard output stream
    public void displayInfo() {
        System.out.println("\tLoaned Item Barcode: " + item.getBarcode());
        System.out.println("\tLoanee User ID: " + user.getUserID());
        System.out.println("\tIssue Date: " + getReadableDate(issueDate));
        System.out.println("\tDue Date: " + getReadableDate(dueDate));
        System.out.println("\tNumber of Renewals: " + numberOfRenewals);
    }

    // Renews the loan
    // Throws Exception if the loan has reached it's maximum number of renewals
    public void renew() throws Exception {
        // Throws exception if maximum number of renewals is reached
        if (numberOfRenewals >= maxRenewals) {
            throw new Exception("Max Renewals Reached");
        }

        // Updates the return date
        setReturnDate(true);
        // Adds 1 to the number of renewals
        numberOfRenewals++;
    }

    // Calculates and sets the return date
    private void setReturnDate(boolean renew) {
        // Declares the local variables
        int increaseAmount;

        // Sets the amount to increase the return date based on the item's type
        if (item.getType().equals("Book")) {
            // Reduces the increase if this is a renewal
            increaseAmount = renew ? 2 : 4;
        } else {
            increaseAmount = 1;
        }

        // Gets a calender for time transformations
        Calendar calendar = Calendar.getInstance();
        // Sets the date for the calendar to the due date
        calendar.setTime(dueDate);
        // Adds the needed number of weeks to the due date
        calendar.add(Calendar.WEEK_OF_YEAR, increaseAmount);
        // Sets the due date to the updated time
        dueDate = calendar.getTime();
    }

    // Returns a string with all the loan's information
    public String toString() {
        return item.getBarcode() + "," + user.getUserID() + ","
                + fileDateFormatter.format(issueDate) + ","
                + fileDateFormatter.format(dueDate) + "," + numberOfRenewals;
    }

}
