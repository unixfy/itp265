import java.util.*;
/**
* Description: A simple demo of while loops to check number input
* @author Alex Wang
* @version Spring 2023
* ITP 265, Coffee Section
* 01/16/2024
* Email: awang756@usc.edu
*/
public class InputCheck{

    public static void main(String[] args){
        //Test the method you write!
        int answer = getNumberBetween(1, 10);
        System.out.println("User entered the number: " + answer);
    }

    /**
     * Method getNumberBetween uses the inputInt method to ask the user to input a number between
     * the two provided values (inclusive of min, exclusive of max). 
     * It provides error checking to make sure the int is valid before returning the 
     * user-provided number
     *
     * @param min The smaller int value (inclusive)
     * @param max The larger int value (exclusive)
     * @return an int between min and max
     */
    public static int getNumberBetween(int min, int max){
        int num = inputInt("Please enter a number between " + min + " and " + max);
       
       // While the num is outside the specified range, continue to request the user's input
        while(!(num >= min && num < max)) {
            System.out.println("Invalid input: " + num);
            num = inputInt("Try again. Enter a number between " + min + " and " + max);
        }

        return num;
    }


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

}