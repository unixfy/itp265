/**
* Description: Simple demo of boolean operators in Java with some integer comparisons
* @author Alex Wang
* @version Spring 2024
* ITP 265, coffee Section
* 01/09/2024
* Email: awang756@usc.edu
*/

public class Main {
    public static void main(String[] args)  {
    // call the method with various sample inputs to make sure it works properly
       System.out.println("isValid(5, 10) returned: " + isValid(5,10) + " (should be true)");
        System.out.println("isValid(-1, 10) returned: " + isValid(-1,10)+ " (should be true)");
       System.out.println("isValid(11, 10) returned: " + isValid(11,10)+ " (should be false)");
    }
    
    public static boolean isValid (int num, int max){
       // Return true if the num is between 1 and max or equal to -1, false otherwise
       // I'm going to assume "between" is inclusive
      return ((num >= 1 && num <= max) || (num == -1));
    }

}
