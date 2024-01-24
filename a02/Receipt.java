import java.util.*;
import java.time.*;
/**
* Description: A simple interface to create a receipt, using the logic we learned this week
* @author Alex Wang
* @version Spring 2023
* ITP 265, Coffee Section
* 01/16/2024
* Email: awang756@usc.edu
*/

public class Receipt {
    public static final String STORE_NAME = "Shop265"; // a constant
    public static final double TAX_RATE = 9.5; // a constant

    public static void main(String[] args){
        System.out.println("Welcome to the command-line receipt tool.");
        String name = input("Please enter item name");
        int qty = inputInt("Please enter quantity of item");
        double price = inputDouble("Please enter price of item");
        printReceipt(name, qty, price);
        System.out.println("Good-bye.");
    }

    /**
     * Method printReceipt: Prints a receipt based on the arguments
     *
     * @param name The name of the item being purchases
     * @param qty How many of that item is being purchased
     * @param price The price of one item
     */
    public static void printReceipt(String name, int qty, double price){
        // print newline
        System.out.print("\n");

        // print 50 hyphens using a loop
        printHyphenDivider();

        // Print store name
        printReceiptLine("Store: Shop265");

        // Print date 
        String date = LocalDateTime.now().toString(); //gets current time as a String
        printReceiptLine("Date: " + date); // prints the current date and time

        // print newline
        printReceiptLine("");

        // print item name
        printReceiptLine("Item: " + name);

        // print quantity
        printReceiptLine("Qty: " + qty);

        // print price
        printReceiptLine("Price: " + String.format("%.2f", price));

        //print divider with 50 periods using a loop
        printPeriodDivider();

        // print subtotal = qty*price and tax rate (formatted to 2 decimals)
        printReceiptLine("Subtotal: $" + String.format("%.2f", calculateSubtotal(price, qty)));
        printReceiptLine("Tax Rate: " + String.format("%.2f", TAX_RATE) + "%");
        printReceiptLine("");

        // print divider with 50 periods using a loop
        printPeriodDivider();

        // print total price with tax (formatted to 2 decimals)
        printReceiptLine("Total Price: $" + String.format("%.2f", calculateTotal(price, qty, TAX_RATE)));
        printReceiptLine("");

        // print thank you message
        printReceiptLine("Thanks for shopping with us.");
        printReceiptLine("Visit us online: alexwang.net");

        // print another 50 hyphens
        printHyphenDivider();

    }

    // method to print 50 hyphens and a newline
    public static void printHyphenDivider() {
        for (int i=0; i < 50; i++) {
            System.out.print('-');
        }
        System.out.print("\n");
    }

    // method to print 50 periods and a newline
    public static void printPeriodDivider() {
        for (int i=0; i < 50; i++) {
            System.out.print('.');
        }
        System.out.print('\n');
    }

    // method to print a line with a | at the start
    public static void printReceiptLine(String text) {
        System.out.println("| " + text);
    }

    // method to calculate a subtotal from a price and quantity
    public static double calculateSubtotal(double price, int quantity) {
        return price * quantity;
    }

    // method to calculate a tax-inclusive total from a price, quantity and tax rate
    public static double calculateTotal(double price, int quantity, double taxRate) {
        return price * quantity * ((taxRate/100) + 1.00);
    }
    
   /***********************************************************************
    Below are some helper methods to get input from the user in a manner 
    similar to Python. We will be discussing how this works (and a better way
    to do this) during an upcoming lecture.
   ************************************************************************/
    
    /**
     * Method inputInt prints a prompt (or question) for the user to answer and returns 
     * whatever int the user types in. Note: this method will CRASH if the user does not type
     * in an int
     *
     * @param prompt The message to print for the user
     * @return The int value the user types it
     */
    public static int inputInt(String prompt){
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        return number;
    }
    
       public static double inputDouble(String prompt){
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        double number = sc.nextDouble();
        return number;
    }
 
    public static String input(String prompt){
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        String value = sc.nextLine();
        return value;
    }
    
}
