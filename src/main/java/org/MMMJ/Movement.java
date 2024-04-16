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
    /**
     * Scanner object
     */
    private Scanner scnr;
    
    private Board theBoard;

    public Movement(Board board){this.theBoard= board;}


    /**
     * Constructor for the movement class
     */
    public Movement() {
        this.theBoard = new Board(5);
    }


    public boolean checkCollision(Tile tile ,String key){
        switch (key){
            case "w":
                if (tile.getCurrNum() != 0) {
                    if (tile.getXPos() - 1 >= 0) {
                        if(theBoard.getValueAt(tile.getXPos() - 1,tile.getYPos()).getCurrNum() == 0){
                            return true;
                        }

                    }
                }
                break;
            case "s":
                if (tile.getCurrNum() != 0) {
                    if (tile.getXPos() + 1 < theBoard.getSize()) {
                        if (theBoard.getValueAt(tile.getXPos() + 1, tile.getYPos()).getCurrNum() == 0) {
                            return true;
                        }
                    }
                }
                break;
            case "a":
                if (tile.getCurrNum() != 0) {
                    if (tile.getYPos() - 1 >= 0) {
                        if (theBoard.getValueAt(tile.getXPos() , tile.getYPos() - 1).getCurrNum() == 0) {
                            return true;
                        }
                    }
                }
                break;
            case "d":
                if (tile.getCurrNum() != 0) {
                    if (tile.getYPos() + 1 < theBoard.getSize()) {
                        if (theBoard.getValueAt(tile.getXPos() , tile.getYPos() + 1).getCurrNum() == 0) {
                            return true;
                        }
                    }
                }
                break;
            default:
                return false;
        }
        return false;
    }

    public void moveTile(Scanner scnr) throws TileOccupiedException, OutOfBoardException {
//        scnr = new Scanner(System.in);
        System.out.println("Enter direction you want to head in:");
        switch(scnr.next()){
            case "w":
                for(Tile[] row : theBoard.getBoard()){
                    for(Tile tile : row) {
                        if (tile.getCurrNum() != 0){
                            while (checkCollision(tile, "w")) {
                                int oldYPos = tile.getYPos();
                                int oldXPos = tile.getXPos();
                                theBoard.addTile(oldXPos - 1, oldYPos, tile);
                                theBoard.replaceTile(oldXPos, oldYPos, new Tile());
                            }
                        }
                    }
                }
                break;
            case "s":
                for(int i = theBoard.getSize() -1;  i >= 0; i--) {
                    for (int j = theBoard.getSize() -1; j >= 0; j--) {
                        Tile tile = theBoard.getValueAt(i,j);
                        if(tile.getCurrNum() != 0) {
                            while (checkCollision(tile, "s")) {
                                int oldYPos = tile.getYPos();
                                int oldXPos = tile.getXPos();
                                theBoard.addTile(oldXPos + 1, oldYPos, tile);
                                theBoard.replaceTile(oldXPos, oldYPos, new Tile());
                            }
                        }
                    }
                }
                break;
            case "a":
                for(Tile[] row : theBoard.getBoard()) {
                    for (Tile tile : row) {
                        if(tile.getCurrNum() != 0) {
                            while (checkCollision(tile, "a")) {
                                int oldYPos = tile.getYPos();
                                int oldXPos = tile.getXPos();
                                theBoard.addTile(oldXPos , oldYPos - 1, tile);
                                theBoard.replaceTile(oldXPos, oldYPos, new Tile());
                            }
                        }
                    }
                }
                break;
            case "d":
                for(int i = theBoard.getSize() -1;  i >= 0; i--) {
                    for (int j = theBoard.getSize() -1; j >= 0; j--) {
                        Tile tile = theBoard.getValueAt(i,j);
                        if(tile.getCurrNum() != 0) {
                            while (checkCollision(tile, "d")) {
                                int oldYPos = tile.getYPos();
                                int oldXPos = tile.getXPos();
                                theBoard.addTile(oldXPos , oldYPos + 1, tile);
                                theBoard.replaceTile(oldXPos, oldYPos, new Tile());
                            }
                        }
                    }
                }
                break;
        }
    }

    public Board getTheBoard(){return theBoard;}

    public static void main(String[] args) throws TileOccupiedException, OutOfBoardException {
        Scanner scnr = new Scanner(System.in);
        Board testBoard = new Board(4);
        testBoard.addTile(2,1, new Tile(4));
        testBoard.addTile(2,2,new Tile(8));
        testBoard.addTile(1,1, new Tile(9));
//        testBoard.printBoard();
        Movement movement = new Movement(testBoard);
        movement.theBoard.printBoard();
        for (int i = 0; i <10 ; i++) {
            movement.moveTile(scnr);
            movement.theBoard.printBoard();
        }




    }

}