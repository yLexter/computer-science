package AbstractData.StacksAndQueues;

public class PilhaArray {

    private final Vector<Integer> vector;

    public PilhaArray() {
        this.vector = new Vector<>();
    }

    public PilhaArray(int size) {
        this.vector = new Vector<>(size);
    }

    public void push(Integer data) {
        vector.insert(data);
    }

    public Integer pop() {
        return vector.pop();
    }

    public int size() {
        return vector.getSize();
    }

    public boolean isEmpty() {
        return vector.isEmpty();
    }


}
