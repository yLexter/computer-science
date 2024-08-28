package AbstractData.StacksAndQueues;

public class FilaArray {

    private final Vector<String> vector;

    public FilaArray() {
        this.vector = new Vector<>();
    }

    public FilaArray(int size) {
        this.vector = new Vector<>(size);
    }

    public void enqueue(String data) {
        vector.insert(data);
    }

    public String dequeue() {
        return vector.pop(0);
    }

    public int size() {
        return vector.getSize();
    }

    public boolean isEmpty() {
        return vector.isEmpty();
    }

}
