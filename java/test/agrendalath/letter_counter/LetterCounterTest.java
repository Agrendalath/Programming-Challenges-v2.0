package agrendalath.letter_counter;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

import static org.junit.Assert.assertEquals;

public class LetterCounterTest {
    private ForkJoinPool pool;
    private String textBase = "aposjfojsapofajspoajsfpoaposjfaposjvponaspovnsaovnaspvnapos";

    @Before
    public void setUp() {
        pool = ForkJoinPool.commonPool();
    }

    private int invokeWordFinder(String text, char letter) {
        return pool.invoke(new LetterCounter(text, letter));
    }



    private String generateString(int size) {
        StringBuilder text = new StringBuilder();
        for (int i=0; i<size; ++i)
            text.append(textBase);
        return text.toString();
    }

    @Test
    public void testWordFinder() {
        char letter = 'a';
        int size = 1;

        assertEquals(size*10, invokeWordFinder(generateString(size), letter));
    }

    @Test
    public void testLongText() {
        char letter = 'a';
        int size = 100;

        assertEquals(size*10, invokeWordFinder(generateString(size), letter));
    }

    @Test
    public void testLongerText() {
        char letter = 'a';
        int size = 10000;

        assertEquals(size*10, invokeWordFinder(generateString(size), letter));
    }

    @Test
    public void testVeryLongText() {
        char letter = 'a';
        int size = 1000000;

        assertEquals(size*10, invokeWordFinder(generateString(size), letter));
    }
}
