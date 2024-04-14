/**
 * Kendra's provided StoreFactory
 * @author Kendra Walther
 *
 * Spring 2024, ITP 265, coffee
 * Email: kwalther@usc.edu
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


public class StoreFactory {
    public static final String ITEMS_FILE_SHORT = "items.csv";
    public static final String STREAMABLE_FILE_SHORT = "streamable.csv";
    public static final String ITEMS_FILE_LONG = "from ide";
    public static final String STREAMABLE_FILE_LONG = "from ide";

    //public static final String ITEMS_FILE_LONG = "/Users/kendra/Documents/code/itp265/Solutions/A10-productsPart1/src/items.csv";
    //public static final String STREAMABLE_FILE_LONG = "/Users/kendra/Documents/code/itp265/Solutions/A10-productsPart1/src/streamable.csv";

    public static void main(String[] args) {
        for(Product p: setUpStoreProducts()) {
            System.out.println(p);
        }
    }


    public static List<Product> setUpStoreProducts() {
        List<Product> allProducts = new ArrayList<>();
        readFile(ITEMS_FILE_SHORT, ITEMS_FILE_LONG, allProducts);
        readFile(STREAMABLE_FILE_SHORT,STREAMABLE_FILE_LONG , allProducts);
        return allProducts;
    }



    private static void readFile(String file_short, String file_long,  List<Product> list) {
        String fileName = file_short;
        boolean read = true, triedAlt = false;
        do {
            try (FileInputStream fis = new FileInputStream(fileName);
                 Scanner scan = new Scanner(fis)) {  // NOTE: resources will be closed automatically with this try block
                if (scan.hasNext()) {
                    String header = scan.nextLine();
                    while (scan.hasNextLine()) {
                        String line = scan.nextLine();
                        Product p = parseStringIntoProduct(line);
                        if (p != null) {
                            list.add(p);
                        }
                    }
                } else {
                    System.err.println("File was empty: " + fileName);
                }
                read = true;
            } catch (IOException e) {
                System.err.println("Name File not found, trying long version: " + file_long);
                read = false; //to force a second try
                if (!triedAlt) {
                    fileName = file_long;
                    triedAlt = true;
                } else {
                    System.err.println("Neither file name worked. Try to fix it. Exiting program.");
                    System.exit(1);
                }
            }
        }while(!read);
    }

    /**
     * @param line
     * @return
     */
    private static Product parseStringIntoProduct(String line) {
        //CSV format:
        //Product Type,Name,Price,Rating,Item or Streamable,Item quantity,Streamable duration,withPrime,releaseYear,Other Data
        Product product = null;
        try {

            Scanner sc = new Scanner(line);
            sc.useDelimiter(",");
            String type = sc.next();
            String rest = sc.nextLine();
            if(type.equalsIgnoreCase("Item")) {
                product = parseItem(rest);
            }
            else if(type.equalsIgnoreCase("Streamable")) {
                product = parseStreamable(rest);

            }else {
                System.err.println("Unknown category in file: " + type);
            }

        }
        catch(Exception e) {
            System.err.println("Error reading line of file: " + line + "\nerror; " + e);
        }
        return product;
    }


    private static StreamableProduct parseStreamable(String line) {
        StreamableProduct thing = null;
        try {
            Scanner sc = new Scanner(line);
            sc.useDelimiter(",");
            String productType = sc.next();
            String name = sc.next();
            double price = sc.nextDouble();
            double rating = sc.nextDouble();
            double duration = sc.nextDouble();
            boolean withPrime = sc.nextBoolean();
            int releaseYear = sc.nextInt();
            String otherData = sc.next();
            if(productType.equalsIgnoreCase("Music")) {
                MusicGenre genre = parseMusicGenre(otherData);
                thing = new Music(name, price, rating, duration, withPrime, releaseYear, genre);
            }
            else if(productType.equalsIgnoreCase("Video")) {
                Genre genre = parseVideoGenre(otherData);
                thing = new Video(name, price, rating, duration, withPrime, releaseYear, genre);
            }

        }
        catch(Exception e) {
            System.err.println("Error reading line of file: " + line + "\nerror; " + e);
        }
        return thing;
    }

    /**
     * @param otherData
     * @return
     */
    private static Genre parseVideoGenre(String otherData) {
        Genre genre = Genre.valueOf(otherData.toUpperCase());

        return genre;
    }

    /**
     * @param otherData
     * @return
     */
    private static MusicGenre parseMusicGenre(String otherData) {
        return MusicGenre.valueOf(otherData.toUpperCase()) ;

    }

    private static Item parseItem(String line) {
        Item item = null;
        try {
            Scanner sc = new Scanner(line);
            sc.useDelimiter(",");
            String productType = sc.next();
            String name = sc.next();
            double price = sc.nextDouble();
            double rating = sc.nextDouble();
            int quantity = sc.nextInt();
            // get otherData column(s)
            if(productType.equalsIgnoreCase("Book")) {
                String genre =  sc.next();
                int numPages =  sc.nextInt();
                item = new Book(name, price, rating, quantity, genre, numPages);
            }
            else if(productType.equalsIgnoreCase("Electronics")) {
                String dim =  sc.next();
                String weight= sc.next();
                item = new Electronics(name, price, rating, quantity,dim, weight);
            }
            else if(productType.equalsIgnoreCase("Games")) {
                String otherData = sc.next();
                item = new Games(name, price, rating, quantity,otherData);
            }
        }
        catch(Exception e) {
            System.err.println("Error reading line of file: " + line + "\nerror; " + e);
            e.printStackTrace();
        }
        return item;
    }


}