package views_controllers;

import com.sun.org.apache.xml.internal.security.Init;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.InHouse;
import models.Inventory;
import models.Outsourced;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPartScreenController implements Initializable {

    private String partType;
    private int partID;

    @FXML private RadioButton addPartRadioInHouse;
    @FXML private RadioButton addPartRadioOutsourced;
    @FXML private Label addPartLabelCompOrMach;
    @FXML private TextField addPartFieldID;
    @FXML private TextField addPartFieldName;
    @FXML private TextField addPartFieldInv;
    @FXML private TextField addPartFieldPrice;
    @FXML private TextField addPartFieldMax;
    @FXML private TextField addPartFieldMin;
    @FXML private TextField addPartFieldCompOrMach;
    @FXML private Button addPartButtonSave;

    @FXML
    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void inHouseRadioHandler(ActionEvent event) {
        if (addPartRadioInHouse.isSelected()) {
            addPartLabelCompOrMach.setText("Machine ID");
            partType = "In House";
            addPartFieldCompOrMach.setText("");
            //addPartRadioOutsourced.setSelected(false);
        }
    }

    @FXML
    private void outsourcedRadioHandler(ActionEvent event) {
        if (addPartRadioOutsourced.isSelected()) {
            addPartLabelCompOrMach.setText("Company Name");
            partType = "Outsourced";
            addPartFieldCompOrMach.setText("");
            //addPartRadioInHouse.setSelected(false);
        }
    }

    @FXML
    private void addPartSaveButtonHandler() {
        String partName = addPartFieldName.getText();
        String partInv = addPartFieldInv.getText();
        String partPrice = addPartFieldPrice.getText();
        String partMin = addPartFieldMax.getText();
        String partMax = addPartFieldMin.getText();
        String partCompOrMach = addPartFieldCompOrMach.getText();


        if (partType == "In House") {
            InHouse inHousePart = new InHouse();
            inHousePart.setId(partID);
            inHousePart.setName(partName);
            inHousePart.setStock(Integer.parseInt(partInv));
            inHousePart.setPrice(Double.parseDouble(partPrice));
            inHousePart.setMax(Integer.parseInt(partMax));
            inHousePart.setMin(Integer.parseInt(partMin));
            inHousePart.setMachineId(Integer.parseInt(partCompOrMach));
            Inventory.addPart(inHousePart);
        } else {
            Outsourced outsourcedPart = new Outsourced();
            outsourcedPart.setId(partID);
            outsourcedPart.setName(partName);
            outsourcedPart.setStock(Integer.parseInt(partInv));
            outsourcedPart.setPrice(Double.parseDouble(partPrice));
            outsourcedPart.setMax(Integer.parseInt(partMax));
            outsourcedPart.setMin(Integer.parseInt(partMin));
            outsourcedPart.setCompanyName(partCompOrMach);
            Inventory.addPart(outsourcedPart);
        }
    }

    public void initialize(URL url, ResourceBundle rb) {
        partType = "In House";
        partID = Inventory.getPartsLength();
        addPartFieldID.setText(Integer.toString(partID));
    }
}