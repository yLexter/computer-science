package AbstractData.HashTable.HashTable;
import java.util.function.Function;

/**
 * Um registro (record) que representa uma função de hash.
 * Cada instância possui um nome descritivo e uma função que mapeia strings para valores inteiros.
 */
public record HashFunction(String name, Function<String, Integer> function) { }
