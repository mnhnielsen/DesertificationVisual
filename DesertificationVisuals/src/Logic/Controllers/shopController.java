package Logic.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class shopController {


    public Label coinLabel;
    public Button buyButton;
    public Label saplingLabel;
    public Button sellButton;
    public Label trashLabel;
    public Button buyButtonShovel;


    public void initialize() {
        if(!RoomController.hasShovel){
            buyButtonShovel.setVisible(true);
        }else{
            buyButtonShovel.setVisible(false);
        }
        updateText();
    }

    public void onAction(ActionEvent actionEvent) {

        if (actionEvent.getSource() == sellButton && RoomController.inventory.hasTrash()) {
            RoomController.inventory.removeTrash();
            RoomController.coins++;
            updateText();

        } else if (actionEvent.getSource() == buyButton && RoomController.coins > 0 && RoomController.inventory.countSapling() < 13) {
            RoomController.coins--;
            RoomController.inventory.addSapling();
            updateText();
        } else if (actionEvent.getSource() == buyButtonShovel && RoomController.coins > 2){
            RoomController.hasShovel = true;
            RoomController.coins -= 3;
            buyButtonShovel.setVisible(false);
            updateText();
        }
    }

    private void updateText() {
        coinLabel.setText("" + RoomController.coins);
        trashLabel.setText("" + RoomController.inventory.countTrash());
        saplingLabel.setText("" + RoomController.inventory.countSapling());
    }


}
