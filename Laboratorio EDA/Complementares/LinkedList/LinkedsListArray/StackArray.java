package AbstractData;

class StackArray<T>  {

    private final BaseVector<T> vector;

    public StackWithArray() {
        this.vector = new BaseVector<T>();
    }

    public void push(T data) {
        vector.insert(data);
    }

    public T pop() {
        return vector.pop();
    }

    public int getSize() {
        return vector.getSize();
    }

    public boolean isEmpty() {
        return vector.isEmpty();
    }


}


