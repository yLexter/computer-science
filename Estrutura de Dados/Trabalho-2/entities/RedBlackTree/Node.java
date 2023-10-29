package entities.RedBlackTree;

public class Node<T> {
    public T value;
    public Node<T> left;
    public Node<T> right;
    public Node<T> parent;
    boolean color;
    public Node(T value, boolean color) {
        this.value = value;
        this.color = color;
        left = null;
        right = null;
        parent = null;
    }

}
