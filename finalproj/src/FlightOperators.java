public enum FlightOperators {
    DL("Delta Air Lines"),
    UA("United Airlines"),
    AA("American Airlines"),
    B6("JetBlue Airways"),
    WN("Southwest Airlines");

    private final String fullName;

    FlightOperators(String fullName) {
        this.fullName = fullName;
    }
}
