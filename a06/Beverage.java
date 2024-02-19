/**
 * A class that represents a beverage
 * 
 * @author Alex Wang
 *         awang756@usc.edu
 *         ITP 265, Spring 2024
 *         coffee Class Section
 **/

public class Beverage {
    private String name; // needs accessor and mutator
    private double price; // needs accessor
    private char size; // needs accessor and mutator, limited to S/M/L

    // constants
    private static final double SMALL_PRICE = 2.99;
    private static final double MEDIUM_PRICE = 3.99;
    private static final double LARGE_PRICE = 4.99;

    // constructor takes name and size as arguments and calculate the price
    public Beverage(String name, char size) {
        this.name = name;

        if (size == 'S' || size == 'M' || size == 'L') {
            this.size = size;
        } else {
            this.size = 'L';
        }

        this.price = getPriceFromSize(this.size);
    }

    private double getPriceFromSize(char size) {
        if (size == 'S') {
            return SMALL_PRICE;
        } else if (size == 'M') {
            return MEDIUM_PRICE;
        } else if (size == 'L') {
            return LARGE_PRICE;
        } else {
            return LARGE_PRICE;
        }
    }

    // accessor for name
    public String getName() {
        return name;
    }

    // mutator for name
    public void setName(String name) {
        this.name = name;
    }

    // accessor for price
    public double getPrice() {
        return price;
    }

    // accessor for size
    public char getSize() {
        return size;
    }

    // mutator for size
    public void setSize(char size) {
        if (size == 'S' || size == 'M' || size == 'L') {
            this.size = size;
        } else {
            this.size = 'L';
        }
        // also set the price
        this.price = getPriceFromSize(this.size);
    }

    // toString method
    public String toString() {
        // turn char size into a string
        String sizeString = "";
        if (size == 'S') {
            sizeString = "Small";
        } else if (size == 'M') {
            sizeString = "Medium";
        } else if (size == 'L') {
            sizeString = "Large";
        }

        return sizeString + " " + name + ", $" + price;
    }

    // equals method
    public boolean equals(Beverage other) {
        return this.name.equalsIgnoreCase(other.name) && this.size == other.size && Math.abs(this.price - other.price) < 0.01;
    }

    // code for testing
    // public static void main(String[] args) {
    //     // create two identical beverages, test if they're equal
    //     Beverage b1 = new Beverage("Coffee", 'M');
    //     Beverage b2 = new Beverage("Coffee", 'M');
    //     Beverage b3 = new Beverage("Iced Tea", 'S');
    //     Beverage b4 = new Beverage("Espresso", 'L');
    //     System.out.println(b1.equals(b2)); // should print true
    //     System.out.println(b1); // should print "Medium Coffee, $3.99"

    //     System.out.println(b3);
    //     System.out.println(b4);
    //     System.out.println(b3.equals(b4)); // should print false
    // }

}