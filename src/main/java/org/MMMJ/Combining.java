/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 4/11/24
 *Time: 11:23 PM
 *
 *Project: csci205_final_project
 *Package: org.MMMJ
 *Class: Combining
 *Description:
 * **************************************** */
package org.MMMJ;

public class Combining {

    /**
     * The board
     */
    private Board theBoard;


    /**
     * The first block in which
     */
    private Tile block1;
    /**
     *  The second block and the block that the final tile will be placed
     */
    private Tile block2;

    public Combining(Tile block1, Tile block2){
        this.block1 = block1;
        this.block2 = block2;
    }

    public Combining(Board theBoard){
        this.theBoard = theBoard;
    }

    public void combine() throws TileOccupiedException, OutOfBoardException {
        if(block1.equals(block2)){
            int newNumber = block2.getCurrNum() * 2;
            this.block1.setCurrNum(0);
            this.block2.setCurrNum(newNumber);
            this.theBoard.replaceTile(block1.getXPos(),block2.getYPos(),this.block1);
            this.theBoard.replaceTile(block2.getXPos(),block2.getYPos(),this.block2);
        }

    }

    public void setBlock1(Tile block1){
        this.block1 = block1;

    }

    public void setBlock2(Tile block2){
        this.block2 = block2;
    }

    public static void main(String[] args) throws TileOccupiedException, OutOfBoardException {
        Board board = new Board(5);

        Movement movement = new Movement(board);
        Combining combining = new Combining(board);
    }

    public Tile getBlock2() {
        return block2;
    }

    public Tile getBlock1() {
        return block1;
    }
}