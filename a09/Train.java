public class Train {
    private String modelName;
    private String operator;
    private String manufacturer;

    public Train(String modelName, String operator, String manufacturer) {
        this.modelName = modelName;
        this.operator = operator;
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        // generated w/ intellij
        return "Train{" +
                "modelName='" + modelName + '\'' +
                ", operator='" + operator + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}
