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
        initEventHandlers();
    }

    /**
     * A helper method used to set the text of the label to the value in the array given
     * @param array - an array of tile objects
     */
    private void updateLabelInGridPane(Tile[][] array){
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
            try {
                movement.moveTile("w");
                array2D.set(theBoard.getBoard());
                updateLabelInGridPane(array2D.get());
            } catch (TileOccupiedException e) {
                throw new RuntimeException(e);
            } catch (OutOfBoardException e) {
                throw new RuntimeException(e);
            }
        });
        btnDown.setOnAction(keyEvent -> {
            try {
                movement.moveTile("s");
                array2D.set(theBoard.getBoard());
                updateLabelInGridPane(array2D.get());
            } catch (TileOccupiedException e) {
                throw new RuntimeException(e);
            } catch (OutOfBoardException e) {
                throw new RuntimeException(e);
            }
        });
        btnLeft.setOnAction(keyEvent -> {
            try {
                movement.moveTile("a");
                array2D.set(theBoard.getBoard());
                updateLabelInGridPane(array2D.get());
            } catch (TileOccupiedException e) {
                throw new RuntimeException(e);
            } catch (OutOfBoardException e) {
                throw new RuntimeException(e);
            }
        });
        btnRight.setOnAction(keyEvent -> {
            try {
                movement.moveTile("d");
                array2D.set(theBoard.getBoard());
                updateLabelInGridPane(array2D.get());
            } catch (TileOccupiedException e) {
                throw new RuntimeException(e);
            } catch (OutOfBoardException e) {
                throw new RuntimeException(e);
            }
        });

//        btnNewGame.setOnAction(actionEvent -> {
//            array2D.set(tiles);
//        });
    }
        /**
         * need to have moveTile take in a string
         * need movement to properly handle the board instead of the console
         * btnNewGame is the only thing that allows scenebuilder to change
         * Made exceptions their own class
         * changed board constructor to add 0s
         */
}
