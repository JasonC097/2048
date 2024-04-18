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

import java.util.Random;

class OutOfBoardException extends Exception{
    public OutOfBoardException(String msg){
        super(msg);
    }
}

class TileOccupiedException extends  Exception{
    public TileOccupiedException(String msg){
        super(msg);
    }
}

public class Board {

    private Tile[][] board;

    private int size;

    public Board(int boardSize){
        this.size = boardSize;
        this.board = new Tile[size][size];
        initBoard();
    }

    public Tile[][] getBoard(){
        return this.board;
    }


    public void initBoard(){
        for (int i = 0; i < size ; i++) {
            for (int j = 0; j < size ; j++) {
                Tile tile1 = new Tile();
                board[i][j] = tile1;
                tile1.setXPos(i);
                tile1.setYPos(j);
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < size ; i++) {
            for (int j = 0; j < size ; j++) {
                System.out.print("|" + board[i][j]);
            }
            System.out.println();
        }
    }






    public int getSize(){return this.size;}

    public Tile getValueAt(int row, int col){return this.board[row][col];}

    public void addTile(int row, int col, Tile tile) throws OutOfBoardException, TileOccupiedException {
        testTile(row, col);
        this.board[row][col] = tile;

    }


    public void testTile(int row, int col) throws OutOfBoardException, TileOccupiedException {
        if (row >= size || col >= size){
            throw new OutOfBoardException("ROW OR COL OUT OF BOARD");
        }else if(this.board[row][col].getCurrNum() != 0 ){
            throw new TileOccupiedException("OCCUPIED TILE");
        }
    }


    public static void main(String[] args) throws TileOccupiedException, OutOfBoardException {
        Board test = new Board(5);
        Tile tile = new Tile(4);
        test.addTile(3,3,tile);
        System.out.println(test.getValueAt(3,3));
        test.printBoard();

    }

    public boolean checkCollision(Tile tile ,String key){
        switch (key){
            case "w":
                if(board[tile.getXPos()][tile.getYPos()-1].getCurrNum() != 0){
                    return true;
                }
            case "s":
                if(board[tile.getXPos()][tile.getYPos()+1].getCurrNum() != 0){
                    return true;
                }
            case "a":
                if(board[tile.getXPos()-1][tile.getYPos()].getCurrNum() != 0){
                    return true;
                }
            case "d":
                if(board[tile.getXPos()+1][tile.getYPos()].getCurrNum() != 0){
                    return true;
                }
            default:
                return false;
        }
    }

    public void moveTile(String move, Tile[][] array){
        switch(move){
            case "s":
                for (int row = 0; row < array.length; row++) {
                    for (int col = 0; col < array[row].length; col++) {
                            array[row+1][col] = array[row][col];
                    }
                }
            default:
                break;
        }
    }


}