package entities.RedBlackTree;

import entities.BaseTree;

import java.util.LinkedList;
import java.util.Queue;

public class RedBlackTree<T extends Comparable<T>> extends BaseTree<T> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node<T> root;

    public RedBlackTree() {
        super("Árvore Preto e Vermelho");
        root = null;
    }

    @Override
    public void insert(T valor) {
        Node<T> nodeInsert = new Node<T>(valor, RED);

        if (root == null) {
            root = nodeInsert;
        } else {
            Node<T> parent = null;
            Node<T> current = root;

            while (current != null) {
                parent = current;
                int cmp = valor.compareTo(current.value);
                if (cmp < 0) {
                    current = current.left;
                } else if (cmp > 0) {
                    current = current.right;
                } else {
                    return; // Valor já existe na árvore
                }
            }

            nodeInsert.parent = parent;
            if (valor.compareTo(parent.value) < 0) {
                parent.left = nodeInsert;
            } else {
                parent.right = nodeInsert;
            }insertFixup(nodeInsert);
        }

        root.color = BLACK;
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

    private void insertFixup(Node<T> node) {

        boolean isDoubleRotateLeft = false;
        boolean isDoubleRotateRigth = false;

        while (node.parent != null && node.parent.color == RED) {

            if (node.parent == node.parent.parent.left) {
                Node<T> uncle = node.parent.parent.right;

                if (uncle != null && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                } else {

                    if (node == node.parent.right) {
                        node = node.parent;

                        if (isDoubleRotateLeft) {
                            totalDoubleRotation++;
                            isDoubleRotateLeft = false;
                        } else {
                            isDoubleRotateRigth = false;
                            isDoubleRotateLeft = true;
                        }

                        rotateLeft(node);
                    }

                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;

                    if (isDoubleRotateRigth) {
                        totalDoubleRotation++;
                        isDoubleRotateRigth = false;
                    } else {
                        isDoubleRotateRigth = true;
                        isDoubleRotateLeft = false;
                    }

                    rotateRight(node.parent.parent);
                }
            } else {
                Node<T> uncle = node.parent.parent.left;
                if (uncle != null && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;

                        if (isDoubleRotateRigth) {
                            totalDoubleRotation++;
                            isDoubleRotateRigth = false;
                        } else {
                            isDoubleRotateRigth = true;
                            isDoubleRotateLeft = false;
                        }

                        rotateRight(node);
                    }
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;

                    if (isDoubleRotateLeft) {
                        totalDoubleRotation++;
                        isDoubleRotateLeft = false;
                    } else {
                        isDoubleRotateRigth = false;
                        isDoubleRotateLeft = true;
                    }

                    rotateLeft(node.parent.parent);

                }
            }
        }
    }

    private void rotateLeft(Node<T> node) {
        Node<T> rightChild = node.right;
        node.right = rightChild.left;

        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }
        rightChild.parent = node.parent;
        if (node.parent == null) {
            root = rightChild;
        } else if (node == node.parent.left) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }
        rightChild.left = node;
        node.parent = rightChild;
        totalRotations++;
    }

    private void rotateRight(Node<T> node) {
        Node<T> leftChild = node.left;
        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }
        leftChild.parent = node.parent;
        if (node.parent == null) {
            root = leftChild;
        } else if (node == node.parent.right) {
            node.parent.right = leftChild;
        } else {
            node.parent.left = leftChild;
        }
        leftChild.right = node;
        node.parent = leftChild;
        totalRotations++;
    }



}



