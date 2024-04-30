/**
 * Represents a free user
 *
 * @author Alex Wang
 * Spring 2024, ITP 265, coffee
 * Email: awang756@usc.edu
 */
public class FreeUser extends User {
    public FreeUser(String name, String username, String password) {
        super(name, username, password);
    }

    public FreeUser(int id, String name, String username, String password, boolean banned) {
        super(id, name, username, password, banned);
    }

    public PremiumUser upgrade() {
        return new PremiumUser(this.getName(), this.getUsername(), this.getPassword());
    }
}
