package useCases;

import entities.BaseTree;
import infrastructure.BenchmarkDTO;
import interfaces.ITreeOperation;

import java.util.List;

public class TreeOperations {

    public static <T extends Comparable<T>> BenchmarkDTO benchmarkInsert(BaseTree<T> tree, List<T> data) {

        long startTime = System.nanoTime();

        for (T element : data) {
            tree.insert(element);
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        return new BenchmarkDTO(elapsedTime, tree.getName(), data.size(), "Inserção");
    }

    public static <T extends Comparable<T>> BenchmarkDTO benchmarkSearch(BaseTree<T> tree, List<T> data) {

        long startTime = System.nanoTime();

        for (T element : data) {
            tree.search(element);
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        return new BenchmarkDTO(elapsedTime, tree.getName(), data.size(), "Busca");
    }

    public static List<ITreeOperation> getAllOperations() {
        return List.of(
            TreeOperations::benchmarkSearch,
            TreeOperations::benchmarkInsert
        );
    }

}
