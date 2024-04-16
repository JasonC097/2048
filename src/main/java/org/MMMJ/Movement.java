/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 4/11/24
 *Time: 3:25 PM
 *
 *Project: csci205_final_project
 *Package: org.MMMJ
 *Class: Movement
 *Description:
 * **************************************** */
package org.MMMJ;

import java.util.ArrayList;
import java.util.Scanner;

public class Movement {

    
    private Board theBoard;


    /**
     * Constructor for the movement class
     */
    public Movement() {
        this.theBoard = new Board(5);
    }

    public boolean checkCollision(Tile tile ,String key){
        switch (key){
            case "w":
                if(theBoard.getValueAt(tile.getXPos(),tile.getYPos()-1).getCurrNum() != 0){
                    return true;
                }
            case "s":
                if(theBoard.getValueAt(tile.getXPos(),tile.getYPos()+1).getCurrNum() != 0){
                    return true;
                }
            case "a":
                if(theBoard.getValueAt(tile.getXPos()-1,tile.getYPos()).getCurrNum() != 0){
                    return true;
                }
            case "d":
                if(theBoard.getValueAt(tile.getXPos()+1,tile.getYPos()).getCurrNum() != 0){
                    return true;
                }
            default:
                return false;
        }
    }

    public void moveTile(String move, Board board){
        switch(move){
            case "w":
                for(Tile[] row : board.getBoard()){
                    for(Tile tile : row) {
                        while (!checkCollision(tile, "w")) {
                            tile.setYPos(tile.getYPos() - 1);
                        }
                    }
                }
                break;
            case "s":
                for(Tile[] row : board.getBoard()) {
                    for (Tile tile : row) {
                        while (!checkCollision(tile, "s")) {
                            tile.setYPos(tile.getYPos() + 1);
                        }
                    }
                }
                break;
            case "a":
                for(Tile[] row : board.getBoard()) {
                    for (Tile tile : row) {
                        while (!checkCollision(tile, "a")) {
                            tile.setYPos(tile.getYPos() - 1);
                        }
                    }
                }
                break;
            case "d":
                for(Tile[] row : board.getBoard()) {
                    for (Tile tile : row) {
                        while (!checkCollision(tile, "d")) {
                            tile.setYPos(tile.getYPos() + 1);
                        }
                    }
                }
        }
    }

}