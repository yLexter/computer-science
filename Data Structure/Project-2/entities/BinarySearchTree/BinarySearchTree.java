package entities.BinarySearchTree;

import entities.BaseTree;

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> extends BaseTree<T> {
    private Node<T> root;
    public BinarySearchTree() {
        super("Árvore Binária de Busca");
        root = null;
    }

    @Override
    public void insert(T valor) {
        Node<T> newNode = new Node<>(valor);

        if (root == null) {
            root = newNode;
        } else {
            Node<T> current = root;
            Node<T> parent;

            while (true) {
                parent = current;
                if (valor.compareTo(current.value) < 0) {
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }

        }

    }
    @Override
    public boolean search(T valor) {
        Node<T> current = root;

        while (current != null) {
            int cmp = valor.compareTo(current.value);
            if (cmp == 0) {
                return true;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }
    @Override
    public void clear() {
        root = null;
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

}
