package AbstractData.BinaryTree;

import org.junit.Test;
import static org.junit.Assert.*;

public class TesteArvoreBinaria {
    @Test
    public void testeAdicionarNo() {
        ArvoreBinaria arvore = new ArvoreBinaria();
        arvore.adicionarNo(5);
        arvore.adicionarNo(3);
        arvore.adicionarNo(8);
        arvore.adicionarNo(1);
        arvore.adicionarNo(4);
        assertEquals(5, arvore.getRaiz().valor);
        assertEquals(3, arvore.getRaiz().esquerda.valor);
        assertEquals(8, arvore.getRaiz().direita.valor);
        assertEquals(1, arvore.getRaiz().esquerda.esquerda.valor);
        assertEquals(4, arvore.getRaiz().esquerda.direita.valor);
    }

    @Test
    public void testeBuscar() {
        ArvoreBinaria arvore = new ArvoreBinaria();
        arvore.adicionarNo(5);
        arvore.adicionarNo(3);
        arvore.adicionarNo(8);
        arvore.adicionarNo(1);
        arvore.adicionarNo(4);
        assertTrue(arvore.buscar(5));
        assertTrue(arvore.buscar(1));
        assertFalse(arvore.buscar(7));
    }

    @Test
    public void testePercursoPreOrdem() {
        ArvoreBinaria arvore = new ArvoreBinaria();
        arvore.adicionarNo(5);
        arvore.adicionarNo(3);
        arvore.adicionarNo(8);
        arvore.adicionarNo(1);
        arvore.adicionarNo(4);
        assertEquals("5 3 1 4 8", arvore.percursoPreOrdem());
    }

    @Test
    public void testePercursoInOrdem() {
        ArvoreBinaria arvore = new ArvoreBinaria();
        arvore.adicionarNo(5);
        arvore.adicionarNo(3);
        arvore.adicionarNo(8);
        arvore.adicionarNo(1);
        arvore.adicionarNo(4);
        assertEquals("1 3 4 5 8", arvore.percursoInOrdem());
    }

    @Test
    public void testePercursoPosOrdem() {
        ArvoreBinaria arvore = new ArvoreBinaria();
        arvore.adicionarNo(5);
        arvore.adicionarNo(3);
        arvore.adicionarNo(8);
        arvore.adicionarNo(1);
        arvore.adicionarNo(4);
        assertEquals("1 4 3 8 5", arvore.percursoPosOrdem());
    }

    @Test
    public void testePercursoProfundidade() {
        ArvoreBinaria arvore = new ArvoreBinaria();
        arvore.adicionarNo(5);
        arvore.adicionarNo(3);
        arvore.adicionarNo(8);
        arvore.adicionarNo(1);
        arvore.adicionarNo(4);
        assertEquals("5 3 1 4 8", arvore.percursoProfundidade());
    }

    @Test
    public void testeRemoverNoFolha() {
        ArvoreBinaria arvore = new ArvoreBinaria();
        arvore.adicionarNo(5);
        arvore.adicionarNo(3);
        arvore.adicionarNo(8);
        arvore.adicionarNo(1);
        arvore.adicionarNo(4);
// Remover um nó folha (4)
        arvore.removerNo(4);
        assertEquals("5 3 1 8", arvore.percursoPreOrdem());
    }

    @Test
    public void testeRemoverNoComUmFilho() {
        ArvoreBinaria arvore = new ArvoreBinaria();
        arvore.adicionarNo(5);
        arvore.adicionarNo(3);
        arvore.adicionarNo(8);
        arvore.adicionarNo(1);
        arvore.adicionarNo(4);
// Remover um nó com um filho (3)
        arvore.removerNo(3);
        assertEquals("5 4 1 8", arvore.percursoPreOrdem());
    }

    @Test
    public void testeRemoverNoComDoisFilhos() {
        ArvoreBinaria arvore = new ArvoreBinaria();
        arvore.adicionarNo(5);
        arvore.adicionarNo(3);
        arvore.adicionarNo(8);
        arvore.adicionarNo(1);
        arvore.adicionarNo(4);
// Remover um nó com dois filhos (3)
        arvore.removerNo(3);
        assertEquals("5 4 1 8", arvore.percursoPreOrdem());
    }
}