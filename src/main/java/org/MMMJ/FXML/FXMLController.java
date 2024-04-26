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
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    /**An instance of the {@link Movement} class**/
    private Movement movement = new Movement(theBoard);


    @FXML
    void initialize() throws TileOccupiedException, OutOfBoardException {
        assert btnNewGame != null : "fx:id=\"btnNewGame\" was not injected: check your FXML file 'FinalFXML.fxml'.";
        assert labelScore != null : "fx:id=\"labelScore\" was not injected: check your FXML file 'FinalFXML.fxml'.";
        assert tileGrid != null : "fx:id=\"tileGrid\" was not injected: check your FXML file 'FinalFXML.fxml'.";
        theBoard.addTile(1,1,new Tile(4));
        initBindings();
        initEventHandlers();
        updateLabelInGridPane(theBoard.getBoard());
    }

    /**
     * sets up a binding between the board and the labels in tileGrid
     * used information from @see </https://stackoverflow.com/questions/26838183/how-to-monitor-changes-on-objects-contained-in-an-observablelist-javafx>
     * to correctly bind 2D Observable list of tiles with the labels in the grid pane
     */
    public void initBindings(){
        for (int row = 0; row < theBoard.getBoard().size(); row++) {
            theBoard.getBoard().get(row).addListener(new ListChangeListener<Tile>() {
                @Override
                public void onChanged(Change<? extends Tile> change) {
                    updateLabelInGridPane(theBoard.getBoard());
                }
            });
        }

    }

    /**
     * A helper method used to set the text of the label to the value in the array given
     * @param array - an array of tile objects
     */
    private void updateLabelInGridPane(ObservableList<ObservableList<Tile>> array){
        for (int row = 0; row < array.size(); row++){
            for (int col = 0; col < array.get(row).size(); col++){
                Label label = (Label) tileGrid.lookup("#label" +row + col);
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

        btnNewGame.setOnAction(actionEvent -> {
            theBoard = new Board(4);
            System.out.println("pressed");
        });
    }

    /**
     * A helper method that takes in a direction, then uses the movement class to
     * move the tiles and finally update the array
     * @param direction - direction the user wants the tiles to move
     */
    private void changeBoard(String direction) {
        try {
            movement.moveTile(direction);
            theBoard.printBoard();
        } catch (TileOccupiedException e) {
            throw new RuntimeException(e);
        } catch (OutOfBoardException e) {
            throw new RuntimeException(e);
        }
    }
}
