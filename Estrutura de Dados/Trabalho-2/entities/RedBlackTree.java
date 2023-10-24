package entities;

public class RedBlackTree<T extends Comparable<T>> extends BaseTree<T> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private static class Node<T> {
        public T value;
        public Node<T> left;
        public Node<T> right;
        public boolean color;

        public Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.color = RED;
        }
    }

    public RedBlackTree(String name) {
        super(name);
    }

    public void clear() { root = null; }

    private Node<T> root;

    // Implementação dos métodos da interface ITreeOperations<T> aqui

    public void insert(T value) {
        root = insertRec(root, value);
        root.color = BLACK; // Garante que a raiz seja sempre preta
    }

    private Node<T> insertRec(Node<T> node, T value) {
        if (node == null) {
            return new Node<>(value);
        }

        if (value.compareTo(node.value) < 0) {
            node.left = insertRec(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            node.right = insertRec(node.right, value);
        } else {
            // Valor já existe, não é inserido novamente.
            return node;
        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    public boolean search(T value) {
        return searchRec(root, value);
    }

    private boolean searchRec(Node<T> node, T value) {
        if (node == null) {
            return false;
        }

        if (value.equals(node.value)) {
            return true;
        }

        if (value.compareTo(node.value) < 0) {
            return searchRec(node.left, value);
        }

        return searchRec(node.right, value);
    }

    public void remove(T value) {
        root = removeRec(root, value);
        if (root != null) {
            root.color = BLACK; // Garante que a raiz seja sempre preta após a remoção
        }
    }

    private Node<T> removeRec(Node<T> node, T value) {
        if (node == null) {
            return null;
        }

        if (value.compareTo(node.value) < 0) {
            node.left = removeRec(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            node.right = removeRec(node.right, value);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            Node<T> minRight = minValueNode(node.right);
            node.value = minRight.value;
            node.right = removeMin(node.right);
        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    private Node<T> removeMin(Node<T> node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    private Node<T> minValueNode(Node<T> node) {
        Node<T> current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private boolean isRed(Node<T> node) {
        if (node == null) {
            return false;
        }
        return node.color == RED;
    }

    private Node<T> rotateLeft(Node<T> h) {
        Node<T> x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node<T> rotateRight(Node<T> h) {
        Node<T> x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node<T> h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }
}
