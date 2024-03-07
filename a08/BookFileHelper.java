import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
/**
 * This is a class to interact with the file system to read and write Books to a file.
 * @author Alex Wang
 * email: awang756@usc.edu
 * ITP 265,
 * @version Spring 2024
 */
 public class BookFileHelper{

     /**
     * This function takes in a fileName to read, where the data should represent Book Objects. It will
     * read the file data, skipping the header, reading each line, creating a Book object from that line
     * and then adding each Book object into the ArrayList parameter books (Lists are mutable, so we will be updating
     * the list that was passed into this function)
     */
     public static void readFileIntoList(String fileName, ArrayList<Book> books){
        // to do
     }
    // helper function to turn a line of data into a book. THe data should be TAB separated with the following information
    //Timestamp	Title of Book	Author of Book	Price of Book	Format of Book	Rating
    // timestamp can be skipped, format and rating MIGHT be empty, and if they are, you need to
    //use the constants inside of Book for setting format and rating.
    private static Book parseDataToBook(String data){
       //TODO

        return null; 
    }

    public static void writeBooksToFile(String file, ArrayList<Book> list){
        String header = "title\tauthor\tprice\tformat\trating";
         // Uses PrintWriter to write to a file.
         //First add the header line 
         // Then use a loop to write each book in the list to the file - with tabs between each attribute
        
        System.out.println("Wrote the list of books to the file: " + file);
     }




 }
