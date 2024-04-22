/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Jason Chung
 * Section: 02 / 61
 * Date: 4/18/2024
 * Time: 3:40 PM
 *
 * Project: csci205_final_project
 * Package: org.MMMJ
 * Class: GameManager
 *
 * Description: GameManager class for 2048
 *
 * ****************************************
 */
package org.MMMJ;

import java.util.Arrays;

/**
 * @author Jason Chung
 * Class to handle win and lose conditions
 */
public class GameManager {
    /** The board of the game*/
    private Board board;

    private final int BOARD_SIZE = 4; // Can change later for making custom board sizes

    private int gameEndNumber; // Change to 2048 for actual game, smaller number for testing only

    private Movement movement; // Sounds clunky, change later to maybe something abstract or interface

    /**
     * Constructor class for GameManager that generates the default board and movement
     */
    public GameManager() {
        this.board = new Board(BOARD_SIZE);
        this.gameEndNumber = 2048; //Default way for user to win
        this.movement = new Movement(this.board);

        // Probably make customization to let person play with smaller number if 2048 is too hard to achieve
        // Or make larger number if 2048 too easy
        // Must check if valid (a valid value of 2^n)
    }

    /**
     * Constructor for user that wants a different board size and different number to get to
     * @param userDesiredSize - integer of the user's desired board size
     * @param userDesiredEndNum - integer of the user's desired end number
     */
    public GameManager(int userDesiredSize, int userDesiredEndNum){
        this.board = new Board(userDesiredSize);
        this.gameEndNumber = userDesiredEndNum;
        this.movement = new Movement(this.board);
    }
    /** Getter method for accessing the board for unit testing*/
    public Board getBoard() {return board;}

    /** Getter method for accessing movement for unit testing*/
    public Movement getMovement() {return movement;}

    /**
     * Player win is defined as player reaches the desired number
     * @return boolean true if player has reached the desired number. Otherwise, return false
     */
    public boolean didPlayerWin() {
        // Block 2 is the new number to replace the combined numbers
        // Grab the value of the new number to see if it is the desired number to end the game
        for (Tile [] row : this.board.getBoard()){
            for (Tile tile : row){
                if (tile.getCurrNum() == this.gameEndNumber){
                    System.out.println("You win!");
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Player lose is defined as no more possible movements can be made
     * @return boolean true if player can not make more moves. Otherwise, return false if possible moves can still be made
     */
    public boolean didPlayerLose() throws TileOccupiedException, OutOfBoardException {
        // Create copy of board and movement to not interfere with user's current board
        // Used for predicting possible movements
        Board tempBoard = makeCopyOfBoard();
        Movement tempMovement = new Movement(tempBoard);
        // Try each possible movement; board will change when combination happens or tiles change positions
        tempMovement.moveTile("w");
        tempMovement.moveTile("s");
        tempMovement.moveTile("a");
        tempMovement.moveTile("d");
        // Check if board changed from any possible movement
        return areBoardsSame(tempBoard);
    }

    /**
     * Helper method to determine whether the boards are equivalent to each other
     * @param tempBoard
     * @return
     */
    private boolean areBoardsSame(Board tempBoard) {
        // Iterate through each row and column coordinate pair to see if tiles are different
        for (int row = 0; row < this.board.getSize(); row++){
            for (int column = 0; column < this.board.getSize(); column++){
                Tile originalTile1 = this.board.getTileAt(row, column);
                Tile tempBoardTile = tempBoard.getTileAt(row, column);
                if (!originalTile1.equals(tempBoardTile)){
                    return false;
                }
            }
        }
        // If no difference present, then boards are the same
        return true;
    }

    /**
     * Helper method to creating a separate board for seeing if possible moves can be made or not
     * @return Board copy of the player's current board
     * @throws OutOfBoardException - in case of placing a tile out of the board
     */
    private Board makeCopyOfBoard() throws OutOfBoardException {
        Board tempBoard = new Board(this.board.getSize());
        for (int row = 0; row < this.board.getSize(); row++){
            for (int column = 0; column < this.board.getSize(); column++){
                int tileValue = this.board.getTileAt(row, column).getCurrNum();
                tempBoard.replaceTile(row, column, new Tile(tileValue));
            }
        }
        return tempBoard;
    }



}