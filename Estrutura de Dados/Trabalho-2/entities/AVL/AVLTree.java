package entities.AVL;

import entities.BaseTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AVLTree<T extends Comparable<T>> extends BaseTree<T> {
    private Node<T> root;

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

    private int height(Node<T> node) {
        if (node == null)
            return 0;
        return node.height;
    }

    private int getBalance(Node<T> node) {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }

    private Node<T> rotateRight(Node<T> y) {
        Node<T> x = y.left;
        Node<T> T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        totalRotations++;

        return x;
    }

    private Node<T> rotateLeft(Node<T> x) {
        Node<T> y = x.right;
        Node<T> T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        totalRotations++;

        return y;
    }

    private Node<T> insert(Node<T> node, T value) {
        if (node == null) return new Node<T>(value);

        Stack<Node<T>> stack = new Stack<>();
        stack.push(node);

        while (true) {
            Node<T> current = stack.pop();

            if (value.compareTo(current.value) < 0) {
                if (current.left == null) {
                    current.left = new Node<T>(value);
                    break;
                } else {
                    stack.push(current.left);
                }
            } else if (value.compareTo(current.value) > 0) {
                if (current.right == null) {
                    current.right = new Node<T>(value);
                    break;
                } else {
                    stack.push(current.right);
                }
            } else {
                return node; // Chaves iguais não são permitidas na árvore
            }
        }

        while (!stack.isEmpty()) {
            Node<T> current = stack.pop();
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
                Node<T> parent = stack.peek();
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
