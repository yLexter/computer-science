package entities.BinarySearchTree;

public class Node <T extends Comparable<T>> {
    public T value;
    public Node<T> left;
    public Node<T> right;
    public Node<T> parent;
    public Node(T value) {
        this.value = value;
        left = null;
        right = null;
        parent = null;
    }


}
