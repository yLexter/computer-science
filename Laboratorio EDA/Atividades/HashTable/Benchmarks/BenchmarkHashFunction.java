package AbstractData.HashTable.Benchmarks;

import AbstractData.HashTable.Benchmarks.BenchmarkDTO.BenchmarkDTO;
import AbstractData.HashTable.Benchmarks.BenchmarkDTO.BenchmarkInsertDTO;
import AbstractData.HashTable.Benchmarks.BenchmarkDTO.BenchmarkSearchDTO;
import AbstractData.HashTable.HashTable.HashFunction;
import AbstractData.HashTable.HashTable.HashTable;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BenchmarkHashFunction {

    private final Map<String, Map<String, List<BenchmarkDTO>>> hashTableBenchmarks;

    private final HashTable hashTable;

    private String[] testMassForInsertion;

    private String[] testMassForSearch;

    public BenchmarkHashFunction(HashTable hashTable, String[] testMassForInsertion, String[] testMassForSearch) {
        this.hashTable = hashTable;
        this.testMassForInsertion = testMassForInsertion;
        this.testMassForSearch = testMassForSearch;
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

    private  Map<String, Map<String, List<BenchmarkDTO>>> computeBenchmarks() {

        for (HashFunction hashfuction : hashTable.getListHashFunctions()) {
            benchmarkInsert(hashfuction);
            benchmarkSearch(hashfuction);
            hashTable.clear();
        }

        return hashTableBenchmarks;
    }

    private float benchmark(Runnable function) {
        long startTime = System.currentTimeMillis();
        function.run();
        long endTime = System.currentTimeMillis();

        return (float) (endTime - startTime);
    }

    private void benchmarkInsert(HashFunction hashFunction) {
        List<BenchmarkDTO> listBenchmarksInsert = new ArrayList<>();

        System.out.println("- Inciando Operação de Inserção com a Função " + hashFunction.name() + " ..." );

        for (String data : testMassForInsertion) {

              Runnable functionBenchmarkInserction = () -> {
                hashTable.insert(data, hashFunction.funciton());
              };

              float elapsedTime = benchmark(functionBenchmarkInserction);

              BenchmarkInsertDTO benchmarkInsertDTO = new BenchmarkInsertDTO(
                    hashFunction.name(),
                    elapsedTime,
                    data
             );

             listBenchmarksInsert.add(benchmarkInsertDTO);
        };


        System.out.println("- Inserção finzalida");
        System.out.println();

        registerBenchmark(hashFunction, listBenchmarksInsert, BenchamarkOperation.insert);

    }

    private void benchmarkSearch(HashFunction hashFunction) {
        float elapsedTime;
        List<BenchmarkDTO> listBenchmarkSearch = new ArrayList<>();
        BenchmarkSearchDTO benchmarkSearchDTO;

        System.out.println("- Inciando a Operação de Busca com a Função " + hashFunction.name() + " ..." );

        for (String data : testMassForSearch) {

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

        System.out.println("- Busca finalizada\n");

        registerBenchmark(hashFunction, listBenchmarkSearch, BenchamarkOperation.search);
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

    public void run() {
        computeBenchmarks();
        //showResults();
        saveData();
    }

    public void saveData() {
        JSONObject outerJsonObject = new JSONObject();
        String fileName = "data.json";

        for (Map.Entry<String, Map<String, List<BenchmarkDTO>>> outerEntry : hashTableBenchmarks.entrySet()) {
            String outerKey = outerEntry.getKey();
            Map<String, List<BenchmarkDTO>> innerMap = outerEntry.getValue();

            JSONObject innerJsonObject = new JSONObject();

            for (Map.Entry<String, List<BenchmarkDTO>> innerEntry : innerMap.entrySet()) {
                String innerKey = innerEntry.getKey();
                List<BenchmarkDTO> benchmarkList = innerEntry.getValue();

                JSONArray benchmarkArray = new JSONArray();

                for (BenchmarkDTO benchmarkDTO : benchmarkList) {
                    JSONObject benchmarkObject = new JSONObject();
                    benchmarkObject.put("time", benchmarkDTO.getTime());

                    if (benchmarkDTO instanceof BenchmarkInsertDTO) {
                        benchmarkObject.put("stringInserted", ((BenchmarkInsertDTO) benchmarkDTO).getStringInserted());
                    } else if (benchmarkDTO instanceof BenchmarkSearchDTO) {
                        benchmarkObject.put("stringSearched", ((BenchmarkSearchDTO) benchmarkDTO).getStringSearched());
                    }

                    benchmarkArray.put(benchmarkObject);
                }

                innerJsonObject.put(innerKey, benchmarkArray);
            }

            outerJsonObject.put(outerKey, innerJsonObject);
        }

        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(outerJsonObject.toString(4)); // 4 é o número de espaços de indentação
            System.out.println("Arquivo JSON criado com sucesso: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
      }

}
