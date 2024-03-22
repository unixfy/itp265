public class User {
    private String userName;
    private String password;
    private Train favoriteTrain; // extra credit yay!
    public static int numUsersCreated;

    public User(String userName, String password, Train favoriteTrain) {
        this.userName = userName;
        this.password = password;
        this.favoriteTrain = favoriteTrain;
        numUsersCreated++;
    }

    // basic setters and getters for username, favoritetrain

    public String getUserName() {
        return userName;
    }

    public Train getFavoriteTrain() {
        return favoriteTrain;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFavoriteTrain(Train favoriteTrain) {
        this.favoriteTrain = favoriteTrain;
    }

    // validator and updater for password
    public boolean checkPassword(String passwordAttempt) {
        // check if passwordAttempt is correct
        return password.equals(passwordAttempt);
    }

    public boolean updatePassword(String password, String newPassword) {
        // check if password is correct
        boolean correctPassword = checkPassword(password);
        // if so, update password
        if (correctPassword) {
            this.password = newPassword;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
//       generated with intellij
        // should we be displaying passwords in plaintext?! probably not
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", favoriteTrain=" + favoriteTrain +
                '}';
    }
}
