package AbstractData;

class QueueArray<T>  {
    private final BaseVector<T> vector;

    public QueueWithArray() {
        this.vector = new BaseVector<T>();
    }

    public void enQueue(T data) {
        vector.insert(data);
    }

    public T deQueue() {
        return vector.pop(0);
    }

    public int getSize() {
        return vector.getSize();
    }

    public boolean isEmpty() {
        return vector.isEmpty();
    }

}
