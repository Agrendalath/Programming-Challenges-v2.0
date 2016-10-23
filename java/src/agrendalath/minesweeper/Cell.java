package agrendalath.minesweeper;

import java.util.EnumSet;

class Cell {
    private EnumSet<Board.State> state;
    private int adjacentBombs;
    private int adjacentFlags;

    Cell(EnumSet<Board.State> state) {
        this.state = state;
    }

    void addState(Board.State state) {
        this.state.add(state);
    }

    void removeState(Board.State state) {
        this.state.remove(state);
    }

    boolean containsState(Board.State state) {
        return this.state.contains(state);
    }

    int getAdjacentBombs() {
        return adjacentBombs;
    }

    void increaseAdjacentBombs(int x) {
        adjacentBombs += x;
    }

    int getAdjacentFlags() {
        return adjacentFlags;
    }

    void increaseAdjacentFlags(int x) {
        adjacentFlags += x;
    }
}
