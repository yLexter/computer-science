package AbstractData;

interface ILinkedList<T> {
    void insertAtBeginning(T element);
    void removeFromStart();
}


public class SingleLinkedList<T> implements ILinkedList<T>{

    private CustomNode<T> root;

    public static class CustomNode<T> {

        private CustomNode<T> next;

        private T element;

        public CustomNode(T element) {
            this.element = element;
            this.next = null;
        }

        public CustomNode<T> getNext() {
            return next;
        }

        public T getElement() {
            return element;
        }

    }

    public CustomNode<T> getRoot() {
        return root;
    }

    @Override
    public void insertAtBeginning(T element) {

        CustomNode<T> temp;

        if (root == null) {
            root = new CustomNode<T>(element);
            return;
        }

        temp = new CustomNode<T>(element);
        temp.next = root;

        root = temp;
    }

    @Override
    public void removeFromStart() {

        if (root == null) {
            return;
        }

        root = root.next;
    }
    
}

