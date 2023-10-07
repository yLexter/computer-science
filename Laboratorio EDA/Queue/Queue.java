package AbstractData;

public class Queue<T> {

    private int size;
    
    private CustomNode<T> root;

    private CustomNode<T> last;

    public class CustomNode<T> {
        private CustomNode<T> previous;

        private CustomNode<T> next;

        private T data;

        public CustomNode(T data) {
            this.data = data;
            this.next = null;
            this.previous = null;
        }

        public CustomNode<T> getPrevious() {
            return previous;
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

    public boolean isEmpty() {
        return size == 0;
    }

    public void enQueue(T data) {

        size++;

        if (root == null) {
            root = new CustomNode<T>(data);
            last = root;
            return;
        }

        last.next = new CustomNode<T>(data);
        last.next.previous = last;
        last = last.next;
    }

    public T deQueue() {

        CustomNode<T> aux = root;

        if (root == null)
            throw new IllegalStateException("Fila Vazia");

        size--;

        if (root.next == null) {
            root = null;
            return aux.data;
        }

        root = root.next;
        root.previous = null;

        return aux.data;
    }


    public void show() {

        CustomNode<T> aux = root;

        while(aux != null) {
            System.out.println("Data: " + aux.data);
            aux = aux.next;
        }

    }
    
    public CustomNode<T> getLast() {
        return last;
    }

    public CustomNode<T> getRoot() {
        return root;
    }

    public static void main(String[] args) {

    }



}
