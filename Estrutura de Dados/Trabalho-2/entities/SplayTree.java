package entities;

public class SplayTree<T extends Comparable<T>> extends BaseTree<T> {
    private SplayNode<T> root;

    private int totalRotations;

    public SplayTree() {
        super("Árvore Splay");
        this.root = null;
    }

    @Override
    public void insert(T value) {
        root = splayInsert(root, value);
    }

    @Override
    public void remove(T value) {
        root = splayRemove(root, value);
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
    }

    @Override
    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(SplayNode<T> node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
    @Override
    public int getTotalRotations() {
        return totalRotations;
    }

    private SplayNode<T> splayInsert(SplayNode<T> node, T value) {
        if (node == null) {
            return new SplayNode<>(value);
        }

        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            node.left = splayInsert(node.left, value);
            node = splayRightRotate(node);
        } else if (cmp > 0) {
            node.right = splayInsert(node.right, value);
            node = splayLeftRotate(node);
        }

        return node;
    }

    private SplayNode<T> splayRemove(SplayNode<T> node, T value) {
        if (node == null) {
            return null;
        }

        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            node.left = splayRemove(node.left, value);
        } else if (cmp > 0) {
            node.right = splayRemove(node.right, value);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            T minValue = minValue(node.right);
            node.value = minValue;
            node.right = splayRemove(node.right, minValue);
        }

        return node;
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

    private T minValue(SplayNode<T> node) {
        if (node.left == null) {
            return node.value;
        }
        return minValue(node.left);
    }

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
}

