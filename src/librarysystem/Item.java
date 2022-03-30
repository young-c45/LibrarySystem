/*
 * LibrarySystem Item class
 * Developed by: Patrick White
 * B-number: B00798718
 * Created: 15/03/2022
 */
package librarysystem;

public class Item {

    // Declares instance variables
    private String barcode;
    private String author;
    private String title;
    private String type;
    private String year;
    private String isbn;

    // Creates the object
    public Item(String newBarcode, String newAuthor, String newTitle,
            String newType, String newYear, String newIsbn) {
        // Initialises the variables to those passed to the constructor
        barcode = newBarcode;
        author = newAuthor;
        title = newTitle;
        type = newType;
        year = newYear;
        isbn = newIsbn;
    }

    // Returns the item's barcode
    public String getBarcode() {
        return barcode;
    }

    // Returns the item's item type
    public String getType() {
        return type;
    }

    // Outputs the item's information to the standard output stream
    public void displayItem() {
        System.out.println("\tItem Barcode: " + barcode);
        System.out.println("\tAuthor: " + author);
        System.out.println("\tTitle: " + title);
        System.out.println("\tItem Type: " + type);
        System.out.println("\tYear Published: " + year);
        System.out.println("\tISBN: " + isbn);
    }

}
