package useCases;

import entities.BaseTree;
import infrastructure.BenchmarkDTO;
import interfaces.IBenchmark;
import interfaces.ITreeOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class BenchmarkTree<T extends Comparable<T>> {

    private List<BaseTree<T>> trees;

    private IBenchmark<T> benchmark;

    public BenchmarkTree(List<BaseTree<T>> trees, IBenchmark<T> benchmark) {
        this.trees = trees;
        this.benchmark = benchmark;
    }

    public List<BenchmarkDTO> getBenchmarks(List<T> data) {

      List<BenchmarkDTO> list =  new ArrayList<>();
      List<ITreeOperation> allOperations = TreeOperations.getAllOperations();

        for (BaseTree<T> tree : trees) {

            for (ITreeOperation operation : allOperations) {
                BenchmarkDTO benchmarkDTO = operation.run(tree, data);
                list.add(benchmarkDTO);
            }

        }

       return list;
    }
    
    public void getAllBenchmarks() {

    }


}
