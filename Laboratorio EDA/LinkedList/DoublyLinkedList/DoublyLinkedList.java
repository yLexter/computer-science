package AbstractData;

public class DoublyLinkedList<T> {

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

    public void remove(T value) {
        Node current = root, aux = null;

        if (root == null)
            return;

        if (root.element == value) {
            if (root.next != null) {
                root = root.next;
                root.previous = null;
            } else {
                root = null;
                tail = null;
            }
            return;
        }

        while (current != null) {
            if (current.element == value) {
                if (current.equals(tail)) {
                    tail = tail.previous;
                    tail.next = null;
                }
                aux = current.previous;
                aux.next = current.next;
            }
            current = current.next;
        }

    }

    public void insert(int value, int index) {
        CustomNode<T> current = root, node = new CustomNode<>(value), aux = null;
        int count = 0;

        if (index == 0) {
            insertAtBeginning(value);
            return;
        }

        while (current != null && count < index) {
            current = current.next;
            count++;
        }

        if (current == null && index >= count) {
            insertAtEnd(value);
            return;
        }

        current = current.previous;

        aux = current.next;
        current.next = node;
        node.previous = current;
        node.next = aux;
        aux.previous = node;
    }

}
