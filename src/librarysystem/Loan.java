/*
 * LibrarySystem Loan class
 * Developed by: Patrick White
 * B-number: B00798718
 * Created: 15/03/2022
 */
package librarysystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Loan {

    protected static SimpleDateFormat formatter = new SimpleDateFormat(
            "dd/MM/yyyy");

    private Item item;
    private User user;
    private Date issueDate;
    private Date dueDate;
    private int numberOfRenewals;

    public Loan(Item newItem, User newUser, String newIssueDate,
            String newDueDate, int newNumberOfRenewals) throws ParseException {
        item = newItem;
        user = newUser;
        issueDate = formatter.parse(newIssueDate);
        dueDate = formatter.parse(newDueDate);
        numberOfRenewals = newNumberOfRenewals;
    }

    public String getReturnDate() {
        return formatter.format(dueDate);
    }

    public String getBarcode() {
        return item.getBarcode();
    }

    public void displayInfo() {
        System.out.println("\tLoaned Item Barcode: " + item.getBarcode());
        System.out.println("\tLoanee User ID: " + user.getUserID());
        System.out.println("\tIssue Date: " + formatter.format(issueDate));
        System.out.println("\tDue Date: " + formatter.format(dueDate));
        System.out.println("\tNumber of Renewals: " + numberOfRenewals);

    }

}
