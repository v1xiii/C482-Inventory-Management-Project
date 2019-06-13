package views_controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    //@FXML private Button exitButton;
    //@FXML private Button addPartButton;

    @FXML private TableView<Part> partTable;
    @FXML private TableColumn<Part, Integer> partTableColID;
    @FXML private TableColumn<Part, String> partTableColName;
    @FXML private TableColumn<Part, Integer> partTableColInv;
    @FXML private TableColumn<Part, Double> partTableColPrice;

    private static Part partToModify;

    @FXML
    public void exitProgram(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void openAddParts(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("add-part-screen.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add Part");
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, 750, 750));
        stage.show();
    }

    public static Part getPartToModify(){
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
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, 750, 750));
        stage.show();
    }

    @FXML
    public void openAddProducts(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("add-product-screen.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add Product");
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, 1366, 768));
        stage.show();
    }

    @FXML
    public void openModifyProducts(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("modify-product-screen.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Modify Product");
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, 1366, 768));
        stage.show();
    }

    public void initialize(URL url, ResourceBundle rb) {
        partTableColID.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partTableColName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partTableColInv.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partTableColPrice.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        partTable.refresh();
        partTable.setItems(Inventory.getAllParts());

        Inventory.getAllParts().add(new InHouse(1, "10-20mm lens", 250.02, 11, 1, 100, 4100));
        Inventory.getAllParts().add(new Outsourced(2, "20-30mm lens", 300.01, 10, 1, 100, "Nikon"));
    }
}