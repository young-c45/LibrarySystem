/*
 * LibrarySystem Item class
 * Developed by: Patrick White
 * B-number: B00798718
 * Created: 15/03/2022
 */

package librarysystem;

public class Item {
    
   private String barcode ;
   private String author ;
   private String title ;
   private String type ;
   private String year;
   private String isbn;
   
   public Item(String newBarcode, String newAuthor, String newTitle, String newType, String newYear, String newIsbn){
       barcode = newBarcode; 
       author = newAuthor;
       title = newTitle;
       type = newType;
       year = newYear;
       isbn = newIsbn;
   }
         
   public String getBarcode(){
       return barcode;
   }

  public void displayItem(){
      System.out.println("Displayed Item");
  }
          


}

