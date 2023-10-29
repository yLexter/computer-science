package entities.AVL;

public class Node<T> {
    public T value;
    public Node<T> left, right;
    public int height;
    public Node(T value) {
        this.value = value;
        this.height = 0;
        left = null;
        right = null;
    }
}
