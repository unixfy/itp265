
/**
 * ProductType enum
 *
 * @author Kendra Walther
 * ITP 265, Spring 2020
 * Email: kwalther@usc.edu
 *
 */
public enum ProductType {
    BOOK,
    GAMES,
    ELECTRONICS,
    VIDEO,
    MUSIC,
    TOYS,
    APPLIANCES,
    UNKNOWN;

    public static String makeProductTypeMenu() {

        String categoryMenu = "Choose a category to view items: ";
        for(ProductType t : ProductType.values()){
            categoryMenu += "\n" + (t.ordinal() + 1)
                    + ": " + t.name();

        }
        return categoryMenu;

    }
    /**
     *
     * @param num (Indexed starting at 1)
     * @return
     */
    public static ProductType getProductNumbered(int num) {
        ProductType[] allProducts = ProductType.values();
        return allProducts[num-1];
    }
    public static ProductType getProductType(String name) {
        ProductType match = UNKNOWN;
        for(ProductType t: ProductType.values()) {
            if(t.name().equalsIgnoreCase(name)) {
                match = t;
            }
        }
        return  match;
    }
}