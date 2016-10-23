package agrendalath.rock_paper_scissors;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RPSTest {
    @Test
    public void fight() throws Exception {
        RPS rps = new RPS();

        // Rock
        FigureInterface first = RPS.Figures.Rock;
        FigureInterface second = RPS.Figures.Scissors;
        assertTrue(first + " should beat " + second, rps.fight(first, second));
        second = RPS.Figures.Paper;
        assertFalse(first + " should not beat " + second, rps.fight(first, second));

        // Scissors
        first = RPS.Figures.Scissors;
        assertTrue(first + " should beat " + second, rps.fight(first, second));
        second = RPS.Figures.Rock;
        assertFalse(first + " should not beat " + second, rps.fight(first, second));

        // Paper
        first = RPS.Figures.Paper;
        assertTrue(first + " should beat " + second, rps.fight(first, second));
        second = RPS.Figures.Scissors;
        assertFalse(first + " should not beat " + second, rps.fight(first, second));
    }
}
