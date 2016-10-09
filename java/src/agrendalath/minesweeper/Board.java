package agrendalath.minesweeper;

import java.awt.*;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Random;

public class Board {
    enum State {REVEALED, BOMB, FLAG}

    private Dimension boardSize;
    private int bombs;
    private Field[][] fields;
    private boolean gameOver = false;

    class Field {
        EnumSet<State> state;
        int adjacentBombs;

        Field(EnumSet<State> state) {
            this.state = state;
        }
    }

    Board(int width, int height, int bombs) {
        boardSize = new Dimension(width, height);
        this.bombs = bombs;
        createBoard();
        generateBoard();
    }

    private void createBoard() {
        fields = new Field[boardSize.width][boardSize.height];
        for (int i = 0; i < boardSize.width; ++i)
            for (int j = 0; j < boardSize.height; ++j)
                fields[i][j] = new Field(EnumSet.noneOf(State.class));
    }

    private void incrementNeighbours(Point point) {
        --fields[point.x][point.y].adjacentBombs;
        for (int i = point.x - 1; i <= point.x + 1; ++i)
            for (int j = point.y - 1; j <= point.y + 1; ++j)
                if (i >= 0 && j >= 0 && i < boardSize.width && j < boardSize.height)
                    ++fields[i][j].adjacentBombs;
    }

    private void generateBoard() {
        Random random = new Random();
        for (int i = 0; i < bombs; ++i) {
            int x = random.nextInt(boardSize.width);
            int y = random.nextInt(boardSize.height);
            if (fields[x][y].state.contains(State.BOMB))
                --i;
            else {
                fields[x][y].state.add(State.BOMB);
                incrementNeighbours(new Point(x, y));
            }
        }
    }

    boolean isGameOver() {
        return gameOver;
    }

    void reveal(Point point) {
        if (point.x < 0 || point.y < 0 || point.x >= boardSize.width || point.y >= boardSize.height ||
                (fields[point.x][point.y].state.contains(State.REVEALED) &&
                        fields[point.x][point.y].adjacentBombs == 0) &&
                        !fields[point.x][point.y].state.contains(State.FLAG))
            return;

        fields[point.x][point.y].state.add(State.REVEALED);

        if (fields[point.x][point.y].state.contains(State.BOMB)) {
            gameOver = true;
            return;
        }

        for (int i = point.x - 1; i <= point.x + 1; ++i)
            for (int j = point.y - 1; j <= point.y + 1; ++j)
                if (i >= 0 && j >= 0 && i < boardSize.width && j < boardSize.height && fields[i][j].state.isEmpty()) {
                    if (fields[i][j].adjacentBombs == 0)
                        reveal(new Point(i, j));
                    else
                        fields[i][j].state.add(State.REVEALED);
                }

    }

    // Access will be changed after creating game class.
    private char[][] getBoard() {
        char[][] board = new char[boardSize.width][boardSize.width];
        for (int i = 0; i < boardSize.width; ++i) {
            for (int j = 0; j < boardSize.height; ++j) {
                if (!isGameOver()) {
                    if (fields[i][j].state.contains(State.REVEALED))
                        board[i][j] = fields[i][j].adjacentBombs == 0 ? '_' : Character.forDigit(fields[i][j].adjacentBombs, 10);
                    else if (fields[i][j].state.contains(State.FLAG))
                        board[i][j] = 'F';
                    else
                        board[i][j] = '#';
                } else {
                    if (fields[i][j].state.contains(State.BOMB))
                        board[i][j] = 'B';
                    else
                        board[i][j] = fields[i][j].adjacentBombs == 0 ? '_' : Character.forDigit(fields[i][j].adjacentBombs, 10);
                }
            }
        }
        return board;
    }

    private void printBoard() {
        System.out.println(Arrays.deepToString(getBoard()).replace("], ", "\n").replace("[", "").replace(",", "").replace("]", ""));
    }

    public static void main(String[] args) {
        Board board = new Board(10, 10, 4);
        board.reveal(new Point(4, 4));
        board.printBoard();
    }
}
