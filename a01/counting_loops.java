/**
* Description: Simple demo of for loop by counting some numbers
* @author Alex Wang
* @version Spring 2024
* ITP 265, coffee Section
* 01/09/2024
* Email: awang756@usc.edu
*/

public class Main {
    public static void main(String[] args){
     // We have two for loops: one to produce the rows, and the other to produce the columns
     // The row for loop simply increments by one for each iteration, 1...10
     // The column for loop multiplies each value from the (outer) row for loop by 1...10 and prints the resulting value

     // Row for loop
     for (int i = 1; i <= 10; i++) {
         // Column for loop
         for(int j = 1; j <= 10; j++){
             System.out.print(i*j + " ");
         }
         System.out.println();
     }
 }

}
