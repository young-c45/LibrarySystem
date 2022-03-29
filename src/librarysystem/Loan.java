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

    protected static SimpleDateFormat fileDateFormatter
            = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);

    private Item item;
    private User user;
    private Date issueDate;
    private Date dueDate;
    private int numberOfRenewals;

    private int maxRenews;

    public Loan(Item newItem, User newUser, String newIssueDate,
            String newDueDate, int newNumberOfRenewals) throws ParseException {
        item = newItem;
        user = newUser;
        issueDate = fileDateFormatter.parse(newIssueDate);
        dueDate = fileDateFormatter.parse(newDueDate);
        numberOfRenewals = newNumberOfRenewals;
        maxRenews = item.getType().equals("Book") ? 3 : 2;
    }

    public Loan(Item newItem, User newUser) {
        item = newItem;
        user = newUser;
        issueDate = new Date();
        dueDate = new Date();
        setReturnDate(false);
        numberOfRenewals = 0;
        maxRenews = item.getType().equals("Book") ? 3 : 2;
    }

    private String getReadableDate(Date date) {
        SimpleDateFormat readableDateFormatter
                = new SimpleDateFormat(" 'of' MMMM, yyyy", Locale.UK);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int dayNum = calendar.get(Calendar.DAY_OF_MONTH);
        String dayString = dayNum + "";

        if (dayNum > 10 && dayNum < 20) {
            dayString += "th";
        } else {
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

        return dayString + readableDateFormatter.format(date);

    }

    public String getBarcode() {
        return item.getBarcode();
    }

    public void displayInfo() {
        System.out.println("\tLoaned Item Barcode: " + item.getBarcode());
        System.out.println("\tLoanee User ID: " + user.getUserID());
        System.out.println("\tIssue Date: " + getReadableDate(issueDate));
        System.out.println("\tDue Date: " + getReadableDate(dueDate));
        System.out.println("\tNumber of Renewals: " + numberOfRenewals);
    }

    public void renew() throws Exception {
        if (numberOfRenewals >= maxRenews) {
            throw new Exception("Max Renewals Reached");
        }

        setReturnDate(true);
        numberOfRenewals++;
    }

    private void setReturnDate(boolean renew) {
        int increaseAmount;

        if (item.getType().equals("Book")) {
            increaseAmount = renew ? 2 : 4;
        } else {
            increaseAmount = 1;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dueDate);
        calendar.add(Calendar.WEEK_OF_YEAR, increaseAmount);

        dueDate = calendar.getTime();
    }

}
