package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static int partId = 0;
    /*Setting part ID as zero, so I can increment it in the getIdMethod to generate unique ID's.
    UUID isn't super readable and rand from the Math class could cause duplicates.
     */

    private static int productId = 0;

    private static ObservableList <Part> allParts = FXCollections.observableArrayList();


    private static ObservableList <Product> allProducts = FXCollections.observableArrayList();


    /**
     * @return
     * Returns an ObservableList of all parts in inventory.
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * @return
     * Returns and ObservableList of all Products in inventory.
     */
    public static ObservableList <Product> getAllProducts() {
        return allProducts;
    }

    /**
     * @param part
     * Adds a part object to the ObservableList of all parts.
     */
    public static void addPart(Part part) {
        allParts.add(part);
    }

    /**
     * @param product
     * Adds a product object to the ObservableList of all products.
     */
    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    /**
     * @param partId
     * @return
     * Performs a search in the allParts ObservableList for parts with matching ID's.
     */
    public static Part lookupPart(int partId) {
        Part queriedPart = null;

        for (Part part : allParts) {
            if (part.getId() == partId) {
                queriedPart = part;
            }
        }
        return queriedPart;
    }

    /**
     * @param productId
     * @return
     * Performs a search in the allProducts ObservableList for products with matching ID's.
     */
    public static Product lookupProduct(int productId) {
        Product queriedProduct = null;

        for (Product product : allProducts) {
            if (product.getId() == productId) {
                queriedProduct = product;
            }
        }
        return queriedProduct;
    }

    /**
     * @param partName
     * @return
     * Performs a search in the allParts ObservableList for parts with matching characters in their String name variable.
     * Uses the toLower(); method when comparing so the search function is not case-sensitive.
     *
     ** RUNTIME ERROR:
     * When first implementing the lookupPart method. I ran into the issue of returning only one part from the method.
     * Thus, my tableview would only return the first product in the allParts ObservableList that contained matching
     * characters. After running my IDE in debug mode I noticed that I had placed a return statement inside the if
     * statement contained by the for loop. This caused my method to return the partsFound ObservableList and the one
     * part added to it as soon as the if statement condition was met. I fixed this bug by moving my return statement
     * outside the for loop so the for loop could iterate through every Part object and add every match to the
     * ObservableList before returning it.
     *
     *
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> partsFound = FXCollections.observableArrayList();

        for (Part part : allParts) {
            if (part.getName().toLowerCase().contains(partName.toLowerCase())) {
                //using the .toLowerCase() so my search field is not case-sensitive
                partsFound.add(part);
            }
        }

        return partsFound;
    }

    /**
     * @param productName
     * @return
     * Performs a search in the allProducts ObservableList for products with matching characters in their String name variable.
     * Uses the toLower(); method when comparing so the search function is not case-sensitive.
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> productsFound = FXCollections.observableArrayList();

        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
                //using the .toLowerCase() so my search field is not case-sensitive
                productsFound.add(product);
            }
        }

        return productsFound;
    }

    /**
     * @param index
     * @param selectedPart
     * Allows you to set the index of a Part object in the allParts Observable list.
     */
    public static void updatePart (int index, Part selectedPart) {

        allParts.set(index, selectedPart);
    }

    /**
     * @param index
     * @param selectedProduct
     * Allows you to set the index of a Product object in the allProducts Observable list.
     */
    public static void updateProduct (int index, Product selectedProduct) {

        allProducts.set(index, selectedProduct);
    }

    /**
     * @param selectedPart
     * @return
     * Checks that the selected part exists in the allParts ObservableList then deletes it from the list if it exists.
     */
    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * @param selectedProduct
     * @return
     * Checks that the selected product exists in the allProducts ObservableList then deletes it from the list if it exists.
     */
    public static boolean deleteProduct(Product selectedProduct) {
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * @return
     * Returns a unique Integer ID for a part object.
     */
    public static int generatePartId() {
        return partId++;
    }

    /**
     * @return
     * Returns a unique Integer ID for a product object.
     */
    public static int generateProductId() {
        return productId++;
    }
}
