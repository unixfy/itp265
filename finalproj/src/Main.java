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
    private final HashMap<User, ArrayList<Service>> userBookingMap;
    private final HashMap<String, User> userDatabase;
    private static final BFF bff = new BFF();
    private User currentUser;
    private final ArrayList<Service> currentUserBookingQueue;

    public Main() {
        // todo: logic to load users from file into userDatabase
        this.userBookingMap = new HashMap<>();
        this.userDatabase = new HashMap<>();
        this.currentUserBookingQueue = new ArrayList<>();
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
        bff.print("Please choose an option:");

        bff.print("1. Add service to your booking order");
        bff.print("2. View or finalize a booking order");
        bff.print("3. Remove service from a booking order");
        bff.print("4. View booking history");
        bff.print("5. Cancel previous booking");
        bff.print("6. Upgrade/downgrade account");
        bff.print("7. Logout");

//        if the user is admin, show admin options
        if (currentUser instanceof AdminUser) {
            bff.print("8. Ban a user");
            bff.print("9. Unban a user");
        }
    }

    private void transitionUserTier() {
        // allows user to transition between free and premium
        if (currentUser instanceof FreeUser) {
            // drop the user from the database and re-add them as a premium user
            userDatabase.remove(currentUser.getUsername());
            currentUser = ((FreeUser) currentUser).upgrade();
            userDatabase.put(currentUser.getUsername(), currentUser);
            bff.print("Congratulations! You are now a premium user!");
            bff.print("The total cost of the upgrade is $" + ((PremiumUser) currentUser).getPrice() + ".");
        } else if (currentUser instanceof PremiumUser) {
            // drop the user from the database and re-add them as a free user
            userDatabase.remove(currentUser.getUsername());
            currentUser = ((PremiumUser) currentUser).downgrade();
            userDatabase.put(currentUser.getUsername(), currentUser);
            bff.print("You are now a free user.");
        } else {
            bff.print("You're not eligible to use this function.");
        }
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

                    bff.print("Welcome back, " + currentUser.getName() + "!");
                    if (currentUser instanceof PremiumUser) {
                        bff.print("Thank you for being a premium user!");
                    }
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
        return currentUser != null;
    }

    private void quit() {
        bff.print("Goodbye!");

        // todo: write everything to disk
    }

    private void processBookingOrder() {
        if (currentUserBookingQueue.isEmpty()) {
            bff.print("You have no bookings to finalize.");
        } else {
            bff.print("These are the bookings you have in your cart:");
            for (int i = 0; i < currentUserBookingQueue.size(); i++) {
                bff.print((i + 1) + ". " + currentUserBookingQueue.get(i).toString());
            }
            bff.print("Do you want to finalize all of these bookings?");
            boolean finalize = bff.inputYesNo("Enter 'yes' or 'no': ");
            if (finalize) {
                // add all the bookings to the user's list of bookings
                // make sure to create a new ArrayList if the user doesn't have any bookings yet
                userBookingMap.computeIfAbsent(currentUser, k -> new ArrayList<>());
                userBookingMap.get(currentUser).addAll(currentUserBookingQueue);
                // clear the user's booking queue
                currentUserBookingQueue.clear();

                bff.print("Bookings finalized!");
            }
        }
    }

    private void cancelBooking() {
        if (userBookingMap.get(currentUser) == null || userBookingMap.get(currentUser).isEmpty()) {
            bff.print("You have no bookings to cancel.");
        } else {
            // list all of the user's bookings
            // user selects a booking to cancel
            // remove the booking from the user's list of bookings
            bff.print("These are the bookings you may cancel:");
            for (int i = 0; i < userBookingMap.get(currentUser).size(); i++) {
                bff.print((i + 1) + ". " + userBookingMap.get(currentUser).get(i).toString());
            }
            int cancellationTargetBooking = bff.inputInt("Enter the number of the booking you would like to cancel: ", 1, userBookingMap.get(currentUser).size());
            // remove the booking from the user's list of bookings
            // note that ArrayList.remove automatically shifts everything to the left
            userBookingMap.get(currentUser).remove(cancellationTargetBooking);
        }
    }

    private void addServicetoBookingQueue() {
//        ask the user which type of service they want to book
        bff.print("These are the services you may book.");
        bff.print("1: Flight");
        bff.print("2: Hotel");
        bff.print("3: Cruise");

        int serviceChoice = bff.inputInt("Enter the number of the service you would like to book: ", 1, 4);

        switch (serviceChoice) {
            case 1:
                // book a flight

                // allow user to select an operator
                bff.print("These are the operators you may book a flight with.");
                for (FlightOperators operator : FlightOperators.values()) {
                    bff.print((operator.ordinal() + 1) + ": " + operator);
                }

                int operatorChoice = bff.inputInt("Enter the number of the operator you would like to book with: ", 1, FlightOperators.values().length);
                FlightOperators operator = FlightOperators.values()[operatorChoice - 1];

                String flightNumber = bff.inputWord("Enter the flight number: ");
                String departureCity = bff.inputWord("Enter the departure airport: ");
                String arrivalCity = bff.inputWord("Enter the arrival airport: ");
                double price = bff.inputDouble("Enter the price: ");

                // allow the user to select a fare class
                bff.print("These are the fare classes you may book.");
                for (FlightFareClasses fareClass : FlightFareClasses.values()) {
                    bff.print((fareClass.ordinal() + 1) + ": " + fareClass);
                }
                int fareClassChoice = bff.inputInt("Enter the number of the fare class you would like to book: ", 1, FlightFareClasses.values().length);
                FlightFareClasses fareClass = FlightFareClasses.values()[fareClassChoice - 1];

                FlightBooking newFlight = new FlightBooking(operator, flightNumber, departureCity, arrivalCity, price, fareClass);
                currentUserBookingQueue.add(newFlight);
                newFlight.book();
                break;
            case 2:
                // book a hotel
                String city = bff.inputWord("Enter the city: ");
                int numRooms = bff.inputInt("Enter the number of rooms: ");
                double nightlyRate = bff.inputDouble("Enter the nightly rate: ");
                int numNights = bff.inputInt("Enter the number of nights: ");
                Hotel newHotel = new Hotel(numRooms, nightlyRate, numNights, city);

                currentUserBookingQueue.add(newHotel);
                newHotel.book();
                break;
            case 3:
                // onlyl premium user can book a cruise
                if (currentUser instanceof PremiumUser) {
                    // book a cruise
                    String cruiseName = bff.inputWord("Enter the cruise name: ");
                    String departurePort = bff.inputWord("Enter the departure port: ");
                    String arrivalPort = bff.inputWord("Enter the arrival port: ");
                    double ticketPrice = bff.inputDouble("Enter the ticket price: ");
                    Cruise newCruise = new Cruise(cruiseName, arrivalPort, departurePort, ticketPrice);
                    currentUserBookingQueue.add(newCruise);
                    newCruise.book();
                } else {
                    bff.print("You must be a premium user to book a cruise.");
                }
                break;
        }
    }

    private void removeServiceFromBookingQueue() {
        if (currentUserBookingQueue.isEmpty()) {
            bff.print("You have no bookings to remove.");
        } else {
            bff.print("These are the bookings you have in your cart:");
            for (int i = 0; i < currentUserBookingQueue.size(); i++) {
                bff.print((i + 1) + ". " + currentUserBookingQueue.get(i).toString());
            }
            int removalTargetBooking = bff.inputInt("Enter the number of the booking you would like to remove: ", 1, currentUserBookingQueue.size());
            // remove the booking from the user's list of bookings
            // note that ArrayList.remove automatically shifts everything to the left
            currentUserBookingQueue.remove(removalTargetBooking);
        }
    }

    private void showBookingHistory() {
        if (userBookingMap.get(currentUser) == null || userBookingMap.get(currentUser).isEmpty()) {
            bff.print("You have no bookings.");
        } else {
            bff.print("These are your bookings:");
            for (int i = 0; i < userBookingMap.get(currentUser).size(); i++) {
                bff.print((i + 1) + ". " + userBookingMap.get(currentUser).get(i).toString());
            }
        }
    }

    //    return true if the user is successfully banned
    private boolean banUser(String username) {
        if (userDatabase.get(username) != null) {
            userDatabase.get(username).setBanned(true);
            return true;
        } else {
            return false;
        }
    }

    //    return true if the user is successfully unbanned
    private boolean unbanUser(String username) {
        if (userDatabase.get(username) != null) {
            userDatabase.get(username).setBanned(false);
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        while (true) {
            // user menu
            if (main.verifyUserLoggedIn()) {
                main.printUserMenu();
                int userChoice = bff.inputInt("Enter your choice: ", 1, 9);

                switch (userChoice) {
                    case 1:
                        main.addServicetoBookingQueue();
                        break;
                    case 2:
                        main.processBookingOrder();
                        break;
                    case 3:
                        main.removeServiceFromBookingQueue();
                        break;
                    case 4:
                        // view booking history
                        main.showBookingHistory();
                        break;
                    case 5:
                        main.cancelBooking();
                        break;
                    case 6:
                        main.transitionUserTier();
                        break;
                    case 7:
                        // logout
                        main.currentUser = null;
                        break;
                    case 8:
                        if (main.currentUser instanceof AdminUser) {
                            String targetUsername = bff.inputWord("Enter the username of the user you would like to ban: ");
                            if (main.banUser(targetUsername)) {
                                bff.print("User banned.");
                            } else {
                                bff.print("User not found.");
                            }
                        } else {
                            bff.print("You're not an admin.");
                        }
                        break;
                    case 9:
                        if (main.currentUser instanceof AdminUser) {
                            String targetUsername = bff.inputWord("Enter the username of the user you would like to unban: ");
                            if (main.unbanUser(targetUsername)) {
                                bff.print("User unbanned.");
                            } else {
                                bff.print("User not found.");
                            }
                        } else {
                            bff.print("You're not an admin.");
                        }
                        break;
                }

            } else {
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
                        main.quit();
                        System.exit(0);
                }
            }
        }
    }
}