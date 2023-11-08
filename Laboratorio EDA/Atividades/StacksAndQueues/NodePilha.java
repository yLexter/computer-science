package AbstractData.StacksAndQueues;

public class NodePilha {

    public NodePilha next;

    public NodePilha previous;

    public String data;

    public NodePilha(String data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

}
