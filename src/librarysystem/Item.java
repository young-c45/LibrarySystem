/*
 * LibrarySystem Item class
 * Developed by: Patrick White
 * B-number: B00798718
 * Created: 15/03/2022
 */
package librarysystem;

public class Item {

    private String barcode;
    private String author;
    private String title;
    private String type;
    private String year;
    private String isbn;

    public Item(String newBarcode, String newAuthor, String newTitle, String newType, String newYear, String newIsbn) {
        barcode = newBarcode;
        author = newAuthor;
        title = newTitle;
        type = newType;
        year = newYear;
        isbn = newIsbn;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getType() {
        return type;
    }

    public void displayItem() {
        System.out.println("\tItem Barcode: " + barcode);
        System.out.println("\tAuthor: " + author);
        System.out.println("\tTitle: " + title);
        System.out.println("\tItem Type: " + type);
        System.out.println("\tYear Published: " + year);
        System.out.println("\tISBN: " + isbn);
    }

}
