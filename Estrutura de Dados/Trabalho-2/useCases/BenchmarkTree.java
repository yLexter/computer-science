package useCases;

import entities.BaseTree;
import infrastructure.BenchmarkTreeDTO;
import interfaces.IDataProvider;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class BenchmarkTree<T extends Comparable<T>> {

    private List<BaseTree<T>> trees;

    private IDataProvider<T> dataProvider;

    public BenchmarkTree(List<BaseTree<T>> trees, IDataProvider<T> dataProvider) {
        this.trees = trees;
        this.dataProvider = dataProvider;
    }

    public Map<String, Map<String, Map<String, Map<String, List<BenchmarkTreeDTO>>>>>  getBenchmarks() {

        TreeOperations.BenchmarkInsert<T> benchmarkInsert = new TreeOperations.BenchmarkInsert<T>();
        TreeOperations.BenchmarkSearch<T> benchmarkSearch = new TreeOperations.BenchmarkSearch<>();

        Map<String, List<List<T>>> data = dataProvider.getMassTestForInsert();
        Map<String, Map<String, Map<String, Map<String, List<BenchmarkTreeDTO>>>>> benchMap = new LinkedHashMap<>();

        for (BaseTree<T> tree : trees) {
            String nameTree = tree.getName();
            Map<String, Map<String, Map<String, List<BenchmarkTreeDTO>>>> mapTree = new LinkedHashMap<>();

            if (!benchMap.containsKey(nameTree)) {
                benchMap.put(nameTree, mapTree);
            }

            System.out.println("-- " + tree.getName() + "\n");

            for (Map.Entry<String, List<List<T>>> entry : data.entrySet()) {
                String typeVector = entry.getKey();
                List<List<T>> matrix = entry.getValue();

                if (!mapTree.containsKey(typeVector)) {
                    mapTree.put(typeVector, new LinkedHashMap<>());
                }

                Map<String, Map<String, List<BenchmarkTreeDTO>>> mapOperations = mapTree.get(typeVector);
                String insert = benchmarkInsert.getName();
                String search = benchmarkSearch.getName();

                if (!mapOperations.containsKey(insert))
                    mapOperations.put(benchmarkInsert.getName(), new LinkedHashMap<>());

                if (!mapOperations.containsKey(benchmarkSearch.getName()))
                    mapOperations.put(benchmarkSearch.getName(), new LinkedHashMap<>());

                for (List<T> list : matrix) {
                    benchmarkInsert(mapOperations.get(insert), tree, list, benchmarkInsert);

                    tree.clearRotations();

                    benchmarkSearch(mapOperations.get(search), tree, list, benchmarkSearch);

                    tree.clear();
                }

            }

            System.out.println("-".repeat(50) + "\n");
        }

        return benchMap;
    }

    public void benchmarkInsert(Map<String, List<BenchmarkTreeDTO>> map, BaseTree<T> tree, List<T> list, TreeOperations.BenchmarkInsert<T> benchmarkInsert) {
        BenchmarkTreeDTO benchmarkTreeDTO = benchmarkInsert.run(tree, list);
        String lenVector = String.format("%d", list.size());

        if (!map.containsKey(lenVector)) {
            map.put(lenVector, new ArrayList<>());
        }

         map.get(lenVector).add(benchmarkTreeDTO);
    }

    public void benchmarkSearch(Map<String, List<BenchmarkTreeDTO>> map, BaseTree<T> tree, List<T> vectorTree, TreeOperations.BenchmarkSearch<T> benchmarkSearch) {
        Map<String, List<BenchmarkTreeDTO>> data = benchmarkSearch.run(tree, dataProvider, vectorTree);

        for (Map.Entry<String, List<BenchmarkTreeDTO>> entry : data.entrySet()) {
            String typeVector = String.format("%s", entry.getKey());
            List<BenchmarkTreeDTO> matrixBenchMark = entry.getValue();

            if (!map.containsKey(typeVector)) {
                map.put(typeVector, new ArrayList<>());
            }

            map.get(typeVector).addAll(matrixBenchMark);
        }
    }

    private void generateMassTest() {
        dataProvider.getMassTestForInsert();
        dataProvider.getMassTestForSearch();
    }

    public void run() {
        generateMassTest();

        Map<String, Map<String, Map<String, Map<String, List<BenchmarkTreeDTO>>>>> mapBenchmark = getBenchmarks();

        showBenchmarks(mapBenchmark);
        saveBenchmarks(mapBenchmark);
    }

    public void saveBenchmarks(Map<String, Map<String, Map<String, Map<String, List<BenchmarkTreeDTO>>>>> data) {

        JSONObject jsonObject = mapToJson(data);

        String jsonStr = jsonObject.toString();

        String fileName = "data.json";

        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(jsonStr);
            System.out.println("JSON salvo com sucesso em " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject benchmarkDtoToJson(BenchmarkTreeDTO benchmark) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("time", benchmark.time());
        jsonObject.put("name", benchmark.name());
        jsonObject.put("sizeVectorInsertion", benchmark.sizeVectorInsertion());
        jsonObject.put("heigth", benchmark.heigth());
        jsonObject.put("rotations", benchmark.rotations());
        jsonObject.put("sizeVectorSearch", benchmark.sizeVectorSearch());
        jsonObject.put("doubleRotations", benchmark.doubleRotations());
        return jsonObject;
    }

    public static JSONObject mapToJson(Map<String, ?> map) {
        JSONObject jsonObject = new JSONObject();
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                jsonObject.put(key, mapToJson((Map<String, ?>) value));
            } else if (value instanceof List) {
                JSONArray jsonArray = new JSONArray();
                for (Object item : (List<?>) value) {
                    if (item instanceof BenchmarkTreeDTO) {
                        jsonArray.put(benchmarkDtoToJson((BenchmarkTreeDTO) item));
                    } else {
                        jsonArray.put(item);
                    }
                }
                jsonObject.put(key, jsonArray);
            } else {
                jsonObject.put(key, value);
            }
        }
        return jsonObject;
    }

    public void showBenchmarks(Map<String, Map<String, Map<String, Map<String, List<BenchmarkTreeDTO>>>>> data) {
        for (Map.Entry<String, Map<String, Map<String, Map<String, List<BenchmarkTreeDTO>>>>> entry1 : data.entrySet()) {
            System.out.println("" + entry1.getKey());
            for (Map.Entry<String, Map<String, Map<String, List<BenchmarkTreeDTO>>>> entry2 : entry1.getValue().entrySet()) {
                System.out.println("\t" + entry2.getKey());
                for (Map.Entry<String, Map<String, List<BenchmarkTreeDTO>>> entry3 : entry2.getValue().entrySet()) {
                    System.out.println("\t\t" + entry3.getKey());
                    for (Map.Entry<String, List<BenchmarkTreeDTO>> entry4 : entry3.getValue().entrySet()) {
                        System.out.println("\t\t\t" + entry4.getKey());
                        for (BenchmarkTreeDTO benchmark : entry4.getValue()) {
                            System.out.printf("\t\t\t\t" + benchmark + "\n");
                        }
                    }
                }
            }
        }
    }

}
