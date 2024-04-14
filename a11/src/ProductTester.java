

import java.util.*;

/**
 * ProductTester class
 *
 * @author Kendra Walther
 * ITP 265, Section
 * Email: kwalther@usc.edu
 *
 */
public class ProductTester {

    private Map<ProductType, List<Product>> inventoryMap;
    private List<Product> allProducts;


    public ProductTester() {
        allProducts = StoreFactory.setUpStoreProducts();
        inventoryMap= new HashMap<>();
        setUpMapOfProducts();
    }

    public void setUpMapOfProducts(){
        // setting up by map keys. USE producttype enum to set up the keys, each going to a new empty list
        for (ProductType productType : ProductType.values()) {
            inventoryMap.put(productType, new ArrayList<>());
        }

        for (Product product : allProducts) {
            ProductType productType = ProductType.getProductType(product.getClass().getSimpleName());
            List<Product> productTypeList = inventoryMap.get(productType);
            productTypeList.add(product);
        }
        //After that, organize each Product by storing it in the list associated with its product type

    }

    public void run(){
        System.out.println("\n******Test 0 --Printing list of All products (using to String)");
        showAll();
        System.out.println("\n******Test 1 -- Printing list of All products (with just price, name & rating):");
        printProductPriceAndName();
        System.out.println("\n******Test 2 -- Same as Test 2, but after sorting");
        Collections.sort(allProducts);
        printProductPriceAndName();
        System.out.println("\n******Test 3 --Inventory in the store by Product Type (using map)");
        showInventoryByCategory();
        System.out.println("\n******Test 4 --Only Rentable items:");
        showRentals();
    }

    public static void main(String[] args) {
        System.out.println("PRODUCT TESTER");
        ProductTester store = new ProductTester();
        store.run();
    }

    public void showAll() { //for Test 0
        // loop through allProducts and print each product's toString
        for (Product p : allProducts) {
            System.out.println(p);
        }
    }
    public void printProductPriceAndName() { //for Test 1 and 2
        //String friendly = String.format("$%.2f", p.getPrice());
        // Loops through allProducts, print $2.99 Product Name (Rating/5.0)
        for (Product p : allProducts) {
            System.out.printf("$%.2f : %s (%.1f/5.0)%n", p.getPrice(), p.getName(), p.getRating());
        }

    }

    public void showInventoryByCategory() { //Test 3
        // Use the map and print each key -- the ProductType (or category)
        // if the list for that product is empty, print "\tNo matching items"
        // if list is not empty, sort it before looping through to print contents
        // otherwise  print the list contents, one per line with a tab followed by the product's toString result
        for (ProductType productType : inventoryMap.keySet()) {
            System.out.println(productType);
            List<Product> productTypeList = inventoryMap.get(productType);

            // nice method for List!
            if (productTypeList.isEmpty()) {
                System.out.println("\tNo matching items");
            } else {
                // another useful method to sort everything inside a list
                Collections.sort(productTypeList);
                for (Product p : productTypeList) {
                    System.out.println("\t" + p);
                }
            }
        }
    }


    private void showRentals() { //Test 4
        // loop through all the products and see if a given product is Rentable
        // print each rentable product in the form, with rental price and name of product
        //(Rent from : $0.00) - Psych
        for (Product p : allProducts) {
            // something is rentable if it is an instanceof Rentable interface
            if (p instanceof Rentable) {
                // must cast to Rentable to get the rental price
                @SuppressWarnings("PatternVariableCanBeUsed") Rentable rentable = (Rentable) p;
                System.out.printf("(Rent from : $%.2f) - %s%n", rentable.getRentalPrice(), p.getName());
            }
        }

    }


}
