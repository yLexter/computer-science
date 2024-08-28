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

/**
 * Classe responsável por realizar benchmarks de inserção e busca em funções de hash e armazenar os resultados.
 * Os benchmarks são realizados para cada função de hash presente em uma tabela de hash específica.
 * Os tempos de execução são registrados em objetos do tipo BenchmarkDTO e organizados em uma estrutura de dados.
 *
 */
public class BenchmarkHashFunction {

    /**
     * Mapa que armazena os benchmarks organizados por função de hash e tipo de operação.
     */
    private final Map<String, Map<String, List<BenchmarkDTO>>> hashTableBenchmarks;

    /**
     * Tabela de hash na qual os benchmarks serão executados.
     */
    private final HashTable hashTable;

    /**
     * Conjunto de dados de teste para operações de inserção.
     */
    private String[] testMassForInsertion;

    /**
     * Conjunto de dados de teste para operações de busca.
     */
    private String[] testMassForSearch;

    /**
     * Construtor da classe BenchmarkHashFunction.
     *
     * @param hashTable            A tabela de hash na qual os benchmarks serão executados.
     * @param testMassForInsertion Conjunto de dados de teste para operações de inserção.
     * @param testMassForSearch    Conjunto de dados de teste para operações de busca.
     */
    public BenchmarkHashFunction(HashTable hashTable, String[] testMassForInsertion, String[] testMassForSearch) {
        this.hashTable = hashTable;
        this.testMassForInsertion = testMassForInsertion;
        this.testMassForSearch = testMassForSearch;
        this.hashTableBenchmarks = new LinkedHashMap<>();
    }

    /**
     * Realiza benchmarks para inserção e busca em cada função de hash presente na tabela e armazena os resultados.
     *
     * @return Mapa contendo os benchmarks organizados por função de hash e tipo de operação.
     */
    private Map<String, Map<String, List<BenchmarkDTO>>> computeBenchmarks() {

        for (HashFunction hashFunction : hashTable.getListHashFunctions()) {
            benchmarkInsert(hashFunction);
            benchmarkSearch(hashFunction);
            hashTable.clear();
        }

        return hashTableBenchmarks;
    }

    /**
     * Executa uma função de benchmark e retorna o tempo de execução.
     *
     * @param function A função a ser benchmarked.
     * @return O tempo de execução da função em milissegundos.
     */
    private double benchmark(Runnable function) {
        long startTime = System.currentTimeMillis();
        function.run();
        long endTime = System.currentTimeMillis();

        return (double) (endTime - startTime);
    }

    /**
     * Realiza benchmarks de inserção para uma função de hash específica e armazena os resultados.
     *
     * @param hashFunction A função de hash para a qual os benchmarks de inserção serão realizados.
     */
    private void benchmarkInsert(HashFunction hashFunction) {
        List<BenchmarkDTO> listBenchmarksInsert = new ArrayList<>();

        System.out.println("- Iniciando Operação de Inserção com a Função " + hashFunction.name() + " ...");

        Runnable functionBenchmarkInsertion = () -> {
           for (String data : testMassForInsertion) {
              hashTable.insert(data, hashFunction.function());
           }
        };

        double elapsedTime = benchmark(functionBenchmarkInsertion);

        BenchmarkInsertDTO benchmarkInsertDTO = new BenchmarkInsertDTO(
                hashFunction.name(),
                elapsedTime,
                testMassForInsertion.length
        );

        listBenchmarksInsert.add(benchmarkInsertDTO);

        System.out.println("- Inserção finalizada");
        System.out.println();

        registerBenchmark(hashFunction, listBenchmarksInsert, BenchamarkOperation.insert);
    }

    /**
     * Realiza benchmarks de busca para uma função de hash específica e armazena os resultados.
     *
     * @param hashFunction A função de hash para a qual os benchmarks de busca serão realizados.
     */
    private void benchmarkSearch(HashFunction hashFunction) {
        double elapsedTime;
        List<BenchmarkDTO> listBenchmarkSearch = new ArrayList<>();
        BenchmarkSearchDTO benchmarkSearchDTO;

        System.out.println("- Iniciando a Operação de Busca com a Função " + hashFunction.name() + " ...");

        for (String data : testMassForSearch) {

            Runnable functionBenchmarkSearch = () -> {
                hashTable.search(data, hashFunction.function());
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

    /**
     * Registra os benchmarks para uma função de hash e tipo de operação específicos.
     *
     * @param hashFunction    A função de hash para a qual os benchmarks foram realizados.
     * @param listBenchmarkDTO A lista de objetos BenchmarkDTO contendo os resultados dos benchmarks.
     * @param operation       O tipo de operação associado aos benchmarks (inserção ou busca).
     */
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

    /**
     * Executa os benchmarks para inserção e busca em todas as funções de hash da tabela.
     */
    public void run() {
        computeBenchmarks();
        saveData();
    }

    /**
     * Mostra resultados dos benchmarks
     * */
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

    public void saveData() {
        JSONObject outerJsonObject = new JSONObject();
        JSONArray listsArray = new JSONArray();

        for (String string : testMassForInsertion) {
            listsArray.put(string);
        }

        outerJsonObject.put("stringInserted", listsArray);

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
                        benchmarkObject.put("vectorLength", ((BenchmarkInsertDTO) benchmarkDTO).getVectorLength());
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
            fileWriter.write(outerJsonObject.toString(4));
            System.out.println("Arquivo JSON criado com sucesso: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
      }

}
