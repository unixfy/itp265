/**
 * Represents a video
 * @author Alex Wang
 *
 * Spring 2024, ITP 265, coffee
 * Email: awang756@usc.edu
 */

public class Video extends StreamableProduct implements Rentable {
    private Genre genre;

    public Video(String name, double price, double rating, double duration, boolean isIncludedWithPrime, int releaseYear, Genre genre) {
        super(name, price, rating, isIncludedWithPrime, duration, releaseYear);
        this.genre = genre;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Video{" +
                "genre=" + genre +
                '}';
    }

    @Override
    public double getRentalPrice() {
        return 0;
    }
}
