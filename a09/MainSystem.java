import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainSystem {
    private final BFF helper;
    private final HashMap<String, User> userDatabase;
    private User currentUser;

    public MainSystem() {
        this.helper = new BFF();
        this.userDatabase = new HashMap<String, User>();
        this.currentUser = null;
    }

    public static void main(String[] args) {
        MainSystem program = new MainSystem();
        program.run();
    }

    private User findUser(String name) {
        return userDatabase.get(name); // by default, this method will return null if the user is not found which is perfect for our use
    }

    private boolean verifyUser() {
        // verifies if currentUser is not null
        if (currentUser == null) {
            System.out.println("You must be logged in!");
            return false;
        } else {
            return true;
        }
    }

    public void run() {
        // loop that displays login menu, gets a choice as a number using bff, translates to a loginmenu option, and have a switch statement for each case of a menu option

        while (true) {
            // display login menu
            System.out.println(LoginMenu.getMenuString());
            // get user choice
            // note the only valid choices are between 0 and 1 less than the LoginMenu's number of values
            int choice = helper.inputInt("Enter choice:", 0, LoginMenu.values().length - 1);
            // translate user choice to a login menu option
            LoginMenu option = LoginMenu.getOptionNumber(choice);
            // switch statement for each case of a menu option
            switch (option) {
                case CREATE_ACCOUNT:
                    createAccount();
                    break;
                case LOGIN:
                    login();
                    break;
                case LOGOUT:
                    logout();
                    break;
                case MAKE_FAKE_ACCOUNTS:
                    makeFakeAccounts();
                    break;
                case CHANGE_PASSWORD:
                    changePassword();
                    break;
                case SHOW_USERNAMES:
                    showUsernames();
                    break;
                case DISPLAY_ALL_USERS:
                    displayAllUsers();
                    break;
                case DISPLAY_USER_FAV:
                    displayUserFav();
                    break;
                case PRINT_MATCHES:
                    printMatches(this.getUsersWithSameFavoriteAsCurrentUser());
                    break;
                case QUIT:
                    quit();
                    return;
            }
        }
    }

    private void printMatches(ArrayList<User> matches) {
        if (matches == null) {
            return;
        }

        // prints all users in matches
        for (User user : matches) {
            System.out.println(user.getUserName());
        }

    }

    //    this is a really verbose name!
    private ArrayList<User> getUsersWithSameFavoriteAsCurrentUser() {
        // gets all users with the same favorite train as currentUser
        if (!verifyUser()) {
            return null;
        }

        ArrayList<User> matches = new ArrayList<User>();
        for (User user : userDatabase.values()) {
            if (user.getFavoriteTrain().equals(currentUser.getFavoriteTrain())) {
                matches.add(user);
            }
        }
        return matches;
    }

    private void displayUserFav() {
        // gets all users and prints their favorite trains using entrySet
        if (!verifyUser()) {
            return;
        }

        for (Map.Entry<String, User> item : userDatabase.entrySet()) {
            System.out.println(item.getKey() + " : " + item.getValue().getFavoriteTrain());
        }
    }

    private void showUsernames() {
        if (!verifyUser()) {
            return;
        }

        // prints all keys in userDatabase
        for (String key : userDatabase.keySet()) {
            System.out.println(key);
        }
    }

    private void displayAllUsers() {
        if (!verifyUser()) {
            return;
        }

        // gets all values from userDatabase and prints each
        for (User user : userDatabase.values()) {
            System.out.println(user);
        }
    }

    private void changePassword() {
        // first, check that someone is logged in
        if (!verifyUser()) {
            return;
        }

        // ask for current password
        String currentPassword = helper.inputWord("Enter current password:");
        // ask for new password
        String newPassword = helper.inputWord("Enter new password:");

        if (currentUser.updatePassword(currentPassword, newPassword)) {
            System.out.println("Password successfully updated.");
        } else {
            System.out.println("Password update failed.");
        }
    }

    private void logout() {
        // sets currentUser to null
        currentUser = null;
    }

    private void login() {
        // ask for a username
        String username = helper.inputWord("Enter username:");
        // if the user exists, ask for a password
        if (this.findUser(username) != null) {
            String password = helper.inputWord("Enter password:");
            // if the password is correct, log in the user
            if (this.findUser(username).checkPassword(password)) {
                currentUser = this.findUser(username);
                System.out.println("You're logged in as " + currentUser.getUserName());
            } else {
                System.out.println("Incorrect password!");
            }
        } else {
            System.out.println("User not found.");
        }
    }

    private void createAccount() {
        // ask for a username
        String desiredUsername = helper.inputWord("Enter a username:");

        // check if the username is already taken
        // HashMap containsKey method is really useful for this, nice!
        if (userDatabase.containsKey(desiredUsername)) {
            System.out.println("Username already taken!");

            // ask the user if they want to try a different username or log into an existing account
            int choice = helper.inputInt("Enter 1 to try a different uesrname, 2 to log in to an existing account, or 0 to go back to main menu:", 0, 2);
            if (choice == 1) {
                createAccount();
            } else if (choice == 2) {
                login();
            } else if (choice == 0) {
            }
        } else {
            System.out.println("No user exists with this username.");
//            ask for favorite train details
            String favoriteTrainModelName = helper.inputWord("Enter model name for your favorite train:");
            String favoriteTrainOperator = helper.inputWord("Enter operator for your favorite train:");
            String favoriteTrainManufacturer = helper.inputWord("Enter manufacturer for your favorite train:");

            // make the train
            Train favoriteTrain = new Train(favoriteTrainModelName, favoriteTrainOperator, favoriteTrainManufacturer);

            // ask for a password
            String password = helper.inputWord("Enter a password:");
            // make the user confirm the password
            String passwordConfirm = helper.inputWord("Confirm password:");
            // check if the passwords match
            if (!password.equals(passwordConfirm)) {
                System.out.println("Account creation failed, passwords do not match!");
                return;
            }

            // create the user
            User newUser = new User(desiredUsername, password, favoriteTrain);
            // add the user to the userDatabase
            userDatabase.put(desiredUsername, newUser);
            // log into the user
            currentUser = newUser;
            System.out.println("Successfully created account and logged in as " + currentUser.getUserName());
        }
    }

    private void makeFakeAccounts() {
        // my personal favorite?!
        Train myFavoriteTrain = new Train("7000-series", "WMATA", "Kawasaki");
        // create a few fake accounts
        User user1 = new User("alexwang1", "abc123", myFavoriteTrain);
        User user2 = new User("alexwang2", "def456", myFavoriteTrain);
        User user3 = new User("alexwang3", "ghi789", myFavoriteTrain);

        // add the fake accounts to the userDatabase
        userDatabase.put(user1.getUserName(), user1);
        userDatabase.put(user2.getUserName(), user2);
        userDatabase.put(user3.getUserName(), user3);
    }

    private void quit() {
        System.out.println("Goodbye!");
        // in the future, we will have logic here to write to file
    }
}
