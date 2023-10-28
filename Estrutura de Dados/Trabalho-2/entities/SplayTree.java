package entities;

import java.util.LinkedList;
import java.util.Queue;

public class SplayTree<T extends Comparable<T>> extends BaseTree<T> {
    private SplayNode<T> root;

    private static class SplayNode<T> {
        T value;
        SplayNode<T> left, right;
        int height;

        SplayNode(T value) {
            this.value = value;
            this.height = 1;
            this.left = null;
            this.right = null;
        }
    }

    public SplayTree() {
        super("Árvore Splay");
        this.root = null;
    }

    @Override
    public void insert(T value) {
        root = insert(root, value);
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

        Queue<SplayNode<T>> queue = new LinkedList<>();
        queue.add(root);

        int height = -1;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            height++;

            for (int i = 0; i < levelSize; i++) {
                SplayNode<T> node = queue.poll();
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
    public boolean search(T value) {

        if (root == null) {
            return false;
        }

        SplayNode<T> currentNode = root;
        SplayNode<T> prevNode = null;

        while (currentNode != null && !currentNode.value.equals(value)) {

            prevNode = currentNode;

            if (value.compareTo(currentNode.value) < 0) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }

        if (currentNode == null) {
            root = splay(root, prevNode, value);
            return false;
        } else {
            root = splay(root, currentNode, value);
            return true;
        }
    }

    private SplayNode<T> splay(SplayNode<T> root, SplayNode<T> targetNode, T value) {
        if (targetNode == null) {
            return root;
        }

        while (root != targetNode) {
            if (value.compareTo(root.value) < 0) {
                if (root.left != null && value.compareTo(root.left.value) < 0) {
                    root = rotateRight(root);
                }
                if (root.left == null) {
                    break;
                }
                root = rotateRight(root);
            } else {
                if (root.right != null && value.compareTo(root.right.value) > 0) {
                    root = rotateLeft(root);
                }
                if (root.right == null) {
                    break;
                }
                root = rotateLeft(root);
            }
        }

        return root;
    }

    private int height(SplayNode<T> node) {
        if (node == null) return 0;
        return node.height;
    }

    private SplayNode<T> rotateRight(SplayNode<T> y) {
        SplayNode<T> x = y.left;
        SplayNode<T> T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        totalRotations++;

        return x;
    }

    private SplayNode<T> rotateLeft(SplayNode<T> x) {
        SplayNode<T> y = x.right;
        SplayNode<T> T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        totalRotations++;

        return y;
    }

    private SplayNode<T> insert(SplayNode<T> node, T value) {

        if (node == null)
            return new SplayNode<>(value);

        SplayNode<T> dummy = new SplayNode<>(null);
        dummy.left = dummy.right = null;
        SplayNode<T> leftMax = dummy;
        SplayNode<T> rightMin = dummy;

        boolean isDoubleRotateLeft = false;
        boolean isDoubleRotateRigth = false;

        while (true) {

            if (value.compareTo(node.value) < 0) {

                if (node.left == null) {
                    node.left = new SplayNode<>(value);
                    break;
                }

                if (value.compareTo(node.left.value) < 0) {

                    if (isDoubleRotateRigth) {
                        totalDoubleRotation++;
                        isDoubleRotateRigth = false;
                    } else {
                        isDoubleRotateRigth = true;
                        isDoubleRotateLeft = false;
                    }

                    node = rotateRight(node);
                    if (node.left == null) break;
                }

                if (rightMin == dummy) rightMin = node;
                node = node.left;

            } else if (value.compareTo(node.value) > 0) {

                if (node.right == null) {
                    node.right = new SplayNode<>(value);
                    break;
                }

                if (value.compareTo(node.right.value) > 0) {

                    if (isDoubleRotateLeft) {
                        totalDoubleRotation++;
                        isDoubleRotateLeft = false;
                    } else {
                        isDoubleRotateRigth = false;
                        isDoubleRotateLeft = true;
                    }

                    node = rotateLeft(node);
                    if (node.right == null) break;
                }
                if (leftMax == dummy) leftMax = node;
                node = node.right;
            } else {
                break;
            }

        }

        leftMax.right = node.left;
        rightMin.left = node.right;
        node.left = dummy.right;
        node.right = dummy.left;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        return node;
    }

}

