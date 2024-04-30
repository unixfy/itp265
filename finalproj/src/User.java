import java.util.Random;

/**
 * Represents a generic user
 *
 * @author Alex Wang
 * Spring 2024, ITP 265, coffee
 * Email: awang756@usc.edu
 */

public abstract class User {
    private int id;
    private String username;
    private String name;
    private String password;
    private boolean banned;

    public User(String name, String username, String password) {
        Random random = new Random();
        this.id = random.nextInt(1000000); // this should probably be safe to avoid collision...
        this.username = username;
        this.name = name;
        this.password = password;
        this.banned = false;
    }

    public User(int id, String name, String username, String password, boolean banned) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.banned = banned;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }

    public String getPassword() {
        return password;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

}