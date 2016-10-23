package agrendalath.rock_paper_scissors;

import static org.junit.Assert.assertEquals;

interface TestHelper {
    default void assertWins(RPS rps, FigureInterface first, FigureInterface second) {
        assertEquals(first + " should beat " + second, 1, rps.fight(first, second));
    }

    default void assertLoses(RPS rps, FigureInterface first, FigureInterface second) {
        assertEquals(first + " should not beat " + second, -1, rps.fight(first, second));
    }

    default void assertDraws(RPS rps, FigureInterface first, FigureInterface second) {
        assertEquals(first + " should draw " + second, 0, rps.fight(first, second));
    }
}
