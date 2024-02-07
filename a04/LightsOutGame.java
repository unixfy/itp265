import java.util.*;

/**
 * Description: This is a basic demo of arrays through a game where users try to
 * turn off all the lights
 * 
 * @author Alex Wang
 * @version Spring 2024
 *          ITP 265, coffee Section
 *          02/06/2024
 *          Email: awang756@usc.edu
 */
public class LightsOutGame {

    // instance variables
    private boolean[] lightArray; // the array of booleans that represent the lights in the game.
    private String displayType; // will be "line", "grid" or "emoji"
    private Scanner sc; // to get user input from console

    // constants
    public static final String ON = "💡";
    public static final String OFF = "⬛";

    public LightsOutGame() { // complete
        sc = new Scanner(System.in);
        makeNewBoard(); // sets up lightArray and displayType
    }

    /**
     * This method prompts the user for 3-15 (inclusive) lights, creates a bool
     * array of that size, randomly assigns each light to be on or off,
     * and sets the displayType according to what the user inputs (line, grid, or
     * emoji)
     */
    private void makeNewBoard() {
        // use the built in getNumLights to get the number of lights
        int numLights = getNumLights();

        // init array
        lightArray = new boolean[numLights];

        // fill array with random booleans (the idea is that there is a 50% chance of a
        // random nextint being even or odd)
        for (int i = 0; i < numLights; i++) {
            lightArray[i] = new Random().nextInt() % 2 == 0;
        }

        displayType = input("How would you like your game to be displayed (line, grid, or emoji)?");

        while (!(displayType.equals("line") || displayType.equals("grid") || displayType.equals("emoji"))) {
            displayType = input("Invalid input, please enter line, grid, or emoji");
        }
    }

    private int getNumLights() { // complete
        System.out.println("⬛ 💡 Welcome to Lights Out! 💡 ⬛");
        System.out.println("The objective is to turn all the lights off.");
        int choice = inputInt("How many lights would you like to have (3-15)?", 3, 15);
        return choice;

    }

    public void displayGameBoard() {
        // based on displayType, call helper methods as needed
        if (displayType.equals("line")) {
            printLine();
        } else if (displayType.equals("grid")) {
            printGrid();
        } else if (displayType.equals("emoji")) {
            printEmoji();
        }
    }

    /**
     * This method prints a singular line of lights, with each light separated by a
     * |
     */
    private void printSingleLine() {
        System.out.print("|");
        for (int i = 0; i < lightArray.length; i++) {
            if (lightArray[i]) {
                System.out.print("****|");
            } else {
                System.out.print("    |");
            }
        }
        System.out.println();
    }

    /**
     * This method prints 4 spaces if the light is off, 4 asterisks if the light is
     * on, all separated by |'s
     */
    private void printLine() {
        printSingleLine();
        printNumbers();
    }

    /**
     * This method prints the lights as emojis
     */
    private void printEmoji() {
        for (int i = 0; i < lightArray.length; i++) {
            if (lightArray[i]) {
                System.out.print(" " + ON + "  ");
            } else {
                System.out.print(" " + OFF + "  ");
            }
        }
        System.out.println();
        printNumbers();
    }

    /**
     * This method prints each "on" light as a block of 4x4 * and each "off" light
     * as a block of 4x4 spaces
     * Each light is separated by a |
     */
    private void printGrid() {
        // calls printSingleLine 4 times, then prints the numbers
        for (int i = 0; i < 4; i++) {
            printSingleLine();
        }

        printNumbers();
    }

    /**
     * This method prints a line of numbers equal to lightArray's length, each
     * separated by 3 spaces (if the number is less than 10, it will have another
     * space because it's only one digit)
     */
    private void printNumbers() {
        for (int i = 1; i <= lightArray.length; i++) {
            if (i < 10) {
                System.out.print("  " + i + "  ");
            } else {
                System.out.print("  " + i + " ");
            }
        }
        System.out.println();
    }

    public void play() {

        while (true) {
            // show the game board
            displayGameBoard();

            // put this here to handle the edge case of the user winning on the first move
            // note that we don't put hasUserwon() in the while condition because we want to
            // displaygameboard() no matter what
            // the loop will keep running until the user wins or quits (and the break
            // happens)
            if (hasUserWon()) {
                System.out.println("Congratulations! You've won!");
                break;
            }

            // first, ask the user which light they want to select
            // input of -1 causes the game to quit immediately
            // if the user selects a light that is out of bounds, they are prompted to try
            // again
            int selectedLight = inputInt(
                    "Which light would you like to select? (1-" + lightArray.length + ") or -1 to quit", 1,
                    lightArray.length, -1);

            if (selectedLight == -1) {
                break;
            }

            selectedLight -= 1; // now, subtract one because array indices start at 0

            // once a valid light number is selected, that light and its immediate neighbors
            // are all toggled
            for (int i = selectedLight - 1; i <= selectedLight + 1; i++) {
                if (i >= 0 && i < lightArray.length) {
                    lightArray[i] = !lightArray[i];
                }
            }
        }

        // put this condition here to ask the user if they want to play again when the while loop breaks
        if (inputYesNo("Thank you for playing. Would you like to play again? (yes/y/no/n)")) {
            makeNewBoard();
            play();
        } else {
            System.out.println("Goodbye!");
        }

    }

