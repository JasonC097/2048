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
        myMovement.moveTile("w");
        assertEquals(myMovement.getTheBoard().getTileAt(0,1).getCurrNum(),2);
        assertEquals(myMovement.getTheBoard().getTileAt(1,1).getCurrNum(), 4);
        assertEquals(myMovement.getTheBoard().getTileAt(0,2).getCurrNum(), 8);

    }
    @Test
    void moveTileDown() throws TileOccupiedException, OutOfBoardException {
        myMovement.moveTile("s");
        assertEquals(myMovement.getTheBoard().getTileAt(4,1).getCurrNum(),2);
        assertEquals(myMovement.getTheBoard().getTileAt(5,1).getCurrNum(), 4);
        assertEquals(myMovement.getTheBoard().getTileAt(5,2).getCurrNum(), 8);

    }
    @Test
    void moveTileRight() throws TileOccupiedException, OutOfBoardException {
        myMovement.moveTile("d");
        assertEquals(myMovement.getTheBoard().getTileAt(2,5).getCurrNum(),2);
        assertEquals(myMovement.getTheBoard().getTileAt(3,4).getCurrNum(), 4);
        assertEquals(myMovement.getTheBoard().getTileAt(3,5).getCurrNum(), 8);
    }
    @Test
    void moveTileLeft() throws TileOccupiedException, OutOfBoardException {
        myMovement.moveTile("a");
        assertEquals(myMovement.getTheBoard().getTileAt(2,0).getCurrNum(),2);
        assertEquals(myMovement.getTheBoard().getTileAt(3,0).getCurrNum(), 4);
        assertEquals(myMovement.getTheBoard().getTileAt(3,1).getCurrNum(), 8);
    }

    @Test
    void combine() throws TileOccupiedException, OutOfBoardException {
        // Add some tiles for combining upwards
        // Replace the tile 2 with 4 to see if combining happens when moving upwards
        this.myMovement.getTheBoard().replaceTile(2, 1, new Tile(4));
        this.myMovement.moveTile("w");
        // Should be updated to the tile value of 8
        assertEquals(this.myMovement.getTheBoard().getTileAt(0,1).getCurrNum(), 8);

        // Should remain the same as 8
        assertEquals(this.myMovement.getTheBoard().getTileAt(0,2).getCurrNum(), 8);

        // Should see 16 in the top left corner
        this.myMovement.moveTile("a");
        assertEquals(this.myMovement.getTheBoard().getTileAt(0,0).getCurrNum(), 16);

        //Test to see if multiple combining can happen at once when possible
        this.myMovement.getTheBoard().addTile(1, 0, new Tile(16));
        this.myMovement.getTheBoard().addTile(0, 3, new Tile(32));
        this.myMovement.getTheBoard().addTile(1, 3, new Tile(32));
        this.myMovement.moveTile("s");

        assertEquals(this.myMovement.getTheBoard().getTileAt(5,0).getCurrNum(), 32);
        assertEquals(this.myMovement.getTheBoard().getTileAt(5,3).getCurrNum(), 64);

        // Ensure that tiles combine only once
        this.myMovement.getTheBoard().addTile(5, 1,  new Tile(32));
        this.myMovement.moveTile("d");
        // Should have 64 next to each other instead of combined to 128
        assertEquals(this.myMovement.getTheBoard().getTileAt(5,5).getCurrNum(), 64);
        assertEquals(this.myMovement.getTheBoard().getTileAt(5,4).getCurrNum(), 64);

    }
}