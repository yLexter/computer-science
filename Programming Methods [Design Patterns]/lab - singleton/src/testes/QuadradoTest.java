package testes;

import static org.junit.jupiter.api.Assertions.*;

import entities.Quadrado;
import erros.Figura2DException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuadradoTest {

    private Quadrado quadrado;

    @BeforeEach
    public void setUp() {
        quadrado = new Quadrado(5.0); // Criando uma instância do quadrado antes de cada teste
    }

    @AfterEach
    public void tearDown() {
        quadrado = null; // Destruindo a instância do quadrado após cada teste
    }

    @Test
    public void testGetLado() {
        assertEquals(5.0, quadrado.getLado(), 0.0001); // Verificando se o lado do quadrado é 5.0
    }

    @Test
    public void testSetLado() {
        quadrado.setLado(10.0); // Alterando o lado do quadrado para 10.0
        assertEquals(10.0, quadrado.getLado(), 0.0001); // Verificando se o lado do quadrado foi alterado corretamente
    }

    @Test
    public void testGetArea() {
        assertEquals(25.0, quadrado.getArea(), 0.0001); // Verificando se a área do quadrado é 25.0
    }

    @Test
    public void testGetNome() {
        assertEquals("Quadrado", quadrado.getNome()); // Verificando se o nome do quadrado é "Quadrado"
    }

    @Test
    public void testGetPerimetro() {
        assertEquals(20.0, quadrado.getPerimetro(), 0.0001); // Verificando se o perímetro do quadrado é 20.0
    }

    @Test
    public void testLadoNegativo() {
        assertThrows(Figura2DException.class, () -> {
            new Quadrado(-5.0); // Tentando criar um quadrado com lado negativo, deve lançar Figura2DException
        });
    }
}

