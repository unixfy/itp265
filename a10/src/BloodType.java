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
     * @return blood type string for the BloodType instance
     */
    public String getType() {
        return type;
    }

    /**
     * The blood type as a string
     */
    private String type;

    BloodType(String s) {
        this.type = s;
    }

    /**
     * Returns a random blood type, selected using Math.random()
     * @return an instance of BloodType with the random blood type selected
     */
    public static BloodType getRandom(){
        return BloodType.values()[(int)(Math.random() * BloodType.values().length)];
    }

    /**
     * Returns a string array of all the different blood types defined in this enum
     * @return a String array of all blood types
     */
    public static String[] getBloodTypeStringsAsArray(){
        String[] array = new String[BloodType.values().length];
        for(int i = 0; i < BloodType.values().length; i++){
            array[i] = BloodType.values()[i].getType();
        }
        return array;
    }

    /**
     * Finds the BloodType instance with a type matching the given "type" string
     * @param type the "type" string to match
     * @return the BloodType instance that matches the given string
     */
    public static BloodType matchString(String type){
        BloodType bType = O_NEGATIVE;
        for(BloodType b: BloodType.values()){
            if(b.getType().equalsIgnoreCase(type)){
                bType = b;
            }
        }
        return bType;
    }
}
