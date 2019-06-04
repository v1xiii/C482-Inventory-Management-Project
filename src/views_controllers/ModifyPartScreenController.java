package views_controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class ModifyPartScreenController {

    @FXML
    private RadioButton inHouse_Radio;
    @FXML
    private RadioButton outsourced_Radio;
    @FXML
    private Label inHouseOutsourced_Label;

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
