package AbstractData.StacksAndQueues;

public class FilaListaEncadeada {
    
    private int size;

    private NodeFila root;

    private NodeFila last;
    
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(Integer data) {

        size++;

        if (root == null) {
            root = new NodeFila(data);
            last = root;
            return;
        }

        last.next = new NodeFila(data);
        last.next.previous = last;
        last = last.next;
    }

    public Integer dequeue() {

        NodeFila aux = root;

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


}
