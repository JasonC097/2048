/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 4/11/24
 *Time: 3:48PM
 *
 *Project: csci205_final_project
 *Package: org.MMMJ
 *Class: Board
 *Description:
 * **************************************** */
package org.MMMJ;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Board {
    /**
     * 2d representation of the board
     */
    private ObservableList<ObservableList<Tile>> board;

    /**
     *  the size the sides of the square board
     */
    private int size;

    /**
     * Constructor for the Board class
     * @param boardSize - size of length of the rows and columns of the board
     */
    public Board(int boardSize){
        this.size = boardSize;
        this.board = FXCollections.<ObservableList<Tile>>observableArrayList();
        initBoard();
    }


    /**
     * Setter method to help with cloning the board
     * @param board the Tile[][] object to be wrapped as a board object
     */
    public void setBoard(ObservableList<ObservableList<Tile>> board) { this.board = board;}

    /**
     * @return gets the 2D representation of the board
     */
    public ObservableList<ObservableList<Tile>> getBoard(){
        return this.board;
    }

    /**
     * Initialized the board with tiles of a current number 0 as well as setting each tiles metadata
     */
    public void initBoard(){
        for (int i = 0; i < size ; i++) {
            final ObservableList<Tile> row = FXCollections.<Tile>observableArrayList();
            this.board.add(i,row);
            for (int j = 0; j < size ; j++) {
                Tile tile1 = new Tile();
                board.get(i).add(j, tile1);
                tile1.setXPos(i);
                tile1.setYPos(j);
            }
        }
    }

    /**
     * Prints out a string representation of the board in the terminal
     */
    public void printBoard() {
        for (int i = 0; i < size ; i++) {
            for (int j = 0; j < size ; j++) {
                System.out.print("|" + board.get(i).get(j));
            }
            System.out.println("|");
        }
    }

    /**
     * Returns the size of the board
     * @return size
     */
    public int getSize(){return this.size;}

    /**
     * returns the tile at a specific index of the board
     * @param row - row of the board
     * @param col - column of the board
     * @return index of the row/col
     */
    public Tile getTileAt(int row, int col){return this.board.get(row).get(col);}

    /**
     * Adds a tile to the board in an unoccupied position
     *
     * @param row the row of the new tile
     * @param col the col of the new tile
     * @param tile the new tile
     * @throws OutOfBoardException if (row,col) pair is outside the board
     * @throws TileOccupiedException if (row,col) pair is replacing a part that already has a valid tile
     */
    public void addTile(int row, int col, Tile tile) throws OutOfBoardException, TileOccupiedException {
        testTile(row, col);

        this.board.get(row).set(col, tile);
        tile.setXPos(row);
        tile.setYPos(col);
    }


    /**
     *  replaces a tile of the of an occupied position
     *
     * @param row the row of the new tile
     * @param col the col of the new tile
     * @param tile the new tile
     * @throws OutOfBoardException if (row,col) pair is outside the board
     */
    public void replaceTile(int row, int col, Tile tile) throws OutOfBoardException {
        if (row >= size || row < 0 || col < 0 || col >= size){
            throw new OutOfBoardException("ROW OR COL OUT OF BOARD " + row + ", " + col);
        }
        this.board.get(row).set(col, tile);
        tile.setXPos(row);
        tile.setYPos(col);
    }

    /**
     * test whether (row,col) pair is inside the board and if it's occupied tile
     * @param row the row of the tile
     * @param col the col of the tile
     * @throws OutOfBoardException
     * @throws TileOccupiedException
     */
    public void testTile(int row, int col) throws OutOfBoardException, TileOccupiedException {
        if (row >= size || row < 0 || col < 0 || col >= size){
            throw new OutOfBoardException("ROW OR COL OUT OF BOARD " + row + ", " + col);
        }else if(this.board.get(row).get(col).getCurrNum() != 0 ){
            throw new TileOccupiedException("OCCUPIED TILE " + row +","+ col);
        }
    }

    /**
     * Checks to see if the board is full, by iterating through and
     * checking to see if any indexes contain a 0
     * @return - T if the board is full, F otherwise
     */
    public boolean isBoardFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.get(i).get(j).getCurrNum() == 0) {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) throws TileOccupiedException, OutOfBoardException {
        Board test = new Board(5);
        Tile tile = new Tile(16);
        test.addTile(3,3,tile);
        System.out.println(test.getTileAt(3,3).getXPos());
        System.out.println(test.getTileAt(3,3).getYPos());


//        System.out.println(test.getValueAt(3,3));
        test.printBoard();


    }
}