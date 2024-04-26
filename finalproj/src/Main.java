/**
 * Our main class, handles all the interaction with the user (the view and the controller)
 *
 * @author Alex Wang
 * Spring 2024, ITP 265, coffee
 * Email: awang756@usc.edu
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    private ArrayList<Service> bookingDatabase;
    private HashMap<User, Service> userBookingMap;
    private HashMap<String, User> userDatabase;
    private static final BFF bff = new BFF();
    private User currentUser;

    public Main() {
        // todo: logic to load users from file into userDatabase
        // todo: logic to load services from file into bookingDatabase
        this.bookingDatabase = new ArrayList<>();
        this.userBookingMap = new HashMap<>();
        this.userDatabase = new HashMap<>();
        this.currentUser = null;
    }

    private void printUserAuthMenu() {
        bff.print("Welcome to Alex's travel booking service. Please choose an option:");

        bff.print("1. Login");
        bff.print("2. Create Account");
        bff.print("3. Exit");
    }

    private User findUser(String username) {
        return userDatabase.get(username);
    }

    private void printUserMenu() {
        bff.print("Welcome back, " + currentUser.getName() + "!");
        if (currentUser instanceof PremiumUser) {
            bff.print("Thank you for being a premium user!");
        }
        bff.print("Please choose an option:");

        bff.print("1. Book a service");
        bff.print("2. View your bookings");
        bff.print("3. Cancel a booking");
        bff.print("4. Logout");
        bff.print("5. Upgrade/downgrade account");
    }

    private void transitionUserTier() {
        // todo: allows user to transition between free and premium
    }

    private void userLogin() {
        String username = bff.inputWord("Enter your username: ");

        // if user exists, check if password is correct
        if (this.findUser(username) != null) {
            String password = bff.inputWord("Enter your password: ");

            if (this.findUser(username).verifyPassword(password)) {
                if (this.findUser(username).isBanned()) {
                    bff.print("You are banned!");
                } else {
                    this.currentUser = this.findUser(username);
                }
            } else {
                bff.print("Incorrect password");
            }
        } else {
            bff.print("User not found!");
        }
    }

    private void userRegister() {
        String username = bff.inputWord("Enter your username: ");

        // check if user already exists
        if (this.findUser(username) != null) {
            bff.print("User already exists!");
            return;
        } else {
            // create a new user
            String name = bff.inputWord("Enter your name: ");

            String password = bff.inputWord("Enter your password: ");
            String passwordConfirm = bff.inputWord("Confirm your password: ");

            if (!password.equals(passwordConfirm)) {
                bff.print("Passwords do not match!");
                return;
            }

            // instantiate new FreeUser
            User newUser = new FreeUser(name, username, password);
            this.userDatabase.put(username, newUser);

            this.currentUser = newUser;
            bff.print("Successfully registered!");
        }
    }

    private boolean verifyUserLoggedIn() {
        if (currentUser == null) {
            System.out.println("You must be logged in!");
            return false;
        } else {
            return true;
        }
    }

    private void quit() {
        bff.print("Goodbye!");

        // write everything to disk
    }


    public static void main(String[] args) {
        Main main = new Main();
        while (true) {
            // authentication
            main.printUserAuthMenu();

            int choice = bff.inputInt("Enter your choice: ", 1, 3);

            switch (choice) {
                case 1:
                    main.userLogin();
                    break;
                case 2:
                    main.userRegister();
                    break;
                case 3:
                    System.exit(0);
                    break;
            }

            // user menu
            if (main.verifyUserLoggedIn()) {
                main.printUserMenu();
                int userChoice = bff.inputInt("Enter your choice: ", 1, 4);

                switch (userChoice) {
                    case 1:
                        // book a service
                        break;
                    case 2:
                        // view bookings
                        break;
                    case 3:
                        // cancel booking
                        break;
                    case 4:
                        // logout
                        break;
                    case 5:
                        // upgrade/downgrade account
//                        main.transitionUserTier();
                        break;
                }

            }
        }
    }
}