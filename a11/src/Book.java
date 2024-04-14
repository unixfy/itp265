/**
 * Represnets a book
 * @author Alex Wang
 *
 * Spring 2024, ITP 265, coffee
 * Email: awang756@usc.edu
 */

public class Book extends Item implements Rentable {
    private String genre;
    private int numberOfPages;
    private final double RENTAL_PRICE = 0.99;

    public Book(String name, double price, double rating, int quantity, String genre, int numberOfPages) {
        super(name, price, rating, quantity);
        this.genre = genre;
        this.numberOfPages = numberOfPages;
    }

    public String getGenre() {
        return genre;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public double getRentalPrice() {
        return RENTAL_PRICE;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", genre: " + getGenre() + ", number of pages: " + getNumberOfPages() + ", rental price: $" + getRentalPrice();
    }
}
