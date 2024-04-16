/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 4/11/24
 *Time: 10:09 PM
 *
 *Project: csci205_final_project
 *Package: org.MMMJ
 *Class: GenerateSquares
 *Description:
 * **************************************** */
package org.MMMJ;

import java.util.Random;

public class GenerateSquares {
    private Random random;

    private Board board;
    public GenerateSquares(Board board){
        this.random = new Random();
        this.board = board;
    }

    /**
     * Generates a new square between 2 and 4 has a ration that chooses between the two
     *
     * @return the tile object with the new number
     */
    public Tile generateNewTile(){
        int ratio = random.nextInt(2);
        if(ratio == 1){
            return new Tile(2);
        }else{
            return new Tile(4);
        }
    }

    /**
     * finds a random new position on the board that is empty
     *
     * @return a list [row, col] of the row,column pair
     */
    public int[] findEmptyPosition(){
        int testRow;
        int testCol;
        int[] emptyPos = new int[2];
        while(true) {
            testRow = random.nextInt(board.getSize());
            testCol = random.nextInt(board.getSize());
            try {
                board.testTile(testRow, testCol);
                break;
            } catch (TileOccupiedException | OutOfBoardException e) {
            }
        }


        emptyPos[0] = testRow;
        emptyPos[1] = testCol;

        return emptyPos;
    }


    public static void main(String[] args) throws TileOccupiedException, OutOfBoardException {
        Board board = new Board(5);
        GenerateSquares generateSquares = new GenerateSquares(board);
        board.addTile(0,0,new Tile(4));
        board.addTile(0,1,new Tile(4));
        board.addTile(1,0,generateSquares.generateNewTile());
        board.addTile(0,4,generateSquares.generateNewTile());
        Combining combining = new Combining(board.getTileAt(0,0), board.getTileAt(0,1));


        board.printBoard();


        int[] emptyPos = generateSquares.findEmptyPosition();

        for (int pos: emptyPos){
            System.out.println(pos);
        }


    }
}