    /**
     * This method checks if all the lights are off (i.e., if the user has won)
     */
    private boolean hasUserWon() {
        for (int i = 0; i < lightArray.length; i++) {
            if (lightArray[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]) { // complete
        LightsOutGame myGame = new LightsOutGame();
        myGame.play();
    }

    /*********************************************************************************************************
     * Helper methods for input and stuff
     *********************************************************************************************************/
    public void print(String output) {
        System.out.println(output);
    }

    /**
     * Prompt the user and read one line of text as a String
     * 
     * @param prompt: the question to ask the user
     * @return: a line of user input (including spaces, until they hit enter)
     */
    public String input(String prompt) {
        System.out.print(prompt + " : ");
        return sc.nextLine();
    }

    /**
     * Prompt the user and read an int, clearing whitespace or the enter after the
     * number
     * 
     * @param prompt: the question to ask the user
     * @return: an int
     */
    public int inputInt(String prompt) {
        System.out.print(prompt + " : ");
        int input = 0;
        while (!sc.hasNextInt()) {
            String garbage = sc.nextLine();
            System.out.println(garbage + " was not an integer please try again. ");
            System.out.print(prompt + " : ");

        }
        input = sc.nextInt();
        sc.nextLine(); // clear buffer
        return input;
    }

    /**
     * Prompt the user and read an int between (inclusive) of minValue and maxValue,
     * clearing whitespace or the enter after the number
     * 
     * @param prompt: the question to ask the user
     * @return: an int between minValue and maxValue
     */
    public int inputInt(String prompt, int minValue, int maxValue) {
        int input = inputInt(prompt);
        while (input > maxValue || input < minValue) {
            System.out.println("Invalid input, please enter value " + minValue + " - " + maxValue);
            input = inputInt(prompt);
        }
        return input;
    }

    public int inputInt(String prompt, int minValue, int maxValue, int quit) {
        int input = inputInt(prompt);
        while (!(input == quit || (input <= maxValue && input >= minValue))) {
            System.out.println(
                    "Invalid input, please enter value " + minValue + " - " + maxValue + " or " + quit + " to quit");
            input = inputInt(prompt);
        }
        return input;
    }

    /**
     * Prompt the user and read a floating point number, clearing whitespace or the
     * enter after the number (error checking requiring a double to be entered)
     * 
     * @param prompt: the question to ask the user
     * @return: a double value
     */
    public double inputDouble(String prompt) {
        System.out.print(prompt + " : ");
        double input = 0;
        while (!sc.hasNextDouble()) {
            String garbage = sc.nextLine();
            System.out.println(garbage + " was not an double please try again. ");
            System.out.print(prompt + " : ");
        }
        input = sc.nextDouble();
        sc.nextLine(); // clear buffer
        return input;
    }

    /**
     * Prompt the user and read a boolean value, clearing whitespace or the enter
     * after the number
     * 
     * @param prompt: the question to ask the user
     * @return: a boolean value
     */
    public boolean inputBoolean(String prompt) {
        System.out.print(prompt + " : ");
        boolean input = false;
        while (!sc.hasNextBoolean()) {
            String garbage = sc.nextLine();
            System.out.println(garbage + " was not a boolean please try again. Type true or false");
            System.out.print(prompt + " : ");

        }
        input = sc.nextBoolean();
        sc.nextLine(); // clear buffer
        return input;
    }

    /**
     * Prompt the user enter yes or no (will match y/yes and n/no any case) and
     * return true for yes and false for no.
     * 
     * @param prompt: the question to ask the user
     * @return: a boolean value
     */
    public boolean inputYesNo(String prompt) {
        // Enter a loop to ask for a guess until a valid response (yes/no) is inputted
        String input = input(prompt);
        while (!(input.equalsIgnoreCase("Yes") || input.equalsIgnoreCase("Y")
                || input.equalsIgnoreCase("N") || input.equalsIgnoreCase("No"))) { // not good
            System.out.println(input + " was Invalid, please type yes or no");
            input = input(prompt);
        }
        return input.equalsIgnoreCase("Yes") || input.equalsIgnoreCase("Y"); // true for yes, false for no
    }
}