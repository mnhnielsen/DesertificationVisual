package Logic.Controllers;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;

public class EntryRoomController {
    public Button StartGame;
    public AnchorPane anchorPane;


    public void StartGame(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/_Presentation/Room.fxml"));
        Stage CurrentStage = (Stage) StartGame.getScene().getWindow();

        Scene scene = new Scene(fxmlLoader.load(), 900, 800);
        Stage stage = new Stage();
        stage.setTitle("World of Zuul");
        stage.setScene(scene);
        stage.show();
        CurrentStage.close();

    }

}