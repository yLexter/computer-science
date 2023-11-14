package AbstractData.HashTable.HashTable;
import java.util.function.Function;

public record HashFunction(String name, Function<String, Integer> funciton) {}
