package views_controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.InHouse;
import models.Inventory;
import models.Outsourced;
import models.Product;

import java.net.URL;
import java.util.ResourceBundle;

public class AddProductScreenController implements Initializable {

    private String productType;
    private int productID;

    @FXML private TextField addProductFieldID;
    @FXML private TextField addProductFieldName;
    @FXML private TextField addProductFieldInv;
    @FXML private TextField addProductFieldPrice;
    @FXML private TextField addProductFieldMax;
    @FXML private TextField addProductFieldMin;
    @FXML private Button addProductButtonSave;

    public void initialize(URL url, ResourceBundle rb) {
        //partType = "In House";
        productID = Inventory.getProductsLength();
        addProductFieldID.setText(Integer.toString(productID));
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
        
        Product product = new Product();
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
}
