package views_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.*;

import java.net.URL;
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

        productID = product.getId() - 1;

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
        allPartsTable.refresh();
        allPartsTable.setItems(Inventory.getAllParts());

        assocPartsTableColID.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        assocPartsTableColName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        assocPartsTableColInv.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        assocPartsTableColPrice.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        assocPartsTable.refresh();
        assocPartsTable.setItems(product.getAllAssociatedParts());
    }

    @FXML
    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void modifyProductSaveButtonHandler(ActionEvent event) {
        String productName = modifyProductFieldName.getText();
        String productInv = modifyProductFieldInv.getText();
        String productPrice = modifyProductFieldPrice.getText();
        String productMin = modifyProductFieldMax.getText();
        String productMax = modifyProductFieldMin.getText();
        
        Product product = new Product();
        product.setId(productID + 1);
        product.setName(productName);
        product.setStock(Integer.parseInt(productInv));
        product.setPrice(Double.parseDouble(productPrice));
        product.setMax(Integer.parseInt(productMax));
        product.setMin(Integer.parseInt(productMin));
        Inventory.updateProduct(productID, product);

        //Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        //stage.close();
    }

    @FXML
    private void modifyProductAssocPartHandler(ActionEvent event) {
        Part part = allPartsTable.getSelectionModel().getSelectedItem();
        product.addAssociatedPart(part);
        assocParts.add(part);
        assocPartsTable.setItems(assocParts);
        System.out.println(product.getAllAssociatedParts());
    }

    @FXML
    private void modifyProductRemovePartHandler(ActionEvent event) {

    }
}
