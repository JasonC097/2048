package org.MMMJ;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    int boardSize;
    Board theBoard;
    @BeforeEach
    void setUp() throws TileOccupiedException, OutOfBoardException {
        boardSize = 5;
        theBoard = new Board(boardSize);

    }

    /**
     * Verifies each tile is initialized  with the right metadata
     */
    @Test
    void initBoard() {
        Random random = new Random();

        // gets random tile to guess since all tiles in this case should be initialized the same
        int randomX = random.nextInt(boardSize);
        int randomY = random.nextInt(boardSize);
        // test whether the tile class data is initialized correctly
        Tile randomTile = theBoard.getTileAt(randomX, randomY);
        assertEquals(randomTile.getXPos(), randomX);
        assertEquals(randomTile.getYPos(),randomY);
        assertEquals(randomTile.getCurrNum(), 0);
    }

    /**
     * Tests adding a tile to the board.
     *
     * @throws TileOccupiedException If the tile position is already occupied.
     * @throws OutOfBoardException   If the tile position is out of the board boundaries.
     */
    @Test
    void addTile() throws TileOccupiedException, OutOfBoardException {
        // Its actually in the board
        theBoard.addTile(3,4,new Tile(8));
        Tile tileAt = theBoard.getTileAt(3,4);

        // information is updated correctly in the Tile class
        assertEquals(tileAt.getCurrNum(), 8);
        assertEquals(tileAt.getXPos(), 3);
        assertEquals(tileAt.getYPos(), 4);
    }

    /**
     * Tests adding a tile that results in an OutOfBoardException.
     */
    @Test
    void addTileOutOfBoardException(){
        assertThrows(OutOfBoardException.class,() -> theBoard.addTile(boardSize,boardSize, new Tile(4)));

    }

    /**
     * Tests adding a tile to an occupied position and replacing a tile.
     *
     * @throws TileOccupiedException If the tile position is already occupied.
     * @throws OutOfBoardException   If the tile position is out of the board boundaries.
     */
    @Test
    void addTileOccupiedException() throws TileOccupiedException, OutOfBoardException {
        theBoard.addTile(4,4,new Tile(8));
        // should not be able to add tile to an existing spot
        assertThrows(TileOccupiedException.class,() -> theBoard.addTile(4,4, new Tile(4)));
        // should be able to replace the tile
        assertDoesNotThrow(() -> theBoard.replaceTile(4,4, new Tile(2)));

    }




}