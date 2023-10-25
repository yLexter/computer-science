package entities;

public class AVLTree<T extends Comparable<T>> extends BaseTree<T> {
    private Node<T> root;
    private int totalRotations;
    private int totalDoubleRotation;
    private static class Node<T> {
        T value;
        Node<T> left, right;
        int height;

        Node(T value) {
            this.value = value;
            this.height = 0;
            left = null;
            right = null;
        }
    }

    public AVLTree() {
        super("Árvore AVL");
        root = null;
        totalRotations = 0;
        totalDoubleRotation = 0;
    }

    @Override
    public void insert(T value) {
        root = insert(root, value);
    }

    private Node<T> insert(Node<T> node, T value) {
        if (node == null) {
            return new Node<>(value);
        }

        int compareResult = value.compareTo(node.value);

        if (compareResult < 0) {
            node.left = insert(node.left, value);
        } else if (compareResult > 0) {
            node.right = insert(node.right, value);
        } else {
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balanceFactor = getBalanceFactor(node);

        if (balanceFactor > 1 && value.compareTo(node.left.value) < 0) {
            return rotateRight(node);
        }

        if (balanceFactor < -1 && value.compareTo(node.right.value) > 0) {
            return rotateLeft(node);
        }

        if (balanceFactor > 1 && value.compareTo(node.left.value) > 0) {
            totalDoubleRotation++;
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balanceFactor < -1 && value.compareTo(node.right.value) < 0) {
            totalDoubleRotation++;
            node.right = rotateRight(node.right);
            return rotateLeft(node);
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

        int compareResult = value.compareTo(node.value);

        if (compareResult < 0) {
            node.left = remove(node.left, value);
        } else if (compareResult > 0) {
            node.right = remove(node.right, value);
        } else {

            if ((node.left == null) || (node.right == null)) {
                Node<T> temp;

                if (node.left != null)
                    temp = node.left;
                else
                    temp = node.right;

                if (temp == null) {
                    temp = node;
                    node = null;
                } else
                    node = temp;
            } else {

                Node<T> temp = minValueNode(node.right);

                node.value = temp.value;

                node.right = remove(node.right, temp.value);
            }
        }

        if (node == null)
            return null;

        node.height = Math.max(height(node.left), height(node.right)) + 1;

        int balanceFactor = getBalanceFactor(node);

        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            return rotateRight(node);

        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
            return rotateLeft(node);

        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    @Override
    public boolean search(T value) {
        Node<T> current = root;

        while (current != null && !current.value.equals(value)) {

            int compareResult = value.compareTo(current.value);

            if (compareResult < 0)
                current = current.left;
            else
                current = current.right;
        }

        return current != null;
    }

    @Override
    public void clear() {
        root = null;
        totalRotations = 0;
        totalDoubleRotation = 0;
    }

    @Override
    public int getHeight() {
        return height(root);
    }


    @Override
    public int getTotalRotations() {
        return totalRotations;
    }

    @Override
    public int getTotalDoubleRotations() {
        return totalDoubleRotation;
    }

    private int height(Node<T> n) {
        if (n == null) {
            return -1;
        } else {
            return n.height;
        }
    }

    private int getBalanceFactor(Node<T> n) {
        if (n == null) {
            return 0;
        } else {
            return height(n.left) - height(n.right);
        }


    }

    private Node<T> rotateLeft(Node<T> y) {
        Node<T> x = y.right;
        Node<T> T2 = x.left;

        totalRotations++;

        x.left = y;
        y.right = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private Node<T> rotateRight(Node<T> x) {
        Node<T> y = x.left;
        Node<T> T2 = y.right;

        y.right = x;
        x.left = T2;

        totalRotations++;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    private Node<T> minValueNode(Node<T> node) {
        Node<T> current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }


}
