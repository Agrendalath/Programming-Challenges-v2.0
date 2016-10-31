package agrendalath.letter_counter;

import java.util.concurrent.RecursiveTask;

class LetterCounter extends RecursiveTask<Integer> {
    private String text;
    private char letter;

    LetterCounter(String text, char letter) {
        this.text = text;
        this.letter = letter;
    }

    private int findThemAll() {
        int counter = -1;
        int index = 0;
        while (index != -1) {
            ++counter;
            index = text.indexOf(letter, index);
            if (index != -1)
                ++index;
        }
        return counter;
    }

    @Override
    protected Integer compute() {
        final int THRESHOLD = 200000;
        if (text.length() <= THRESHOLD)
            return findThemAll();
        else
            return new LetterCounter(text.substring(0, text.length() / 2), letter).compute() + (new LetterCounter(text.substring(text.length() / 2), letter)).compute();
    }

    public static void main(String[] args) {
        System.out.println("BLABLABLA");
    }
}
