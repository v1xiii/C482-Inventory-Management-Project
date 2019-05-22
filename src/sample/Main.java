package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.scene.control.Button;

public class Main extends Application { //main calls launch, which calls start

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main-screen.fxml"));
        primaryStage.setTitle("Inventory Management System"); // stage title
        Button exit = new Button("Exit Program"); // new button
        exit.setWrapText(true); // button text wrap
        exit.setMaxSize(200, 100); // button size
        //primaryStage.setScene(new Scene(new StackPane(exit), 600, 550)); // scene is sent a stackpane, stackpane is sent a button
        primaryStage.setScene(new Scene(root, 600, 550));
        primaryStage.show(); // shows this stage

        VBox addPartVBox = new VBox(new Label("Add Part")); // VBox positions child nodes in vertical row
        Scene addPartScene = new Scene(addPartVBox, 300, 275); // make another scene, pass VBox vertical row to it

        Stage addPartStage = new Stage(); // make another stage
        addPartStage.setTitle("Add Part"); // set title
        //addPartStage.setCursor(Cursor.OPEN_HAND); // set cursor
        //addPartStage.initStyle(StageStyle.UNDECORATED); // set window decoration
        addPartStage.initOwner(primaryStage); // make primaryStage own this stage, but why???
        addPartStage.setScene(addPartScene); // set scene to stage
        addPartStage.initModality(Modality.APPLICATION_MODAL); // make this stage a blocking modal
        //addPartStage.show(); // show it!
    }

    public static void main(String[] args) {
        launch(args); // launches the JavaFX application (calls start)
    }
}
