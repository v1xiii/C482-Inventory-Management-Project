package views_controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProductScreenController implements Initializable {

    @FXML private TextField modifyProductFieldID;
    @FXML private TextField modifyProductFieldName;
    @FXML private TextField modifyProductFieldInv;
    @FXML private TextField modifyProductFieldPrice;
    @FXML private TextField modifyProductFieldMax;
    @FXML private TextField modifyProductFieldMin;
    @FXML private Button modifyProductButtonSave;

    private Product product;
    private int productID;

    public void initialize(URL url, ResourceBundle rb) {
        product = MainScreenController.getProductToModify();

        productID = product.getId() - 1;

        modifyProductFieldID.setText(Integer.toString(product.getId()));
        modifyProductFieldName.setText(product.getName());
        modifyProductFieldInv.setText(Integer.toString(product.getStock()));
        modifyProductFieldPrice.setText(Double.toString(product.getPrice()));
        modifyProductFieldMax.setText(Integer.toString(product.getMax()));
        modifyProductFieldMin.setText(Integer.toString(product.getMin()));
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
}
