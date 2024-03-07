import java.util.*;
import java.time.*;
/**
* The Receipt Class has some functions for printing a receipt of beverages
 * ITP 265
 * 
 * Based on Sample Solution from A02, but updated to work for a list of beverages.
 */ 
public class Receipt {

    public static final double TAX_RATE = 9.5; // a constant
    /**
     * Method printReceipt: Prints a receipt based on the arguments
     *
     * @param storeName The name of the store
     * @param drinks each drink that is purchased
     */
    public static void print(String storeName, ArrayList<Beverage> drinks){
        print("-", 50);
        print("| Store: " + storeName);
        double price = 0;
        String date = LocalDate.now().toString(); //gets current time as a String
        System.out.println("| Date: " + date); // prints the current date and time
        print("|");
        for(Beverage b: drinks){
            print("| " + b);
            price += b.getPrice();
        }
        print(".", 50);


        print("| Subtotal: $" + String.format("%.2f",price ));
        print("| Tax Rate: " + String.format("%.2f", TAX_RATE) + "%");
        print(".", 50);
        double total = price + price*TAX_RATE/100.0 ;
        print("| Total Price: $" + String.format("%.2f",total ));
        print("|");
        print("| Thanks for visiting? Come again!\n| Fill out this survey for a free drink"
                + "\nhttps://forms.gle/V8sPreVqxms7N2pF9");
        print("-", 50);

    }
    public static void print(String message){
        System.out.println(message);
    }
    public static void print(String ch, int numTimes){
        for(int i = 0; i < numTimes; i++){
            System.out.print(ch);
        }
        System.out.println();
    }


}

