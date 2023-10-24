package interfaces;

import entities.BaseTree;
import infrastructure.BenchmarkDTO;

import java.util.List;
import java.util.Map;

public interface ITreeOperationSearch<T extends Comparable<T>> {
    Map<String, List<BenchmarkDTO>> run(BaseTree<T> tree, IBenchmark<T> benchmark, List<T> vectorTree);
    String getName();
}
