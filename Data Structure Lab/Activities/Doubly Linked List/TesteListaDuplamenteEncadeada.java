package main;
import org.junit.Test;
import static org.junit.Assert.*;

public class TesteListaDuplamenteEncadeada {

    @Test
    public void testeAdicionarNoInicio() {
        ListaDuplamenteEncadeada list = new ListaDuplamenteEncadeada();
        list.adicionarNoInicio(1);
        assertEquals(1, list.cabeca.informacao);
        list.adicionarNoInicio(2);
        assertEquals(2, list.cabeca.informacao);
    }

    @Test
    public void testeAdicionarNoFinal() {
        ListaDuplamenteEncadeada list = new ListaDuplamenteEncadeada();
        list.adicionarNoFinal(1);
        assertEquals(1, list.cauda.informacao);
        list.adicionarNoFinal(2);
        assertEquals(2, list.cauda.informacao);
    }

    @Test
    public void testebuscar() {
        ListaDuplamenteEncadeada list = new ListaDuplamenteEncadeada();
        list.adicionarNoInicio(1);
        list.adicionarNoInicio(2);
        list.adicionarNoFinal(3);
        assertTrue(list.buscar(1));
        assertTrue(list.buscar(2));
        assertTrue(list.buscar(3));
        assertFalse(list.buscar(4));
    }

    @Test
    public void testeRemover() {
        ListaDuplamenteEncadeada list = new ListaDuplamenteEncadeada();
        list.adicionarNoInicio(1);
        list.adicionarNoInicio(2);
        list.adicionarNoFinal(3);
        list.remover(2);
        assertFalse(list.buscar(2));
        list.remover(3);
        assertFalse(list.buscar(3));
    }
    
    @Test
    public void testeAdicionarNaPosicaoK() {
        ListaDuplamenteEncadeada list = new ListaDuplamenteEncadeada();
        list.adicionarNoInicio(1);
        list.adicionarNoInicio(2);
        list.adicionarNaPosicaoK(3, 1);
        assertEquals(3, list.cabeca.proximo.informacao);
        list.adicionarNaPosicaoK(4, 0);
        assertEquals(4, list.cabeca.informacao);
        list.adicionarNaPosicaoK(5, 10);
        assertEquals(5, list.cauda.informacao);
    }
}
