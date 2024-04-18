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
 * Description:
 *
 * ****************************************
 */
package org.MMMJ;

public class GameManager {
    /** The board of the game*/
    private Board board;

    private final int BOARD_SIZE = 4; // Can change later for making custom board sizes

    private final int GAME_END_NUMBER = 32; // Change to 2048 for actual game, smaller number for testing only

    private Movement movement; // Sounds clunky, change later to maybe something abstract or interface

    public GameManager() {
        this.board = new Board(BOARD_SIZE);
        this.movement = new Movement(this.board);

        // Probably make customization to let person play with smaller number if 2048 is too hard to achieve
        // Or make larger number if 2048 too easy
        // Must check if valid (a valid value of 2^n)
    }

    /**
     * Player win is defined as player reaches the desired number
     * @return boolean true if player has reached the desired number. Otherwise, return false
     */
    private boolean didPlayerWin() {
        // Block 2 is the new number to replace the combined numbers
        // Grab the value of the new number to see if it is the desired number to end the game
        int checkedNewNum = this.movement.getCombining().getBlock2().getCurrNum();
        if (checkedNewNum == GAME_END_NUMBER){
            System.out.println("You win!");
            return true;
        }
        return false;
    }

    /**
     * Player lost is defined as no more possible movements can be made
     * @return boolean true if player can not make more moves. Otherwise, return false if possible moves can still be made
     */
    private boolean didPlayerLost(){

        return false;
    }
}