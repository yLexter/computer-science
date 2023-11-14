package AbstractData.HashTable.Benchmarks;

import AbstractData.HashTable.Benchmarks.BenchmarkDTO.BenchmarkDTO;
import AbstractData.HashTable.Benchmarks.BenchmarkDTO.BenchmarkInsertDTO;
import AbstractData.HashTable.Benchmarks.BenchmarkDTO.BenchmarkSearchDTO;
import AbstractData.HashTable.HashTable.HashFunction;
import AbstractData.HashTable.HashTable.HashTable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BenchmarkHashFunction {
    private final Map<String, Map<String, List<BenchmarkDTO>>> hashTableBenchmarks;
    private final HashTable hashTable;
    private String[] testDough;

    public BenchmarkHashFunction(HashTable hashTable, String[] testDough) {
        this.hashTable = hashTable;
        this.testDough = testDough;
        this.hashTableBenchmarks = new LinkedHashMap<>();
    }

    private void showResults() {
        for (Map.Entry<String, Map<String, List<BenchmarkDTO>>> entry1 : hashTableBenchmarks.entrySet()) {
            String outerKey = entry1.getKey();
            Map<String, List<BenchmarkDTO>> innerMap = entry1.getValue();

            System.out.println("Função Hash:" + outerKey);

            for (Map.Entry<String, List<BenchmarkDTO>> entry2 : innerMap.entrySet()) {
                String innerKey = entry2.getKey();
                List<BenchmarkDTO> benchmarkDTOList = entry2.getValue();

                System.out.println("\tOperação: " + innerKey);

                for (BenchmarkDTO benchmarkDTO : benchmarkDTOList) {
                    System.out.println("\t\t" + benchmarkDTO);
                }
            }

            System.out.println();
        }
    }

    private  Map<String, Map<String, List<BenchmarkDTO>>> compúteBenchmarks() {

        for (HashFunction hashfuction : hashTable.getListHashFunctions()) {
            benchmarkInsert(hashfuction, testDough);
            benchmarkSearch(hashfuction, testDough);
            hashTable.clear();
        }

        return hashTableBenchmarks;
    }

    private long benchmark(Runnable function) {
        long startTime = System.currentTimeMillis();
        function.run();
        long endTime = System.currentTimeMillis();

        return endTime - startTime;
    }

    private void benchmarkInsert(HashFunction hashFunction, String[] testDough) {
        List<BenchmarkDTO> listBenchmarksInsert = new ArrayList<>();

        Runnable functionBenchmarkInserction = () -> {
            for (String data : testDough) {
                hashTable.insert(data, hashFunction.funciton());
            }
        };

        long elapsedTime = benchmark(functionBenchmarkInserction);

        BenchmarkInsertDTO benchmarkInsertDTO = new BenchmarkInsertDTO(
                hashFunction.name(),
                elapsedTime,
                testDough.length
        );

        listBenchmarksInsert.add(benchmarkInsertDTO);
        registerBenchmark(hashFunction, listBenchmarksInsert, BenchamarkOperation.insert);
    }

    private void registerBenchmark(HashFunction hashFunction, List<BenchmarkDTO> listBenchmarkDTO, BenchamarkOperation operation) {
        Map<String, List<BenchmarkDTO>> mapOperations = null;
        String nameHashFunction = hashFunction.name();
        String typeOperation = operation.getDescription();

        if (!hashTableBenchmarks.containsKey(nameHashFunction)) {
            hashTableBenchmarks.put(nameHashFunction, new LinkedHashMap<>());
        }

        mapOperations = hashTableBenchmarks.get(nameHashFunction);

        if (!mapOperations.containsKey(typeOperation)) {
           mapOperations.put(typeOperation, listBenchmarkDTO);
        }

    }

    private void benchmarkSearch(HashFunction hashFunction, String[] testDough) {
        long elapsedTime;
        List<BenchmarkDTO> listBenchmarkSearch = new ArrayList<>();
        BenchmarkSearchDTO benchmarkSearchDTO;

        for (String data : testDough) {
            Runnable functionBenchmarkSearch = () -> {
                hashTable.search(data, hashFunction.funciton());
            };

            elapsedTime = benchmark(functionBenchmarkSearch);

            benchmarkSearchDTO = new BenchmarkSearchDTO(
                hashFunction.name(),
                elapsedTime,
                data
            );

            listBenchmarkSearch.add(benchmarkSearchDTO);
        }

        registerBenchmark(hashFunction, listBenchmarkSearch, BenchamarkOperation.search);
    }

    public void run() {
        compúteBenchmarks();
        showResults();
    }

}
