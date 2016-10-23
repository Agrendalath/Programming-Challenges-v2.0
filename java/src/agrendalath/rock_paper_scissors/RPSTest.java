package agrendalath.rock_paper_scissors;

import org.junit.Test;

public class RPSTest implements TestHelper {
    private RPS rps = new RPS();

    @Test
    public void fight() throws Exception {
        // Rock
        FigureInterface first = rps.getFigure("Rock");
        FigureInterface second = rps.getFigure("Rock");
        assertDraws(rps, first, second);
        second = rps.getFigure("Scissors");
        assertWins(rps, first, second);
        second = rps.getFigure("Paper");
        assertLoses(rps, first, second);

        // Scissors
        first = rps.getFigure("Scissors");
        second = rps.getFigure("Scissors");
        assertDraws(rps, first, second);
        second = rps.getFigure("Paper");
        assertWins(rps, first, second);
        second = rps.getFigure("Rock");
        assertLoses(rps, first, second);

        // Paper
        first = rps.getFigure("Paper");
        second = rps.getFigure("Paper");
        assertDraws(rps, first, second);
        second = rps.getFigure("Rock");
        assertWins(rps, first, second);
        second = rps.getFigure("Scissors");
        assertLoses(rps, first, second);
    }
}
