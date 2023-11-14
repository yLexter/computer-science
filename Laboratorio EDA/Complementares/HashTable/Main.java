package AbstractData.HashTable;

import AbstractData.HashTable.Benchmarks.BenchmarkHashFunction;
import AbstractData.HashTable.HashTable.HashTable;

public class Main {

    public static void main(String[] args) {

        HashTable hashTable = new HashTable(100);
        String[] testDough = {"A", "B", "C", "D"};

        BenchmarkHashFunction benchmarkHashFunction = new BenchmarkHashFunction(
                hashTable,
                testDough
        );

        benchmarkHashFunction.run();

    }

}
