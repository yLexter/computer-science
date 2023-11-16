package AbstractData.HashTable.HashTable;

import java.util.List;
import java.util.function.Function;

public class HashTable  {
    private LinkedList<String>[] vector;
    private final int size;

    public HashTable(int size) {
        this.vector = new LinkedList[size];
        this.size = size;
    }

    public void insert(String key, Function<String, Integer> hashFunction) {
         Integer positionInVector = hashFunction.apply(key);

         if (positionInVector >= size || positionInVector < 0)
             throw new IllegalArgumentException("A Posição fornecida da função Hash é maior que o tamanho do vetor");

         if (vector[positionInVector] == null) {
             LinkedList<String> linkedList = new LinkedList<>();
             linkedList.insert(key);

             vector[positionInVector] = linkedList;
             return;
         }

         vector[positionInVector].insert(key);
    }
    public boolean search(String key, Function<String, Integer> hashFunction) {
        Integer positionInVector = hashFunction.apply(key);

        if (positionInVector >= size || positionInVector < 0)
            throw new IllegalArgumentException("A Posição fornecida é inválida");

        if (vector[positionInVector] == null)
            return false;

        return vector[positionInVector].search(key);
    }

    public Integer hashDivision(String key) {
        int sum = 0;

        for (char charString : key.toCharArray()) {
            sum += charString;
        }

        return sum % size;
    }

    public Integer hashMultiplication(String key) {
        int sum = 0;
        double CONSTANT = (Math.sqrt(5) - 1) / 2;

        for (char charString : key.toCharArray()) {
            sum += charString;
        }

        return (int) Math.floor(size * (sum * CONSTANT) % 1);
    }

    public Integer hashWTF(String key) {
        int sum = 1;

        for (char charString : key.toCharArray())
            sum += (charString & sum) | sum;

        return sum % size;
    }

    public void clear() {
        this.vector = new LinkedList[size];
    }

    public List<HashFunction> getListHashFunctions() {
         return List.of(
               new HashFunction("Função de Divisão", this::hashDivision),
               new HashFunction("Função WTF (Minha Função)", this::hashWTF),
               new HashFunction("Função de Multiplicação", this::hashMultiplication)
         );
    }

}
