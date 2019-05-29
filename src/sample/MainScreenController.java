package sample;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class MainScreenController {

    @FXML
    private Button exitButton;

    @FXML
    public void exitProgram(ActionEvent event) {
        System.exit(0);
    }
}
