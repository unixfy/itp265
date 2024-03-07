/**
 * The Beverage Class represents a beverage object
 * ITP 265 
 * 
 * Sample Solution 
 */


public class Beverage
{
    // instance variables
    private String name;
    private double price;
    private char size;
    
    // class variables 
    private static double SMALL_PRICE = 2.99;
    private static double MEDIUM_PRICE = 3.99; 
    private static double LARGE_PRICE = 4.99;

    /**
     * Full version of the Constructor
     * Take in the parameters and save them to the instance variables, price will be set based on the size
     * @param name
     * @param size
     */
    public Beverage(String name, char size) {

        this.name = name; 
        setSize(size);
    }

    public String getName(){
        return name;
    }
    public void setName(String newName){
        name = newName;
    }
    
    public double getPrice(){
        return price;
    }
    
    public char getSize(){
        return size;
    }

    /**
    * The set method for size will set the size of the beverage to S, M, or L (all uppercase). It will match any case version 
    * of s, m, or l. If a different character is passed in, then size will be set to L. This method also updates the price instance variable 
    * so the price  corresponds to the size.
    */
    public void setSize(char newSize){
        if(newSize == 'S' || newSize == 's' ){
            size = 'S'; // standardize to upper case.
            price = SMALL_PRICE;
        }
        else if(newSize == 'M' || newSize == 'm' ){
            size = 'M'; // standardize to upper case.
            price = MEDIUM_PRICE;
        }
        else{
             size = 'L'; // standardize to upper case.
             price = LARGE_PRICE;
        }
    }
    
    /**
     * This method takes all of each beverage's information and formats it into an user friendly way. 
     * @return: a nicely formatted 
     */
    public String toString() {
     
        String beverageInfo = "";
        if (this.size == 'S'){
            beverageInfo = "Small "; 
        } else if (this.size == 'M'){
            beverageInfo = "Medium ";
        } else {
            beverageInfo = "Large " ;
        }
        beverageInfo += name + ", $" + price;
        return beverageInfo;
    }
    
    /**
     * This method compares if two beverages are the same. 
     *
     * @param: another Beverage object
     * @return: a boolean of whether two beverages are the same
     */
    public boolean equals(Beverage other){
        return this.name.equalsIgnoreCase(other.name)
        && this.size == other.size
        && Math.abs(this.price - other.price) <= 0.001;
    }
    
} 
