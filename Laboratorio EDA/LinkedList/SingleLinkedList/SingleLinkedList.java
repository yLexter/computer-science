package AbstractData;

interface ILinkedList<T> {
    void insertAtBeginning(T data);
    void removeFromStart();
    void insert(T data);
    void remove(T data);
}


public class Main<T> implements ILinkedList<T> {

    private CustomNode<T> root;

    private CustomNode<T> tail;

    private int size;

    public static class CustomNode<T> {

        private CustomNode<T> next;

        private T data;

        public CustomNode(T data) {
            this.data = data;
            this.next = null;
        }

        public CustomNode<T> getNext() {
            return next;
        }

        public T getData() {
            return data;
        }

    }

    public int getSize() {
        return size;
    }

    public CustomNode<T> getRoot() {
        return root;
    }

    public void insert(T data) {
        CustomNode<T> node = new CustomNode<>(data);

        if (root == null) {
            root = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }

        size++;
    }

    @Override
    public void insertAtBeginning(T data) {

        if (root == null)
            throw new IllegalCallerException("Lista vazia");

        CustomNode<T> node = new CustomNode<T>(data);

        if (root == null) {
            root = node;
            tail = node;
        } else {
            root.next = node;
            root = node;
        }

        size++;
    }

    public void remove(T data) {

        CustomNode<T> current = root, prev = null;

        if (root == null)
            throw new IllegalCallerException("Lista vazia");

        if (root.data.equals(data)) {
            root = root.next;
            size--;
            return;
        }

        while (current != null) {

            if (current.data.equals(data)) {

                if (tail.data.equals(data)) {
                   tail = prev;  
                }    

                prev.next = current.next;
                size--;
                return;
            }

            prev = current;
            current = current.next;
        }
    }

    @Override
    public void removeFromStart() {

        if (root == null) 
            throw new IllegalCallerException("Lista vazia");

        root = root.next;
    }

    public void show() {

        CustomNode<T> current = root;

        if (current == null)
            throw new IllegalCallerException("Lista vazia");

        while (current != null) {
            System.out.printf("%s ", current.data);
            current = current.next;
        }

        System.out.println();
    }

    public CustomNode<T> search(T data) {
        CustomNode<T> current = root;

         if (current == null)
            throw new IllegalCallerException("Lista vazia");

         while(current != null && current.data.equals(data)) {
             current = current.next;
         }

         return current;
    }


    public static void main(String[] args) {


    }

}