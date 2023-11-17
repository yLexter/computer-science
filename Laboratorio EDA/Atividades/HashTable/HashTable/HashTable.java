package AbstractData.HashTable.HashTable;

import java.util.List;
import java.util.function.Function;


/**
 * Implementação de uma tabela de hash em Java.
 * Utiliza listas encadeadas para lidar com colisões.
 * Oferece operações de inserção e busca, bem como várias funções de hash.
 *
 */

public class HashTable {

    /**
     * Vetor de listas encadeadas para armazenar os elementos da tabela de hash.
     */
    private LinkedList<String, String>[] vector;

    /**
     * O tamanho da tabela de hash (tamanho do vetor).
     */
    private final int size;

    /**
     * Construtor que inicializa a tabela de hash com o tamanho especificado.
     *
     * @param size O tamanho desejado da tabela de hash.
     */
    public HashTable(int size) {
        this.vector = new LinkedList[size];
        this.size = size;
    }

    /**
     * Insere um elemento na tabela de hash usando a função de hash fornecida.
     *
     * @param key          A chave do elemento a ser inserido.
     * @param hashFunction A função de hash a ser utilizada.
     * @throws IllegalArgumentException Se a posição fornecida pela função de hash for inválida.
     */
    public void insert(String key, Function<String, Integer> hashFunction) {
        Integer positionInVector = hashFunction.apply(key);

        if (positionInVector >= size || positionInVector < 0)
            throw new IllegalArgumentException("A Posição fornecida da função Hash é maior que o tamanho do vetor");

        if (vector[positionInVector] == null) {
            LinkedList<String, String> linkedList = new LinkedList<>();
            linkedList.insert(key, key);

            vector[positionInVector] = linkedList;
            return;
        }

        vector[positionInVector].insert(key, key);
    }


    /**
     * Verifica se um elemento está presente na tabela de hash usando a função de hash fornecida.
     *
     * @param key A chave do elemento a ser pesquisado.
     * @param hashFunction A função de hash a ser utilizada.
     * @return true se o elemento estiver presente, false caso contrário.
     * @throws IllegalArgumentException Se a posição fornecida pela função de hash for inválida.
     */
    public boolean search(String key, Function<String, Integer> hashFunction) {
        Integer positionInVector = hashFunction.apply(key);

        if (positionInVector >= size || positionInVector < 0)
            throw new IllegalArgumentException("A Posição fornecida é inválida");

        if (vector[positionInVector] == null)
            return false;

        return vector[positionInVector].search(key);
    }

    /**
     * Função de hash que utiliza a técnica de divisão.
     *
     * @param key A chave a ser hashida.
     * @return O resultado da função de hash.
     */
    public Integer hashDivision(String key) {
        int sum = 0;

        for (char charString : key.toCharArray()) {
            sum += charString;
        }

        return sum % size;
    }

    /**
     * Função de hash que utiliza a técnica de multiplicação.
     *
     * @param key A chave a ser hashida.
     * @return O resultado da função de hash.
     */
    public Integer hashMultiplication(String key) {
        int sum = 0;
        double CONSTANT = (Math.sqrt(5) - 1) / 2;

        for (char charString : key.toCharArray()) {
            sum += charString;
        }

        return (int) Math.floor(size * (sum * CONSTANT) % 1);
    }

    /**
     * Função de hash personalizada.
     *
     * @param key A chave a ser hashida.
     * @return O resultado da função de hash.
     */
    public Integer hashWTF(String key) {
        int sum = 1;

        for (char charString : key.toCharArray())
            sum += (charString & sum) | sum;

        return sum % size;
    }

    /**
     * Limpa a tabela de hash, reinicializando o vetor de listas encadeadas.
     */
    public void clear() {
        this.vector = new LinkedList[size];
    }

    /**
     * Obtém uma lista de funções de hash predefinidas.
     *
     * @return Uma lista de funções de hash.
     */
    public List<HashFunction> getListHashFunctions() {
        return List.of(
                new HashFunction("Função de Divisão", this::hashDivision),
                new HashFunction("Função WTF (Minha Função)", this::hashWTF),
                new HashFunction("Função de Multiplicação", this::hashMultiplication)
        );
    }
}

