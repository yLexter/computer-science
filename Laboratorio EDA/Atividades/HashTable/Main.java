package AbstractData.HashTable;

import AbstractData.HashTable.Benchmarks.BenchmarkHashFunction;
import AbstractData.HashTable.DataProvider.StringProvider;
import AbstractData.HashTable.HashTable.HashTable;

/**
 * Classe principal que demonstra o uso da tabela de hash e realiza benchmarks.
 * Cria uma tabela de hash, gera vetores de strings aleatórias para operações de inserção e busca,
 * e executa benchmarks usando a classe {@link BenchmarkHashFunction}.
 *
 * Configurações:
 * - Comprimento das strings: {@value #stringLength}
 * - Comprimento do vetor: {@value #vectorLength}
 * - Porcentagem do vetor para operações de inserção: {@value #insertionVectorPercentage}%
 *
 */

public class Main {

    /**
     * Comprimento das strings geradas.
     */
    public static final int stringLength = 50;

    /**
     * Comprimento total do vetor de strings.
     */
    public static final int vectorLength = 10_000;

    /**
     * Porcentagem do vetor de inserção no vetor de busca.
     */
    public static final int insertionVectorPercentage = 30;

    /**
     * Tamanho da tabela hash
     * */
    public static final int sizeHashTable = 5;

    /**
     * Método principal que cria uma tabela de hash, gera vetores de strings aleatórias
     * para operações de inserção e busca, e executa benchmarks usando a classe {@link BenchmarkHashFunction}.
     *
     */
    public static void main(String[] args) {

        HashTable hashTable = new HashTable(sizeHashTable);

        String[] testMassForInsertion = StringProvider.generateRandomVector(stringLength, vectorLength);
        String[] testMassForSearch = StringProvider.getVectorForSearch(
                testMassForInsertion,
                insertionVectorPercentage,
                stringLength
        );

        BenchmarkHashFunction benchmarkHashFunction = new BenchmarkHashFunction(
                hashTable,
                testMassForInsertion,
                testMassForSearch
        );

        benchmarkHashFunction.run();
    }
}

