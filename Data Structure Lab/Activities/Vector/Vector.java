package Algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

interface IVector<T> {
    T search(T element);

    T min();

    T max();

    T sucessor(T element);

    T predecessor(T element);

    void insert(T element);

    void delete(T element);
}

class VectorTest {

    private Vector vector;

    @BeforeEach
    public void start() {
        vector = new Vector();
    }

    @Test
    public void testInsert() {

        for (int x = 0; x < Vector.INITAL_SIZE; x++)
            vector.insert(x);

        assertEquals(Vector.INITAL_SIZE, vector.getSize(), "Tamanho do vetor após a inserção inicial.");

        assertEquals(9, vector.getVector()[9], "Valor na posição 9 após a inserção.");

        vector.insert(42, 5);
        assertEquals(42, vector.getVector()[5], "Valor inserido na posição 5.");

        vector.insert(52, 0);
        assertEquals(52, vector.getVector()[0], "Valor inserido na posição 0.");

        vector.insert(52, vector.getSize());
        assertEquals(52, vector.getVector()[vector.getSize() - 1], "Valor inserido na última posição do vetor.");

        assertThrows(IllegalArgumentException.class, () -> {
            vector.insert(100, vector.getSize() + 1);
        }, "Inserção em posição inválida (fora dos limites).");

        assertThrows(IllegalArgumentException.class, () -> {
            vector.insert(100, -1);
        }, "Inserção em posição inválida (posição negativa).");
    }

    @Test
    public void testDelete() {
        assertThrows(IllegalStateException.class, () -> {
            vector.delete(100);
        }, "Exclusão de um valor de um vetor vazio.");

        vector.insert(50);
        vector.insert(30);
        vector.insert(42);

        vector.delete(30);
        assertEquals(2, vector.getSize(), "Tamanho do vetor após a exclusão de um valor.");
        assertEquals(42, vector.getVector()[1], "Valor após a exclusão de um elemento.");

        assertThrows(IllegalArgumentException.class, () -> {
            vector.delete(100);
        }, "Exclusão de um valor que não existe no vetor.");
    }

    @Test
    public void testPop() {

        assertThrows(IllegalStateException.class, () -> {
            vector.pop();
        }, "Pop de um vetor vazio.");

        assertThrows(IllegalStateException.class, () -> {
            vector.pop(100);
        }, "Pop de uma posição inválida (fora dos limites).");

        for (int x = 0; x < Vector.INITAL_SIZE; x++)
            vector.insert(x);

        vector.pop();
        assertNull(vector.getVector()[vector.getSize()], "Pop de um elemento do vetor.");

        vector.pop(vector.getSize() - 1);
        assertNull(vector.getVector()[vector.getSize() + 1], "Pop de um elemento da última posição do vetor.");

        vector.pop(0);
        assertEquals(1, vector.getVector()[0], "Pop de um elemento da primeira posição do vetor.");

        vector.pop(3);
        assertEquals(5, vector.getVector()[3], "Pop de um elemento de uma posição intermediária do vetor.");

        assertThrows(IllegalArgumentException.class, () -> {
            vector.pop(-1);
        }, "Pop de uma posição inválida (posição negativa).");

        assertThrows(IllegalArgumentException.class, () -> {
            vector.pop(vector.getSize());
        }, "Pop de uma posição inválida (fora dos limites).");
    }

    @Test
    public void testSearch() {

        assertThrows(IllegalStateException.class, () -> {
            vector.search(100);
        }, "Buscar em um vetor vazio.");

        vector.insert(52);

        assertNull(vector.search(42), "Buscar por um valor que não existe no vetor.");

        vector.insert(72);
        vector.insert(102);
        vector.insert(42);

        assertEquals(42, vector.search(42), "Buscar por um valor que existe no vetor.");
    }

    @Test
    public void testMax() {
        assertThrows(IllegalStateException.class, () -> {
            vector.max();
        }, "Encontrar o máximo com o vetor vazio.");

        vector.insert(42);
        vector.insert(10);
        vector.insert(99);

        assertEquals(99, vector.max(), "Encontrar o valor máximo no vetor.");
    }

    @Test
    public void testMin() {

        assertThrows(IllegalStateException.class, () -> {
            vector.min();
        }, "Encontrar o mínimo em um vetor vazio.");

        vector.insert(42);
        vector.insert(10);
        vector.insert(99);

        assertEquals(10, vector.min(), "Encontrar o valor mínimo no vetor.");
    }

