import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * This is a class to interact with the file system to read and write Books to a
 * file.
 * 
 * @author Alex Wang
 *         email: awang756@usc.edu
 *         ITP 265,
 * @version Spring 2024
 */
public class BookFileHelper {

    /**
     * This function takes in a fileName to read, where the data should represent
     * Book Objects. It will
     * read the file data, skipping the header, reading each line, creating a Book
     * object from that line
     * and then adding each Book object into the ArrayList parameter books (Lists
     * are mutable, so we will be updating
     * the list that was passed into this function)
     */
    public static void readFileIntoList(String fileName, ArrayList<Book> books) {
        try (Scanner fileReader = new Scanner(new FileInputStream(fileName));) {
            // skip the header line
            String header = fileReader.nextLine();
            // loop over the file, reading each line and parsing it into a Book object
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                Book newBook = parseDataToBook(line);

                if (newBook != null) {
                    books.add(newBook);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + fileName);
        }
    }

    // helper function to turn a line of data into a book. THe data should be TAB
    // separated with the following information
    // Timestamp Title of Book Author of Book Price of Book Format of Book Rating
    // timestamp can be skipped, format and rating MIGHT be empty, and if they are,
    // you need to
    // use the constants inside of Book for setting format and rating.
    private static Book parseDataToBook(String data) {
        // split the data into an array of strings (this is the method I'd use in python
        // and it seems to translate nicely to java; I think it's a little nicer than
        // scanner)
        String[] bookData = data.split("\t");

        // grab data from the array of strings
        // use relevant utils to parse data types when needed
        String title = bookData[1];
        String author = bookData[2];
        double price = Double.parseDouble(bookData[3]);
        String format = bookData[4];

        // we need some special handling for rating since it's an int
        String ratingRaw = bookData[5];
        int rating;
        // if format is empty, set it to Book.DEFAULT_FORMAT
        if (format.isEmpty() || format == null) {
            format = Book.DEFAULT_FORMAT;
        }

        // if rating is empty, set it to Book.DEFAULT_RATING
        // else, parse the string into an int
        if (ratingRaw.isEmpty() || ratingRaw == null) {
            rating = Book.DEFAULT_RATING;
        } else {
            rating = Integer.parseInt(ratingRaw);
        }

        // create a new Book object and return it
        return new Book(title, author, price, format, rating);
    }

    public static void writeBooksToFile(String file, ArrayList<Book> list) {
        String header = "title\tauthor\tprice\tformat\trating";

        try (PrintWriter fileWriter = new PrintWriter(new FileOutputStream(file));) {
            // write out header line
            fileWriter.println(header);

            // iterate over each book, write it to the file
            for (Book book : list) {
                // janky way to convert the book to a string
                fileWriter.println(book.getTitle() + "\t" + book.getAuthor() + "\t" + book.getPrice() + "\t"
                        + book.getFormat() + "\t" + book.getRating());
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + file);
        }

        System.out.println("Wrote the list of books to the file: " + file);
    }

}
