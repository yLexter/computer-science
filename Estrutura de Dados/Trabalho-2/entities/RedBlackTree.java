package entities;

public class RedBlackTree<T extends Comparable<T>> extends BaseTree<T> {
    private Node<T> root;
    private int totalRotations;
    public RedBlackTree() {
        super("Árvore Red Black");
        root = null;
    }

    private static class Node<T> {
        T value;
        Node<T> left, right;
        boolean isRed;

        Node(T value, boolean isRed) {
            this.value = value;
            this.isRed = isRed;
            left = null;
            right = null;
        }
    }

    @Override
    public void insert(T value) {
        root = insert(root, value);
        root.isRed = false;
    }

    private Node<T> insert(Node<T> node, T value) {
        if (node == null) {
            return new Node<>(value, true);
        }

        if (value.compareTo(node.value) < 0) {
            node.left = insert(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            node.right = insert(node.right, value);
        } else {
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

    @Override
    public void remove(T value) {
        root = remove(root, value);
    }

    private Node<T> remove(Node<T> node, T value) {
        if (node == null) {
            return null;
        }

        if (value.compareTo(node.value) < 0) {
            if (!isRed(node.left) && !isRed(node.left.left)) {
                node = moveRedLeft(node);
            }
            node.left = remove(node.left, value);
        } else {
            if (isRed(node.left)) {
                node = rotateRight(node);
            }
            if (value.compareTo(node.value) == 0 && node.right == null) {
                return null;
            }
            if (!isRed(node.right) && !isRed(node.right.left)) {
                node = moveRedRight(node);
            }
            if (value.compareTo(node.value) == 0) {
                Node<T> minNode = findMinNode(node.right);
                node.value = minNode.value;
                node.right = deleteMinNode(node.right);
            } else {
                node.right = remove(node.right, value);
            }
        }

        return balance(node);
    }

    @Override
    public boolean search(T value) {
        return search(root, value);
    }

    private boolean search(Node<T> node, T value) {
        while (node != null) {
            int cmp = value.compareTo(node.value);

            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return true;
            }
        }

        return false;
    }

    @Override
    public void clear() {
        root = null;
        totalRotations = 0;
    }

    @Override
    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(Node<T> node) {
        if (node == null) {
            return -1;
        }

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    @Override
    public int getTotalRotations() {
        return totalRotations;
    }

    private boolean isRed(Node<T> node) {
        return node != null && node.isRed;
    }

    private Node<T> rotateLeft(Node<T> h) {
        Node<T> x = h.right;

        h.right = x.left;
        x.left = h;

        x.isRed = h.isRed;
        h.isRed = true;

        totalRotations++;

        return x;
    }

    private Node<T> rotateRight(Node<T> h) {
        Node<T> x = h.left;

        h.left = x.right;
        x.right = h;

        x.isRed = h.isRed;
        h.isRed = true;

        totalRotations++;

        return x;
    }

    private void flipColors(Node<T> h) {
        h.isRed = true;
        h.left.isRed = false;
        h.right.isRed = false;
    }

    private Node<T> moveRedLeft(Node<T> node) {
        flipColors(node);
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColors(node);
        }
        return node;
    }

    private Node<T> moveRedRight(Node<T> node) {
        flipColors(node);
        if (isRed(node.left.left)) {
            node = rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    private Node<T> deleteMinNode(Node<T> node) {
        if (node.left == null) {
            return null;
        }

        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }

        node.left = deleteMinNode(node.left);

        return balance(node);
    }

    private Node<T> findMinNode(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node<T> balance(Node<T> node) {
        if (isRed(node.right)) {
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

}



