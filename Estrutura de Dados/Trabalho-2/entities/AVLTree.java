package entities;

public class AVLTree<T extends Comparable<T>> extends BaseTree<T> {

    private static class Node<T> {
        public T value;
        public Node<T> left;
        public Node<T> right;
        public int height;
        public Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    private Node<T> root;

    public AVLTree(String name) {
        super(name);
    }

    // Implementação dos métodos da interface ITreeOperations<T> aqui

    public void insert(T value) {
        root = insertRec(root, value);
    }

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

        // Atualiza a altura do nó atual
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Verifica o equilíbrio do nó e realiza rotações, se necessário
        int balance = getBalance(node);

        // Rotação simples à direita
        if (balance > 1 && value.compareTo(node.left.value) < 0) {
            return rotateRight(node);
        }

        // Rotação simples à esquerda
        if (balance < -1 && value.compareTo(node.right.value) > 0) {
            return rotateLeft(node);
        }

        // Rotação dupla à direita-esquerda
        if (balance > 1 && value.compareTo(node.left.value) > 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Rotação dupla à esquerda-direita
        if (balance < -1 && value.compareTo(node.right.value) < 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    public boolean search(T value) {
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
            if ((node.left == null) || (node.right == null)) {
                Node<T> temp = null;
                if (temp == node.left) {
                    temp = node.right;
                } else {
                    temp = node.left;
                }

                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                Node<T> temp = minValueNode(node.right);
                node.value = temp.value;
                node.right = removeRec(node.right, temp.value);
            }
        }

        if (node == null) {
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // Rotação simples à direita
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rotateRight(node);
        }

        // Rotação simples à esquerda
        if (balance < -1 && getBalance(node.right) <= 0) {
            return rotateLeft(node);
        }

        // Rotação dupla à direita-esquerda
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Rotação dupla à esquerda-direita
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    private Node<T> minValueNode(Node<T> node) {
        Node<T> current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private int height(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalance(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private Node<T> rotateRight(Node<T> y) {
        Node<T> x = y.left;
        Node<T> T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private Node<T> rotateLeft(Node<T> x) {
        Node<T> y = x.right;
        Node<T> T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    public void clear() { root = null; }

}
