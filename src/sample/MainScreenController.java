package sample;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScreenController {

    @FXML
    private Button exitButton;
    @FXML
    private Button addPartButton;

    @FXML
    public void exitProgram(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void openAddParts(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("add-part-screen.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add Part");
        stage.setScene(new Scene(root, 750, 750));
        stage.show();
    }

    @FXML
    public void openModifyParts(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("modify-part-screen.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Modify Part");
        stage.setScene(new Scene(root, 750, 750));
        stage.show();
    }
}


