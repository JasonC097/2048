/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Mike Merola
 * Section: 01
 * Date: 4/16/24
 * Time: 2:32 PM
 *
 * Project: csci205_final_project
 * Package: org.MMMJ.FXML
 * Class: FXMLModel
 *
 * Description:
 *
 * ****************************************
 */
package org.MMMJ.FXML;

import org.MMMJ.*;

import java.util.Random;

public class FXMLModel {
    private Board theBoard;


    public void addNewTile() throws TileOccupiedException, OutOfBoardException {
        GenerateSquares newTile = new GenerateSquares(theBoard);
        int r1 = new Random().nextInt(0,4);
        int r2 = new Random().nextInt(0,4);
        theBoard.addTile(r1,r2, newTile.generateNewTile());
    }


}