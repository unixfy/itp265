/**
 * Enum of all possible operators for a flight
 *
 * @author Alex Wang
 * Spring 2024, ITP 265, coffee
 * Email: awang756@usc.edu
 */

public enum FlightOperators {
    DL("Delta Air Lines"),
    UA("United Airlines"),
    AA("American Airlines"),
    B6("JetBlue Airways"),
    WN("Southwest Airlines"),
    AC("Air Canada");

    private final String fullName;

    FlightOperators(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return fullName + " (" + this.name() + ")";
    }
}
