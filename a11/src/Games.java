/**
 * Represnets a game
 * @author Alex Wang
 *
 * Spring 2024, ITP 265, coffee
 * Email: awang756@usc.edu
 */


public class Games extends Item {
    private String description;

    public Games(String name, double price, double rating, int quantity, String description) {
        super(name, price, rating, quantity);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Games{" +
                "description='" + description + '\'' +
                '}';
    }
}
