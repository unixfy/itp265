/**
 * Represents a booking for a hotel
 *
 * @author Alex Wang
 * Spring 2024, ITP 265, coffee
 * Email: awang756@usc.edu
 */
public class Hotel extends Service implements IPaymentRequired {
    private final int numberOfRooms;
    private final double nightlyPrice;
    private final int numberOfNights;
    private final String location;

    public Hotel(int numberOfRooms, double nightlyPrice, int numberOfNights, String location) {
        super();
        this.numberOfRooms = numberOfRooms;
        this.nightlyPrice = nightlyPrice;
        this.numberOfNights = numberOfNights;
        this.location = location;
    }

    @Override
    public double getPrice() {
        return numberOfRooms * nightlyPrice * numberOfNights;
    }

    @Override
    public void book() {
        System.out.println("Booking hotel: " + this);
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public double getNightlyPrice() {
        return nightlyPrice;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Hotel at " + getLocation() + " for " + getNumberOfRooms() + " rooms for " + getNumberOfNights() + " nights at $" + getNightlyPrice() + " per night; total cost: $" + getPrice();
    }
}
