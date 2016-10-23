package agrendalath.rock_paper_scissors;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RPSTest {
    @Test
    public void fight() throws Exception {
        RPS rps = new RPS();

        // Rock
        FigureInterface first = RPS.getFigure("Rock");
        FigureInterface second = RPS.getFigure("Scissors");
        assertTrue(first + " should beat " + second, rps.fight(first, second));
        second = RPS.getFigure("Paper");
        assertFalse(first + " should not beat " + second, rps.fight(first, second));

        // Scissors
        first = RPS.getFigure("Scissors");
        assertTrue(first + " should beat " + second, rps.fight(first, second));
        second = RPS.getFigure("Rock");
        assertFalse(first + " should not beat " + second, rps.fight(first, second));

        // Paper
        first = RPS.getFigure("Paper");
        assertTrue(first + " should beat " + second, rps.fight(first, second));
        second = RPS.getFigure("Scissors");
        assertFalse(first + " should not beat " + second, rps.fight(first, second));
    }
}
