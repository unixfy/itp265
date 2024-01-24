/**
* Description: Demonstrating that floating point comparisons don't work all that * intuitively
* @author Alex Wang
* @version Spring 2024
* ITP 265, coffee Section
* 01/09/2024
* Email: awang756@usc.edu
*/


public class Main{
    public static void main(String[] args){
        double a = 1.001;
        double b = 0.001;
        double c = a - b;
        
        //   note that we use <= operator instead of == as floating points won't be exactly equal (as == expects)
        if(Math.abs(c - 1.00) <= 0.01){
            System.out.println("a-b equals 1.0");
        }
        else{
              System.out.println("a-b is NOT equal to 1.0");
        }
      
    }
}