/**
* Description: A simple demo of conditionals to tell me if someone is a teenager
* @author Alex Wang
* @version Spring 2023
* ITP 265, Coffee Section
* 01/16/2024
* Email: awang756@usc.edu
*/


public class Conditionals {

    public static void testTeenager(int age, boolean isGrumpy){
        //print the values passed in to the method 
        System.out.println("Passed in " + age + " and " + isGrumpy + " to testTeenager");
       //then use chart to determine what to print
        if (age >= 13 && age <= 19 && isGrumpy) {
            System.out.println("Definitely a moody teenager");
        } else if (age >= 13 && age <= 19 && !isGrumpy) {
            System.out.println("Definitely a teenager. Be thankful they aren't grumpy.");
        } else if (age > 10 && age < 13 && isGrumpy) {
            System.out.println("Definitely a preteen!");
        } else {
            System.out.println("Not a teenager");
        }
    }
    public static void main (String[] args) {
        //declare two variables
        int num1 = 14;
        boolean isMoody = true;
        //call testTeenager
        testTeenager(num1, isMoody);

        // from the lesson
        testTeenager(Integer.parseInt(args[0]), Boolean.parseBoolean(args[1]));
        testTeenager(Integer.parseInt(args[2]), Boolean.parseBoolean(args[3]));
        testTeenager(Integer.parseInt(args[4]), Boolean.parseBoolean(args[5]));
    }
}
