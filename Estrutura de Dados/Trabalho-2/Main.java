import entities.AVL.AVLTree;
import entities.BinarySearchTree.BinarySearchTree;
import entities.RedBlackTree.RedBlackTree;
import entities.SplayTree.SplayTree;
import infrastructure.integerData.IntegerData;
import entities.*;
import useCases.BenchmarkTree;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        List<BaseTree<Integer>> treeList = List.of(
                new BinarySearchTree<>(),
                new RedBlackTree<>(),
                new AVLTree<>(),
                new SplayTree<>()
        );

        IntegerData integerData = new IntegerData();

        BenchmarkTree<Integer> benchmarkTree = new BenchmarkTree<>(
                treeList,
                integerData
        );

        benchmarkTree.run();

    }


}