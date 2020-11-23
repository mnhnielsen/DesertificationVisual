package sample;

import WorldOfZuul.Game;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("World of Desert");

        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();

        Game game = new Game();
        game.play();
    }




    public static void main(String[] args) {
        launch(args);
    }
}
