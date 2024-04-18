package org.MMMJ;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MovementTest {

    private Movement myMovement;

    @BeforeEach
    void setUp() throws TileOccupiedException, OutOfBoardException {
        Board myBoard = new Board(6);
        myBoard.addTile(3,1, new Tile(4));
        myBoard.addTile(3,2, new Tile(8));
        myBoard.addTile(2,1, new Tile(2));
        myMovement = new Movement(myBoard);
    }


    @Test
    void moveTileUp() throws TileOccupiedException, OutOfBoardException {
        Scanner scanner = getScanner("w");
        myMovement.moveTile(scanner);
        scanner.nextLine();
        assertEquals(myMovement.getTheBoard().getTileAt(0,1).getCurrNum(),2);
        assertEquals(myMovement.getTheBoard().getTileAt(1,1).getCurrNum(), 4);
        assertEquals(myMovement.getTheBoard().getTileAt(0,2).getCurrNum(), 8);

    }
    @Test
    void moveTileDown() throws TileOccupiedException, OutOfBoardException {
        Scanner scanner = getScanner("s");
        myMovement.moveTile(scanner);
        scanner.nextLine();
        assertEquals(myMovement.getTheBoard().getTileAt(4,1).getCurrNum(),2);
        assertEquals(myMovement.getTheBoard().getTileAt(5,1).getCurrNum(), 4);
        assertEquals(myMovement.getTheBoard().getTileAt(5,2).getCurrNum(), 8);

    }
    @Test
    void moveTileRight() throws TileOccupiedException, OutOfBoardException {
        Scanner scanner = getScanner("d");
        myMovement.moveTile(scanner);
        scanner.nextLine();
        assertEquals(myMovement.getTheBoard().getTileAt(2,5).getCurrNum(),2);
        assertEquals(myMovement.getTheBoard().getTileAt(3,4).getCurrNum(), 4);
        assertEquals(myMovement.getTheBoard().getTileAt(3,5).getCurrNum(), 8);
    }
    @Test
    void moveTileLeft() throws TileOccupiedException, OutOfBoardException {
        Scanner scanner = getScanner("a");
        myMovement.moveTile(scanner);
        scanner.nextLine();
        assertEquals(myMovement.getTheBoard().getTileAt(2,0).getCurrNum(),2);
        assertEquals(myMovement.getTheBoard().getTileAt(3,0).getCurrNum(), 4);
        assertEquals(myMovement.getTheBoard().getTileAt(3,1).getCurrNum(), 8);
    }

    private static Scanner getScanner(String direction) {
        String userInput = direction + "\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        Scanner scanner = new Scanner(System.in);
        return scanner;
    }
}