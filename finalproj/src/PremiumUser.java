/**
 * Represents a booking for a user with a paid subscription to our site
 *
 * @author Alex Wang
 * Spring 2024, ITP 265, coffee
 * Email: awang756@usc.edu
 */

public class PremiumUser extends User implements IPaymentRequired {
    public FreeUser downgrade() {
        return new FreeUser(this.getUsername(), this.getPassword());
    }
}
