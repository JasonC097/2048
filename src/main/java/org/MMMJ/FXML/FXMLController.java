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

import java.net.URL;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import org.MMMJ.*;


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

    public void initBindings(){

//        System.out.println("initBindings");
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

    }

    public static Board getTheBoard() {
        return theBoard;
    }

    public static Movement getMovement(){
        return movement;
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
}
