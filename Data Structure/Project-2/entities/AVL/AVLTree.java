package entities.AVL;

import entities.BaseTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AVLTree<T extends Comparable<T>> extends BaseTree<T> {
    private Node<T> root;

    private int size = 0;

    private enum ChildType {
        LEFT, RIGHT
    }

    public AVLTree() {
        super("Árvore AVL");
        root = null;
        totalRotations = 0;
        totalDoubleRotation = 0;
    }

    @Override
    public boolean search(T data) {
        Node<T> current = root;

        while (current != null && !current.data.equals(data)) {

            int compareResult = data.compareTo(current.data);

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
        clearRotations();
    }

    @Override
    public int getHeight() {
        if (root == null) {
            return -1;
        }

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        int height = -1;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            height++;

            for (int i = 0; i < levelSize; i++) {
                Node<T> node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        return height;
    }

    @Override
    public void insert(T data) {
        root = insert(root, data);
    }

    private int height(Node<T> node) {
        return (node != null) ? node.height : 0;
    }

    private int getBalanceFactor(Node<T> node) {
        return (node != null) ? height(node.left) - height(node.right) : 0;
    }

    private void updateHeight(Node<T> node) {
        if (node != null) {
            node.height = Math.max(height(node.left), height(node.right)) + 1;
        }
    }

    private Node<T> rotateRight(Node<T> y) {
        Node<T> x = y.left;
        Node<T> T2 = x.right;

        x.right = y;
        y.left = T2;

        totalRotations++;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private Node<T> rotateLeft(Node<T> x) {
        Node<T> y = x.right;
        Node<T> T2 = y.left;

        y.left = x;
        x.right = T2;

        totalRotations++;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    private Node<T> insert(Node<T> node, T data) {

        if (node == null) {
            return new Node<T>(data);
        }

        if (data.compareTo(node.data) < 0) {
            node.left = insert(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = insert(node.right, data);
        } else {
            return node;
        }

        updateHeight(node);

        int balance = getBalanceFactor(node);

        if (balance > 1 && data.compareTo(node.left.data) < 0) {
            return rotateRight(node);
        }

        if (balance < -1 && data.compareTo(node.right.data) > 0) {
            return rotateLeft(node);
        }

        if (balance > 1 && data.compareTo(node.left.data) > 0) {
            node.left = rotateLeft(node.left);
            totalDoubleRotation++;
            return rotateRight(node);
        }

        if (balance < -1 && data.compareTo(node.right.data) < 0) {
            node.right = rotateRight(node.right);
            totalDoubleRotation++;
            return rotateLeft(node);
        }

        return node;
    }

}
