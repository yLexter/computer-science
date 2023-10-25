package entities;

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> extends BaseTree<T> {
    private TreeNode<T> root;
    private int totalRotations;

    public BinarySearchTree() {
        super("Árvore de Busca Binária");
        this.root = null;
        this.totalRotations = 0;
    }

    @Override
    public void insert(T value) {
        root = insertRec(root, value);
    }

    @Override
    public void remove(T value) {
        root = removeRec(root, value);
    }

    @Override
    public boolean search(T value) {
        return searchRec(root, value);
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

    @Override
    public int getTotalRotations() {
        return totalRotations;
    }

    @Override
    public int getTotalDoubleRotations() {
        return 0;
    }

    private TreeNode<T> insertRec(TreeNode<T> root, T value) {
        if (root == null) {
            return new TreeNode<>(value);
        }

        if (value.compareTo(root.data) < 0) {
            root.left = insertRec(root.left, value);
        } else if (value.compareTo(root.data) > 0) {
            root.right = insertRec(root.right, value);
        }

        return root;
    }

    private TreeNode<T> removeRec(TreeNode<T> root, T value) {
        if (root == null) {
            return root;
        }

        if (value.compareTo(root.data) < 0) {
            root.left = removeRec(root.left, value);
        } else if (value.compareTo(root.data) > 0) {
            root.right = removeRec(root.right, value);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.data = minValue(root.right);
            root.right = removeRec(root.right, root.data);
        }

        return root;
    }

    private T minValue(TreeNode<T> root) {
        T minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

    private boolean searchRec(TreeNode<T> root, T value) {
        if (root == null) {
            return false;
        }

        if (value.equals(root.data)) {
            return true;
        }

        if (value.compareTo(root.data) < 0) {
            return searchRec(root.left, value);
        }

        return searchRec(root.right, value);
    }

    private int getHeight(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    private static class TreeNode<T> {
        T data;
        TreeNode<T> left;
        TreeNode<T> right;

        TreeNode(T data) {
            this.data = data;
            left = null;
            right = null;
        }
    }
}
