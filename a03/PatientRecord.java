import java.time.LocalDate;
import java.util.Scanner; // import scanner

/**
 * A demo of scanner and Java helper functions in a primitive health records
 * application
 * 
 * @author Alex Wang
 *         Email: awang756@usc.edu
 *         ITP 265, Spring 2024, coffee Class Section
 **/

public class PatientRecord {
    // Step 1: Define Variables -- define all variables for gathering input

    private static String firstName;
    private static String lastName;
    private static char gender;
    private static int birthMonth;
    private static int birthDay;
    private static int birthYear;
    private static double height;
    private static double weight;
    private static boolean covidVaccinated;
    private static String allergies;

    private static int age;

    /**
     * This method prints a prompt and returns the user's input as a String
     */
    public static String input(String prompt) {
        System.out.print(prompt + ": ");
        Scanner sc = new Scanner(System.in);
        String value = sc.nextLine();
        return value;
    }

    /**
     * This method prints a prompt and returns the user's input as an int
     */
    public static int inputInt(String prompt) {
        System.out.print(prompt + ": ");
        Scanner sc = new Scanner(System.in);
        int value = sc.nextInt();
        return value;
    }

    /**
     * This method prints a prompt and returns the user's input as a double
     */
    public static double inputDouble(String prompt) {
        System.out.print(prompt + ": ");
        Scanner sc = new Scanner(System.in);
        double value = sc.nextDouble();
        return value;
    }

    public static void main(String[] args) {

        // print a nice message
        System.out.println("Welcome to the HealthProfile system. Please enter patient information.");

        // Step 2: Data Collection and Validation

        // get firstName
        firstName = input("Please enter first name");

        // get lastName
        lastName = input("Please enter last name");

        // get gender
        // get first char
        gender = input("Please enter gender (M/F/O)").toLowerCase().charAt(0);
        // validate gender is 'm' 'f' or 'o'
        while (gender != 'm' && gender != 'f' && gender != 'o') {
            System.out.println(gender + " is not a valid gender.");
            gender = input("Please enter gender (M/F/O)").toLowerCase().charAt(0);
        }

        // get birthMonth
        birthMonth = inputInt("Please enter birth month (1-12)");
        // validate birthMonth between 1-12
        while (birthMonth < 1 || birthMonth > 12) {
            System.out.println(birthMonth + " is not a valid birth month.");
            birthMonth = inputInt("Please enter birth month (1-12)");
        }

        // get birthDay
        birthDay = inputInt("Please enter birth day (1-31)");
        // validate birthDay between 1-31
        while (birthDay < 1 || birthDay > 31) {
            System.out.println(birthDay + " is not a valid birth day.");
            birthDay = inputInt("Please enter birth day (1-31)");
        }

        // get birthYear
        birthYear = inputInt("Please enter birth year (1900-2023)");
        // validate birthYear between 1900-2023
        while (birthYear < 1900 || birthYear > 2023) {
            System.out.println(birthYear + " is not a valid birth year.");
            birthYear = inputInt("Please enter birth year (1900-2023)");
        }

        // get height
        height = inputDouble("Please enter height in inches");
        // validate height between 20-90
        while (height < 20 || height > 90) {
            System.out.println(height + " is not a valid height.");
            height = inputDouble("Please enter height in inches");
        }

        // get weight
        weight = inputDouble("Please enter weight (in pounds)");
        // validate weight between 5-300
        while (weight < 5 || weight > 300) {
            System.out.println(weight + " is not a valid weight.");
            weight = inputDouble("Please enter weight (in pounds)");
        }

        // get allergies
        allergies = input("Please enter all of your allergies in a list (if any)");
        // if the user just hits enter, set allergies to "none"
        if (allergies.equals("")) {
            allergies = "None";
        }

        // get covidVaccinaed
        covidVaccinated = Boolean.parseBoolean(input("Are you vaccinated for covid? (Enter \"true\" or \"false\")"));

        // Step 3: initial simple print of record - make a helper function
        printSimpleRecord();

        // STOP AND TEST YOUR CODE BEFORE GOING FORWARD

        // Step 4: Additional functionality

        age = calculateAge();

        // Step 5: Printing out the final health record in a nice format
        printFullRecord();
    }

