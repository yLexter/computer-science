package AbstractData;

public class Stack<T> {

    private int size;

    private CustomNode<T> last;

    private CustomNode<T> root;

    public static class CustomNode<T> {
        private CustomNode<T> next;

        private CustomNode<T> previous;

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

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(T data) {

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

    public T pop() {

        CustomNode<T> node = last;

        if (root == null)
            throw new IllegalStateException("Pilha Vazia");

        size--;

        if (node.previous == null) {
            last = null;
            root = null;
            return node.data;
        }

        last.previous.next = null;
        last = last.previous;

        return node.data;
    }

    public void show() {

        CustomNode<T> aux = root;

        while(aux != null) {
            System.out.printf("%s " + aux.data);
            aux = aux.next;
        }
        
    }

    public CustomNode<T> getTop() {
        return last;
    }

    public int getSize() {
        return size;
    }

    public CustomNode<T> getRoot() {
        return root;
    }

    public static void main(String[] args) {

    }

}
