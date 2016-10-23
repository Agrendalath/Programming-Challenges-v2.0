package agrendalath.rock_paper_scissors;

import org.junit.Test;

import static org.junit.Assert.*;

public class RPSLSTest {
    @Test
    public void fight() throws Exception {
        RPSLS rps = new RPSLS();

        // Rock
        FigureInterface first = RPSLS.Figures.Rock;
        FigureInterface second = RPSLS.Figures.Scissors;
        assertTrue(first + " crushes " + second, rps.fight(first, second));
        second = RPSLS.Figures.Lizard;
        assertTrue(first + " crushes " + second, rps.fight(first, second));
        second = RPSLS.Figures.Paper;
        assertFalse(first + " should not beat " + second, rps.fight(first, second));
        second = RPSLS.Figures.Spock;
        assertFalse(first + " should not beat " + second, rps.fight(first, second));

        // Scissors
        first = RPSLS.Figures.Scissors;
        second = RPSLS.Figures.Paper;
        assertTrue(first + " cuts " + second, rps.fight(first, second));
        second = RPSLS.Figures.Lizard;
        assertTrue(first + " decapitates " + second, rps.fight(first, second));
        second = RPSLS.Figures.Rock;
        assertFalse(first + " should not beat " + second, rps.fight(first, second));
        second = RPSLS.Figures.Spock;
        assertFalse(first + " should not beat " + second, rps.fight(first, second));

        // Paper
        first = RPSLS.Figures.Paper;
        second = RPSLS.Figures.Rock;
        assertTrue(first + " covers " + second, rps.fight(first, second));
        second = RPSLS.Figures.Spock;
        assertTrue(first + " disproves " + second, rps.fight(first, second));
        second = RPSLS.Figures.Scissors;
        assertFalse(first + " should not beat " + second, rps.fight(first, second));
        second = RPSLS.Figures.Lizard;
        assertFalse(first + " should not beat " + second, rps.fight(first, second));

        // Lizard
        first = RPSLS.Figures.Lizard;
        second = RPSLS.Figures.Paper;
        assertTrue(first + " eats " + second, rps.fight(first, second));
        second = RPSLS.Figures.Spock;
        assertTrue(first + " poisons " + second, rps.fight(first, second));
        second = RPSLS.Figures.Scissors;
        assertFalse(first + " should not beat " + second, rps.fight(first, second));
        second = RPSLS.Figures.Rock;
        assertFalse(first + " should not beat " + second, rps.fight(first, second));

        // Spock
        first = RPSLS.Figures.Spock;
        second = RPSLS.Figures.Rock;
        assertTrue(first + " vaporizes " + second, rps.fight(first, second));
        second = RPSLS.Figures.Scissors;
        assertTrue(first + " smashes " + second, rps.fight(first, second));
        second = RPSLS.Figures.Paper;
        assertFalse(first + " should not beat " + second, rps.fight(first, second));
        second = RPSLS.Figures.Lizard;
        assertFalse(first + " should not beat " + second, rps.fight(first, second));
    }
}
