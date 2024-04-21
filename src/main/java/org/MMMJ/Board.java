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
    /**
     * 2d representation of the board
     */
    private Tile[][] board;
    /**
     *  the size the sides of the square board
     */
    private int size;

    public Board(int boardSize){
        this.size = boardSize;
        this.board = new Tile[size][size];
        initBoard();
    }

    /**
     * Helper constructor for creating a copy of whether the player lost
     * @param boardCopy - the current board the player has
     */
    public Board(Board boardCopy){
        this.board = boardCopy.getBoard();
        this.size = boardCopy.getSize();
    }

    /**
     * Setter method to help with cloning the board
     * @param board the Tile[][] object to be wrapped as a board object
     */
    public void setBoard(Tile[][] board) { this.board = board;}

    /**
     * @return gets the 2D representation of the board
     */
    public Tile[][] getBoard(){
        return this.board;
    }

    /**
     * Initialized the board with tiles of a current number 0 as well as setting each tiles metadata
     */
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
            System.out.println("|");
        }
    }






    public int getSize(){return this.size;}

    public Tile getTileAt(int row, int col){return this.board[row][col];}

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
        this.board[row][col] = tile;
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
        this.board[row][col] = tile;
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
        }else if(this.board[row][col].getCurrNum() != 0 ){
            throw new TileOccupiedException("OCCUPIED TILE " + row +","+ col);
        }
    }

    public boolean isBoardFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].getCurrNum() == 0) {
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