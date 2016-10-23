package agrendalath.morse_code;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

class DST implements Serializable {
    private final Map<Character, String> map;
    private Node root = new Node();

    DST() {
        createTree();
        map = toMap();
    }

    private void addKey(char key, String code) {
        if (code.length() == 0)
            throw new IllegalArgumentException("Code cannot be empty.");

        Node currentNode = root;
        for (char sign : code.toCharArray()) {
            if (sign == '.') {
                if (currentNode.getLeft() == null)
                    currentNode.setLeft(new Node());

                currentNode = currentNode.getLeft();
            } else {
                if (currentNode.getRight() == null)
                    currentNode.setRight(new Node());

                currentNode = currentNode.getRight();
            }
        }
        currentNode.setValue(Character.toUpperCase(key));
    }

    private char getKey(String code) {
        if (code.length() == 0)
            throw new IllegalArgumentException("Code cannot be empty.");

        Node currentNode = root;

        for (char sign : code.toCharArray()) {
            if (sign == '.')
                currentNode = currentNode.getLeft();
            else
                currentNode = currentNode.getRight();
        }

        return currentNode.getValue();
    }

    String translateToText(String message) {
        message = message.toUpperCase();
        if (message.length() == 0)
            throw new IllegalArgumentException("Sequence cannot be empty.");

        StringBuilder stringBuilder = new StringBuilder();

        for (String word : message.split("\\| ")) {
            for (String letter : word.split(" ")) {
                stringBuilder.append(getKey(letter));
            }
            stringBuilder.append(" ");
        }

        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }

    String translateToMorseCode(String message) {
        message = message.toUpperCase();
        StringBuilder stringBuilder = new StringBuilder();
        for (char letter : message.toCharArray()) {
            if (letter == ' ')
                stringBuilder.append("|");
            else
                stringBuilder.append(map.get(letter));

            stringBuilder.append(" ");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }

    private void addKeyToMap(Map<Character, String> map, Node node, String sequence) {
        if (node != null) {
            if (node != root)
                map.put(node.getValue(), sequence);

            addKeyToMap(map, node.getLeft(), sequence + ".");
            addKeyToMap(map, node.getRight(), sequence + "-");
        }
    }

    private Map<Character, String> toMap() {
        Map<Character, String> map = new HashMap<>();
        addKeyToMap(map, root, "");
        return map;
    }

    private void createTree() {
        addKey('A', ".-");
        addKey('B', "-...");
        addKey('C', "-.-.");
        addKey('D', "-..");
        addKey('E', ".");
        addKey('F', "..-.");
        addKey('G', "--.");
        addKey('H', "....");
        addKey('I', "..");
        addKey('J', ".---");
        addKey('K', "-.-");
        addKey('L', ".-..");
        addKey('M', "--");
        addKey('N', "-.");
        addKey('O', "---");
        addKey('P', ".--.");
        addKey('Q', "--.-");
        addKey('R', ".-.");
        addKey('S', "...");
        addKey('T', "-");
        addKey('U', "..-");
        addKey('V', "...-");
        addKey('W', ".--");
        addKey('X', "-..-");
        addKey('Y', "-.--");
        addKey('Z', "--..");
        addKey('0', "-----");
        addKey('1', ".----");
        addKey('2', "..---");
        addKey('3', "...--");
        addKey('4', "....-");
        addKey('5', ".....");
        addKey('6', "-....");
        addKey('7', "--...");
        addKey('8', "---..");
        addKey('9', "----.");
        addKey('Ą', ".-.-");
        addKey('Ć', "-.-..");
        addKey('Ę', "..-..");
        addKey('Ł', ".-..-");
        addKey('Ń', "--.--");
        addKey('Ó', "---.");
        addKey('Ś', "...-...");
        addKey('Ż', "--..-.");
        addKey('Ź', "--..-");
        addKey('.', ".-.-.-");
        addKey(',', "--..--");
        addKey(':', "---...");
        addKey('?', "..--..");
        addKey('\'', ".----.");
        addKey('-', "-....-");
        addKey('/', "-..-.");
        addKey('"', ".-..-.");
        addKey('@', ".--.-.");
        addKey('=', "-...-");
    }
}
