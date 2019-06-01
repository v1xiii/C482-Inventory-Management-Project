package sample;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainScreenController {

    //@FXML
    //private Button exitButton;
    //@FXML
    //private Button addPartButton;

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

    @FXML
    public void openModifyParts(ActionEvent event) throws IOException {
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
}


