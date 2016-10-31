package agrendalath.morse_code;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DSTTest {
    @Test
    public void translateToText() throws Exception {
        DST tree = new DST();
        String message = "... --- ...";
        assertEquals("Wrong translation of " + message, "SOS", tree.translateToText(message));
        message = ".... . .-.. .-.. --- --..-- | - .... .. ... | .. ... | - . ... - .-.-.-";
        assertEquals("Wrong translation of " + message, "Hello, this is test.".toUpperCase(), tree.translateToText(message));
    }

    @Test
    public void translateToMorseCode() throws Exception {
        DST tree = new DST();
        String message = "SOS";
        assertEquals("Wrong translation of " + message, "... --- ...", tree.translateToMorseCode(message));
        message = "Hello, this is test.";
        assertEquals("Wrong translation of " + message, ".... . .-.. .-.. --- --..-- | - .... .. ... | .. ... | - . ... - .-.-.-", tree.translateToMorseCode(message));
    }

}
