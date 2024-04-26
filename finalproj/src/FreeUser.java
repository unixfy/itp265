/**
 * Represents a free user
 *
 * @author Alex Wang
 * Spring 2024, ITP 265, coffee
 * Email: awang756@usc.edu
 */
public class FreeUser extends User {
    public PremiumUser upgrade() {
        return new PremiumUser(this.getUsername(), this.getPassword());
    }
}
