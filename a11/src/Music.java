/**
 * Represents some music
 * @author Alex Wang
 *
 * Spring 2024, ITP 265, coffee
 * Email: awang756@usc.edu
 */

public class Music extends StreamableProduct {
    private MusicGenre musicGenre;

    public Music(String name, double price, double rating, boolean isIncludedWithPrime, double duration, int releaseYear, MusicGenre musicGenre) {
        super(name, price, rating, isIncludedWithPrime, duration, releaseYear);
        this.musicGenre = musicGenre;
    }

    @Override
    public String toString() {
        return "Music{" +
                "musicGenre=" + musicGenre +
                '}';
    }
}
