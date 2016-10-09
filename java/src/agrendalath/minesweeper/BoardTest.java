package agrendalath.minesweeper;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class BoardTest {
    @Test
    public void testGeneratingBoard() throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
        final int HEIGHT = 40;
        final int WIDTH = 30;
        final int BOMBS = 4;

        int bombCounter = 0;
        Board board = new Board(WIDTH, HEIGHT, BOMBS);
        java.lang.reflect.Field boardFields = Board.class.getDeclaredField("fields");
        boardFields.setAccessible(true);
        Board.Field[][] fields = (Board.Field[][]) boardFields.get(board);

        for (int i = 0; i < WIDTH; ++i) {
            for (int j = 0; j < HEIGHT; ++j) {
                if (fields[i][j].state.contains(Board.State.BOMB))
                    ++bombCounter;
                assertFalse("All fields should be not flagged", fields[i][j].state.contains(Board.State.FLAG));
                assertFalse("All fields should be not revealed", fields[i][j].state.contains(Board.State.REVEALED));
            }
        }

        assertEquals("Wrong number of bombs", BOMBS, bombCounter);
    }

    @Test
    public void testGameOver() throws NoSuchFieldException, IllegalAccessException {
        final int HEIGHT = 40;
        final int WIDTH = 30;
        final int BOMBS = 4;

        Board board = new Board(WIDTH, HEIGHT, BOMBS);
        java.lang.reflect.Field boardFields = Board.class.getDeclaredField("fields");
        boardFields.setAccessible(true);
        Board.Field[][] fields = (Board.Field[][]) boardFields.get(board);

        Point firstBomb = null;
        for (int i = 0; i < WIDTH && firstBomb == null; ++i)
            for (int j = 0; j < HEIGHT && firstBomb == null; ++j)
                if (fields[i][j].state.contains(Board.State.BOMB))
                    firstBomb = new Point(i, j);

        board.reveal(firstBomb);
        assertTrue("Game should be over after selecting the bomb", board.isGameOver());
    }
}
