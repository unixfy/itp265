/**
* Description: A simple demo of switch statements in Java
* @author Alex Wang
* @version Spring 2023
* ITP 265, Coffee Section
* 01/16/2024
* Email: awang756@usc.edu
*/

public class Main {
    public static void main(String[] args)  {
    // call the method with various sample inputs to make sure it works properly
       mapKey('a');
       mapKey('s');
       mapKey('w');
       mapKey('d');
        mapKey('v');

        mapKey('A'); // after the method is working.
        mapKey('W');
    }
    
    public static void mapKey(char letter){
       String command = "unknown";

        // NOTE: we declare a new var for the lowercased letter so that the println at the end still prints the original "letter" passed into this method
       char caseInsensitiveLetter = Character.toLowerCase(letter);

        // set the value of command based on the letter that mapKey is called with
       switch(caseInsensitiveLetter) {
        // note use single quotes to check letter as a char not string
         case 'w':
           command = "forward";
           break;
         case 'a':
           command = "left";
           break;
         case 's':
           command = "backward";
           break;
         case 'd': 
           command = "right";
           break;
         default:
           command = "unknown";
       }

       System.out.println(letter + " means " + command);
    }
}
