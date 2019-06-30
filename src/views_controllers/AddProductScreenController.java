package views_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.*;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddProductScreenController implements Initializable {

    private Product product = new Product();
    private String productType;
    private int productID;

    @FXML private TextField addProductFieldID;
    @FXML private TextField addProductFieldName;
    @FXML private TextField addProductFieldInv;
    @FXML private TextField addProductFieldPrice;
    @FXML private TextField addProductFieldMax;
    @FXML private TextField addProductFieldMin;
    @FXML private Button addProductButtonSave;

    @FXML private TextField partSearchField;

    @FXML private TableView<Part> allPartsTable;
    @FXML private TableColumn<Part, Integer> allPartsTableColID;
    @FXML private TableColumn<Part, String> allPartsTableColName;
    @FXML private TableColumn<Part, Integer> allPartsTableColInv;
    @FXML private TableColumn<Part, Double> allPartsTableColPrice;

    @FXML private TableView<Part> assocPartsTable;
    @FXML private TableColumn<Part, Integer> assocPartsTableColID;
    @FXML private TableColumn<Part, String> assocPartsTableColName;
    @FXML private TableColumn<Part, Integer> assocPartsTableColInv;
    @FXML private TableColumn<Part, Double> assocPartsTableColPrice;

    private final ObservableList<Part> assocParts = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle rb) {
        //partType = "In House";
        Product product = new Product();
        productID = Inventory.getProductsLength();
        addProductFieldID.setText(Integer.toString(productID));

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

        allPartsTableColID.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        allPartsTableColName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        allPartsTableColInv.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        allPartsTableColPrice.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        allPartsTableColPrice.setCellFactory(tc -> new TableCell<Part, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(currencyFormat.format(price));
                }
            }
        });
        allPartsTable.refresh();
        allPartsTable.setItems(Inventory.getAllParts());

        assocPartsTableColID.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        assocPartsTableColName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        assocPartsTableColInv.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        assocPartsTableColPrice.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        assocPartsTableColPrice.setCellFactory(tc -> new TableCell<Part, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(currencyFormat.format(price));
                }
            }
        });
        assocPartsTable.refresh();
        //assocPartsTable.setItems(product.getAllAssociatedParts());
    }

    @FXML
    public void closeWindow(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Canceling");
        alert.setContentText("Are you sure you want to cancel adding this product?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void addProductSaveButtonHandler(ActionEvent event) {
        //System.out.println(product.getAllAssociatedParts().size());
        if (product.getAllAssociatedParts().size() > 0) {
            String productName = addProductFieldName.getText();
            String productInv = addProductFieldInv.getText();
            String productPrice = addProductFieldPrice.getText();
            String productMin = addProductFieldMax.getText();
            String productMax = addProductFieldMin.getText();
            //String productCompOrMach = addProductFieldCompOrMach.getText();

            product.setId(productID);
            product.setName(productName);
            product.setStock(Integer.parseInt(productInv));
            product.setPrice(Double.parseDouble(productPrice));
            product.setMax(Integer.parseInt(productMax));
            product.setMin(Integer.parseInt(productMin));
            //product.setCompanyName(productCompOrMach);
            Inventory.addProduct(product);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Product has no parts");
            alert.setContentText("Product must have at least one associated part.");
            alert.showAndWait();
        }
    }

    @FXML
    private void addProductAssocPartHandler(ActionEvent event) {
        Part part = allPartsTable.getSelectionModel().getSelectedItem(); // grab the selected part
        product.addAssociatedPart(part); // attach it to this product
        assocParts.add(part); // add it to this observableList
        assocPartsTable.setItems(assocParts); // add the observableList to the table
        System.out.println(product.getAllAssociatedParts());
    }

    @FXML
    private void addProductRemovePartHandler(ActionEvent event) {
        Part part = assocPartsTable.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Removing part #" + part.getId() + " - \"" + part.getName() + "\"");
        alert.setContentText("Are you sure you want to remove the part from this product?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            product.deleteAssociatedPart(part);
            assocParts.remove(part);
        }
    }

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
            allPartsTable.setItems(tempPartList);
        }
    }
}
