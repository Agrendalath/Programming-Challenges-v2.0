package agrendalath.rock_paper_scissors;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RPSTest {
    private RPS rps = new RPS();

    private void assertWins(FigureInterface first, FigureInterface second) {
        assertEquals(first + " should beat " + second, 1, rps.fight(first, second));
    }

    private void assertLoses(FigureInterface first, FigureInterface second) {
        assertEquals(first + " should not beat " + second, -1, rps.fight(first, second));
    }

    private void assertDraws(FigureInterface first, FigureInterface second) {
        assertEquals(first + " should not beat " + second, 0, rps.fight(first, second));
    }

    @Test
    public void fight() throws Exception {
        // Rock
        FigureInterface first = rps.getFigure("Rock");
        FigureInterface second = rps.getFigure("Rock");
        assertDraws(first, second);
        second = rps.getFigure("Scissors");
        assertWins(first, second);
        second = rps.getFigure("Paper");
        assertLoses(first, second);

        // Scissors
        first = rps.getFigure("Scissors");
        second = rps.getFigure("Scissors");
        assertDraws(first, second);
        second = rps.getFigure("Paper");
        assertWins(first, second);
        second = rps.getFigure("Rock");
        assertLoses(first, second);

        // Paper
        first = rps.getFigure("Paper");
        second = rps.getFigure("Paper");
        assertDraws(first, second);
        second = rps.getFigure("Rock");
        assertWins(first, second);
        second = rps.getFigure("Scissors");
        assertLoses(first, second);
    }
}