    /**
     * This method prints a simple version of all the patient's information
     */
    public static void printSimpleRecord() {
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Gender: " + gender);
        System.out.println("DOB: " + birthMonth + "/" + birthDay + "/" + birthYear);
        // format weight and height with one decimal point
        System.out.println("Height: " + String.format("%.1f", height) + " inches");
        System.out.println("Weight: " + String.format("%.1f", weight) + " pounds");
        System.out.println("Allergies: " + allergies);
        System.out.println("Covid Vaccinated: " + covidVaccinated);
    }

    // Add Additional Functions as needed

    /**
     * This method calculates the user's age based on their birthdate
     */
    public static int calculateAge() {
        int currentYear = LocalDate.now().getYear();
        int currentMonth = LocalDate.now().getMonthValue();
        int currentDay = LocalDate.now().getDayOfMonth();

        int age = currentYear - birthYear;

        // if the current month is before the birth month, or if the current month is
        // the birth month and the current day is before the birth day, then subtract 1
        // from the age
        if (currentMonth < birthMonth) {
            age--;
        } else if (currentMonth == birthMonth && currentDay < birthDay) {
            age--;
        }

        return age;
    }

    /**
     * This method returns the user's maximum and target heart rates
     * according to the formula:
     * maximum = 220 - age in years
     * target = 50 to 85% of maximum heart rate
     * returns results as a string
     */
    public static String getHeartRateMessage() {
        int age = calculateAge();
        double maxHeartRate = 220.0 - (double) age;
        double targetHeartRateMin = (maxHeartRate * 0.5);
        double targetHeartRateMax = (maxHeartRate * 0.85);

        String message = "Maximum heart rate is: " + String.format("%.1f", maxHeartRate) + ". "
                + "Target heart rate is:  " + String.format("%.2f", targetHeartRateMin) + " - "
                + String.format("%.2f", targetHeartRateMax);

        return message;
    }

    /**
     * This method calculates the person's BMI as a double
     * according to the formula:
     * (weight * 703) / (height ^2)
     */
    public static double getBMI() {
        double bmi = (weight * 703) / (height * height);
        return bmi;
    }

    /**
     * This method produces a BMI chart, the user's BMI, and the category that it
     * puts them in
     */
    public static String getBMIMessage() {
        double bmi = getBMI();
        String bmiCategory;

        // use a bunch of conditionals to calculate the category
        if (bmi < 18.5) {
            bmiCategory = "Underweight";
        } else if (bmi < 25) {
            bmiCategory = "Normal";
        } else if (bmi < 30) {
            bmiCategory = "Overweight";
        } else {
            bmiCategory = "Obese";
        }

        String message = "BMI VALUES: \n";

        // BMI chart given in Edstem
        message += "\tUnderweight: less than 18.5\n";
        message += "\tNormal: between 18.5 and 24.9\n";
        message += "\tOverweight: between 25 and 29.9\n";
        message += "\tObese: 30 or greater\n";

        message += "Patient's BMI is: " + String.format("%.1f", bmi) + ", which puts them in the " + bmiCategory
                + " category\n";

        return message;
    }

    /**
     * This method uses a loop to print 75 asterisks
     */
    public static void printAsterisks() {
        for (int i = 0; i < 75; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    /**
     * This method prints a line with a "*" before it
     */
    public static void printLine(String line) {
        System.out.println("* " + line);
    }

    /**
     * This method prints the full health record in a nice format
     * and warns the user if they need a covid vaccine
     */
    public static void printFullRecord() {
        printAsterisks();

        // print the required health info
        printLine("Health Profile for " + firstName + " " + lastName);
        printLine("Gender: " + gender);
        printLine("DOB: " + birthMonth + "/" + birthDay + "/" + birthYear);
        // format weight and height with one decimal point
        printLine("Height: " + String.format("%.1f", height) + " inches");
        printLine("Weight: " + String.format("%.1f", weight) + " pounds");
        printLine("Age: " + age);
        printLine("Heart info: " + getHeartRateMessage());
        printLine("ALLERGIES: " + allergies);
        printLine("");

        // alert the user if they need a covid vaccine
        if (!covidVaccinated) {
            printLine("ALERT: NEEDS COVID VACCINE");
        } else if (covidVaccinated) {
            printLine("Vaccines are up to date");
        }

        // print asterisks
        printAsterisks();

        // don't use printLine because we don't want asterisks
        System.out.print(getBMIMessage());

        // print more asterisks
        printAsterisks();
    }

}