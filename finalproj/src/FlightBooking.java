/**
 * Represents a booking for a flight
 *
 * @author Alex Wang
 * Spring 2024, ITP 265, coffee
 * Email: awang756@usc.edu
 */
public class FlightBooking extends Service implements IPaymentRequired {
    private FlightFareClasses fareClass;
    private FlightOperators operator;
    private String flightNumber;
    private String departureAirport;
    private String arrivalAirport;
    private double rawPrice;

    public FlightBooking(FlightOperators operator, String flightNumber, String departureAirport, String arrivalAirport, double rawPrice, FlightFareClasses fareClass) {
        this.operator = operator;
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.rawPrice = rawPrice;
        this.fareClass = fareClass;
    }

    public FlightOperators getOperator() {
        return operator;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    @Override
    public double getPrice() {
        return rawPrice * fareClass.getPriceMultiplier();
    }

    public double getRawPrice() {
        return rawPrice;
    }

    public FlightFareClasses getFareClass() {
        return fareClass;
    }

    @Override
    public void book() {
        System.out.println("Booking flight: " + this);
    }

    public String toString() {
        return "Flight " + getOperator() + " " + getFlightNumber() + " from " + getDepartureAirport() + " to " + getArrivalAirport() + " in " + fareClass + " class for total $" + getPrice();
    }
}
