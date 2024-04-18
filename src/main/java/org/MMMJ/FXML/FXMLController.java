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

import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import org.MMMJ.Board;
import org.MMMJ.Movement;
import org.MMMJ.Tile;


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

    private Tile[][] array = theBoard.getBoard();


    @FXML
    void initialize() {
        assert btnNewGame != null : "fx:id=\"btnNewGame\" was not injected: check your FXML file 'FinalFXML.fxml'.";
        assert labelScore != null : "fx:id=\"labelScore\" was not injected: check your FXML file 'FinalFXML.fxml'.";
        assert tileGrid != null : "fx:id=\"tileGrid\" was not injected: check your FXML file 'FinalFXML.fxml'.";

        System.out.println(array[0][0].getCurrNum());
        array[0][0].setCurrNum(4);
        initBinidings();
        System.out.println("here");
        initEventHandlers();
    }
    /**
     * set up bindings for the game
     */
    public void initBinidings(){
        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[row].length; col++) {
                Label label = (Label) tileGrid.lookup("#label" + row + col);
                ObjectProperty<String> valueProperty = new SimpleObjectProperty<>(Integer.toString(array[row][col].getCurrNum()));
                label.textProperty().bindBidirectional(valueProperty);
            }
        }
    }

    /**
     * Set up event handlers
     */
    public void initEventHandlers(){
        System.out.println("event");
        tileGrid.setOnKeyPressed(KeyEvent -> {
            System.out.println("Key pressed: " + KeyEvent.getCode());
            for (Node node : tileGrid.getChildren()) {
                ((Label) node).setText("New Value");
                }
        });
    }

}
