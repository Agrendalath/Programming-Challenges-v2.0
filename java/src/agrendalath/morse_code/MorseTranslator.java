package agrendalath.morse_code;

import java.io.*;

class MorseTranslator {
    private static final String SAVE_LOCATION = "saves/";
    private DST tree;

    MorseTranslator() {
        initialize();
    }

    private void initialize() {
        try {
            tree = (DST) new ObjectInputStream(new FileInputStream(SAVE_LOCATION + "DST.ser")).readObject();
        } catch (IOException | ClassNotFoundException e) {
            tree = new DST();
            try {
                //noinspection ResultOfMethodCallIgnored
                new File(SAVE_LOCATION).mkdirs();
                new ObjectOutputStream(new FileOutputStream(SAVE_LOCATION + "DST.ser")).writeObject(tree);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    String translateToMorseCode(String message) {
        return tree.translateToMorseCode(message);
    }

    String translateToText(String message) {
        return tree.translateToText(message);
    }
}
