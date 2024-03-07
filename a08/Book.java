/**
 * The Book Class represents a physical or digital book
 * ITP 265 
 * 
 * Sample Solution 
 */


public class Book {
    //Initialize the instance variables
    private String title, author, format;
    private double price;
    private int rating;

    //Class (GLOBAL) constants
    public static final double DEFAULT_PRICE = 9.99;
    public static final int DEFAULT_RATING = 3;
    public static final String DEFAULT_FORMAT = "paperback";

    /**
     * Full version of the Constructor
     * Take in the parameters and save them to the instance variables
     * @param title
     * @param author
     * @param price
     * @param format
     * @param rating
     */
    public Book(String title, String author, double price, String format, int rating) {
        this.title = title;
        this.author = author;
        this.price = price;
        setFormat(format);
        setRating(rating);
    }

    /**
     * Constructor 2 (uses constructor 1):
     * Take in the parameters (if just 3 given) and save them to the instance variables
     * Then sets the other instance variables to defaults  
     * @param title
     * @param author
     * @param price
     */
    public Book(String theTitle, String author, double price) {
        this(theTitle, author, price, DEFAULT_FORMAT, DEFAULT_RATING);
    }

    /**
     * Constructor 3 (uses constructor 2 and 1):
     * Take in the parameters (if just 3 given) and save them to the instance variables
     * @param title
     * @param author
     * @param pages
     */
    public Book(String title, String author) {
        this(title, author, DEFAULT_PRICE);
    }

    /**
     * setTitle takes in a string title and saves it to the title instance variable
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * setFormat takes in a string format and saves it to the format instance variable
     * @param format
     */
    public void setFormat(String format) {
        if(format.isBlank()){
            this.format = DEFAULT_FORMAT;
        }else{this.format = format;
        }
    }

    /**
     * setAuthor takes in a string author and saves it to the author instance variable
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * setPrice takes in a double price and saves it to the price instance variable
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * setPages takes in an int number of pages and saves it to the pages instance variable
     * @param pages
     */
    public void setRating(int r) {
        this.rating = DEFAULT_RATING; // start at default
        if(r >=1 && r <= 5){ // if argurment is valid, use it.
            this.rating = r;
        }
    }

    /**
     * getTitle returns the title of the book as a string
     * @return title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * getTitle returns the title of the book as a string
     * @return format
     */
    public String getFormat() {
        return this.format;
    }

    /**
     * getAuthor returns the author of the book as a string
     * @return title
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * getPrice returns the price of the book as a double
     * @return price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * getPages returns the number of pages in the book as an int
     * @return pages
     */
    public int getRating() {
        return this.rating;
    }

    /**
     * The toString function converts all of the information associated with an instance
     * of the Book class and returns it as a nicely formatted string
     * @return stringForm
     */
    public String toString() {
        //using string method to repeat the * string the nmber of times for the raring
       return  "\"" + title + "\" by " + author + " (" + format + ") $" + price + ", " + rating + " stars " + "*".repeat(rating);
        }

    /**
     * The equals function accepts a second Book instance as a parameter,
     * and then checks to see if values stored in instance variables are the same.
     * If they are, it returns true.
     * @param other
     * @return equals
     */
    public boolean equals(Book other) {
        return (this.title.equalsIgnoreCase(other.title) 
            && this.author.equalsIgnoreCase(other.author) 
            && Math.abs(this.price - other.price) < 0.001 
            && this.format.equalsIgnoreCase(other.format) 
            && this.rating == other.rating);
    }
}
