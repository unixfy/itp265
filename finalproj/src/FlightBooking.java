/**
 * Represents a booking for a flight
 *
 * @author Alex Wang
 * Spring 2024, ITP 265, coffee
 * Email: awang756@usc.edu
 */
public class FlightBooking extends Service implements IPaymentRequired {
    private FlightFareClasses fareClass;
    private Flight flight;
}
