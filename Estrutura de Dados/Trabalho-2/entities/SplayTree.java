package entities;

public class SplayTree<T extends Comparable<T>> extends BaseTree<T> {
    private static class Node<T> {
        public T value;
        public Node<T> left;
        public Node<T> right;
        public Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node<T> root;

    private String name;

    public SplayTree(String name) {
        super(name);
    }

    public void insert(T value) {
        root = insertRec(root, value);
    }

    public void clear() { root = null; }

    private Node<T> insertRec(Node<T> node, T value) {
        if (node == null) {
            return new Node<>(value);
        }

        if (value.compareTo(node.value) < 0) {
            node.left = insertRec(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            node.right = insertRec(node.right, value);
        } else {
            // Valor já existe, não é inserido novamente.
            return node;
        }

        // Realiza as operações de Splay após a inserção
        return splay(node, value);
    }

    public boolean search(T value) {
        root = splay(root, value);
        return searchRec(root, value);
    }

    private boolean searchRec(Node<T> node, T value) {
        if (node == null) {
            return false;
        }

        if (value.equals(node.value)) {
            return true;
        }

        if (value.compareTo(node.value) < 0) {
            return searchRec(node.left, value);
        }

        return searchRec(node.right, value);
    }

    public void remove(T value) {
        root = removeRec(root, value);
    }

    private Node<T> removeRec(Node<T> node, T value) {
        if (node == null) {
            return null;
        }

        if (value.compareTo(node.value) < 0) {
            node.left = removeRec(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            node.right = removeRec(node.right, value);
        } else {
            // Valor encontrado, remove o nó
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            node.value = minValue(node.right);
            node.right = removeRec(node.right, node.value);
        }

        // Realiza as operações de Splay após a remoção
        return splay(node, value);
    }

    private T minValue(Node<T> node) {
        T minValue = node.value;
        while (node.left != null) {
            minValue = node.left.value;
            node = node.left;
        }
        return minValue;
    }

    private Node<T> splay(Node<T> node, T value) {
        if (node == null || node.value.equals(value)) {
            return node;
        }

        if (value.compareTo(node.value) < 0) {
            if (node.left == null) {
                return node;
            }

            if (value.compareTo(node.left.value) < 0) {
                // Zig-Zig (Rotação à direita em ambos os lados)
                node.left.left = splay(node.left.left, value);
                node = rotateRight(node);
            } else if (value.compareTo(node.left.value) > 0) {
                // Zig-Zag (Rotação à esquerda, seguida de rotação à direita)
                node.left.right = splay(node.left.right, value);
                if (node.left.right != null) {
                    node.left = rotateLeft(node.left);
                }
            }

            return (node.left == null) ? node : rotateRight(node);
        } else {
            if (node.right == null) {
                return node;
            }

            if (value.compareTo(node.right.value) < 0) {
                // Zag-Zig (Rotação à direita, seguida de rotação à esquerda)
                node.right.left = splay(node.right.left, value);
                if (node.right.left != null) {
                    node.right = rotateRight(node.right);
                }
            } else if (value.compareTo(node.right.value) > 0) {
                // Zag-Zag (Rotação à esquerda em ambos os lados)
                node.right.right = splay(node.right.right, value);
                node = rotateLeft(node);
            }

            return (node.right == null) ? node : rotateLeft(node);
        }
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> x = node.right;
        node.right = x.left;
        x.left = node;
        return x;
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> x = node.left;
        node.left = x.right;
        x.right = node;
        return x;
    }
}

