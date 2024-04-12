/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 4/11/24
 *Time: 3:48 PM
 *
 *Project: csci205_final_project
 *Package: org.MMMJ
 *Class: Board
 *Description:
 * **************************************** */
package org.MMMJ;

public class Board {

    Tile[][] board;

    int size;

    public Board(int boardSize){
        this.size = boardSize;
        this.board = new Tile[size][size];
        initBoard();
    }


    public void initBoard(){
        for (int i = 0; i < size ; i++) {
            for (int j = 0; j < size ; j++) {
                board[i][j] = new Tile();
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

    public void addTile(int row, int col, Tile tile){
        this.board[row][col] = tile;
    }


    public static void main(String[] args) {
        Board test = new Board(5);
        Tile tile = new Tile(4);
        test.addTile(3,3,tile);
        test.printBoard();
    }

}