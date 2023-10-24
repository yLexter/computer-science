import app.BenchmarkInteger;
import entities.*;
import infrastructure.BenchmarkDTO;
import useCases.BenchmarkTree;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        List<BaseTree<Integer>> treeList = List.of(
                new BinarySearchTree<>(),
                new RedBlackTree<>(),
                new AVLTree<>(),
                new SplayTree<>()
        );

        BenchmarkInteger benchmarkInteger = new BenchmarkInteger();

        BenchmarkTree<Integer> benchmarkTree = new BenchmarkTree<>(
                treeList,
                benchmarkInteger
        );

        benchmarkTree.showBenchmarks();
    }


}