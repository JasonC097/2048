package org.MMMJ;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jason Chung
 * Unit tests for GameManager class
 */
class GameManagerTest {

    private GameManager game;
    @BeforeEach
    void setUp() throws TileOccupiedException, OutOfBoardException {
        // Desired board and goal
        this.game = new GameManager(3, 32);
        // Fill board with tiles
        this.game.getBoard().addTile(1,1, new Tile(16));
        this.game.getBoard().addTile(0, 0, new Tile(2));
        this.game.getBoard().addTile(0, 2, new Tile(3));
        this.game.getBoard().addTile(2, 0, new Tile(1));
        this.game.getBoard().addTile(2, 2, new Tile(5));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void didPlayerWin () throws TileOccupiedException, OutOfBoardException {
        // Make sure the game isn't automatically in win condition
        assertFalse(this.game.didPlayerWin());
        this.game.getBoard().addTile(0, 1, new Tile(16));
        this.game.getMovement().moveTile("w");
        // Should see that player has 32, which is the win condition
        assertTrue(this.game.didPlayerWin());
    }

    @Test
    void didPlayerLose() throws TileOccupiedException, OutOfBoardException {
        // Make sure player isn't automatically in lose condition
        assertFalse(this.game.didPlayerLose());
        this.game.getBoard().addTile(0, 1, new Tile(6));
        // Should still be possible to make moves
        this.game.getBoard().addTile(1, 0, new Tile(7));
        // Should still be possible to make moves
        assertFalse(this.game.didPlayerLose());
        this.game.getBoard().addTile(1, 2, new Tile(8));
        // Should still be possible to make moves
        assertFalse(this.game.didPlayerLose());
        this.game.getBoard().addTile(2,1,new Tile(9));
        // Should now be true since no more moves possible
        assertTrue(this.game.didPlayerLose());
    }

    @Test
    void getScore() throws TileOccupiedException, OutOfBoardException {
        //Score default from setting up JUnit tests
        assertEquals(this.game.getScore(), 27);
        // Add some more tiles and see if score is updating
        this.game.getBoard().addTile(0,1, new Tile(15));
        assertEquals(this.game.getScore(), 42);
        this.game.getBoard().addTile(1,0, new Tile(24));
        assertEquals(this.game.getScore(), 66);
        this.game.getBoard().addTile(1,2,new Tile(34));
        assertEquals(this.game.getScore(), 100);
    }

    @Test
    void processUserInputForNewTile(){

    }
}