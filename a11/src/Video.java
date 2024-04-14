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
        return super.toString() + ", genre: " + genre;
    }

    @Override
    public double getRentalPrice() {
        //The videos rental price will be the regular price divided by 3, or $0.00 if the video is included with prime
        if (this.isIncludedWithPrime()) {
            return 0.00;
        } else {
            return this.getPrice() / 3.0;
        }
    }
}
