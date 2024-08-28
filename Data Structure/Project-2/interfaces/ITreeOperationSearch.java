package interfaces;

import entities.BaseTree;
import infrastructure.BenchmarkTreeDTO;

import java.util.List;
import java.util.Map;

public interface ITreeOperationSearch<T extends Comparable<T>> {
    Map<String, List<BenchmarkTreeDTO>> run(BaseTree<T> tree, IDataProvider<T> benchmark, List<T> vectorTree);
    String getName();
}
