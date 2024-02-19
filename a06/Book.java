/**
 * A class that represents a book
 * 
 * @author Alex Wang
 *         awang756@usc.edu
 *         ITP 265, Spring 2024
 *         coffee Class Section
 **/

public class Book {
    private String title; // needs accessor
    private String author; // needs accessor
    private double price; // needs accessor and mutator
    private String format; // needs accessor and mutator
    private int rating; // needs accessor and mutator

    // full constructor
    public Book(String title, String author, double price, String format, int rating) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.format = format;

        // rating should be between 0 and 5
        if (rating >= 0 && rating <= 5) {
            this.rating = rating;
        } else {
            // we just put a default rating of 0
            this.rating = 0;
        }
    }

    // constructor w/ just title and author
    public Book(String title, String author) {
        this(title, author, 9.99, "Paperback", 5);
    }

    // accessor for title
    public String getTitle() {
        return title;
    }

    // accessor for author
    public String getAuthor() {
        return author;
    }

    // accessor for price
    public double getPrice() {
        return price;
    }

    // mutator for price
    public void setPrice(double price) {
        this.price = price;
    }

    // accessor for format
    public String getFormat() {
        return format;
    }

    // mutator for format
    public void setFormat(String format) {
        this.format = format;
    }

    // accessor for rating
    public int getRating() {
        return rating;
    }

    // mutator for rating
    // note that rating should be between 0 and 5
    public boolean setRating(int rating) {
        if (rating >= 0 && rating <= 5) {
            this.rating = rating;
            return true;
        }

        return false;
    }

    // returns a string representation of the book
    public String toString() {
        // format: "Title" by Author (Format) $ price, num stars: {asterisks match
        // number of stars)

        // generate stars according to rating
        String stars = "";
        for (int i = 0; i < rating; i++) {
            stars += "*";
        }

        return "\"" + title + "\" by " + author + " (" + format + ") $" + price + ", " + rating + " stars " + stars;
    }

    // checks if two Books are equal to each other by seeing if everything except
    // rating is equal apart from rating
    public boolean equals(Book other) {
        return this.title.equalsIgnoreCase(other.title) && this.author.equalsIgnoreCase(other.author)
                && Math.abs(this.price - other.price) < 0.01 && format.equalsIgnoreCase(other.format);
    }


    // code for testing
    // public static void main(String[] args) {
    //     // create two identical books, test if they're equal
    //     Book b1 = new Book("Harry Potter", "J.K. Rowling", 19.99, "hardcover", 5);
    //     Book b2 = new Book("Harry Potter", "J.K. Rowling", 19.99, "hardcover", 5);
    //     System.out.println(b1.equals(b2)); // should be true

    //     Book b3 = new Book("Mathematics for Human Flourishing", "Francis Su", 26.0, "hardcover", 4);
    //     Book b4 = new Book("Dare to Lead", "Brene Brown", 28.00, "hardcover", 5);
    //     Book b5 = new Book("Test Book", "Alex Wang");

    //     System.out.println(b3.equals(b4)); // should be false
    //     System.out.println(b3.toString());
    //     System.out.println(b4.toString());
    //     System.out.println(b5.toString());
    // }

}