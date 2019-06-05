package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part part){
        allParts.add(part);
    }

    public static void addProduct(Product product){
        allProducts.add(product);
    }

    public static Part lookupPart(int partId){
        return allParts.get(partId);
    }

    public static Product lookupProduct(int productId){
        return allProducts.get(productId);
    }

    public static ObservableList<Part> lookupPart(String partName){
        return null;
    }

    public static ObservableList<Product> lookupProduct(String productName){
        return null;
    }

    public static void updatePart(int index, Part part){
        allParts.set(index, part);
    }

    public static void updateProduct(int index, Product product){
        allProducts.set(index, product);
    }

    public static void deletePart(Part part){
        allParts.remove(part);
    }

    public static void deleteProduct(Product product){
        allProducts.remove(product);
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}