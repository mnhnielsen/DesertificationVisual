package sample.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.WorldOfZuul.Inventory;
import sample.WorldOfZuul.Room;

public class shopController {



    public Label coinLabel;
    public Button buyButton;
    public Label saplingLabel;
    public Button sellButton;
    public Label trashLabel;





    public void initialize(){
        updateText();
    }

    public void onAction(ActionEvent actionEvent) {

        if(actionEvent.getSource() == sellButton && RoomController.inventory.hasTrash()){
            RoomController.inventory.removeTrash();
            RoomController.coins++;
            updateText();

        }
        else if(actionEvent.getSource() == buyButton && RoomController.coins > 0){
            RoomController.coins--;
            RoomController.inventory.addSapling();
            updateText();
        }
    }

    private void updateText(){
        coinLabel.setText(""+RoomController.coins);
        trashLabel.setText(""+RoomController.inventory.countTrash());
        saplingLabel.setText(""+RoomController.inventory.countSapling());
    }



}
