package sample.controllers;


import WorldOfZuul.Game;
import WorldOfZuul.Room;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.beans.EventHandler;
import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    public HBox topHBox;
    public HBox botHBox;
    public GridPane grid;
    public AnchorPane anchorPane;
    public ImageView background;


    public ImageView makePlayer() {
        Image player = new Image("Resources/playerSprite.png");

        return new ImageView(player);
    }

    public void playerInGrid(MouseEvent event) {
        ImageView player = makePlayer();
        grid.getChildren().add(player);
        player.setId("player");
        GridPane.setColumnIndex(player, 1);
        GridPane.setRowIndex(player, 2);

    }
    public void changeBackground(){
        boolean run = true;

        do
        {
            switch(Game) {
                case x:
                    // code block
                    break;
                case y:
                    // code block
                    break;
                default:
                    // code block
            }

        }
        while (run)
    background.setImage();
    }

    public void plantInGrid(MouseEvent event) {
        ImageView tree = addTree();
        grid.getChildren().add(tree);
        tree.setId("test");
        GridPane.setColumnIndex(tree, 1);
        GridPane.setRowIndex(tree, 2);

    }

    public ImageView addTree() {
        Image tree = new Image("Resources/Tree.png");

        return new ImageView(tree);
    }

    public void playerMove(KeyEvent keyEvent) {
        Node player = null;
        for (Node node :
                grid.getChildren()) {
            if (node.getId() == "player") {
                player = node;
            }
        }



        //System.out.println(keyEvent);
        //System.out.println(GridPane.getRowIndex(player));
        //System.out.println(GridPane.getColumnIndex(player));

        switch (keyEvent.getCode()) {
            case W:
                player.setTranslateY(player.getTranslateY() - 5);
                System.out.println("Move Right");

                break;
            case A:
                player.setTranslateX(player.getTranslateX() - 5);
                System.out.println("Move Left");

                break;
            case S:
                player.setTranslateY(player.getTranslateY() + 5);
                System.out.println("Move Down");
                break;
            case D:
                player.setTranslateX(player.getTranslateX() + 5);
                System.out.println("Move Right");
                break;
        }
    }

    /*
    //static count of trees in anchorpane
    private static int treeCount = 0;




    //returns a tree ready to put into anchorpane
    public ImageView addTree(){
        Image tree = new Image("Resources/Tree.png");
        treeCount++;
        return new ImageView(tree);
    }

    
    //leftclick plants a tree at mouse position
    public void plantMouseClick(MouseEvent event){

        if(removeTree(event.getSceneX(), event.getSceneY())){

        }
        else {
            ImageView tree = addTree();
            //sets id of the tree to: treen, where n is the amount of trees
            tree.setId("tree" + treeCount);
            anchorPane.getChildren().add(tree);
            AnchorPane.setTopAnchor(tree, event.getSceneY());
            AnchorPane.setLeftAnchor(tree, event.getSceneX());

            System.out.println(anchorPane.getChildren());
            //System.out.println(treeCount);
            //System.out.println(anchorPane.getChildren().get(treeCount).getLayoutX() + " " + anchorPane.getChildren().get(treeCount).getLayoutY());
            //listTrees();
        }
    }


    public List<Node> listTrees(){
        List<Node> trees = new ArrayList<>();
        for ( int i = 2; i < anchorPane.getChildren().size(); i++ ) {
            trees.add(anchorPane.getChildren().get(i));
        }
        return trees;

    }

    public boolean removeTree(double x, double y){

        List<Node> trees = listTrees();
        for ( Node tree :
                trees ) {
            double treeX = tree.getLayoutX();
            double treeY = tree.getLayoutY();

            if(x <= treeX+50 && x > treeX && y <= treeY+50 && y>treeY){
               /* System.out.println("mouse" + x + " " + y );
                System.out.println("Tree" + treeX + " "+ treeY);
                System.out.println();

                anchorPane.getChildren().remove(tree);
                
                return true;
            }
        }
        return false;
    }*/


}
