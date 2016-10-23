package agrendalath.rock_paper_scissors;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RPSLSTest {
    @Test
    public void fight() throws Exception {
        RPSLS rps = new RPSLS();

        // Rock
        FigureInterface first = RPSLS.getFigure("Rock");
        FigureInterface second = RPSLS.getFigure("Scissors");
        assertTrue(first + " crushes " + second, rps.fight(first, second));
        second = RPSLS.getFigure("Lizard");
        assertTrue(first + " crushes " + second, rps.fight(first, second));
        second = RPSLS.getFigure("Paper");
        assertFalse(first + " should not beat " + second, rps.fight(first, second));
        second = RPSLS.getFigure("Spock");
        assertFalse(first + " should not beat " + second, rps.fight(first, second));

        // Scissors
        first = RPSLS.getFigure("Scissors");
        second = RPSLS.getFigure("Paper");
        assertTrue(first + " cuts " + second, rps.fight(first, second));
        second = RPSLS.getFigure("Lizard");
        assertTrue(first + " decapitates " + second, rps.fight(first, second));
        second = RPSLS.getFigure("Rock");
        assertFalse(first + " should not beat " + second, rps.fight(first, second));
        second = RPSLS.getFigure("Spock");
        assertFalse(first + " should not beat " + second, rps.fight(first, second));

        // Paper
        first = RPSLS.getFigure("Paper");
        second = RPSLS.getFigure("Rock");
        assertTrue(first + " covers " + second, rps.fight(first, second));
        second = RPSLS.getFigure("Spock");
        assertTrue(first + " disproves " + second, rps.fight(first, second));
        second = RPSLS.getFigure("Scissors");
        assertFalse(first + " should not beat " + second, rps.fight(first, second));
        second = RPSLS.getFigure("Lizard");
        assertFalse(first + " should not beat " + second, rps.fight(first, second));

        // Lizard
        first = RPSLS.getFigure("Lizard");
        second = RPSLS.getFigure("Paper");
        assertTrue(first + " eats " + second, rps.fight(first, second));
        second = RPSLS.getFigure("Spock");
        assertTrue(first + " poisons " + second, rps.fight(first, second));
        second = RPSLS.getFigure("Scissors");
        assertFalse(first + " should not beat " + second, rps.fight(first, second));
        second = RPSLS.getFigure("Rock");
        assertFalse(first + " should not beat " + second, rps.fight(first, second));

        // Spock
        first = RPSLS.getFigure("Spock");
        second = RPSLS.getFigure("Rock");
        assertTrue(first + " vaporizes " + second, rps.fight(first, second));
        second = RPSLS.getFigure("Scissors");
        assertTrue(first + " smashes " + second, rps.fight(first, second));
        second = RPSLS.getFigure("Paper");
        assertFalse(first + " should not beat " + second, rps.fight(first, second));
        second = RPSLS.getFigure("Lizard");
        assertFalse(first + " should not beat " + second, rps.fight(first, second));
    }
}
