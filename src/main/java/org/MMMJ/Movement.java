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

import java.util.Scanner;

public class Movement {
    private Board theBoard;

    public Movement(Board board){
        this.theBoard= board;
    }

    /**
     * Constructor for the movement class
     */
    public Movement() {
        this.theBoard = new Board(5);
    }

    /**
     * Determines if a tile can move to an empty tile or not and if combining event can happen
     * @param tile - The tile being moved
     * @param key - The direction the player wants to do a move
     * @return true if the tile can move to an empty tile. Otherwise, false
     * @throws OutOfBoardException - when trying to place a tile out of bounds of the board
     */
    public boolean checkCollision(Tile tile ,String key) throws OutOfBoardException {
        switch (key){
            case "w":
                if (tile.getXPos() - 1 >= 0  &&  theBoard.getTileAt(tile.getXPos() - 1,tile.getYPos()).getCurrNum() == 0) {
                    return true;
                }else if(tile.getXPos() - 1 >= 0){
                    Tile tile2 = theBoard.getTileAt(tile.getXPos() - 1, tile.getYPos());
                    this.combine(tile, tile2);
                }
                break;
            case "s":
                if (tile.getXPos() + 1 < theBoard.getSize() && theBoard.getTileAt(tile.getXPos() + 1, tile.getYPos()).getCurrNum() == 0) {
                    return true;
                }else if (tile.getXPos() + 1 < theBoard.getSize()){
                    Tile tile2 = theBoard.getTileAt(tile.getXPos() + 1, tile.getYPos());
                    this.combine(tile, tile2);
                }
                break;
            case "a":
                if (tile.getYPos() - 1 >= 0 && theBoard.getTileAt(tile.getXPos() , tile.getYPos() - 1).getCurrNum() == 0 ) {
                    return true;
                }else if (tile.getYPos() - 1 >= 0 ){
                    Tile tile2 = theBoard.getTileAt(tile.getXPos(), tile.getYPos() - 1);
                    this.combine(tile, tile2);
                }
                break;
            case "d":
                if (tile.getYPos() + 1 < theBoard.getSize() && theBoard.getTileAt(tile.getXPos() , tile.getYPos() + 1).getCurrNum() == 0 ) {
                    return true;
                } else if (tile.getYPos()  + 1 < theBoard.getSize()){
                    Tile tile2 = theBoard.getTileAt(tile.getXPos(), tile.getYPos() + 1);
                    this.combine(tile, tile2);
                }
                break;
            default:
                return false;
        }
        return false;
    }

    /**
     * Uses a switch statement to see which direction the user wants to
     * move the tiles on the board
     * @param userInput - direction the user wants to move
     * @throws TileOccupiedException
     * @throws OutOfBoardException
     */
    public void moveTile(String userInput) throws TileOccupiedException, OutOfBoardException {
        switch(userInput){
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
                        Tile tile = theBoard.getTileAt(i,j);
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
                        Tile tile = theBoard.getTileAt(i,j);
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

    /**
     * Combining method to determine if combining can happen, and if so, updating the board when necessary
     * @param tile1 - The tile being combined into tile2
     * @param tile2 - The tile that is being updated
     * @throws OutOfBoardException - thrown when trying to replace a tile outside the board
     */
    public void combine(Tile tile1, Tile tile2) throws OutOfBoardException {
        if(tile1.equals(tile2)){
            int newNumber = tile2.getCurrNum() * 2;
            tile1.setCurrNum(0);
            tile2.setCurrNum(newNumber);
            this.theBoard.replaceTile(tile1.getXPos(),tile2.getYPos(),tile1);
            this.theBoard.replaceTile(tile2.getXPos(),tile2.getYPos(),tile2);
        }

    }
    /** Getter method that returns the board*/
    public Board getTheBoard(){return theBoard;}

    public static void main(String[] args) throws TileOccupiedException, OutOfBoardException, BoardIsFullException {
        Scanner scnr = new Scanner(System.in); // Scanner used for testing with key inputs
        Board testBoard = new Board(4);
        testBoard.addTile(2,2,new Tile(4));
        testBoard.addTile(2,1,new Tile(2));
        testBoard.addTile(1,2,new Tile(4));
        testBoard.addTile(1,1,new Tile(2));
        Movement movement = new Movement(testBoard);
        GenerateTiles generateTiles = new GenerateTiles(movement.theBoard);
        movement.theBoard.printBoard();
        for (int i = 0; i < 100 ; i++) {
            System.out.println("Enter direction you want to head in:");
            String userInput = scnr.nextLine(); // Create an invalid user input handler
            movement.moveTile(userInput);
            generateTiles.generateNewTile();
            int[] emptyPos = generateTiles.findEmptyPosition();
            movement.theBoard.addTile(emptyPos[0],emptyPos[1], generateTiles.generateNewTile());
            movement.theBoard.printBoard();
        }




    }

}