package useCases;

import entities.BaseTree;
import infrastructure.BenchmarkDTO;
import interfaces.IBenchmark;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BenchmarkTree<T extends Comparable<T>> {

    private List<BaseTree<T>> trees;

    private IBenchmark<T> benchmark;

    public BenchmarkTree(List<BaseTree<T>> trees, IBenchmark<T> benchmark) {
        this.trees = trees;
        this.benchmark = benchmark;
    }

    public Map<String, Map<String, Map<String, List<BenchmarkDTO>>>>  getBenchmarks() {

        Map<String, List<List<T>>> data = benchmark.getMassTestForInsert();
        Map<String, Map<String, Map<String, List<BenchmarkDTO>>>> benchMap = new LinkedHashMap<>();

        for (BaseTree<T> tree : trees) {
            String nameTree = tree.getName();
            Map<String, Map<String, List<BenchmarkDTO>>> mapTree = new LinkedHashMap<>();

            if (!benchMap.containsKey(nameTree)) {
                benchMap.put(nameTree, mapTree);
            }

            for (Map.Entry<String, List<List<T>>> entry : data.entrySet()) {
                String typeVector = entry.getKey();
                List<List<T>> matrix = entry.getValue();

                if (!mapTree.containsKey(typeVector)) {
                    mapTree.put(typeVector, new LinkedHashMap<>());
                }

                Map<String, List<BenchmarkDTO>> mapVectors = mapTree.get(typeVector);

                for (List<T> list : matrix) {
                    insertBenchmarkInsert(mapVectors, tree, list);
                    insertBenchmarkSearch(mapVectors, tree, list);

                    tree.clear();
                }

            }
        }

        return benchMap;
    }

    public void insertBenchmarkInsert(Map<String, List<BenchmarkDTO>> map, BaseTree<T> tree, List<T> list) {
        TreeOperations.BenchmarkInsert<T> benchmarkInsert = new TreeOperations.BenchmarkInsert<T>();
        BenchmarkDTO benchmarkDTO = benchmarkInsert.run(tree, list);
        String lenVector = String.format("Insert-%d", list.size());

        if (!map.containsKey(lenVector)) {
            map.put(lenVector, new ArrayList<>());
        }

         map.get(lenVector).add(benchmarkDTO);
    }

    public void insertBenchmarkSearch(Map<String, List<BenchmarkDTO>> map, BaseTree<T> tree, List<T> vectorTree) {
        TreeOperations.BenchmarkSearch<T> benchmarkSearch = new TreeOperations.BenchmarkSearch<>();
        Map<String, List<BenchmarkDTO>> data = benchmarkSearch.run(tree, benchmark, vectorTree);

        for (Map.Entry<String, List<BenchmarkDTO>> entry : data.entrySet()) {
            String typeVector = String.format("Busca-%s",entry.getKey());
            List<BenchmarkDTO> matrixBenchMark = entry.getValue();

            if (!map.containsKey(typeVector)) {
                map.put(typeVector, new ArrayList<>());
            }

            map.get(typeVector).addAll(matrixBenchMark);
        }
    }

    public void showBenchmarks() {

        Map<String, Map<String, Map<String, List<BenchmarkDTO>>>> mapBenchmark = getBenchmarks();

        for (Map.Entry<String, Map<String, Map<String, List<BenchmarkDTO>>>> entry1 : mapBenchmark.entrySet()) {
            System.out.println("Tipo de Árvore : " + entry1.getKey());
            for (Map.Entry<String, Map<String, List<BenchmarkDTO>>> entry2 : entry1.getValue().entrySet()) {
                System.out.println("\tTipo de vetor: " + entry2.getKey());
                for (Map.Entry<String, List<BenchmarkDTO>> entry3 : entry2.getValue().entrySet()) {
                    System.out.println("\t\tTipo de Operação: " + entry3.getKey());
                    for (BenchmarkDTO benchmark : entry3.getValue()) {
                        System.out.println("\t\t\tBenchmarkDTO: time=" + benchmark.time() + ", name=" + benchmark.name() + ", size=" + benchmark.size() + ", height=" + benchmark.heigth() + ", Rotações=" + benchmark.rotations() + ", Vetor Auxuliar(Busca)=" + benchmark.secondVector());
                    }
                }
            }
        }


    }

}
