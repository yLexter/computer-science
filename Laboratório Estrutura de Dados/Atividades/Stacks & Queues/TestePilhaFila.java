package AbstractData.StacksAndQueues;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestePilhaFila {

    @Test
    public void testPilhaArray() {
        PilhaArray pilha = new PilhaArray(5);
   
        assertTrue(pilha.isEmpty());
        assertEquals(0, pilha.size());

        pilha.push(1);
        pilha.push(2);
        pilha.push(3);

        assertFalse(pilha.isEmpty());
        assertEquals(3, pilha.size());
        assertEquals(3, pilha.pop().intValue());

        pilha.pop();
        pilha.pop();

        assertTrue(pilha.isEmpty());
        assertEquals(0, pilha.size());
    }

    @Test
    public void testFilaArray() {
       FilaArray fila = new FilaArray(5);
       
       assertTrue(fila.isEmpty());
       assertEquals(0, fila.size());
       
       fila.enqueue("Alice");
       fila.enqueue("Bob");
       fila.enqueue("Charlie");
       
       assertFalse(fila.isEmpty());
       assertEquals(3, fila.size());
       assertEquals("Alice", fila.dequeue());
       
       fila.dequeue();
       fila.dequeue();
       
       assertTrue(fila.isEmpty());
       assertEquals(0, fila.size());
    }

    @Test
    public void testPilhaListaEncadeada() {
       PilhaListaEncadeada pilha = new PilhaListaEncadeada();
       
       assertTrue(pilha.isEmpty());
       assertEquals(0, pilha.size());
       
       pilha.push("Alice");
       pilha.push("Bob");
       pilha.push("Charlie");
       
       assertFalse(pilha.isEmpty());
       assertEquals(3, pilha.size());
       assertEquals("Charlie", pilha.pop());
       
       pilha.pop();
       pilha.pop();
       
       assertTrue(pilha.isEmpty());
       assertEquals(0, pilha.size());
    }

    @Test
    public void testFilaListaEncadeada() {
       FilaListaEncadeada fila = new FilaListaEncadeada();
       
       assertTrue(fila.isEmpty());
       assertEquals(0, fila.size());
       
       fila.enqueue(1);
       fila.enqueue(2);
       fila.enqueue(3);
       
       assertFalse(fila.isEmpty());
       assertEquals(3, fila.size());
       assertEquals(1, fila.dequeue().intValue());
       
       fila.dequeue();
       fila.dequeue();
       
       assertTrue(fila.isEmpty());
       assertEquals(0, fila.size());
    }

}
