import java.util.ArrayList;

/**
 * Description of class
 * @author (your name)
 * email:
 * ITP 265, Fall 2023, _____ section
 * @version (a version number or a date)
 */
 public class BookTeaque{

     //instance variables
   private String name; //(each BookTeaque object can have a unique name)
    private ArrayList<Book> bookList;
    private Beverage[] drinkArray;
    private ArrayList<Beverage> currentOrder;
    private ArrayList<Book> fList; // filtered list
    private BFF bff;

    //constant
    public static final String BOOK_FILE = "books.tsv";
    private static final int NUM_BEVERAGES = 9;
    //Constructor
    public BookTeaque(String storeName){
        this.name = storeName;
        bookList = new ArrayList<>();
        drinkArray = new Beverage[NUM_BEVERAGES];
        currentOrder = new ArrayList<>();
        bff = new BFF();
        initializeData();
        fList = bookList; // filtered list starts out as everything (all Books)
    }

    private void initializeData(){
        createDrinkArray();
        createBookList();
        System.out.printf("Initialized the data. There are %d drinks and %d books%n", drinkArray.length, bookList.size());
    }
    private void createDrinkArray( ) {
       //TO DO
    }

    private void createBookList(){
        //make fake data to start, or do file reading right away:
      //  BookFileHelper.readFileIntoList(BOOK_FILE, bookList);
    }

    public void showInventory(){
        printBeverages();
        printBooks(bookList);// print list index + each book item
    }

 private void printBeverages() {
       bff.printFancy("BEVERAGE MENU");
        // to do:  print array index (start at 0) + each beverage item
    }
    private void printBooks(ArrayList<Book> list) {
        if(! list.isEmpty()) {
            bff.printFancy("BOOKS");
             // to do:  print numbering (start at 1) + each book item
        }
        else{
            bff.printFancy("No books in the current list.");
        }
    }

   private String getMenuOptions(){
    return "1: Show all inventory\n"
                + "2: Buy beverages\n"
                + "3: Show all books\n"
                + "4: Filter books\n"
                + "5: Show current filtered book list\n"
                + "6: Save filtered book list\n"
                + "7: Reset filtered book list\n"
                + "8: Quit\n";
   }

    public void run(){
        bff.printFancy("Welcome to the " + name + " bookteaque!");
        boolean quit = false;
        do{
            bff.print(getMenuOptions());
            int option = bff.inputInt("Which option", 1, 10);
            switch(option){
                case 1:     showInventory(); break;
                case 2:       buyBeverages(); break;
                case 3:       printBooks(bookList); break;
                case 4:       filter(); break;
                case 5:       printBooks(fList); break;
                case 6:       saveBooksToFile(fList); break;
                case 7:       fList = bookList; break;
                case 8:    bff.print("Goodbye"); quit = true; break;
            }
            if(!quit) bff.input("Hit anything to continue.");

        }while(!quit);
    }

  public void buyBeverages(){
        bff.print("Buy beverages is not yet implemented");
    }

    public void filter(){
        ArrayList<Book> temp = null;
        String filterType = bff.input("What would you like to filter by [title, author, rating]?", "title", "author", "rating");
       if(filterType.equalsIgnoreCase("title")) {
            temp = filterTitle(fList);
       }
       else if(filterType.equalsIgnoreCase("author")){
           temp = filterAuthor(fList);
       }
       else{ // must be rating
           temp = filterRating(fList);
       }
       printBooks(temp);
       if(bff.inputYesNo("Would you like to save this filtered list?")) {
           fList = temp;
       }
    }
  
    public ArrayList<Book> filterTitle(ArrayList<Book> books) {
        //➡ returns a new ArrayList of Book objects where any part of the book title contains the provided word (ignoring capitalization).
       
       //String word = bff.input("What word would you like to look for in the title ?").toLowerCase();
        ArrayList<Book> list = new ArrayList<>();
        bff.print("filter by title is not yet implemented");
        //bff.print("Filtering by " + word + " leads to " + list.size() + " books");

         return list;
    }
    public ArrayList<Book> filterAuthor( ArrayList<Book> books) {
        //returns a new ArrayList of Book objects where any part of the author instance variables matches the provided name (ignoring capitalization).
       
        //String name = bff.input("What author name would you like to look for?").toLowerCase();
        ArrayList<Book> list = new ArrayList<>();
        bff.print("filter by author is not yet implemented");
        return list;
    }
    public ArrayList<Book> filterRating(ArrayList<Book> books){
        //returns a new ArrayList of Book objects where the books are equal or greater than the provided rating number.
       // int num = bff.inputInt("What rating of books are you looking for?", 1, 5);
        ArrayList<Book> list = new ArrayList<>();
       
        bff.print("filter by rating is not yet implemented");
        return list;
    }

    public void saveBooksToFile(ArrayList<Book> books){
        String username = bff.input("Please enter name of the user");
        BookFileHelper.writeBooksToFile(username+"Books.tsv", books);
    }

    public static void main(String[] args){
        // TODO: make sure that you give your BookTeaque a cute name!
        BookTeaque store = new BookTeaque("CTB"); 
        store.run();
   }
 }
