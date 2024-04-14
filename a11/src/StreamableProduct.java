/**
 * Kendra's provided StreamableProduct class
 * @author Kendra Walther
 *
 * Spring 2024, ITP 265, coffee
 * Email: kwalther@usc.edu
 */

public abstract class StreamableProduct extends Product {
    private boolean isIncludedWithPrime;
    private double duration;
    private int releaseYear;

    public StreamableProduct(String name, double price, double rating, boolean isIncludedWithPrime, double duration, int releaseYear) {
        super(name, price, rating);
        this.isIncludedWithPrime = isIncludedWithPrime;
        this.duration = duration;
        this.releaseYear = releaseYear;
    }

    public boolean isIncludedWithPrime() {
        return isIncludedWithPrime;
    }

    public void setIncludedWithPrime(boolean includedWithPrime) {
        isIncludedWithPrime = includedWithPrime;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        String returnString = "Streamable " + getClass().getSimpleName();
        if (isIncludedWithPrime) {
            returnString += " (Included with Prime)";
        }
        return returnString + " " + super.toString();
    }
}
