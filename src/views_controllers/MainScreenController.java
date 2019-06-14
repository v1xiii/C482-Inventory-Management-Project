package views_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    @FXML private TextField partSearchField;

    private static Part partToModify;

    public void initialize(URL url, ResourceBundle rb) {
        partTableColID.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partTableColName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partTableColInv.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partTableColPrice.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        partTable.refresh();
        partTable.setItems(Inventory.getAllParts());

        Inventory.getAllParts().add(new InHouse(1, "Gears", 25.50, 11, 1, 100, 4100));
        Inventory.getAllParts().add(new Outsourced(2, "Capacitors", 1.25, 10, 1, 100, "Chengdu"));
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

        Inventory.deletePart(partToDelete);
    }

    @FXML
    public void searchParts(ActionEvent event) throws IOException {
        String searchTerm = partSearchField.getText();

        int partIndex = -1;
        //if(Inventory.lookupPart(searchPartString) == -1){
            //Alert alert = new Alert(AlertType.INFORMATION);
            //alert.setTitle("Search Error");
            //alert.setHeaderText(searchPartString + " part not found");
            //alert.setContentText(searchPartString + " does not match current records.");
            //alert.showAndWait();
        //} else {
            partIndex = Inventory.searchParts(searchTerm) + 1;
            System.out.println(partIndex);
            Part tempPart = Inventory.getAllParts().get(partIndex);
            ObservableList<Part> tempPartList = FXCollections.observableArrayList();
            tempPartList.add(tempPart);
            partTable.setItems(tempPartList);
       // }
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

    @FXML
    public void openModifyProducts(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("modify-product-screen.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Modify Product");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, 1366, 768));
        stage.show();
    }
}