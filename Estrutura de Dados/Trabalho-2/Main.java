import app.BenchmarkInteger;
import entities.AVLTree;
import entities.BaseTree;
import entities.BinaryTree;
import entities.RedBlackTree;
import useCases.BenchmarkTree;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<BaseTree<Integer>> treeList = List.of(
            new BinaryTree<Integer>("Árvore Bínaria"),
            new RedBlackTree<Integer>("Árvore Red Black"),
            new AVLTree<Integer>("Ávore AVL")
        );

        BenchmarkInteger benchmarkInteger = new BenchmarkInteger();

        BenchmarkTree<Integer> benchmarkTree = new BenchmarkTree<>(
                treeList,
                benchmarkInteger
        );

    }
}