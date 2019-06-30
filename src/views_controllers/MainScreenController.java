package views_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.*;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    //@FXML private Button exitButton;
    //@FXML private Button addPartButton;

    @FXML private TableView<Part> partTable;
    @FXML private TableColumn<Part, Integer> partTableColID;
    @FXML private TableColumn<Part, String> partTableColName;
    @FXML private TableColumn<Part, Integer> partTableColInv;
    @FXML private TableColumn<Part, Double> partTableColPrice;
    @FXML private TextField partSearchField;

    @FXML private TableView<Product> productTable;
    @FXML private TableColumn<Product, Integer> productTableColID;
    @FXML private TableColumn<Product, String> productTableColName;
    @FXML private TableColumn<Product, Integer> productTableColInv;
    @FXML private TableColumn<Product, Double> productTableColPrice;
    @FXML private TextField productSearchField;

    private static Part partToModify;
    private static Product productToModify;

    public void initialize(URL url, ResourceBundle rb) {
        partTableColID.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partTableColName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partTableColInv.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partTableColPrice.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        partTable.refresh();
        partTable.setItems(Inventory.getAllParts());

        productTableColID.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        productTableColName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        productTableColInv.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        productTableColPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        productTable.refresh();
        productTable.setItems(Inventory.getAllProducts());

        Inventory.getAllParts().add(new InHouse(0, "Gears", 25.50, 200, 0, 500, 4100));
        Inventory.getAllParts().add(new Outsourced(1, "Capacitors", 1.25, 100, 0, 500, "Chengdu"));

        Inventory.getAllProducts().add(new Product(0, "Mechanism", 205.50, 11, 0, 100));
        Inventory.getAllProducts().add(new Product(1, "Machine", 200.25, 10, 0, 100));
    }

    @FXML
    public void exitProgram(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void openAddParts(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("add-part-screen.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add Part");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, 750, 750));
        stage.show();
    }

    static Part getPartToModify(){
        return partToModify;
    }

    private void setPartToModify(Part partToModify) {
        MainScreenController.partToModify = partToModify;
    }

    @FXML
    public void openModifyParts(ActionEvent event) throws IOException {

        setPartToModify(partTable.getSelectionModel().getSelectedItem());

        Parent root = FXMLLoader.load(getClass().getResource("modify-part-screen.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Modify Part");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, 750, 750));
        stage.show();
    }

    public void deletePart(){

        Part partToDelete = partTable.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Deleting part #" + partToDelete.getId() + " - \"" + partToDelete.getName() + "\"");
        alert.setContentText("Are you sure you want to delete this part?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            Inventory.deletePart(partToDelete);
        }
    }

    @FXML
    public void searchParts(ActionEvent event) throws IOException {
        String searchTerm = partSearchField.getText().toLowerCase();

        int partIndex = -1;
        if(Inventory.searchParts(searchTerm) == -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(searchTerm + "Part Not Found");
            alert.setContentText(searchTerm + "Search does not match any parts");
            alert.showAndWait();
        } else {
            partIndex = Inventory.searchParts(searchTerm);
            System.out.println("MainScreenController looking for index: " + partIndex);
            Part tempPart = Inventory.getAllParts().get(partIndex);
            System.out.println("Part being found: " + tempPart.getName());
            ObservableList<Part> tempPartList = FXCollections.observableArrayList();
            tempPartList.add(tempPart);
            partTable.setItems(tempPartList);
        }
    }

    @FXML
    public void openAddProducts(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("add-product-screen.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add Product");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, 1366, 768));
        stage.show();
    }

    static Product getProductToModify(){
        return productToModify;
    }

    private void setProductToModify(Product productToModify) {
        MainScreenController.productToModify = productToModify;
    }

    @FXML
    public void openModifyProducts(ActionEvent event) throws IOException {

        setProductToModify(productTable.getSelectionModel().getSelectedItem());

        Parent root = FXMLLoader.load(getClass().getResource("modify-product-screen.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Modify Product");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, 1366, 768));
        stage.show();
    }

    public void deleteProduct(){
        Product productToDelete = productTable.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Deleting product #" + productToDelete.getId() + " - \"" + productToDelete.getName() + "\"");
        alert.setContentText("Are you sure you want to delete this product?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Inventory.deleteProduct(productToDelete);
        }
    }

    @FXML
    public void searchProducts(ActionEvent event) throws IOException {
        String searchTerm = productSearchField.getText().toLowerCase();

        int productIndex = -1;
        if(Inventory.searchProducts(searchTerm) == -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(searchTerm + "Product Not Found");
            alert.setContentText(searchTerm + "Search does not match any products");
            alert.showAndWait();
        } else {
            productIndex = Inventory.searchProducts(searchTerm);
            System.out.println("MainScreenController looking for index: " + productIndex);
            Product tempProduct = Inventory.getAllProducts().get(productIndex);
            System.out.println("Product being found: " + tempProduct.getName());
            ObservableList<Product> tempProductList = FXCollections.observableArrayList();
            tempProductList.add(tempProduct);
            productTable.setItems(tempProductList);
        }
    }
}