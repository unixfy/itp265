import java.util.Objects;

/**
 * This abstract class is a scaffold for products
 * @author Alex Wang
 *
 * Spring 2024, ITP 265, coffee
 * Email: awang756@usc.edu
 */

public abstract class Product implements Comparable<Product> {
    private String name;
    private double price;
    private double rating;

    public Product(String name, double price, double rating) {
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return getName() + ", price: $" + getPrice() + ", rating: " + getRating() + "*";
    }

    @Override
    public int compareTo(Product other) {
        // order first by price (lowest to highest), then alphabetically by name, then by rating (highest to lowest)
        if (this.price < other.price) {
            // smaller price implies "smaller" product
            return -1;
        } else if (this.price > other.price) {
            // bigger price implies "bigger" product
            return 1;
        } else {
            // use built in String.compareTo
            int nameComparisonVal = this.name.compareTo(other.name);
            if (nameComparisonVal != 0) {
                // return the String.comopareTo value if the names are not the same
                return nameComparisonVal;
            } else {
                if (this.rating < other.rating) {
                    // smaller rating implies "bigger" product
                    return 1;
                } else if (this.rating > other.rating) {
                    // bigger rating implies "smaller" product
                    return -1;
                } else {
                    // if everything is the same?!
                    return 0;
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return Double.compare(getPrice(), product.getPrice()) == 0 && Double.compare(getRating(), product.getRating()) == 0 && Objects.equals(getName(), product.getName());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getName());
        result = 31 * result + Double.hashCode(getPrice());
        result = 31 * result + Double.hashCode(getRating());
        return result;
    }
}
