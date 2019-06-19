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
        return allParts;
    }

    public static ObservableList<Product> lookupProduct(String productName){
        return allProducts;
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

    public static int getPartsLength(){
        return allParts.size();
    }

    public static int getProductsLength(){
        return allProducts.size();
    }

    public static int searchParts(String searchTerm){
        boolean isFound = false;
        int index = 0;

        System.out.println("Search term: " + searchTerm);

        if(isInteger(searchTerm)){
            for(int i = 0; i < allParts.size(); i++){
                if(Integer.parseInt(searchTerm) == allParts.get(i).getId()){
                    index = i;
                    System.out.println("Integer detected, returning: " + index);
                    isFound  = true;
                }
            }
        } else {
            for(int i = 0; i < allParts.size(); i++){
                if(searchTerm.equals(allParts.get(i).getName().toLowerCase())) {
                    index = i;
                    System.out.println("String detected, returning: " + index);
                    isFound  = true;
                }
            }
        }

        if(isFound){
            return index;
        } else {
            System.out.println("No Search Results");
            return -1;
        }
    }

    public static int searchProducts(String searchTerm){
        boolean isFound = false;
        int index = 0;

        System.out.println("Search term: " + searchTerm);

        if(isInteger(searchTerm)){
            for(int i = 0; i < allProducts.size(); i++){
                if(Integer.parseInt(searchTerm) == allProducts.get(i).getId()){
                    index = i;
                    System.out.println("Integer detected, returning: " + index);
                    isFound  = true;
                }
            }
        } else {
            for(int i = 0; i < allProducts.size(); i++){
                if(searchTerm.equals(allProducts.get(i).getName().toLowerCase())) {
                    index = i;
                    System.out.println("String detected, returning: " + index);
                    isFound  = true;
                }
            }
        }

        if(isFound){
            return index;
        } else {
            System.out.println("No Search Results");
            return -1;
        }
    }

    public static boolean isInteger(String test) {
        try {
            Integer.parseInt(test);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}