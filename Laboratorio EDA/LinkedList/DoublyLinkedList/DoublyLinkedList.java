package AbstractData;

public class DoublyLinkedList<T>  {

    public CustomNode<T> root;

    public CustomNode<T> tail;

    public static class CustomNode<T> {

        private CustomNode<T> previous;

        private CustomNode<T> next;

        private T element;

        public CustomNode(T element) {
            this.element = element;
            this.next = null;
            this.previous = null;
        }

        public CustomNode<T> getPrevious() {
            return previous;
        }

        public CustomNode<T> getNext() {
            return next;
        }

        public T getElement() {
            return element;
        }

    }

    
    public void insertAtBeginning(T element) {

        CustomNode<T> node = new CustomNode<T>(element);

        if (root == null) {
            root = node;
            tail = node;
        } else {
            root.previous = node;
            node.next = root;
            root = node;
        }
    }

    public void insertAtEnd(T element) {

        CustomNode<T> node = new CustomNode<T>(element);

        if (root == null) {
            root = node;
            tail = node;
        } else {
            tail.next = node;
            node.previous = tail;
            tail = node;
        }

    }
    
    public void removeFromEnd() {

        if (root.next == null) {
            root = null;
            tail = null;
        } else {
            tail = tail.previous;
            tail.next = null;
        }

    }

    public void removeFromStart() {

        if (root.next == null) {
            root = null;
            tail = null;
        } else {
            root = root.next;
            root.previous = null;
        }

    }

    public CustomNode<T> getRoot() {
        return root;
    }

    public CustomNode<T> getTail() {
        return tail;
    }

}
