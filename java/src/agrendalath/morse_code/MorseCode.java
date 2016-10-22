package agrendalath.morse_code;

public class MorseCode {
    public static void main(String[] args) {
        DST tree = new DST();
        System.out.println(tree.translateToMorseCode("SOS"));
        System.out.println(tree.translateToText("... --- ..."));
    }
}
