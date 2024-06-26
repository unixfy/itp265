/**
 * This class represents a "human".
 *
 * @author Alex Wang
 * Spring 2024, ITP 265, coffee
 * Email: awang756@usc.edu
 */

public class Human extends Being {

    // holds the human's blood type
    @SuppressWarnings("FieldMayBeFinal")
    private BloodType bloodType;

    //    same pattern of constructors as the Being class
    public Human(String name, int quarts, String icon, BloodType bloodType) {
        super(name, quarts, icon);
        this.bloodType = bloodType;
    }

    public Human(String name, int quarts, BloodType bloodType) {
        this(name, quarts, DEFAULT_EMOJI, bloodType);
    }

    public Human(String name, String icon, BloodType bloodType) {
        this(name, DEFAULT_NUM_QUARTS, icon, bloodType);
    }

    public Human(String name, BloodType bloodType) {
        this(name, DEFAULT_NUM_QUARTS, DEFAULT_EMOJI, bloodType);
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    /**
     * Decreases the human's blood by 1 quart
     *
     * @return whether the blood was able to be sucked
     */
    public boolean suckBlood() {
        return super.decreaseQuarts(1);
    }

    @Override
    public String toString() {
//        to reduce code repetition
        return super.toString() + " of blood type " + bloodType.getType();
    }
}
