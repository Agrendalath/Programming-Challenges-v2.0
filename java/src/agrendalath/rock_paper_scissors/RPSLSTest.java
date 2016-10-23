package agrendalath.rock_paper_scissors;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RPSLSTest implements TestHelper {
    private RPSLS rps = new RPSLS();

    private void assertWins(RPS rps, FigureInterface first, FigureInterface second, String message) {
        assertEquals(first + " " + message + " " + second, 1, rps.fight(first, second));
    }

    @Test
    public void fight() throws Exception {
        // Rock
        FigureInterface first = rps.getFigure("Rock");
        FigureInterface second = rps.getFigure("Rock");
        assertDraws(rps, first, second);
        second = rps.getFigure("Scissors");
        assertWins(rps, first, second, "crushes");
        second = rps.getFigure("Lizard");
        assertWins(rps, first, second, "crushes");
        second = rps.getFigure("Paper");
        assertLoses(rps, first, second);
        second = rps.getFigure("Spock");
        assertLoses(rps, first, second);

        // Scissors
        first = rps.getFigure("Scissors");
        second = rps.getFigure("Scissors");
        assertDraws(rps, first, second);
        second = rps.getFigure("Paper");
        assertWins(rps, first, second, "cuts");
        second = rps.getFigure("Lizard");
        assertWins(rps, first, second, "decapitates");
        second = rps.getFigure("Rock");
        assertLoses(rps, first, second);
        second = rps.getFigure("Spock");
        assertLoses(rps, first, second);

        // Paper
        first = rps.getFigure("Paper");
        second = rps.getFigure("Paper");
        assertDraws(rps, first, second);
        second = rps.getFigure("Rock");
        assertWins(rps, first, second, "covers");
        second = rps.getFigure("Spock");
        assertWins(rps, first, second, "disproves");
        second = rps.getFigure("Scissors");
        assertLoses(rps, first, second);
        second = rps.getFigure("Lizard");
        assertLoses(rps, first, second);

        // Lizard
        first = rps.getFigure("Lizard");
        second = rps.getFigure("Lizard");
        assertDraws(rps, first, second);
        second = rps.getFigure("Paper");
        assertWins(rps, first, second, "eats");
        second = rps.getFigure("Spock");
        assertWins(rps, first, second, "poisons");
        second = rps.getFigure("Scissors");
        assertLoses(rps, first, second);
        second = rps.getFigure("Rock");
        assertLoses(rps, first, second);

        // Spock
        first = rps.getFigure("Spock");
        second = rps.getFigure("Spock");
        assertDraws(rps, first, second);
        second = rps.getFigure("Rock");
        assertWins(rps, first, second, "vaporizes");
        second = rps.getFigure("Scissors");
        assertWins(rps, first, second, "smashes");
        second = rps.getFigure("Paper");
        assertLoses(rps, first, second);
        second = rps.getFigure("Lizard");
        assertLoses(rps, first, second);
    }
}
