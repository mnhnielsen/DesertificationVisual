package sample.controllers;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.WorldOfZuul.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


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
    public Text Trash;
    public AnchorPane anchorPane;

    private static int trashCount = 0;
    private static int soilCount = 0;
    private static int saplingCount = 0;
    public static int coins = 0;
    public static int saplings = 0;
    public static int trash = 0;

    public static Inventory inventory = new Inventory();
    public Text inventoryTrash;
    public Text Coins;
    public Text Saplings;
    public Button shop;

    private Map<String, ImageView > itemMap6 = new HashMap<>();
    private Map<String, ImageView > itemMap8 = new HashMap<>();
    private Map<String, ImageView > itemMap9 = new HashMap<>();

    private int previousRoomType = 0;


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
            makeHashMaps(3,4,5);

        }else if(game.getCurrentRoom().getType() == 3){
            background.setImage(new Image("Resources/CurrencyRoom.png"));
            removeTrashFromRoom();

        }else if(game.getCurrentRoom().getType() == 4){
            background.setImage(new Image("Resources/CurrencyObtainLeft.png"));
            addTrashToRoom(50, 50, "t1");
            addTrashToRoom(100, 100, "t2");
            addTrashToRoom(300, 600, "t3");
            System.out.println(trashCount);
        }else if(game.getCurrentRoom().getType() == 5){

            removeSoilFromRoom(previousRoomType);
            previousRoomType = 0;
            background.setImage(new Image("Resources/DesertBaseRoom.png"));


        }else if(game.getCurrentRoom().getType() == 6){
            background.setImage(new Image("Resources/DesertLeft.png"));

            addSoilsToRoom( 6);
            previousRoomType = game.getCurrentRoom().getType();

        }else if(game.getCurrentRoom().getType() == 8){
            background.setImage(new Image("Resources/DesertRight.png"));

            addSoilsToRoom(8);
            previousRoomType = game.getCurrentRoom().getType();

        }else if(game.getCurrentRoom().getType() == 9){
            background.setImage(new Image("Resources/DesertTop.png"));

            addSoilsToRoom(9);

            previousRoomType = game.getCurrentRoom().getType();

        }else if(game.getCurrentRoom().getType() == 1){
            background.setImage(new Image("Resources/EntryRoom.png"));
        }else if(game.getCurrentRoom().getType() == 11){
            background.setImage(new Image("Resources/CurrencyObtainRight.png"));
        }


    }

    private void makeHashMaps(int roomLeft, int roomRight, int roomTop) {

        for (int i = 1; i <= roomLeft ; i++) {
            itemMap6.put("s6"+i, addSoil("s6"+i));
        }

        for (int i = 1; i <= roomRight; i++) {
            itemMap8.put("s8"+i, addSoil("s8"+i));
        }

        for (int i = 1; i <= roomTop ; i++) {
            itemMap9.put("s9"+i, addSoil("s9"+i));
        }


    }


    //uniform fordeling op til 6
    private void addSoilsToRoom( int type) {

        switch(type){

            case 6:
                for (int i = 1; i <= itemMap6.size(); i++) {

                    int x =  i * 400/itemMap6.size() - 80;

                    addSoilToRoom(x, 220, "s6"+i);

                }
                break;
            case 8:
                for (int i = 1; i <= itemMap8.size(); i++) {

                    int x =  i * 400/itemMap8.size() - 80;

                    addSoilToRoom(x, 350, "s8"+i);

                }
                break;

            case 9:
                for (int i = 1; i <= itemMap9.size(); i++) {

                    int y =  i * 600/ itemMap9.size() - 80;

                    addSoilToRoom(250, y, "s9"+i);


                }
                break;

        }
        

    }

    public ImageView addTrash(){
        ImageView trash = new ImageView(new Image("Resources/trash.png"));
        trash.setFitHeight(50);
        trash.setFitWidth(50);
        trashCount++;
        trash.setId("t"+trashCount);


        trash.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                anchorPane.getChildren().remove(trash);
                inventory.addTrash();
                Trash.setText(""+inventory.countTrash());
            }
        });


        return trash;
    }

    public Node getItemId(String id){
        for (Node node :
                anchorPane.getChildren()) {
            if(node.getId() != null && node.getId().equals(id)){
                return node;
            }
        }
        return null;
    }


    // xlimit = 350, ylimit = 550
    public void addTrashToRoom(double x, double y, String id){
        if(x>350) x = 350;
        if(y>550) y = 550;
        anchorPane.getChildren().add(addTrash());
        AnchorPane.setTopAnchor(getItemId(id), x);
        AnchorPane.setLeftAnchor(getItemId(id), y);
    }
    public ImageView addSoil(String id){
        ImageView soil = new ImageView(new Image("Resources/Soil.png"));
        soilCount++;
        soil.setId(id);

        soil.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                anchorPane.getChildren().remove(soil);

                inventory.removeSapling();

                ImageView sapling = plantSapling();
                sapling.setId(id+"sapling");

                anchorPane.getChildren().add(sapling);
                AnchorPane.setTopAnchor(sapling, mouseEvent.getSceneY() - 50);
                AnchorPane.setLeftAnchor(sapling, mouseEvent.getSceneX() - 25);

            }
        });


        return soil;
    }

    public ImageView plantSapling(){
        ImageView sapling = new ImageView(new Image("Resources/sapling.png"));

        saplingCount++;

        return sapling;
    }

    // xlimit = 350, ylimit = 550
    public void addSoilToRoom(double x, double y, String id){
        if(x>350) x = 350;
        if(y>550) y = 550;
        if(game.getCurrentRoom().getType() == 6){
            anchorPane.getChildren().add(itemMap6.get(id));
            AnchorPane.setTopAnchor(itemMap6.get(id), x);
            AnchorPane.setLeftAnchor(itemMap6.get(id), y);
        }else if (game.getCurrentRoom().getType() == 8){
            anchorPane.getChildren().add(itemMap8.get(id));
            AnchorPane.setTopAnchor(itemMap8.get(id), x);
            AnchorPane.setLeftAnchor(itemMap8.get(id), y);
        }else if(game.getCurrentRoom().getType() == 9){
            anchorPane.getChildren().add(itemMap9.get(id));
            AnchorPane.setTopAnchor(itemMap9.get(id), x);
            AnchorPane.setLeftAnchor(itemMap9.get(id), y);
        }

    }

    public void openShop(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource()==shop){
            if(game.getCurrentRoom().getType() == 3){

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../fxml/shop.fxml"));

                Scene scene = new Scene(fxmlLoader.load(), 400, 300);
                Stage stage = new Stage();

                stage.setTitle("Shop");
                stage.setScene(scene);
                stage.setOnHiding(event-> updateText());
                stage.show();

            }
        }
    }

    public void updateText(){
        Coins.setText(""+coins);
        Trash.setText(""+inventory.countTrash());
        Saplings.setText(""+inventory.countSapling());
    }

    public void removeTrashFromRoom(){
        for (int i = 1; i <= trashCount; i++) {
            for (Node node :
                anchorPane.getChildren()) {
                if(node.getId() != null && node.getId().equals("t"+i)){
                    anchorPane.getChildren().remove(node);
                    break;
                }
            }
        }
        trashCount=0;
    }

    public void removeSoilFromRoom(int type){
        for (int i = 1; i <= soilCount; i++) {
            for (Node node :
                    anchorPane.getChildren()) {
                if(node.getId() != null && node.getId().equals("s"+type+i)){
                    anchorPane.getChildren().remove(node);
                    break;
                }
            }
        }
        soilCount=0;
    }


}
