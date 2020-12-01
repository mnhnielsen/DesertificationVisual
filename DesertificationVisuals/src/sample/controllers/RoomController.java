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
import javafx.stage.Modality;
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
    public Text Room;

    private Map<String, ImageView > itemMap6 = new HashMap<>();
    private Map<String, ImageView > itemMap8 = new HashMap<>();
    private Map<String, ImageView > itemMap9 = new HashMap<>();

    //Id, [x,y]

    private Map<String, double[]> saplingMap6 = new HashMap<>();
    private Map<String, double[]> saplingMap8 = new HashMap<>();
    private Map<String, double[]> saplingMap9 = new HashMap<>();

    private int previousRoomType = 0;

    private boolean first6 = true;
    private boolean first8 = true;
    private boolean first9 = true;



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


    //Big working function. Should probably split it up more
    public void setBackground() {
        Room.setText(game.getCurrentRoom().getRoomName(game.getCurrentRoom().getType()));
        if (game.getCurrentRoom().getType() == 2) {
            background.setImage(new Image("Resources/TutorialRoom.png"));
            showButtons(true,false,false,false,false);

            //initialize the amount of Soil patches in each desert
            makeHashMaps(3,4,5);

        }else if(game.getCurrentRoom().getType() == 3){
            background.setImage(new Image("Resources/CurrencyRoom.png"));

            showButtons(true,true,true,true,true);

            //removes trash from the scene
            removeTrashFromRoom();

        }else if(game.getCurrentRoom().getType() == 4){
            background.setImage(new Image("Resources/CurrencyObtainLeft.png"));
            showButtons(false,true,false,false,false);

            //manually addding trash. Should refactor
            addTrashToRoom(Math.random()*600, Math.random()*350, "t1");
            addTrashToRoom(Math.random()*550, Math.random()*350, "t2");
            addTrashToRoom(Math.random()*450 + 100, Math.random()*350, "t3");


        }else if(game.getCurrentRoom().getType() == 11){
            background.setImage(new Image("Resources/CurrencyObtainRight.png"));
            showButtons(false, false, false, true, false);


            addTrashToRoom(Math.random()*600, Math.random()*350, "t1");
            addTrashToRoom(Math.random()*550, Math.random()*350, "t2");
            addTrashToRoom(Math.random()*450 + 100, Math.random()*350, "t3");
        }else if(game.getCurrentRoom().getType() == 5){


            removeSoilFromRoom(previousRoomType);
            previousRoomType = 0;

            background.setImage(new Image("Resources/DesertBaseRoom.png"));
            showButtons(true,true,true,true,false);


        }else if(game.getCurrentRoom().getType() == 6){
            background.setImage(new Image("Resources/DesertLeft.png"));
            showButtons(false,true,false,false,false);

            if(first6){
                addSoilsToRoom( 6);
                first6 = false;
            }else{
                keepItems(6);
            }
            previousRoomType = game.getCurrentRoom().getType();

        }else if(game.getCurrentRoom().getType() == 8){
            background.setImage(new Image("Resources/DesertRight.png"));
            showButtons(false,false,false,true,false);

            if(first8){
                addSoilsToRoom(8);
                first8=false;
            }else{
                keepItems(8);
            }
            previousRoomType = game.getCurrentRoom().getType();

        }else if(game.getCurrentRoom().getType() == 9){
            background.setImage(new Image("Resources/DesertTop.png"));
            showButtons(false, false, true, false,false);

            if(first9){
                addSoilsToRoom(9);
                first9 = false;
            }else{
                keepItems(9);
            }
            previousRoomType = game.getCurrentRoom().getType();

        }else if(game.getCurrentRoom().getType() == 1){
            background.setImage(new Image("Resources/EntryRoom.png"));
            showButtons(true, false, false, false, false);
        }
    }


    //initialising all hashmaps with soil pathces
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

    //uniform distribution of soil patches when adding them to room. Only called on initial entry. Could be initialized with the other map.
    private void addSoilsToRoom( int type) {
        switch(type){
            case 6:
                for (int i = 1; i <= itemMap6.size(); i++) {

                    int x =  i * 400/itemMap6.size() - 80;

                    addSoilToRoom(x, 220, "s6"+i);
                    saplingMap6.put("s6"+i, new double[]{x,220});
                }
                break;
            case 8:
                for (int i = 1; i <= itemMap8.size(); i++) {

                    int x =  i * 400/itemMap8.size() - 80;

                    addSoilToRoom(x, 350, "s8"+i);
                    saplingMap8.put("s8"+i, new double[]{x,350});
                }
                break;
            case 9:
                for (int i = 1; i <= itemMap9.size(); i++) {

                    int y =  i * 600/ itemMap9.size() - 80;

                    addSoilToRoom(250, y, "s9"+i);
                    saplingMap9.put("s9"+i, new double[]{250,y});
                }
                break;
        }
    }

    // xlimit = 350, ylimit = 550
    public void addSoilToRoom(double x, double y, String id){
        if(x>350) x = 350;
        if(y>550) y = 550;

        if(game.getCurrentRoom().getType() == 6){
            anchorPane.getChildren().add(itemMap6.get(id));
            AnchorPane.setTopAnchor(itemMap6.get(id), x);
            AnchorPane.setLeftAnchor(itemMap6.get(id), y);
        }
        else if (game.getCurrentRoom().getType() == 8){
            anchorPane.getChildren().add(itemMap8.get(id));
            AnchorPane.setTopAnchor(itemMap8.get(id), x);
            AnchorPane.setLeftAnchor(itemMap8.get(id), y);
        }
        else if(game.getCurrentRoom().getType() == 9){
            anchorPane.getChildren().add(itemMap9.get(id));
            AnchorPane.setTopAnchor(itemMap9.get(id), x);
            AnchorPane.setLeftAnchor(itemMap9.get(id), y);
        }

    }

    //function that is called when a piece of trash is needed.
    // Each piece of trash has an eventhandler for mouseclick.
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

    //function that is called when a soild patch is needed.
    //Each patch has an eventhandler for mouseclick that removes the soil patch and replaces it with a sapling
    // id of soil is s + roomtype + n.  n>0
    public ImageView addSoil(String id){
        ImageView soil = new ImageView(new Image("Resources/Soil.png"));
        soilCount++;
        soil.setId(id);

        soil.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(inventory.hasSapling()){
                    anchorPane.getChildren().remove(soil);
                    inventory.removeSapling();
                    updateText();

                    ImageView sapling = addSapling();

                    // ID is same as the soil patch it came from, but prefixed with "p"
                    sapling.setId("p"+id);

                    anchorPane.getChildren().add(sapling);
                    AnchorPane.setTopAnchor(sapling, mouseEvent.getSceneY() - 50);
                    AnchorPane.setLeftAnchor(sapling, mouseEvent.getSceneX() - 25);

                    int currentroom = game.getCurrentRoom().getType();

                    //adds the sapling to the correct map with coresponding anchors
                    if(currentroom == 6){
                        saplingMap6.put(sapling.getId(), new double[]{mouseEvent.getSceneY() - 50, mouseEvent.getSceneX() - 25});
                        saplingMap6.remove(id);
                    }else if(currentroom == 8){
                        saplingMap8.put(sapling.getId(), new double[]{mouseEvent.getSceneY() - 50, mouseEvent.getSceneX() - 25});
                        saplingMap8.remove(id);
                    }else if(currentroom == 9){
                        saplingMap9.put(sapling.getId(), new double[]{mouseEvent.getSceneY() - 50, mouseEvent.getSceneX() - 25});
                        saplingMap9.remove(id);
                    }
                }
            }
        });

        return soil;
    }

    public ImageView addSapling(){
        ImageView sapling = new ImageView(new Image("Resources/sapling.png"));
        saplingCount++;
        return sapling;
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
    // add trash at positions as arguments and by id
    public void addTrashToRoom(double x, double y, String id){
        if(x>350) x = 350;
        if(y>550) y = 550;
        anchorPane.getChildren().add(addTrash());
        AnchorPane.setTopAnchor(getItemId(id), x);
        AnchorPane.setLeftAnchor(getItemId(id), y);
    }


    //ImageViews to be added to corresponding rooms based on type. calls the map for the room
    public void keepItems(int type){
        if(type == 6){
            saplingMap6.forEach( (k, v) ->{
                if(k.startsWith("p")){
                    ImageView sapling = addSapling();
                    sapling.setId(k);

                    anchorPane.getChildren().add(sapling);
                    AnchorPane.setTopAnchor(sapling, v[0]);
                    AnchorPane.setLeftAnchor(sapling, v[1]);
                }
                else{
                    ImageView soil = addSoil(k);

                    anchorPane.getChildren().add(soil);
                    AnchorPane.setTopAnchor(soil, v[0]);
                    AnchorPane.setLeftAnchor(soil, v[1]);
                }
            });
        }else if(type == 8){
            saplingMap8.forEach( (k, v) ->{
                if(k.startsWith("p")){
                    ImageView sapling = addSapling();
                    sapling.setId(k);

                    anchorPane.getChildren().add(sapling);
                    AnchorPane.setTopAnchor(sapling, v[0]);
                    AnchorPane.setLeftAnchor(sapling, v[1]);
                }
                else{
                    ImageView soil = addSoil(k);

                    anchorPane.getChildren().add(soil);
                    AnchorPane.setTopAnchor(soil, v[0]);
                    AnchorPane.setLeftAnchor(soil, v[1]);
                }
            });
        }else if(type == 9){
            saplingMap9.forEach( (k, v) ->{
                if(k.startsWith("p")){
                    ImageView sapling = addSapling();
                    sapling.setId(k);

                    anchorPane.getChildren().add(sapling);
                    AnchorPane.setTopAnchor(sapling, v[0]);
                    AnchorPane.setLeftAnchor(sapling, v[1]);
                }
                else{
                    ImageView soil = addSoil(k);

                    anchorPane.getChildren().add(soil);
                    AnchorPane.setTopAnchor(soil, v[0]);
                    AnchorPane.setLeftAnchor(soil, v[1]);
                }
            });
        }
    }
    

    //removes trash from the scene when leaving the trash rooms
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

    //removes soil and saplings from scene when leaving deserts
    public void removeSoilFromRoom(int type){

        if(type == 6){
            for (int i = 1; i <= itemMap6.size(); i++) {
                for (Node node :
                        anchorPane.getChildren()) {
                    if(node.getId() != null && node.getId().equals("s6"+i)){
                        anchorPane.getChildren().remove(node);
                        break;
                    }
                    else if(node.getId() != null && node.getId().equals("ps6"+i)){
                        anchorPane.getChildren().remove(node);
                        break;
                    }
                }
            }
        }else if(type == 8){
            for (int i = 1; i <= itemMap8.size(); i++) {
                for (Node node :
                        anchorPane.getChildren()) {
                    if(node.getId() != null && node.getId().equals("s8"+i)){
                        anchorPane.getChildren().remove(node);
                        break;
                    }
                    else if(node.getId() != null && node.getId().equals("ps8"+i)){
                        anchorPane.getChildren().remove(node);
                        break;
                    }
                }
            }
        }else if(type == 9){
            for (int i = 1; i <= itemMap9.size(); i++) {
                for (Node node :
                        anchorPane.getChildren()) {
                    if(node.getId() != null && node.getId().equals("s9"+i)){
                        anchorPane.getChildren().remove(node);
                        break;
                    }
                    else if(node.getId() != null && node.getId().equals("ps9"+i)){
                        anchorPane.getChildren().remove(node);
                        break;
                    }
                }
            }
        }
        soilCount=0;
    }

    //opens new window when Shop button is pressed
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
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();


            }
        }
    }

    public void updateText(){
        Coins.setText(""+coins);
        Trash.setText(""+inventory.countTrash());
        Saplings.setText(""+inventory.countSapling());
    }

    //function called on every room to set visibility on buttons
    public void showButtons(boolean north, boolean east, boolean south, boolean west, boolean vendor){
        North.setVisible(north);
        East.setVisible(east);
        South.setVisible(south);
        West.setVisible(west);
        shop.setVisible(vendor);
    }

}
