/**
 * This Enum is used to represent the different blood types that a person can have. (from edstem, mostly)
 *
 * @author Alex Wang
 * Spring 2024, ITP 265, coffee
 * Email: awang756@usc.edu
 */
public enum BloodType {
    O_POSITIVE("O+"),
    O_NEGATIVE("O-"),
    A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-"),
    ;

    /**
     * autogenerated getter for the blood type
     *
     * @return blood type string for the BloodType instance
     */
    public String getType() {
        return type;
    }

    /**
     * The blood type as a string
     */
    private final String type;

    BloodType(String s) {
        this.type = s;
    }

    /**
     * Returns a random blood type, selected using Math.random()
     *
     * @return an instance of BloodType with the random blood type that is selected
     */
    public static BloodType getRandom() {
        return BloodType.values()[(int) (Math.random() * BloodType.values().length)];
    }

    /**
     * Returns a string array of the "type" values of all the different blood types defined in this enum
     *
     * @return a String array of all blood "type"s
     */
    public static String[] getBloodTypeStringsAsArray() {
        String[] array = new String[BloodType.values().length];
        for (int i = 0; i < BloodType.values().length; i++) {
            array[i] = BloodType.values()[i].getType();
        }
        return array;
    }

    /**
     * Finds the BloodType instance with a type matching the provided "type" string
     *
     * @param type the "type" string to match
     * @return the BloodType instance whose "type" matches the given string
     */
    public static BloodType matchString(String type) {
        BloodType bType = O_NEGATIVE;
        for (BloodType b : BloodType.values()) {
            if (b.getType().equalsIgnoreCase(type)) {
                bType = b;
            }
        }
        return bType;
    }
}
