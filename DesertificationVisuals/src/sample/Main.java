package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.WorldOfZuul.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controllers.RoomController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Room.fxml"));
        primaryStage.setTitle("World of Desert");


        primaryStage.setScene(new Scene(root, 900, 675));

        primaryStage.show();


    }




    public static void main(String[] args) {
        launch(args);
    }
}
