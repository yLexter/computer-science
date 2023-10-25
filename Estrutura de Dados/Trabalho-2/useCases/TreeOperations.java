package useCases;

import entities.BaseTree;
import infrastructure.BenchmarkDTO;
import interfaces.IBenchmark;
import interfaces.ITreeOperationInsert;
import interfaces.ITreeOperationSearch;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TreeOperations {

    public record BenchmarkInsert<T extends Comparable<T>>() implements ITreeOperationInsert<T> {
        @Override
        public BenchmarkDTO run(BaseTree<T> tree, List<T> data) {
            long startTime = System.currentTimeMillis();

            for (T element : data) {
                tree.insert(element);
            }

            long endTime = System.currentTimeMillis();
            long elapsedTime = (endTime - startTime) / 1000;

            return new BenchmarkDTO(
                    elapsedTime,
                    tree.getName(),
                    data.size(),
                    tree.getHeight(),
                    tree.getTotalRotations(),
                    0,
                    tree.getTotalDoubleRotations()
            );
        }

        @Override
        public String getName() {
            return "Operação de Inserção";
        }
    }

    public record BenchmarkSearch<T extends Comparable<T>>() implements ITreeOperationSearch<T> {
        @Override
        public Map<String, List<BenchmarkDTO>> run(BaseTree<T> tree, IBenchmark<T> benchmark, List<T> vectorTree) {
            Map<String, List<List<T>>> dataSearch = benchmark.getMassTestForSearch();
            Map<String, List<BenchmarkDTO>> benchmarskSearch = new LinkedHashMap<>();

            for (Map.Entry<String, List<List<T>>> entry : dataSearch.entrySet()) {
                  String typeVector = entry.getKey();

                  List<List<T>> matrix = entry.getValue();

                  for(List<T> vector : matrix) {

                    String key = String.format("%s", typeVector);

                    long startTime = System.currentTimeMillis();

                    for(T item : vector) {
                        tree.search(item);
                     }

                     long endTime = System.currentTimeMillis();
                     long elapsedTime = (endTime - startTime) / 1000;

                     BenchmarkDTO benchmarkDTO = new BenchmarkDTO(
                             elapsedTime,
                             tree.getName(),
                             vector.size(),
                             tree.getHeight(),
                             tree.getTotalRotations(),
                             vectorTree.size(),
                             tree.getTotalDoubleRotations()
                     );

                     if (!benchmarskSearch.containsKey(key)) {
                         benchmarskSearch.put(key, new ArrayList<>());
                     }

                     benchmarskSearch.get(key).add(benchmarkDTO);
                }

            }

            return benchmarskSearch;
        }

        @Override
        public String getName() {
            return "Operação de Busca";
        }

    }



}
