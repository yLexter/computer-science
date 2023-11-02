package useCases;

import entities.BaseTree;
import infrastructure.BenchmarkTreeDTO;
import interfaces.IDataProvider;
import interfaces.ITreeOperationInsert;
import interfaces.ITreeOperationSearch;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TreeOperations {

    private static long getElapsedTime(long startTime, long endTime) {
        return (endTime - startTime);
    }

    public record BenchmarkInsert<T extends Comparable<T>>() implements ITreeOperationInsert<T> {
        @Override
        public BenchmarkTreeDTO run(BaseTree<T> tree, List<T> data) {
            long startTime = System.currentTimeMillis();

            for (T element : data) {
                tree.insert(element);
            }

            long endTime = System.currentTimeMillis();
            long elapsedTime = getElapsedTime(startTime, endTime);

            System.out.printf(
                    "Inserção - %d elementos  - Tempo: %.5f\n",
                    data.size(),
                    (float) elapsedTime
            );

            return new BenchmarkTreeDTO(
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
        public Map<String, List<BenchmarkTreeDTO>> run(BaseTree<T> tree, IDataProvider<T> benchmark, List<T> vectorTree) {
            Map<String, List<List<T>>> dataSearch = benchmark.getMassTestForSearch();
            Map<String, List<BenchmarkTreeDTO>> benchmarskSearch = new LinkedHashMap<>();

            for (Map.Entry<String, List<List<T>>> entry : dataSearch.entrySet()) {
                  String typeVectorName = entry.getKey();

                  List<List<T>> matrix = entry.getValue();

                  for (List<T> vector : matrix) {

                    long startTime = System.currentTimeMillis();

                    for(T item : vector) {
                        tree.search(item);
                     }

                     long endTime = System.currentTimeMillis();
                     long elapsedTime = getElapsedTime(startTime, endTime);

                      System.out.printf(
                              "Busca - %d elementos na árvore - Tempo: %.5f - Tipo: %s (%d elementos para busca)",
                              vectorTree.size(),
                              (float) elapsedTime,
                              typeVectorName,
                              vector.size()
                      );


                      BenchmarkTreeDTO benchmarkTreeDTO = new BenchmarkTreeDTO(
                             elapsedTime,
                             tree.getName(),
                             vectorTree.size(),
                             tree.getHeight(),
                             tree.getTotalRotations(),
                             vector.size(),
                             tree.getTotalDoubleRotations()
                     );

                     if (!benchmarskSearch.containsKey(typeVectorName)) {
                         benchmarskSearch.put(typeVectorName, new ArrayList<>());
                     }

                     benchmarskSearch.get(typeVectorName).add(benchmarkTreeDTO);
                }

                System.out.println();
            }

            System.out.println();

            return benchmarskSearch;
        }

        @Override
        public String getName() {
            return "Operação de Busca";
        }

    }



}
