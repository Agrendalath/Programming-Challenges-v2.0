package agrendalath.tic_tac_toe;

public class TicTacToe {
    public static void main(String[] args) {
        Board board = new BoardImplementation();
        board.placeMark(0, 0);
        board.placeMark(0, 2);
        board.placeMark(1, 1);
        board.placeMark(1, 2);

        ((BoardImplementation) board).printBoard();
    }
}
