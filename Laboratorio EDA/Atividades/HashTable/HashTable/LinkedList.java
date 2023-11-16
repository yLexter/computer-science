package AbstractData.HashTable.HashTable;

public class LinkedList<T> {

    public Node<T> root;

    public Node<T> tail;

    public static class Node<T> {

        private Node<T> previous;

        private Node<T> next;

        private T element;

        public Node(T element) {
            this.element = element;
            this.next = null;
            this.previous = null;
        }

        public Node<T> getPrevious() {
            return previous;
        }

        public Node<T> getNext() {
            return next;
        }

        public T getElement() {
            return element;
        }

    }

    public void insert(T element) {

        Node<T> node = new Node<T>(element);

        if (root == null) {
            root = node;
            tail = node;
        } else {
            tail.next = node;
            node.previous = tail;
            tail = node;
        }

    }
    public boolean search(T data) {
        Node<T> current = root;
        
        while(current != null) {
            
            if (current.element.equals(data)) 
                return true;
                
            current = current.next;
        }

        return false;
    }

    public Node<T> getRoot() {
        return root;
    }
    public Node<T> getTail() {
        return tail;
    }

}