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
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.MMMJ.Board;
import org.MMMJ.Movement;
import org.MMMJ.Tile;

public class FXMLController {

    private Board theBoard;

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

    private Movement move = new Movement();

    @FXML
    void initialize() {
        assert btnNewGame != null : "fx:id=\"btnNewGame\" was not injected: check your FXML file 'FinalFXML.fxml'.";
        assert labelScore != null : "fx:id=\"labelScore\" was not injected: check your FXML file 'FinalFXML.fxml'.";
        assert tileGrid != null : "fx:id=\"tileGrid\" was not injected: check your FXML file 'FinalFXML.fxml'.";
        initBinidings();
    }

    /**
     * set up bindings for 2048
     */
    public void initBinidings(){

    }

    public void initEventHandlers(){
        //btnNewGame needs to reset the game
        btnNewGame.setOnAction(actionEvent -> {
            this.theBoard.initBoard();
        });

        //when WASD are pressed move the tile
        tileGrid.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case W:
                    move.moveTile("w", theBoard);
                    break;
                case S:
                    move.moveTile("s", theBoard);
                    break;
                case A:
                    move.moveTile("a", theBoard);
                    break;
                case D:
                    move.moveTile("d", theBoard);
                    break;
                default:
                    break;
            }
        });

    }

}
