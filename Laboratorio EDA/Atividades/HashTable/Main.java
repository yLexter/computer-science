package AbstractData.HashTable;

import AbstractData.HashTable.Benchmarks.BenchmarkHashFunction;
import AbstractData.HashTable.DataProvider.StringProvider;
import AbstractData.HashTable.HashTable.HashTable;

public class Main {

    public static final int stringLength = 100;

    public static final int vectorLength = 10_000;

    public static final int insertionVectorPercentage = 10;

    public static void main(String[] args) {

        HashTable hashTable = new HashTable(20);

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
