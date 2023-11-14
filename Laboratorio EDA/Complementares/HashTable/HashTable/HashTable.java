package AbstractData.HashTable.HashTable;

import java.util.List;
import java.util.function.Function;

public class HashTable  {
    private Node[] vector;
    private int size;
    public HashTable(int size) {
        this.vector = new Node[size];
        this.size = size;
    }

    public void insert(String data, Function<String, Integer> hashFunction) {
         Integer positionInVetor = hashFunction.apply(data);

         if (positionInVetor >= size || positionInVetor < 0)
             throw new IllegalArgumentException("A Posição fornecida da função Hash é maior que o tamanho do vetor");

         if (vector[positionInVetor] == null) {
             vector[positionInVetor] = new Node(data);
             return;
         }

         Node aux = vector[positionInVetor];

         while (aux.next != null) {
             aux = aux.next;
         }

         aux.next = new Node(data);
    }

    public boolean search(String data, Function<String, Integer> hashFunction) {
        Integer positionInVetor = hashFunction.apply(data);

        if (positionInVetor >= size || positionInVetor < 0)
            throw new IllegalArgumentException("A Posição fornecida da função Hash é maior que o tamanho do vetor");

        if (vector[positionInVetor] == null)
            return false;

        Node aux = vector[positionInVetor];

        while (aux.next != null) {
            if (aux.data.equals(data))
                return true;

            aux = aux.next;
        }

        return false;
    }

    public Integer hashDivision(String data) {
        int sum = 0;

        for (char charString : data.toCharArray()) {
            sum += charString;
        }

        return sum % size;
    }

    public Integer hashMultiplication(String data) {
        int key = 0;
        double CONSTANT = (Math.sqrt(5) - 1) / 2;

        for (char charString : data.toCharArray()) {
            key += charString;
        }

        return (int) (size * (Math.floor(key * CONSTANT) % 1));
    }

    // ToDo implementar
    public Integer customHashFunction(String data) {
        return 1;
    }

    public void clear() {
        this.vector = new Node[size];
    }

    public List<HashFunction> getListHashFunctions() {
         return List.of(
               new HashFunction("Função de Multiplicação", this::hashMultiplication),
               new HashFunction("Função de Divisão", this::hashDivision)
               //new HashFunction("Função Customizada (Minha)", this::customHashFunction)
         );
    }

}
