/**
 * Enum showing all possible fare classes for a flight booking
 *
 * @author Alex Wang
 * Spring 2024, ITP 265, coffee
 * Email: awang756@usc.edu
 */
public enum FlightFareClasses {
    ECONOMY(1.0),
    BUSINESS(1.5),
    FIRST(2.5);

    private final double priceMultiplier;

    FlightFareClasses(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    public double getPriceMultiplier() {
        return priceMultiplier;
    }
}
