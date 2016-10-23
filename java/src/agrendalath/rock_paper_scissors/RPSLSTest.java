package agrendalath.rock_paper_scissors;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RPSLSTest {
    private RPSLS rps = new RPSLS();

    private void assertWins(FigureInterface first, FigureInterface second, String message) {
        assertEquals(first + " " + message + " " + second, 1, rps.fight(first, second));
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
        assertWins(first, second, "crushes");
        second = rps.getFigure("Lizard");
        assertWins(first, second, "crushes");
        second = rps.getFigure("Paper");
        assertLoses(first, second);
        second = rps.getFigure("Spock");
        assertLoses(first, second);

        // Scissors
        first = rps.getFigure("Scissors");
        second = rps.getFigure("Scissors");
        assertDraws(first, second);
        second = rps.getFigure("Paper");
        assertWins(first, second, "cuts");
        second = rps.getFigure("Lizard");
        assertWins(first, second, "decapitates");
        second = rps.getFigure("Rock");
        assertLoses(first, second);
        second = rps.getFigure("Spock");
        assertLoses(first, second);

        // Paper
        first = rps.getFigure("Paper");
        second = rps.getFigure("Paper");
        assertDraws(first, second);
        second = rps.getFigure("Rock");
        assertWins(first, second, "covers");
        second = rps.getFigure("Spock");
        assertWins(first, second, "disproves");
        second = rps.getFigure("Scissors");
        assertLoses(first, second);
        second = rps.getFigure("Lizard");
        assertLoses(first, second);

        // Lizard
        first = rps.getFigure("Lizard");
        second = rps.getFigure("Lizard");
        assertDraws(first, second);
        second = rps.getFigure("Paper");
        assertWins(first, second, "eats");
        second = rps.getFigure("Spock");
        assertWins(first, second, "poisons");
        second = rps.getFigure("Scissors");
        assertLoses(first, second);
        second = rps.getFigure("Rock");
        assertLoses(first, second);

        // Spock
        first = rps.getFigure("Spock");
        second = rps.getFigure("Spock");
        assertDraws(first, second);
        second = rps.getFigure("Rock");
        assertWins(first, second, "vaporizes");
        second = rps.getFigure("Scissors");
        assertWins(first, second, "smashes");
        second = rps.getFigure("Paper");
        assertLoses(first, second);
        second = rps.getFigure("Lizard");
        assertLoses(first, second);
    }
}
