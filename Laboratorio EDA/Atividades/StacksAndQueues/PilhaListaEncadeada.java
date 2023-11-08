package AbstractData.StacksAndQueues;

public class PilhaListaEncadeada {

    private int size;

    private NodePilha last;

    private NodePilha root;

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(String data) {

        size++;

        if (root == null) {
            root = new NodePilha(data);
            last = root;
            return;
        }

        last.next = new NodePilha(data);
        last.next.previous = last;
        last = last.next;

    }

    public String pop() {

        NodePilha node = last;

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

    public int size() {
        return size;
    }

}
