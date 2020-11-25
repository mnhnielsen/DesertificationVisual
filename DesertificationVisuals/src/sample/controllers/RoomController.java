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
    @FXML
    public TextArea roomInfo;

    Game game = new Game();

    @FXML
    public void setRoomInfo() {
        roomInfo.setText(game.getRoomInfo());
    }

    public void roomDirection(MouseEvent event) {
        if (event.getSource() == East) {
            Command command = new Command(CommandWord.GO, "east");
            game.goRoom(command);
            setRoomInfo();
            setBackground();
        }
        if (event.getSource() == South) {
            Command command = new Command(CommandWord.GO, "south");
            game.goRoom(command);
            setRoomInfo();
            setBackground();
        }
        if (event.getSource() == North) {
            Command command = new Command(CommandWord.GO, "north");
            game.goRoom(command);
            setRoomInfo();
            setBackground();
        }
        if (event.getSource() == West) {
            Command command = new Command(CommandWord.GO, "west");
            game.goRoom(command);
            setRoomInfo();
            setBackground();
        }
    }

    public void setBackground() {
        if (game.getCurrentRoom().getType() == 2) {
            background.setImage(new Image("Resources/TutorialRoom.png"));
        }else if(game.getCurrentRoom().getType() == 3){
            background.setImage(new Image("Resources/CurrencyRoom.png"));
        }else if(game.getCurrentRoom().getType() == 4){
            background.setImage(new Image("Resources/CurrencyObtainLeft.png"));
        }else if(game.getCurrentRoom().getType() == 5){
            background.setImage(new Image("Resources/DesertBaseRoom.png"));
        }else if(game.getCurrentRoom().getType() == 6){
            background.setImage(new Image("Resources/DesertLeft.png"));
        }else if(game.getCurrentRoom().getType() == 8){
            background.setImage(new Image("Resources/DesertRight.png"));
        }else if(game.getCurrentRoom().getType() == 9){
            background.setImage(new Image("Resources/DesertTop.png"));
        }else if(game.getCurrentRoom().getType() == 1){
            background.setImage(new Image("Resources/EntryRoom.png"));
        }else if(game.getCurrentRoom().getType() == 11){
            background.setImage(new Image("Resources/CurrencyObtainRight.png"));
        }


    }


}
