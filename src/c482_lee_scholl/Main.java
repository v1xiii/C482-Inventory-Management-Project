package c482_lee_scholl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application { //main calls launch, which calls start

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/views_controllers/main-screen.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root, 1600, 900));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // launches the JavaFX application (calls start)
    }
}

/* TODO
* ensuring that a product must always have at least one part
* including a confirm dialogue for all "Delete" and "Cancel" buttons
*  WANNADO
* cost columns need to display two decimal points
* id # generation is hot garbage
* make blank search show all items
* */