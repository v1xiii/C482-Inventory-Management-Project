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

public class ModifyProductScreenController implements Initializable {

    private Product product;
    private int productID;

    @FXML private TextField modifyProductFieldID;
    @FXML private TextField modifyProductFieldName;
    @FXML private TextField modifyProductFieldInv;
    @FXML private TextField modifyProductFieldPrice;
    @FXML private TextField modifyProductFieldMax;
    @FXML private TextField modifyProductFieldMin;
    @FXML private Button modifyProductButtonSave;

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
        product = MainScreenController.getProductToModify();

        productID = product.getId();

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

        modifyProductFieldID.setText(Integer.toString(product.getId()));
        modifyProductFieldName.setText(product.getName());
        modifyProductFieldInv.setText(Integer.toString(product.getStock()));
        modifyProductFieldPrice.setText(Double.toString(product.getPrice()));
        modifyProductFieldMax.setText(Integer.toString(product.getMax()));
        modifyProductFieldMin.setText(Integer.toString(product.getMin()));

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
        assocPartsTable.setItems(product.getAllAssociatedParts());
    }

    @FXML
    public void closeWindow(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Canceling");
        alert.setContentText("Are you sure you want to cancel modifying this part?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void modifyProductSaveButtonHandler(ActionEvent event) {
        if (product.getAllAssociatedParts().size() > 0) {
            String productName = modifyProductFieldName.getText();
            String productInv = modifyProductFieldInv.getText();
            String productPrice = modifyProductFieldPrice.getText();
            String productMin = modifyProductFieldMax.getText();
            String productMax = modifyProductFieldMin.getText();

            //Product product = new Product();
            product.setId(productID);
            product.setName(productName);
            product.setStock(Integer.parseInt(productInv));
            product.setPrice(Double.parseDouble(productPrice));
            product.setMax(Integer.parseInt(productMax));
            product.setMin(Integer.parseInt(productMin));
            Inventory.updateProduct(productID, product);

            //System.out.println(product.getAllAssociatedParts());

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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
    private void modifyProductAssocPartHandler(ActionEvent event) { // when the button is pressed
        Part part = allPartsTable.getSelectionModel().getSelectedItem(); // grab the selected part
        product.addAssociatedPart(part); // attach it to this product
        assocParts.add(part); // add it to this observableList
        assocPartsTable.setItems(assocParts); // add the observableList to the table
    }

    @FXML
    private void modifyProductRemovePartHandler(ActionEvent event) {
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