package entities;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AVLTree<T extends Comparable<T>> extends BaseTree<T> {
    private Node root;
    private int totalRotations;
    private int totalDoubleRotation;
    private class Node {
        T value;
        Node left, right;
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

    @Override
    public boolean search(T value) {
        Node current = root;

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
        if (root == null) {
            return -1;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int height = -1;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            height++;

            for (int i = 0; i < levelSize; i++) {
                Node node = queue.poll();
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
    public int getTotalRotations() {
        return totalRotations;
    }

    @Override
    public int getTotalDoubleRotations() {
        return totalDoubleRotation;
    }

    private int height(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    private int getBalance(Node node) {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        totalRotations++;

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        totalRotations++;

        return y;
    }

    private Node insert(Node node, T value) {
        if (node == null) return new Node(value);

        Stack<Node> stack = new Stack<>();
        stack.push(node);

        while (true) {
            Node current = stack.pop();

            if (value.compareTo(current.value) < 0) {
                if (current.left == null) {
                    current.left = new Node(value);
                    break;
                } else {
                    stack.push(current.left);
                }
            } else if (value.compareTo(current.value) > 0) {
                if (current.right == null) {
                    current.right = new Node(value);
                    break;
                } else {
                    stack.push(current.right);
                }
            } else {
                return node; // Chaves iguais não são permitidas na árvore
            }
        }

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            current.height = 1 + Math.max(height(current.left), height(current.right));

            int balance = getBalance(current);

            // Rotação à esquerda simples
            if (balance > 1 && value.compareTo(current.left.value) < 0) {
                current = rotateRight(current);
                totalRotations++;
            }

            // Rotação à direita simples
            if (balance < -1 && value.compareTo(current.right.value) > 0) {
                current = rotateLeft(current);
                totalRotations++;
            }

            // Rotação à esquerda-direita (dupla à direita)
            if (balance > 1 && value.compareTo(current.left.value) > 0) {
                current.left = rotateLeft(current.left);
                current = rotateRight(current);
                totalDoubleRotation++;
            }

            // Rotação à direita-esquerda (dupla à esquerda)
            if (balance < -1 && value.compareTo(current.right.value) < 0) {
                current.right = rotateRight(current.right);
                current = rotateLeft(current);
                totalDoubleRotation++;
            }

            if (stack.isEmpty()) {
                node = current;
            } else {
                Node parent = stack.peek();
                if (value.compareTo(parent.value) < 0) {
                    parent.left = current;
                } else {
                    parent.right = current;
                }
            }
        }

        return node;
    }
    

}
