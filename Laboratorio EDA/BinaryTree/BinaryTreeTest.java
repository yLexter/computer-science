import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;


class BinaryTreeTest {

    private BinaryTree<Integer> tree;

    @BeforeEach
    public void start() {
        this.tree = new BinaryTree<>();
    }

    @Test
    public void testInsertBinaryTree() {
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(1);
        tree.insert(4);

        assertTrue(tree.has(5));
        assertTrue(tree.has(3));
        assertTrue(tree.has(7));
        assertTrue(tree.has(1));
        assertTrue(tree.has(4));
        assertFalse(tree.has(2));
    }

    @Test
    public void testTraversePreOrder() {
        List<Integer> resultado =  new ArrayList<>();
        
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(1);
        tree.insert(4);
        tree.preOrder(resultado::add);
        
        List<Integer> esperado = Arrays.asList(5, 3, 1, 4, 7);
        
        assertEquals(esperado, resultado);
     }

    @Test
    public void testTraverseInOrder() {
        List<Integer> resultado =  new ArrayList<>();

        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(1);
        tree.insert(4);
        tree.inOrder(resultado::add);

        List<Integer> esperado = Arrays.asList(1, 3, 4, 5, 7);

        assertEquals(esperado, resultado);
    }

    @Test
    public void testTraversePostOrder() {
        List<Integer> resultado =  new ArrayList<>();

        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(1);
        tree.insert(4);
        tree.postOrder(resultado::add);

        List<Integer> esperado = Arrays.asList(1, 4, 3, 7, 5);

        assertEquals(esperado, resultado);
    }

}

