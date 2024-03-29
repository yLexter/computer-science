package main;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestelistaSimplesmenteEncadeada {
	
	@Test
	public void testAdicionarNoInicio() {
		listaSimplesmenteEncadeada lista = new listaSimplesmenteEncadeada();
		lista.adicionarNoInicio(1);
		assertEquals(1, lista.cabeca.valor);
		lista.adicionarNoInicio(2);
		assertEquals(2, lista.cabeca.valor);
	}

	@Test
	public void testadicionarNoFinal() {
		listaSimplesmenteEncadeada lista = new listaSimplesmenteEncadeada();
		lista.adicionarNoFinal(1);
		assertEquals(1, lista.cauda.valor);
		lista.adicionarNoFinal(2);
		assertEquals(2, lista.cauda.valor);
	}

	@Test
	public void testBuscar() {
		listaSimplesmenteEncadeada lista = new listaSimplesmenteEncadeada();
		lista.adicionarNoInicio(1);
		lista.adicionarNoInicio(2);
		lista.adicionarNoFinal(3);
		
		assertTrue(lista.buscar(1));
		assertTrue(lista.buscar(2));
		assertTrue(lista.buscar(3));
		assertFalse(lista.buscar(4));
	}
}

	


