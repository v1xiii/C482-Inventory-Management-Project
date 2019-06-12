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

    public void initialize(URL url, ResourceBundle rb) {
        Part part = MainScreenController.getPartToModify();
        //System.out.println("ID: " + part.getId());

        modifyPartFieldID.setText(Integer.toString(part.getId()));
        modifyPartFieldName.setText(part.getName());
        modifyPartFieldInv.setText(Integer.toString(part.getStock()));
        modifyPartFieldPrice.setText(Double.toString(part.getPrice()));
        modifyPartFieldMax.setText(Integer.toString(part.getMax()));
        modifyPartFieldMin.setText(Integer.toString(part.getMin()));

        if (part instanceof InHouse) {
            inHouseOutsourced_Label.setText("Machine ID");
            modifyPartFieldCompOrMach.setText(Integer.toString(((InHouse) part).getMachineId()));
            inHouse_Radio.setSelected(true);
            //outsourced_Radio.setSelected(false);
        }
        else {
            inHouseOutsourced_Label.setText("Company Name");
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
            //inHouse_Radio.setSelected(false);
        }
    }

    @FXML
    private void outsourcedRadioHandler(ActionEvent event) {
        if (outsourced_Radio.isSelected()) {
            inHouseOutsourced_Label.setText("Company Name");
            //outsourced_Radio.setSelected(false);
        }
    }

}
