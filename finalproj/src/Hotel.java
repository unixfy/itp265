/**
 * Represents a booking for a hotel
 *
 * @author Alex Wang
 * Spring 2024, ITP 265, coffee
 * Email: awang756@usc.edu
 */
public class Hotel extends Service implements IPaymentRequired {
    private int numberOfRooms;
    private double nightlyPrice;
    private int numberOfNights;
    private String location;
}
