public enum LoginMenu {
    CREATE_ACCOUNT("Create Account"),
    LOGIN("Login"),
    LOGOUT("Logout"),
    MAKE_FAKE_ACCOUNTS("Make fake data"),
    CHANGE_PASSWORD("Change password (requires login)"),
    SHOW_USERNAMES("Print all usernames (requires login)"),
    DISPLAY_ALL_USERS("Print all users (requires login)"),
    DISPLAY_USER_FAV("Print username-favorites pairing (requires login)"),
    PRINT_MATCHES("Print users with same favorite thing (requires login)"),
    QUIT("Quit");

    public final String option;

    LoginMenu(String option) {
        this.option = option;
    }

    public static String getMenuString() {
        // returns a nice formatted string version of all the "options" here
        StringBuilder menu = new StringBuilder();
        // add 50 asterisks
        menu.append(get50AsterisksString()).append("\n");
        for (int i = 0; i < LoginMenu.values().length; i++) {
            // looks like 0: Create Account\n
            menu.append(i).append(": ").append(LoginMenu.values()[i].option).append("\n");
        }
        // another 50 asterisks
        menu.append(get50AsterisksString()).append("\n");
        return menu.toString();
    }

    public static String get50AsterisksString() {
        return "*".repeat(50); // I'd use a for loop traditionally, but I guess IntelliJ is much smarter than me ;)
    }

    public static LoginMenu getOptionNumber(int n) {
        return LoginMenu.values()[n]; // .values() a useful util for enums from java docs
    }
}
