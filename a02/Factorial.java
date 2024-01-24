/**
* Description: A simple demo of for loops to calculate factorials
* @author Alex Wang
* @version Spring 2023
* ITP 265, Coffee Section
* 01/16/2024
* Email: awang756@usc.edu
*/

public class Factorial{
    public static void main(String[] args){
       // call the calculate method a few times
       // note that we can't do anything bigger than 20! because longs are not of unlimited size
        System.out.println("5! is " + calculate(5));
        System.out.println("20! is " + calculate(20));
        System.out.println("0! is " + calculate(0));
        System.out.println("1! is " + calculate(1));
    }
 
    public static long calculate(int number){
        // declare var for result which will be returned
        // note that we init this as 1 so that 0! = 1
        long result = 1;

        // for loop to evaluate factorial (basically keep multiplying by a monotonically increasing i until i = number)
        for(long i = 1; i <= number; i++) {
            result = result*i;
        }
        // and return the result
        return result;
    }
}