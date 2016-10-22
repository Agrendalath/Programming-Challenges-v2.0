package agrendalath.morse_code;

import java.io.Serializable;

class Node implements Serializable {
    private char value;
    private Node left;
    private Node right;

    void setValue(char value) {
        this.value = value;
    }

    char getValue() {
        return value;
    }

    void setLeft(Node left) {
        this.left = left;
    }

    Node getLeft() {
        return left;
    }

    void setRight(Node right) {
        this.right = right;
    }

    Node getRight() {
        return right;
    }
}
