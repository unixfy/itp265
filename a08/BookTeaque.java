import java.io.File;
import java.util.ArrayList;

/**
 * This is a class to implement our Bookteaque!
 * 
 * @author Alex Wang
 *         email: awang756@usc.edu
 *         ITP 265,
 * @version Spring 2024
 */
public class BookTeaque {

    // instance variables
    private String name; // (each BookTeaque object can have a unique name)
    private ArrayList<Book> bookList;
    private Beverage[] drinkArray;
    private ArrayList<Beverage> currentOrder;
    private ArrayList<Book> fList; // filtered list
    private BFF bff;

    // constant
    public static final String BOOK_FILE = "books.tsv";
    private static final int NUM_BEVERAGES = 9;

    // Constructor
    public BookTeaque(String storeName) {
        this.name = storeName;
        bookList = new ArrayList<>();
        drinkArray = new Beverage[NUM_BEVERAGES];
        currentOrder = new ArrayList<>();
        bff = new BFF();
        initializeData();
        fList = bookList; // filtered list starts out as everything (all Books)
    }

    private void initializeData() {
        createDrinkArray();
        createBookList();
        System.out.printf("Initialized the data. There are %d drinks and %d books%n", drinkArray.length,
                bookList.size());
    }

    private void createDrinkArray() {
        // define 2 arrays with sizes and drink names
        char[] sizes = { 'S', 'M', 'L' };
        String[] names = { "Coffee", "Tea", "Boba" };

        drinkArray = new Beverage[NUM_BEVERAGES];

        // iterate over sizes and names to get all the possibe combinations of drinks,
        // plop them into drinkArray
        int activeIndex = 0;
        for (String name : names) {
            for (char size : sizes) {
                drinkArray[activeIndex] = new Beverage(name, size);
                activeIndex++;
            }
        }
    }

    private void createBookList() {
        // use the helper function to populate the bookList
        BookFileHelper.readFileIntoList(BOOK_FILE, bookList);
    }

    public void showInventory() {
        printBeverages();
        printBooks(bookList);// print list index + each book item
    }

    private void printBeverages() {
        bff.printFancy("BEVERAGE MENU");
        // prints array index and each beverage item
        for (int i = 0; i < drinkArray.length; i++) {
            bff.print(i + ": " + drinkArray[i].toString());
        }
    }

    private void printBooks(ArrayList<Book> list) {
        if (!list.isEmpty()) {
            bff.printFancy("BOOKS");
            // print numbering (start at 1) + each book item
            for (int i = 0; i < list.size(); i++) {
                bff.print((i + 1) + ": " + list.get(i).toString());
            }
        } else {
            bff.printFancy("No books in the current list.");
        }
    }

    private String getMenuOptions() {
        return "1: Show all inventory\n"
                + "2: Buy beverages\n"
                + "3: Show all books\n"
                + "4: Filter books\n"
                + "5: Show current filtered book list\n"
                + "6: Save filtered book list\n"
                + "7: Reset filtered book list\n"
                + "8: Quit\n";
    }

    public void run() {
        bff.printFancy("Welcome to the " + name + " bookteaque!");
        boolean quit = false;
        do {
            bff.print(getMenuOptions());
            int option = bff.inputInt("Which option", 1, 10);
            switch (option) {
                case 1:
                    showInventory();
                    break;
                case 2:
                    buyBeverages();
                    break;
                case 3:
                    printBooks(bookList);
                    break;
                case 4:
                    filter();
                    break;
                case 5:
                    printBooks(fList);
                    break;
                case 6:
                    saveBooksToFile(fList);
                    break;
                case 7:
                    fList = bookList;
                    break;
                case 8:
                    bff.print("Goodbye");
                    quit = true;
                    break;
            }
            if (!quit)
                bff.input("Hit anything to continue.");

        } while (!quit);
    }

    public void buyBeverages() {
        // print all beverages
        printBeverages();

        // ask user which beverage they want and take their input
        int desiredDrink = bff.inputInt("Which drink number would you like?", 0, drinkArray.length - 1);
        
        // ask user how many of the drink they want and take their input
        int numDrinks = bff.inputInt("How many " + drinkArray[desiredDrink].toString() + " would you like (max is 10)?", 1, 10);

        // add the drink to currentOrder numDrinks number of times
        for (int i = 0; i < numDrinks; i++) {
            currentOrder.add(drinkArray[desiredDrink]);
        }

        // ask if user wants to buy another drink
        if (bff.inputYesNo("Would you like to purchase more drinks? y/n?")) {
            buyBeverages();
        } else {
            // print thank you for buying n beverages
            bff.print("Thank you for purchasing " + currentOrder.size() + " beverages.");

            // give the user a receipt
            Receipt.print(name, currentOrder);

            // clear currentOrder
            currentOrder.clear();
        }
    }

    public void filter() {
        ArrayList<Book> temp = null;
        String filterType = bff.input("What would you like to filter by [title, author, rating]?", "title", "author",
                "rating");
        if (filterType.equalsIgnoreCase("title")) {
            temp = filterTitle(fList);
        } else if (filterType.equalsIgnoreCase("author")) {
            temp = filterAuthor(fList);
        } else { // must be rating
            temp = filterRating(fList);
        }
        printBooks(temp);
        if (bff.inputYesNo("Would you like to save this filtered list?")) {
            fList = temp;
        }
    }

    public ArrayList<Book> filterTitle(ArrayList<Book> books) {
        // ➡ returns a new ArrayList of Book objects where any part of the book title
        // contains the provided word (ignoring capitalization).

        String word = bff.input("What word would you like to look for in the title?").toLowerCase();
        ArrayList<Book> list = new ArrayList<>();

        // search for the word in the title of each book
        // maybe not the most efficient search algo, but it works for now...
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(word)) {
                list.add(b);
            }
        }
        bff.print("Filtering by " + word + " leads to " + list.size() + " books");

        return list;
    }

    public ArrayList<Book> filterAuthor(ArrayList<Book> books) {
        // returns a new ArrayList of Book objects where any part of the author instance
        // variables matches the provided name (ignoring capitalization).

        String name = bff.input("What author name would you like to look for?").toLowerCase();
        ArrayList<Book> list = new ArrayList<>();

        // search for the name in the author of each book
        for (Book b : books) {
            if (b.getAuthor().toLowerCase().contains(name)) {
                list.add(b);
            }
        }

        bff.print("Filtering by author " + name + " leads to " + list.size() + " books");

        return list;
    }

    public ArrayList<Book> filterRating(ArrayList<Book> books) {
        // returns a new ArrayList of Book objects where the books are equal or greater
        // than the provided rating number.
        int num = bff.inputInt("What rating of books are you looking for?", 1, 5);
        ArrayList<Book> list = new ArrayList<>();

        // search for books with rating greater than or equal to num
        for (Book b : books) {
            if (b.getRating() >= num) {
                list.add(b);
            }
        }

        bff.print("Filtering by rating " + num + " leads to " + list.size() + " books");

        return list;
    }

    public void saveBooksToFile(ArrayList<Book> books) {
        String username = bff.input("Please enter name of the user");
        BookFileHelper.writeBooksToFile(username + "Books.tsv", books);
    }

    public static void main(String[] args) {
        BookTeaque store = new BookTeaque("Alex's Bookstore");
        store.run();
    }
}
