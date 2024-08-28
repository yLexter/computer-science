package interfaces;

import entities.BaseTree;
import infrastructure.BenchmarkTreeDTO;

import java.util.List;

public interface ITreeOperationInsert<T extends Comparable<T>> {
    BenchmarkTreeDTO run(BaseTree<T> tree, List<T> data);
    String getName();
}
