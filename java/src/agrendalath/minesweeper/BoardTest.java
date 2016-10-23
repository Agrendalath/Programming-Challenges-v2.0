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
        java.lang.reflect.Field boardFields = Board.class.getDeclaredField("cells");
        boardFields.setAccessible(true);
        Cell[][] cells = (Cell[][]) boardFields.get(board);

        for (int i = 0; i < WIDTH; ++i) {
            for (int j = 0; j < HEIGHT; ++j) {
                if (cells[i][j].containsState(Board.State.BOMB))
                    ++bombCounter;
                assertFalse("All fields should be not flagged", cells[i][j].containsState(Board.State.FLAG));
                assertFalse("All fields should be not revealed", cells[i][j].containsState(Board.State.REVEALED));
            }
        }

        assertEquals("Wrong number of bombs", BOMBS, bombCounter);
    }

    @Test
    public void testRevealingFields() throws NoSuchFieldException, IllegalAccessException {
        final int HEIGHT = 40;
        final int WIDTH = 30;
        final int BOMBS = 4;

        Board board = new Board(WIDTH, HEIGHT, BOMBS);
        java.lang.reflect.Field boardFields = Board.class.getDeclaredField("cells");
        boardFields.setAccessible(true);
        Cell[][] cells = (Cell[][]) boardFields.get(board);

        Point bombPosition = null;
        Point fieldWithNoAdjacentBombs = null;
        Point fieldWithAdjacentBombs = null;
        for (int i = 0; i < WIDTH; ++i)
            for (int j = 0; j < HEIGHT; ++j) {
                if (bombPosition == null && cells[i][j].containsState(Board.State.BOMB))
                    bombPosition = new Point(i, j);
                else if (fieldWithNoAdjacentBombs == null && cells[i][j].getAdjacentBombs() == 0)
                    fieldWithNoAdjacentBombs = new Point(i, j);
                else if (fieldWithAdjacentBombs == null && cells[i][j].getAdjacentBombs() != 0)
                    fieldWithAdjacentBombs = new Point(i, j);
            }

        assertNotEquals("No field without adjacent bombs.", -1, fieldWithNoAdjacentBombs);
        board.reveal(fieldWithAdjacentBombs);
        int numberOfRevealedFields = 0;

        for (int i = 0; i < WIDTH; ++i)
            for (int j = 0; j < HEIGHT; ++j)
                if (cells[i][j].containsState(Board.State.REVEALED))
                    ++numberOfRevealedFields;

        assertEquals("There should be only one field revealed.", 1, numberOfRevealedFields);
    }

    @Test
    public void testGameOver() throws NoSuchFieldException, IllegalAccessException {
        final int HEIGHT = 40;
        final int WIDTH = 30;
        final int BOMBS = 4;

        Board board = new Board(WIDTH, HEIGHT, BOMBS);
        java.lang.reflect.Field boardFields = Board.class.getDeclaredField("cells");
        boardFields.setAccessible(true);
        Cell[][] cells = (Cell[][]) boardFields.get(board);

        Point firstBomb = null;
        for (int i = 0; i < WIDTH && firstBomb == null; ++i)
            for (int j = 0; j < HEIGHT && firstBomb == null; ++j)
                if (cells[i][j].containsState(Board.State.BOMB))
                    firstBomb = new Point(i, j);

        board.reveal(firstBomb);
        assertTrue("Game should be over after selecting the bomb", board.isGameOver());
    }
}
