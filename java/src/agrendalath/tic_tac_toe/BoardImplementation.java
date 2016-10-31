package agrendalath.tic_tac_toe;

import java.awt.*;

@SuppressWarnings("WeakerAccess")
public class BoardImplementation implements Board {
    private enum Player {O, X}

    private final int SIZE;
    private final Player[][] cells;
    private Player currentPlayer;
    private Player winner = null;
    private boolean gameOver = false;
    private int moves = 0;

    @SuppressWarnings("WeakerAccess")
    public BoardImplementation() {
        this(3);
    }

    @SuppressWarnings("WeakerAccess")
    public BoardImplementation(int n) {
        SIZE = n;
        cells = new Player[SIZE][SIZE];
        currentPlayer = Player.O;
    }

    private void switchPlayer() {
        if (currentPlayer == Player.O)
            currentPlayer = Player.X;
        else
            currentPlayer = Player.O;
    }

    private void checkWinningState(Point point) {
        boolean column = true;
        boolean row = true;
        boolean diagonal1 = true;
        boolean diagonal2 = true;

        for (int i = 0; i < SIZE; ++i) {
            if (cells[point.x][i] != currentPlayer)
                column = false;
            if (cells[i][point.y] != currentPlayer)
                row = false;
            if (cells[i][i] != currentPlayer)
                diagonal1 = false;
            if (cells[i][SIZE - 1 - i] != currentPlayer)
                diagonal2 = false;
        }

        if (column || row || diagonal1 || diagonal2) {
            winner = currentPlayer;
            gameOver = true;
        } else if (moves == SIZE * SIZE)
            gameOver = true;
    }

    public boolean placeMark(int x, int y) {
        return placeMark(new Point(x, y));
    }

    public boolean placeMark(Point point) {
        if (cells[point.x][point.y] != null)
            return false;

        cells[point.x][point.y] = currentPlayer;
        ++moves;

        checkWinningState(point);
        switchPlayer();
        return true;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String getWinner() {
        if (!gameOver)
            return "Game is not over yet!";

        if (winner == null)
            return "DRAW";

        return winner.toString();
    }

    public char getCurrentPlayer() {
        return currentPlayer.toString().charAt(0);
    }

    public char[][] getBoard() {
        char[][] board = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; ++i)
            for (int j = 0; j < SIZE; ++j)
                board[i][j] = cells[i][j] == null ? ' ' : cells[i][j].toString().charAt(0);

        return board;
    }

    void printBoard() {
        char[][] board = getBoard();

        for (int i = 0; i < SIZE; ++i) {
            for (int j = 0; j < SIZE; ++j) {
                System.out.print(board[i][j]);
                if (j != SIZE - 1)
                    System.out.print('|');
            }
            System.out.println();
            if (i != SIZE - 1) {
                for (int j = 0; j < SIZE; ++j)
                    System.out.print("― ");
                System.out.println();
            }
        }
    }
}
