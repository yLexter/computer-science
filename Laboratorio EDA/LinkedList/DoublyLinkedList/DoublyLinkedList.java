package AbstractData;

public class DoublyLinkedList<T>  {

    private CustomNode<T> root;

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

    public void insertAtBeninnig(T element) {

        CustomNode<T> node;

        if (root == null) {
            root = new CustomNode<T>(element);
            return;
        }

        node = new CustomNode<T>(element);

        root.previous = node;
        node.next = root;
        root = node;
    }

    public void insert(T element) {

        CustomNode<T> prev = root, node;

        if (root == null) {
            root =  new CustomNode<T>(element);
            return;
        }

        while (prev.next != null) {
            prev = prev.next;
        }


        node = new CustomNode<T>(element);
        node.previous = prev;
        prev.next = node;
    }

    public void removeFromEnd() {

        CustomNode<T> prev = root, node;

        while (prev.next != null) {
            prev = prev.next;
        }

        prev.previous.next = null;
    }

    public CustomNode<T> getRoot() {
        return root;
    }

}
