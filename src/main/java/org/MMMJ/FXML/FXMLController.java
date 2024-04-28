/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Mike Merola
 * Section: 01
 * Date: 4/15/24
 * Time: 3:52 PM
 *
 * Project: csci205_final_project
 * Package: org.MMMJ.FXML
 * Class: FXMLController
 *
 * Description:
 *
 * ****************************************
 */
package org.MMMJ.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.MMMJ.*;
import javax.swing.*;


public class FXMLController{

    public static Board theBoard = new Board(4);

    private GameManager manager = new GameManager(theBoard, movement, 16);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnNewGame;

    @FXML
    private Label labelScore;

    @FXML
    private GridPane tileGrid;

    @FXML
    private Button btnUp;

    @FXML
    private Button btnDown;

    @FXML
    private Button btnLeft;

    @FXML
    private Button btnRight;

    @FXML
    private Label labelTitle1;

    /**An instance of the {@link Movement} class**/
    public static  Movement movement = new Movement(theBoard);


    @FXML
    void initialize() throws TileOccupiedException, OutOfBoardException {
        assert btnNewGame != null : "fx:id=\"btnNewGame\" was not injected: check your FXML file 'FinalFXML.fxml'.";
        assert labelScore != null : "fx:id=\"labelScore\" was not injected: check your FXML file 'FinalFXML.fxml'.";
        assert tileGrid != null : "fx:id=\"tileGrid\" was not injected: check your FXML file 'FinalFXML.fxml'.";
        initBindings();
        theBoard.addTile(1,1,new Tile(4));
//        theBoard.printBoard();

        initEventHandlers();
//        tileGrid.requestFocus();
//        updateLabelInGridPane(theBoard.getBoard());
    }

    /**
     * sets up a binding between the board and the labels in tileGrid
     * used information from @see </https://stackoverflow.com/questions/26838183/how-to-monitor-changes-on-objects-contained-in-an-observablelist-javafx>
     * to correctly bind 2D Observable list of tiles with the labels in the grid pane
     */
    public void initBindings(){
        System.out.println("initBindings");
        for (int row = 0; row < theBoard.getBoard().size(); row++) {
            theBoard.getBoard().get(row).addListener(new ListChangeListener<Tile>() {
                @Override
                public void onChanged(Change<? extends Tile> change) {

                    updateLabelInGridPane(theBoard.getBoard());
                    System.out.println("Running on changed");
                    manager.getBoard().printBoard();
//                    if(manager.didPlayerWin()){
//
//                    }
                    manager.didPlayerWin();
//                    try {
//                        manager.didPlayerLose();
//                    } catch (TileOccupiedException e) {
//                        throw new RuntimeException(e);
//                    } catch (OutOfBoardException e) {
//                        throw new RuntimeException(e);
//                    } catch (BoardIsFullException e) {
//                        throw new RuntimeException(e);
//                    }

                }
            });
        }

    }

    /**
     * A helper method used to set the text of the label to the value in the array given
     * @param array - an array of tile objects
     */
    private void updateLabelInGridPane(ObservableList<ObservableList<Tile>> array){
//        System.out.println("Update the grid pane");
        for (int row = 0; row < array.size(); row++){
            for (int col = 0; col < array.get(row).size(); col++){
                Label label = (Label) tileGrid.lookup("#label" +row + col);
//                System.out.println((array.get(row).get(col).getCurrNum()));
                label.setText(String.valueOf(array.get(row).get(col).getCurrNum()));
                if(label.getText() == "0"){
                    label.setVisible(false);
                }

            }
        }
    }



    /**
     * A method used to update the board when specific ques are given
     */
    public void initEventHandlers(){


        btnUp.setOnAction(keyEvent -> {
            changeBoard("w");
            keyEvent.consume();
        });
        btnDown.setOnAction(keyEvent -> {
            changeBoard("s");
            keyEvent.consume();

        });
        btnLeft.setOnAction(keyEvent -> {
            changeBoard("a");
            keyEvent.consume();

        });
        btnRight.setOnAction(keyEvent -> {
            changeBoard("d");
            keyEvent.consume();

        });

        btnNewGame.setOnAction(actionEvent -> {
            theBoard.setBoard(new Board(4).getBoard());
            updateLabelInGridPane(theBoard.getBoard());
            labelScore.setText("0");
        });
    }

    /**
     * A helper method that takes in a direction, then uses the movement class to
     * move the tiles and finally update the array
     * @param direction - direction the user wants the tiles to move
     */
    public void changeBoard(String direction) {
        try {
            movement.moveTile(direction);
//            theBoard.printBoard();
//            theBoard.set(theBoard.getBoard());
            //updateLabelInGridPane(array2D.get());
        } catch (TileOccupiedException | BoardIsFullException e) {
            throw new RuntimeException(e);
        } catch (OutOfBoardException  e) {
            throw new RuntimeException(e);
        }
    }
    public void loserPopup(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("You Lose!");
        alert.setHeaderText("");
        alert.setContentText("You did not reach 2048"+"\n"+"Press new game to play again!");
        alert.setGraphic(labelTitle1);
        alert.showAndWait();
    }

    public void winnerPopup(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("You Won!");
        alert.setHeaderText("");
        alert.setContentText("You reached 2048!"+"\n"+"You may continue playing or start a new game");
        alert.setGraphic(labelTitle1);
        alert.showAndWait();
    }
}
