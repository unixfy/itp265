/**
 * This class uses Book and Beverage classes to create a demo BookTeaque
 * 
 * @author Alex Wang
 *         awang756@usc.edu
 *         ITP 265, Spring 2024
 *         coffee Class Section
 **/
public class BookTeaqueV1 {
    // create array of 3 books and 5 bevs
    public static Book[] books = new Book[3];
    public static Beverage[] beverages = new Beverage[5];

    public static void main(String[] args) {
        // populate books with data
        books[0] = new Book("Midnight in Chernobyl", "Adam Higginbotham", 15.99, "eBook", 5);
        books[1] = new Book("Attack Surface", "Cory Doctorow", 14.99, "Hardcover", 4);
        books[2] = new Book("The Phoenix Project", "Gene Kim", 120.00, "Paperback", 3);

        // populate beverages with data
        beverages[0] = new Beverage("Sparkling Water", 'L');
        beverages[1] = new Beverage("Matcha", 'M');
        beverages[2] = new Beverage("Kola Champagne", 'L');
        beverages[3] = new Beverage("Mango Nectar", 'M');
        beverages[4] = new Beverage("Plum Juice", 'S');

        // print out books
        System.out.println("Here are the books in the bookteaque:");
        for (int i = 0; i < books.length; i++) {
            System.out.println("\t" + (i + 1) + " : " + books[i].toString());
        }

        // print out beverages
        System.out.println("Here are the drinks in the bookteaque:");
        for (int i = 0; i < beverages.length; i++) {
            System.out.println("\t" + (i + 1) + " : " + beverages[i].toString());
        }

        // test mutators and accessors
        System.out.println("Title of book one is: " + books[0].getTitle());
        System.out.println("Drink four is currently: " + beverages[3]);
        // update name of drink four
        beverages[3].setName("Gatorade");
        System.out.println("After updating name with mutator, drinkFour is now: " + beverages[3]);
        // update size of drink four
        System.out.println("Updating the size of drink four to large...");
        beverages[3].setSize('L');
        System.out.println("After updating size with mutator, drinkFour is now: " + beverages[3]);
        // check if drinks are equal
        System.out.println("Checking if drink 2 and 3 are equal: " + beverages[1].equals(beverages[2]));
        System.out.println(
                "Checking if drink 1 and L sparkling water are equal: " + beverages[0].equals(new Beverage("Sparkling Water", 'L')));
    }

    // test case from EdStem
    public static void testMain(String[] args) {
        // populate books with data
        books[0] = new Book("The Catcher in the Rye", "J.D. Salinger", 11.99, "Paperback", 4);
        books[1] = new Book("The Great Gatsby", "F. Scott Fitzgerald", 9.99, "Paperback", 5);
        books[2] = new Book("Turing's Vision: The Birth of Computer Science", "Chris Bernhart", 19.99, "Hardcover", 4);

        // populate beverages with data
        beverages[0] = new Beverage("Water", 'L');
        beverages[1] = new Beverage("Apple Juice", 'M');
        beverages[2] = new Beverage("Coca Cola", 'S');
        beverages[3] = new Beverage("Coffee", 'M');
        beverages[4] = new Beverage("Boba", 'L');

        // print out books
        System.out.println("Here are the books in the bookteaque:");
        for (int i = 0; i < books.length; i++) {
            System.out.println("\t" + (i + 1) + " : " + books[i].toString());
        }

        // print out beverages
        System.out.println("Here are the drinks in the bookteaque:");
        for (int i = 0; i < beverages.length; i++) {
            System.out.println("\t" + (i + 1) + " : " + beverages[i].toString());
        }

        // test mutators and accessors
        System.out.println("Title of book one is: " + books[0].getTitle());
        System.out.println("Drink four is currently: " + beverages[3]);
        // update name of drink four
        beverages[3].setName("Matcha");
        System.out.println("After updating name with mutator, drinkFour is now: " + beverages[3]);
        // update size of drink four
        System.out.println("Updating the size of drink four to large...");
        beverages[3].setSize('L');
        System.out.println("After updating size with mutator, drinkFour is now: " + beverages[3]);
        // check if drinks are equal
        System.out.println("Checking if drink 2 and 3 are equal: " + beverages[1].equals(beverages[2]));
        System.out.println(
                "Checking if drink 1 and L water are equal: " + beverages[0].equals(new Beverage("Water", 'L')));
    }
}
