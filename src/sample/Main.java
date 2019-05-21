package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;

public class Main extends Application { //main calls launch, which calls start

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Inventory Management System"); // stage title
        primaryStage.setScene(new Scene(root, 600, 550)); // scene to be displayed on primaryStage
        primaryStage.show(); // shows this stage

        VBox addPartVBox = new VBox(new Label("Add Part")); // what is VBox?
        Scene addPartScene = new Scene(addPartVBox, 300, 275); // make another scene, pass VBox to it

        Stage addPartStage = new Stage(); // make another stage
        addPartStage.setTitle("Add Part"); // set title
        //addPartStage.setCursor(Cursor.OPEN_HAND); // set cursor
        //addPartStage.initStyle(StageStyle.UNDECORATED); // set window decoration
        addPartStage.initOwner(primaryStage); // make primaryStage own this stage, but why???
        addPartStage.setScene(addPartScene); // set scene to stage
        addPartStage.initModality(Modality.APPLICATION_MODAL); // make this stage a blocking modal
        addPartStage.show(); // show it!
    }


    public static void main(String[] args) {
        launch(args); // launches the JavaFX application (calls start)

    }
}
