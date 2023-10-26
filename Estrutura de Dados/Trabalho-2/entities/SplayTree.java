package entities;

import java.util.LinkedList;
import java.util.Queue;

public class SplayTree<T extends Comparable<T>> extends BaseTree<T> {
    private SplayNode<T> root;

    private int totalRotations;

    private int totalDoubleRotation;

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
    public boolean search(T value) {
        root = splaySearch(root, value);
        return root != null && root.value.equals(value);
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
    public int getTotalRotations() {
        return totalRotations;
    }

    @Override
    public int getTotalDoubleRotations() {
        return totalDoubleRotation;
    }

    private SplayNode<T> splaySearch(SplayNode<T> node, T value) {
        if (node == null || node.value.equals(value)) {
            return node;
        }

        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            if (node.left == null) {
                return node;
            }

            int cmp2 = value.compareTo(node.left.value);
            if (cmp2 < 0) {
                node.left.left = splaySearch(node.left.left, value);
                node = splayRightRotate(node);
            } else if (cmp2 > 0) {
                node.left.right = splaySearch(node.left.right, value);
                if (node.left.right != null) {
                    node.left = splayLeftRotate(node.left);
                }
            }

            return (node.left == null) ? node : splayRightRotate(node);
        } else {
            if (node.right == null) {
                return node;
            }

            int cmp2 = value.compareTo(node.right.value);
            if (cmp2 < 0) {
                node.right.left = splaySearch(node.right.left, value);
                if (node.right.left != null) {
                    node.right = splayRightRotate(node.right);
                }
            } else if (cmp2 > 0) {
                node.right.right = splaySearch(node.right.right, value);
                node = splayLeftRotate(node);
            }

            return (node.right == null) ? node : splayLeftRotate(node);
        }
    }

    private SplayNode<T> splayLeftRotate(SplayNode<T> x) {
        SplayNode<T> y = x.right;
        x.right = y.left;
        y.left = x;
        totalRotations++;
        return y;
    }

    private SplayNode<T> splayRightRotate(SplayNode<T> y) {
        SplayNode<T> x = y.left;
        y.left = x.right;
        x.right = y;
        totalRotations++;
        return x;
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

        return x;
    }

    private SplayNode<T> rotateLeft(SplayNode<T> x) {
        SplayNode<T> y = x.right;
        SplayNode<T> T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    private SplayNode<T> insert(SplayNode<T> node, T value) {
        if (node == null) return new SplayNode<>(value);

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
                break; // Chaves iguais não são permitidas na árvore
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

