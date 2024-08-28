package AbstractData.HashTable.Tests;

import AbstractData.HashTable.HashTable.HashTable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.function.Function;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Classe de teste para a classe {@link HashTable}.
 * Realiza testes de inserção e busca para diferentes funções de hash.
 * Utiliza o framework JUnit 5.
 *
 * @author [Seu Nome]
 * @version 1.0
 */
public class HashTableTest {

    /**
     * Tabela de hash a ser utilizada nos testes.
     */
    private HashTable hashTable;

    /**
     * Inicializa uma nova tabela de hash antes de cada teste.
     */
    @BeforeEach
    public void start() {
        this.hashTable = new HashTable(10);
    }

    /**
     * Testa a função de hash de divisão.
     */
    @Test
    public void testHashDivision() {
        testInsertionAndSearch("Hash Division", hashTable::hashDivision);
    }

    /**
     * Testa a função de hash de multiplicação.
     */
    @Test
    public void testHashMultiplication() {
        testInsertionAndSearch("Hash Multiplication", hashTable::hashMultiplication);
    }

    /**
     * Testa a função de hash personalizada.
     */
    @Test
    public void testHashWTF() {
        testInsertionAndSearch("Hash WTF", hashTable::hashWTF);
    }

    /**
     * Realiza testes de inserção e busca utilizando a função de hash fornecida.
     *
     * @param functionName Nome da função de hash sendo testada.
     * @param hashFunction Função de hash a ser utilizada nos testes.
     */
    private void testInsertionAndSearch(String functionName, Function<String, Integer> hashFunction) {
        int hashValueTest1 = hashFunction.apply("Test1");
        int hashValueTest2 = hashFunction.apply("Test2");
        int hashValueTest3 = hashFunction.apply("Test3");

        // Inserção de elementos na tabela de hash.
        hashTable.insert("Test1", hashFunction);
        hashTable.insert("Test2", hashFunction);
        hashTable.insert("Test3", hashFunction);

        // Verificação de consistência dos valores de hash.
        assertEquals(hashValueTest1, hashFunction.apply("Test1"), functionName + ": Valor de hash para Test1 deve ser consistente");
        assertEquals(hashValueTest2, hashFunction.apply("Test2"), functionName + ": Valor de hash para Test2 deve ser consistente");
        assertEquals(hashValueTest3, hashFunction.apply("Test3"), functionName + ": Valor de hash para Test3 deve ser consistente");

        // Verificação de presença dos elementos na tabela de hash.
        assertTrue(hashTable.search("Test1", hashFunction), functionName + ": Test1 deve ser encontrado");
        assertTrue(hashTable.search("Test2", hashFunction), functionName + ": Test2 deve ser encontrado");
        assertTrue(hashTable.search("Test3", hashFunction), functionName + ": Test3 deve ser encontrado");

        // Verificação de ausência de um valor não presente na tabela.
        assertFalse(hashTable.search("??", hashFunction), "Valor não presente na lista");

        // Limpeza da tabela de hash após cada teste.
        hashTable.clear();
    }
}

