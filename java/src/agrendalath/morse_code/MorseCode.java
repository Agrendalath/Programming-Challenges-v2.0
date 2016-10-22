package agrendalath.morse_code;

import java.util.Scanner;

public class MorseCode {
    public static void main(String[] args) {
        MorseTranslator morseTranslator = new MorseTranslator();

        boolean exit = false;
        while (!exit) {
            System.out.println("Choose one option:");
            System.out.println("0. Translate text to Morse Code.");
            System.out.println("1. Translate Morse Code to text.");
            System.out.println("2. Exit.");
            Scanner scanner = new Scanner(System.in);
            byte option = scanner.nextByte();
            String message = "";
            if (option == 0 || option == 1) {
                System.out.print("Enter your message: ");
                message = scanner.next();
            }
            switch (option) {
                case 0:
                    System.out.println(morseTranslator.translateToMorseCode(message));
                    break;
                case 1:
                    System.out.println(morseTranslator.translateToText(message));
                    break;
                case 2:
                    exit = true;
                    break;
                default:
                    System.out.println("Wrong option.");
            }
        }
    }
}
