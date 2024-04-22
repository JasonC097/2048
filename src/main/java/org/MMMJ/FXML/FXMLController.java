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
import javafx.scene.layout.GridPane;
import org.MMMJ.Board;
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

    /**An instance of the {@link ObservableArray2D} class that is used to create the values
     * in the board **/
    private ObservableArray2D<Tile> array2D = new ObservableArray2D<Tile>(theBoard.getBoard());


    @FXML
    void initialize(){
        System.out.println(Arrays.deepToString(array2D.get()));
        assert btnNewGame != null : "fx:id=\"btnNewGame\" was not injected: check your FXML file 'FinalFXML.fxml'.";
        assert labelScore != null : "fx:id=\"labelScore\" was not injected: check your FXML file 'FinalFXML.fxml'.";
        assert tileGrid != null : "fx:id=\"tileGrid\" was not injected: check your FXML file 'FinalFXML.fxml'.";
        initBindings();
        initEventHandlers();
    }

    /**
     * Uses a listener to bind the values in a 2D array to the labels within the
     * grid pane in Scene Builder
     */
    public void initBindings() {
        array2D.arrayProperty().addListener((observableValue, tiles, t1) -> {
            updateLabelInGridPane(t1);
        });
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
        btnNewGame.setOnAction(actionEvent -> {
            labelScore.textProperty().set("0");
        });

        tileGrid.setOnKeyPressed(keyEvent -> {
            array2D.set(theBoard.getBoard());
        });
    }
}
