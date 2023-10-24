package interfaces;

import entities.BaseTree;
import infrastructure.BenchmarkDTO;
import useCases.TreeOperations;

import java.util.List;

public interface ITreeOperationInsert<T extends Comparable<T>> {
    BenchmarkDTO run(BaseTree<T> tree, List<T> data);
    String getName();
}
