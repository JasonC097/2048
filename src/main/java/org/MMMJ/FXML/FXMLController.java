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
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import org.MMMJ.*;


public class FXMLController {

    private Board theBoard = new Board(4);

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

    /**An instance of the {@link ObservableArray2D} class that is used to create the values
     * in the board **/
    private ObservableArray2D<Tile> array2D = new ObservableArray2D<Tile>(theBoard.getBoard());

//    ObservableList<ObservableList<Tile>> array2D =
    /**An instance of the {@link Movement} class**/
    private Movement movement = new Movement(theBoard);


    @FXML
    void initialize() throws TileOccupiedException, OutOfBoardException {
        assert btnNewGame != null : "fx:id=\"btnNewGame\" was not injected: check your FXML file 'FinalFXML.fxml'.";
        assert labelScore != null : "fx:id=\"labelScore\" was not injected: check your FXML file 'FinalFXML.fxml'.";
        assert tileGrid != null : "fx:id=\"tileGrid\" was not injected: check your FXML file 'FinalFXML.fxml'.";
        theBoard.initBoard();
        theBoard.addTile(3,3,new Tile(2));
        array2D.set(theBoard.getBoard());
        updateLabelInGridPane(array2D.get());
        initBindings();
        initEventHandlers();
    }

    public void initBindings(){
        System.out.println("initBindings");
        array2D.arrayProperty().addListener((observableValue, tiles, t1) ->
                updateLabelInGridPane(t1));

    }

    /**
     * A helper method used to set the text of the label to the value in the array given
     * @param array - an array of tile objects
     */
    private void updateLabelInGridPane(Tile[][] array){
        System.out.println("Update the grid pane");
        for (int row = 0; row < array.length; row++){
            for (int col = 0; col < array[row].length; col++){
                Label label = (Label) tileGrid.lookup("#label" +row + col);
                label.setText(String.valueOf(array[row][col].getCurrNum()));
            }
        }
    }

    /**
     * A method used to update the board when specific ques are given
     */
    public void initEventHandlers(){
        btnUp.setOnAction(keyEvent -> {
            changeBoard("w");
        });
        btnDown.setOnAction(keyEvent -> {
            changeBoard("s");
        });
        btnLeft.setOnAction(keyEvent -> {
            changeBoard("a");
        });
        btnRight.setOnAction(keyEvent -> {
            changeBoard("d");
        });

//        btnNewGame.setOnAction(actionEvent -> {
//            theBoard.initBoard();
//            array2D.set(theBoard.getBoard());
//        });
    }

    /**
     * A helper method that takes in a direction, then uses the movement class to
     * move the tiles and finally update the array
     * @param direction - direction the user wants the tiles to move
     */
    private void changeBoard(String direction) {
        try {
            movement.moveTile(direction);
            array2D.set(theBoard.getBoard());
            //updateLabelInGridPane(array2D.get());
        } catch (TileOccupiedException e) {
            throw new RuntimeException(e);
        } catch (OutOfBoardException e) {
            throw new RuntimeException(e);
        }
    }
}
