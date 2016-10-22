package agrendalath.morse_code;

import java.util.HashMap;
import java.util.Map;

class DST {
    DST() {
        createTree();
        map = toMap();
    }

    private final Map<Character, String> map;

    private class Node {
        char value;
        Node parent;
        Node left;
        Node right;
    }

    private Node root = new Node();

    private void addKey(char key, String code) {
        if (code.length() == 0)
            throw new IllegalArgumentException("Code cannot be empty.");

        Node currentNode = root;
        for (char sign : code.toCharArray()) {
            if (sign == '.') {
                if (currentNode.left == null) {
                    currentNode.left = new Node();
                    currentNode.left.parent = currentNode;
                }
                currentNode = currentNode.left;
            } else {
                if (currentNode.right == null) {
                    currentNode.right = new Node();
                    currentNode.right.parent = currentNode;
                }
                currentNode = currentNode.right;
            }
        }
        currentNode.value = key;
    }

    private char getKey(String code) {
        if (code.length() == 0)
            throw new IllegalArgumentException("Code cannot be empty.");

        Node currentNode = root;

        for (char sign : code.toCharArray()) {
            if (sign == '.')
                currentNode = currentNode.left;
            else
                currentNode = currentNode.right;
        }

        return currentNode.value;
    }

    String translateToText(String message) {
        if (message.length() == 0)
            throw new IllegalArgumentException("Sequence cannot be empty.");

        StringBuilder stringBuilder = new StringBuilder();

        for (String word : message.split("\\|")) {
            for (String letter : word.split(" ")) {
                stringBuilder.append(getKey(letter));
            }
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }

    String translateToMorseCode(String message) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char letter : message.toCharArray()) {
            if (letter == ' ')
                stringBuilder.append("|");
            else
                stringBuilder.append(map.get(letter));

            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    private void addKeyToMap(Map<Character, String> map, Node node, String sequence) {
        if (node != null) {
            if (node != root)
                map.put(node.value, sequence);

            addKeyToMap(map, node.left, sequence + ".");
            addKeyToMap(map, node.right, sequence + "-");
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
