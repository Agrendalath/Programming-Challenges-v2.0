package agrendalath.tic_tac_toe;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BoardImplementationTest {
    private Board board;

    @Before
    public void setUp() {
        board = new BoardImplementation();
        board.placeMark(0, 0);
        board.placeMark(0, 2);
        board.placeMark(1, 1);
        board.placeMark(1, 2);
    }

    @Test
    public void testInvalidMove() {
        assertFalse("Invalid move.", board.placeMark(1, 1));
    }

    @Test
    public void getWinnerNotOver() {
        String message = "Game is not over yet!";
        assertEquals(message, message, board.getWinner());
    }

    @Test
    public void getCurrentPlayer() {
        assertEquals("Current player should be 'O'", 'O', board.getCurrentPlayer());
        board.placeMark(0, 1);
        assertEquals("Current player should be 'X'", 'X', board.getCurrentPlayer());
    }

    @Test
    public void getWinnerDraw() {
        board = new BoardImplementation();
        board.placeMark(0, 0);
        board.placeMark(0, 1);
        board.placeMark(0, 2);
        board.placeMark(1, 1);
        board.placeMark(1, 0);
        board.placeMark(1, 2);
        board.placeMark(2, 1);
        board.placeMark(2, 0);
        board.placeMark(2, 2);
        assertEquals("Should be draw.", "DRAW", board.getWinner());
    }

    @Test
    public void getWinnerO() {
        board.placeMark(2, 2);
        assertEquals("O should be the winner.", "O", board.getWinner());
    }

    @Test
    public void getWinnerX() {
        board.placeMark(0, 1);
        board.placeMark(2, 2);
        assertEquals("X should be the winner.", "X", board.getWinner());
    }
}
