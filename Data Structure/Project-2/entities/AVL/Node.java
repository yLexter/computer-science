package entities.AVL;

public class Node<T extends Comparable<T>> {
    public T data;
    public Node<T> left;
    public Node<T> right;
    public int height;
    public int balanceFactor;
    public Node(T data) {
        this.data = data;
    }


}
