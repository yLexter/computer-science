package AbstractData;

interface ILinkedList<T> {
    void insertAtBeginning(T element);
    void removeFromStart();
}


public class SingleLinkedList<T> implements ILinkedList<T>{

    private CustomNode root;

    public class CustomNode {
        private CustomNode next;

        private T element;

        public CustomNode(T element) {
            this.element = element;
            this.next = null;
        }

        public CustomNode getNext() {
            return next;
        }

        public T getElement() {
            return element;
        }

    }

    public CustomNode getRoot() {
        return root;
    }

    @Override
    public void insertAtBeginning(T element) {

        CustomNode temp;

        if (root == null) {
            root = new CustomNode(element);
            return;
        }

        temp = new CustomNode(element);
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

