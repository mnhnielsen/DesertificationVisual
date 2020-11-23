package sample;


import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.beans.EventHandler;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    public HBox topHBox;
    public HBox botHBox;
    public GridPane grid;
    public AnchorPane anchorPane;
    public ImageView background;

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
                System.out.println();*/

                anchorPane.getChildren().remove(tree);
                
                return true;
            }
        }
        return false;
    }


}
