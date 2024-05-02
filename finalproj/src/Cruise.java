/**
 * Represents a booking for a cruise
 *
 * @author Alex Wang
 * Spring 2024, ITP 265, coffee
 * Email: awang756@usc.edu
 */
public class Cruise extends Service implements IPaymentRequired {
    private final String name;
    private final String destination;
    private final String origin;
    private final double totalPrice;

    public Cruise(String name, String destination, String origin, double totalPrice) {
        this.name = name;
        this.destination = destination;
        this.origin = origin;
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public String getDestination() {
        return destination;
    }

    public String getOrigin() {
        return origin;
    }

    @Override
    public double getPrice() {
        return totalPrice;
    }

    @Override
    public void book() {
        System.out.println("Booking cruise: " + this);
    }

    public String toString() {
        return "Cruise operated by " + name + " from " + origin + " to " + destination + " for $" + totalPrice;
    }
}
