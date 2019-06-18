package views_controllers;

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
import models.Part;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartScreenController implements Initializable {

    @FXML private RadioButton inHouse_Radio;
    @FXML private RadioButton outsourced_Radio;
    @FXML private Label inHouseOutsourced_Label;
    @FXML private TextField modifyPartFieldID;
    @FXML private TextField modifyPartFieldName;
    @FXML private TextField modifyPartFieldInv;
    @FXML private TextField modifyPartFieldPrice;
    @FXML private TextField modifyPartFieldMax;
    @FXML private TextField modifyPartFieldMin;
    @FXML private TextField modifyPartFieldCompOrMach;
    @FXML private Button modifyPartButtonSave;

    private Part part;
    private String labelInitialValue;

    private int partID;

    public void initialize(URL url, ResourceBundle rb) {
        part = MainScreenController.getPartToModify();

        partID = part.getId() - 1;

        System.out.println(partID);

        modifyPartFieldID.setText(Integer.toString(part.getId()));
        modifyPartFieldName.setText(part.getName());
        modifyPartFieldInv.setText(Integer.toString(part.getStock()));
        modifyPartFieldPrice.setText(Double.toString(part.getPrice()));
        modifyPartFieldMax.setText(Integer.toString(part.getMax()));
        modifyPartFieldMin.setText(Integer.toString(part.getMin()));

        if (part instanceof InHouse) {
            inHouseOutsourced_Label.setText("Machine ID");
            labelInitialValue = "Machine ID";
            modifyPartFieldCompOrMach.setText(Integer.toString(((InHouse) part).getMachineId()));
            inHouse_Radio.setSelected(true);
            //outsourced_Radio.setSelected(false);
        }
        else {
            inHouseOutsourced_Label.setText("Company Name");
            labelInitialValue = "Company Name";
            modifyPartFieldCompOrMach.setText(((Outsourced) part).getCompanyName());
            outsourced_Radio.setSelected(true);
            //inHouse_Radio.setSelected(false);
        }
    }

    @FXML
    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void inHouseRadioHandler(ActionEvent event) {
        if (inHouse_Radio.isSelected()) {

            inHouseOutsourced_Label.setText("Machine ID");

            if (modifyPartFieldCompOrMach.getText().isEmpty() || labelInitialValue.equals("Machine ID")){
                modifyPartFieldCompOrMach.setText(Integer.toString(((InHouse) part).getMachineId()));
            } else {
                modifyPartFieldCompOrMach.setText("");
            }
            //inHouse_Radio.setSelected(false);
        }
    }

    @FXML
    private void outsourcedRadioHandler(ActionEvent event) {
        if (outsourced_Radio.isSelected()) {

            inHouseOutsourced_Label.setText("Company Name");

            if (modifyPartFieldCompOrMach.getText().isEmpty() || labelInitialValue.equals("Company Name")){
                modifyPartFieldCompOrMach.setText(((Outsourced) part).getCompanyName());
            } else {
                modifyPartFieldCompOrMach.setText("");
            }
            //outsourced_Radio.setSelected(false);
        }
    }

    @FXML
    private void modifyPartSaveButtonHandler(ActionEvent event) {
        String partName = modifyPartFieldName.getText();
        String partInv = modifyPartFieldInv.getText();
        String partPrice = modifyPartFieldPrice.getText();
        String partMin = modifyPartFieldMax.getText();
        String partMax = modifyPartFieldMin.getText();
        String partCompOrMach = modifyPartFieldCompOrMach.getText();

        if (inHouse_Radio.isSelected()) {
            InHouse inHousePart = new InHouse();
            inHousePart.setId(partID + 1);
            inHousePart.setName(partName);
            inHousePart.setStock(Integer.parseInt(partInv));
            inHousePart.setPrice(Double.parseDouble(partPrice));
            inHousePart.setMax(Integer.parseInt(partMax));
            inHousePart.setMin(Integer.parseInt(partMin));
            inHousePart.setMachineId(Integer.parseInt(partCompOrMach));
            Inventory.updatePart(partID, inHousePart);
        } else {
            Outsourced outsourcedPart = new Outsourced();
            outsourcedPart.setId(partID + 1);
            outsourcedPart.setName(partName);
            outsourcedPart.setStock(Integer.parseInt(partInv));
            outsourcedPart.setPrice(Double.parseDouble(partPrice));
            outsourcedPart.setMax(Integer.parseInt(partMax));
            outsourcedPart.setMin(Integer.parseInt(partMin));
            outsourcedPart.setCompanyName(partCompOrMach);
            Inventory.updatePart(partID, outsourcedPart);
        }

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
