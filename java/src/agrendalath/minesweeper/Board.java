package agrendalath.minesweeper;

import java.awt.*;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Random;

public class Board {
    enum State {REVEALED, BOMB, FLAG}

    private final Dimension BOARD_SIZE;
    private final int BOMBS;
    private int fieldsLeft;
    private Cell[][] cells;
    private boolean gameOver = false;
    private boolean gameWon = false;

    class Cell {
        EnumSet<State> state;
        byte adjacentBombs;
        byte adjacentFlags;

        Cell(EnumSet<State> state) {
            this.state = state;
        }
    }

    Board(int width, int height, int bombs) {
        BOARD_SIZE = new Dimension(width, height);
        BOMBS = bombs;
        this.fieldsLeft = BOARD_SIZE.width * BOARD_SIZE.height - BOMBS;
        createBoard();
        generateBoard();
    }

    private void createBoard() {
        cells = new Cell[BOARD_SIZE.width][BOARD_SIZE.height];
        for (int i = 0; i < BOARD_SIZE.width; ++i)
            for (int j = 0; j < BOARD_SIZE.height; ++j)
                cells[i][j] = new Cell(EnumSet.noneOf(State.class));
    }

    private void incrementNeighbours(Point point) {
        --cells[point.x][point.y].adjacentBombs;
        for (int i = point.x - 1; i <= point.x + 1; ++i)
            for (int j = point.y - 1; j <= point.y + 1; ++j)
                if (isValidCell(new Point(i, j)))
                    ++cells[i][j].adjacentBombs;
    }

    private void generateBoard() {
        Random random = new Random();
        for (int i = 0; i < BOMBS; ++i) {
            int x = random.nextInt(BOARD_SIZE.width);
            int y = random.nextInt(BOARD_SIZE.height);
            if (cells[x][y].state.contains(State.BOMB))
                --i;
            else {
                cells[x][y].state.add(State.BOMB);
                incrementNeighbours(new Point(x, y));
            }
        }
    }

    private boolean isValidCell(Point point) {
        return point.x >= 0 && point.y >= 0 && point.x < BOARD_SIZE.width && point.y < BOARD_SIZE.height;
    }

    private void setGameOver() {
        gameOver = true;

        boolean won = true;

        for (int i = 0; i < BOARD_SIZE.width; ++i)
            for (int j = 0; j < BOARD_SIZE.height; ++j)
                if (cells[i][j].state.contains(State.REVEALED) && cells[i][j].state.contains(State.BOMB))
                    won = false;

        gameWon = won;
    }

    boolean isGameOver() {
        return gameOver;
    }

    private boolean isGameWon() {
        return gameWon;
    }

    boolean reveal(Point point) {
        if (!isValidCell(point))
            return false;

        Cell cell = cells[point.x][point.y];
        boolean forceReveal = false;

        if (cell.state.contains(State.FLAG))
            return false;

        if (cell.state.contains(State.REVEALED) && cell.adjacentFlags == cell.adjacentBombs)
            forceReveal = true;

        if (!cell.state.contains(State.REVEALED))
            --fieldsLeft;

        cell.state.add(State.REVEALED);

        if (cell.state.contains(State.BOMB) || fieldsLeft == 0) {
            setGameOver();
            return true;
        }

        if (cell.adjacentBombs == 0 || forceReveal) {
            for (int i = point.x - 1; i <= point.x + 1; ++i)
                for (int j = point.y - 1; j <= point.y + 1; ++j) {
                    Point newPoint = new Point(i, j);
                    if (isValidCell(newPoint)) {
                        Cell newCell = cells[i][j];
                        if (!newCell.state.contains(State.REVEALED))
                            reveal(newPoint);
                    }
                }
        }

        return true;
    }

    boolean flag(Point point) {
        if (!isValidCell(point))
            return false;

        Cell cell = cells[point.x][point.y];

        if (cell.state.contains(State.REVEALED))
            return false;

        int flagChange;

        if (cell.state.contains(State.FLAG)) {
            cell.state.remove(State.FLAG);
            flagChange = -1;
        } else {
            cell.state.add(State.FLAG);
            flagChange = 1;
        }

        for (int i = point.x - 1; i <= point.x + 1; ++i)
            for (int j = point.y - 1; j <= point.y + 1; ++j)
                if (isValidCell(new Point(i, j)))
                    cells[i][j].adjacentFlags += flagChange;

        return true;
    }

    // Access will be changed after creating game class.
    char[][] getBoard() {
        char[][] board = new char[BOARD_SIZE.width][BOARD_SIZE.width];
        for (int i = 0; i < BOARD_SIZE.width; ++i) {
            for (int j = 0; j < BOARD_SIZE.height; ++j) {
                if (!isGameOver()) {
                    if (cells[i][j].state.contains(State.REVEALED))
                        board[i][j] = Character.forDigit(cells[i][j].adjacentBombs, 10);
                    else if (cells[i][j].state.contains(State.FLAG))
                        board[i][j] = 'F';
                    else
                        board[i][j] = '#';
                } else {
                    if (cells[i][j].state.contains(State.BOMB)) {
                        if (isGameWon())
                            board[i][j] = 'F';
                        else
                            board[i][j] = 'B';
                    } else
                        board[i][j] = Character.forDigit(cells[i][j].adjacentBombs, 10);
                }
            }
        }
        return board;
    }

    private void printBoard() {
        System.out.println(Arrays.deepToString(
                getBoard()).replace("], ", "\n").replace("[", "").replace(",", "").replace("]", "")
        );
    }

    public static void main(String[] args) {
        Board board = new Board(10, 10, 4);
        board.flag(new Point(4, 4));
        board.reveal(new Point(4, 4));
        board.printBoard();
    }
}
