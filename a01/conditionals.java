/**
* Description: A simple demonstration of conditionals by using them to tell if a number is positive, negative, or zero
* @author Alex Wang
* @version Spring 2024
* ITP 265, coffee Section
* 01/09/2024
* Email: awang756@usc.edu
*/

public class Main {
    public static void main(String[] args)  {
    // call the method with various sample inputs to make sure it works properly
       checkNumber(5);
       checkNumber(0);
       checkNumber(-5);
       checkNumber(5828424);
       checkNumber(-294723);
    }
    
    public static void checkNumber (int num){
       // if a number is less than 0, then print that it's negative
       // similarly, if it's more than 0, print that it's positive
       // and of course if it equals zero, print that it's zero
       // note that num is an int not double (so comparison with == is ok)
       if(num < 0) {
         System.out.println(num + " is negative!");
       } else if (num > 0) {
         System.out.println(num + " is positive!");
       } else if (num == 0) {
         System.out.println(num + " is zero!");
       }
    }

}
