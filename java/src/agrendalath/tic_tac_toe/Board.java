package agrendalath.tic_tac_toe;

import java.awt.*;

@SuppressWarnings("WeakerAccess")
public interface Board {
    boolean placeMark(int x, int y);

    boolean placeMark(Point point);

    boolean isGameOver();

    String getWinner();

    char getCurrentPlayer();

    char[][] getBoard();
}
