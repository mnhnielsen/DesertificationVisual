package sample.controllers;


import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.WorldOfZuul.*;


public class RoomController {

    @FXML
    public ImageView background;

    @FXML
    public Button East;
    @FXML
    public Button North;
    @FXML
    public Button West;
    @FXML
    public Button South;

    Game game = new Game();

    @FXML
    public void roomInfo() {
        Stage stage = new Stage();
        HBox root = new HBox();
        TextArea textArea = new TextArea();
        textArea.setText(game.getRoomInfo());
        root.getChildren().add(textArea);
        Scene scene = new Scene(root, 600, 300);
        stage.setScene(scene);
        stage.show();
    }

    public void goNorth(MouseEvent event) {


        if (event.getSource() == East) {
            Command command = new Command(CommandWord.GO, "east");
            game.goRoom(command);
            setBackground();
        }
        if (event.getSource() == South) {
            Command command = new Command(CommandWord.GO, "south");
            game.goRoom(command);
            setBackground();
        }
        if (event.getSource() == North) {
            Command command = new Command(CommandWord.GO, "north");
            game.goRoom(command);
            setBackground();
        }
        if (event.getSource() == West) {
            Command command = new Command(CommandWord.GO, "west");
            game.goRoom(command);
            setBackground();
        }
    }

    public void setBackground() {
        if (game.getCurrentRoom().getType() == 2) {
            background.setImage(new Image("Resources/TutorialRoom.png"));
        }
    }


}