    @Test
    public void testPredecessor() {
        assertThrows(IllegalStateException.class, () -> {
            vector.predecessor(100);
        }, "Encontrar o predecessor em um vetor vazio.");

        vector.insert(42);
        vector.insert(10);

        assertEquals(42, vector.predecessor(10), "Encontrar o predecessor de 10.");
        assertNull(vector.predecessor(42), "Encontrar o predecessor de 42 (não deve existir).");
    }

    @Test
    public void testSuccessor() {

        assertThrows(IllegalStateException.class, () -> {
            vector.sucessor(100);
        }, "Encontrar o sucessor em um vetor vazio.");

        vector.insert(30);
        vector.insert(100);

        assertEquals(100, vector.sucessor(30), "Encontrar o sucessor de 30.");
        assertNull(vector.sucessor(100), "Encontrar o sucessor de 100 (não deve existir).");
    }

}

class Vector implements IVector<Integer> {

    private static final int NOT_FOUND_INDEX = -1;
    public static final int INITAL_SIZE = 100;

    private int size;

    private Integer[] vector;

    public Vector() {
        this.vector = new Integer[INITAL_SIZE];
    }

    public Integer[] getVector() {
        return vector;
    }

    private boolean isInvalidIndex(int index) {
        return index < 0 || index >= size;
    }

    private void checkIfVectorIsEmpty() {
        if (size <= 0)
            throw new IllegalStateException("Lista Vazia");
    }

    private boolean isFull() {
        return vector.length == size;
    }

    private void duplicate() {
        int n = vector.length;
        double size = n >= 1000 ? (n + (n * 0.1)) : n + INITAL_SIZE;
        int sizeInt = (int) size;

        Integer[] copyVector = new Integer[sizeInt];
        System.arraycopy(vector, 0, copyVector, 0, vector.length);
        this.vector = copyVector;
    }

    private int findByIndex(Integer element) {

        for (int x = 0; x < size; x++) {
            if (vector[x].equals(element))
                return x;
        }

        return NOT_FOUND_INDEX;
    }

    public int getSize() {
        return size;
    }

    public void show() {

        for (int x = 0; x < size; x++)
            System.out.printf(vector[x] + " ");

        System.out.println();
    }

    public Integer predecessor(Integer element) {

        checkIfVectorIsEmpty();

        int index = findByIndex(element);

        if (index == NOT_FOUND_INDEX)
            throw new IllegalArgumentException("Elemento não está na lista");

        if (index <= 0)
            return null;

        return vector[index - 1];
    }

    public Integer sucessor(Integer element) {

        checkIfVectorIsEmpty();

        int index = findByIndex(element);

        if (index == NOT_FOUND_INDEX)
            throw new IllegalArgumentException("Elemento não está na lista");

        if (index >= size)
            return null;

        return vector[index + 1];
    }

    public Integer max() {

        checkIfVectorIsEmpty();

        Integer maxValue = vector[0], data;

        for (int x = 0; x < size; x++) {

            data = vector[x];

            if (data > maxValue)
                maxValue = data;

        }

        return maxValue;
    }

    public Integer min() {

        checkIfVectorIsEmpty();

        Integer minValue = vector[0], data;

        for (int x = 0; x < size; x++) {

            data = vector[x];

            if (data.longValue() < minValue.longValue())
                minValue = data;

        }

        return minValue;

    }

    public Integer pop(int index) {

        checkIfVectorIsEmpty();

        Integer next, temp = null, value;

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

    public Integer pop() {
        checkIfVectorIsEmpty();

        Integer element = vector[size - 1];

        vector[size - 1] = null;
        size--;

        return element;
    }

    public void delete(Integer element) {
        checkIfVectorIsEmpty();

        int index = findByIndex(element);

        if (index == NOT_FOUND_INDEX)
            throw new IllegalArgumentException("Elemento não está na lista");

        pop(index);
    }

    public void insert(Integer element, int index) {

        Integer prev = null, temp = null;

        if (isFull()) {
            duplicate();
        }

        if (index == size) {
            insert(element);
            return;
        }

        if (isInvalidIndex(index))
            throw new IllegalArgumentException("Posição invalida");

        for (int x = index; x <= size; x++) {
            temp = vector[x];
            vector[x] = prev;
            prev = temp;
        }

        vector[index] = element;
        size++;

    }

    public void insert(Integer element) {

        if (isFull()) {
            duplicate();
        }

        vector[size] = element;
        size++;
    }

    public Integer search(Integer element) {

        checkIfVectorIsEmpty();

        for (int x = 0; x < size; x++) {

            Integer elem = vector[x];

            if (elem.equals(element))
                return elem;

        }

        return null;
    }

    public static void main(String[] args) {

    }

}
