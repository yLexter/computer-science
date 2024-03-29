package AbstractData;

class BaseVector <T> {

    private static final int NOT_FOUND_INDEX = -1;

    public static final int INITAL_SIZE = 100;

    private int size;

    private T[] vector;

    public BaseVector() {
        this.vector =  (T []) new Object[INITAL_SIZE];
    }

    private boolean isInvalidIndex(int index) {
        return index < 0 || index >= size;
    }

    private void checkIfVectorIsEmpty() {
        if (size <= 0)
            throw new IllegalStateException ("Lista Vazia");
    }

    private boolean isFull() {
        return vector.length == size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    private void duplicate() {
        int n = vector.length;
        double size = n >= 1000 ? (n + (n * 0.1)) : n + INITAL_SIZE;
        int sizeInt = (int) size;

        T[] copyVector = (T []) new Object[sizeInt];
        System.arraycopy(vector, 0, copyVector, 0, vector.length);
        this.vector = copyVector;
    }

    public int getSize() {
        return size;
    }

    public T pop(int index) {

        checkIfVectorIsEmpty();

        T next, temp = null, value;

        if (isInvalidIndex(index))
            throw new IllegalArgumentException("Posição invalida");

        if (index == size - 1)
            return pop();

        value = vector[index];
        next = vector[index + 1];

        for (int x = index + 1; x < size; x++) {

            if (x == size - 1) {
                temp = null;
            } else {
                temp = vector[x + 1];
            }

            vector[x - 1] = next;
            next = temp;
        }

        vector[size - 1] = null;
        size--;

        return value;
    }

    public T pop() {
        checkIfVectorIsEmpty();

        T element = vector[size - 1];

        vector[size - 1] = null;
        size--;

        return element;
    }

    public void insert(T element) {

        if (isFull()) {
            duplicate();
        }

        vector[size] = element;
        size++;
    }

}
