/**
* Description: This program is some practice with division & the modulus operator in Java
* @author Alex Wang
* @version Spring 2024
* ITP 265, coffee Section
* 01/09/2024
* Email: awang756@usc.edu
*/


public class Main {
    public static void main(String[] args)  {
    // call the method with various sample inputs to make sure it works properly
       splitInHalf(5); 
       splitInHalf(0);
       splitInHalf(257);
       giveChange(99);
       giveChange(35);
    }
    
    public static void splitInHalf(int num){
        // declare variable divided by two; note: must cast num to double for precision reasons
        double dividedByTwo = (double)num / 2;
        System.out.println("Half of " + num + " is " + dividedByTwo);
    }


    private static void giveChange(int amount) {
        // the fact that Java has integer division is really useful here
        // we use modulus to figure out what's left over after "giving" change of a certain denomination
        // then use int division to figure out how much change in each denomination we can "give"
        int quarters = amount / 25;
        int dimes = (amount % 25) / 10; 
        int nickels = ((amount % 25) % 10) / 5;
        int pennies = 0; // did we need this variable? not referenced anywhere
        int remainder = (((amount % 25) % 10) % 5);

        System.out.println("Here are the coins to give for " + amount + " cents in change");
        System.out.println("\tQuarters: " + quarters);
        System.out.println("\tDimes: " + dimes);
        System.out.println("\tNickels: " + nickels);
        System.out.println("\tPennies: " + remainder);
    
    }
}
