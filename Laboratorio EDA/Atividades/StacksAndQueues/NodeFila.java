package AbstractData.StacksAndQueues;

public class NodeFila {

    public NodeFila previous;

    public NodeFila next;

    public int data;
    
    public NodeFila(int data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

}
