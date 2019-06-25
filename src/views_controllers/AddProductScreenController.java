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

public class AddProductScreenController implements Initializable {

    private Product product;
    private String productType;
    private int productID;

    @FXML private TextField addProductFieldID;
    @FXML private TextField addProductFieldName;
    @FXML private TextField addProductFieldInv;
    @FXML private TextField addProductFieldPrice;
    @FXML private TextField addProductFieldMax;
    @FXML private TextField addProductFieldMin;
    @FXML private Button addProductButtonSave;

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
        //assocPartsTable.setItems(product.getAllAssociatedParts());
    }

    @FXML
    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addProductSaveButtonHandler(ActionEvent event) {
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

        //Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        //stage.close();

        System.out.println(product.getName());
    }

    @FXML
    private void addProductAssocPartHandler(ActionEvent event) {
        Part part = allPartsTable.getSelectionModel().getSelectedItem();
        product.addAssociatedPart(part);
        assocParts.add(part);
        assocPartsTable.setItems(assocParts);
        System.out.println(product.getAllAssociatedParts());
    }

    @FXML
    private void addProductRemovePartHandler(ActionEvent event) {

    }
}
