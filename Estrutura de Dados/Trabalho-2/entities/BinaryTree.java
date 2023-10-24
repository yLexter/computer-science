package entities;

import java.util.*;

public class BinaryTree<T extends Comparable<T>> extends BaseTree<T> {

    private static class Node<T> {

        public T value;
        public Node<T> left;
        public Node<T> right;

        public Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public T getValue() {
            return value;
        }


    }
    private Node<T> root;

    public BinaryTree(String name) {
        super(name);
    }

    public void clear() { root = null; }
    @Override
    public void insert(T value) {
        root = insertRec(root, value);
    }

    private Node<T> insertRec(Node<T> root, T value) {

        if (root == null) {
            root = new Node<T>(value);
            return root;
        }

        if (value.compareTo(root.getValue()) < 0) {
            root.left = insertRec(root.left, value);
        } else if (value.compareTo(root.getValue()) > 0) {
            root.right = insertRec(root.right, value);
        }

        return root;
    }

    @Override
    public boolean search(T value) {
        return searchRec(root, value);
    }

    private boolean searchRec(Node<T> root, T value) {
        if (root == null) {
            return false;
        }

        if (value.equals(root.getValue())) {
            return true;
        }

        if (value.compareTo(root.getValue()) < 0) {
            return searchRec(root.left, value);
        }

        return searchRec(root.right, value);
    }

    @Override
    public void remove(T value) {
        root = removeRec(root, value);
    }

    private Node<T> removeRec(Node<T> root, T value) {
        if (root == null) {
            return root;
        }

        if (value.compareTo(root.getValue()) < 0) {
            root.left = removeRec(root.left, value);
        } else if (value.compareTo(root.getValue()) > 0) {
            root.right = removeRec(root.right, value);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.value = minValue(root.right);
            root.right = removeRec(root.right, root.value);
        }

        return root;
    }

    private T minValue(Node<T> Node) {
        T minValue = Node.getValue();
        while (Node.left != null) {
            minValue = Node.left.getValue();
            Node = Node.left;
        }
        return minValue;
    }

    public List<T> toArrayList() {
        List<T> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result;
    }

    private void inOrderTraversal(Node<T> Node, List<T> list) {
        if (Node != null) {
            inOrderTraversal(Node.left, list);
            list.add(Node.getValue());
            inOrderTraversal(Node.right, list);
        }
    }

}