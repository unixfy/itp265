/**
 * This abstract class represents some generic item
 * @author Alex Wang
 *
 * Spring 2024, ITP 265, coffee
 * Email: awang756@usc.edu
 */

public abstract class Item extends Product {
    private int quantity;

    public Item(String name, double price, double rating, int quantity) {
        super(name, price, rating);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
