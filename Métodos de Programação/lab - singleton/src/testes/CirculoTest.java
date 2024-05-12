package testes;

import entities.Circulo;
import erros.Figura2DException;
import erros.SingletonException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CirculoTest {

    @BeforeEach
    public void setUp() {
        Circulo.createInstance(5.0); // Criando a instância única antes de executar os testes
    }

    @AfterEach
    public void tearDown() {
        Circulo.resetInstance();
    }

    @Test
    public void testGetInstance() {
        Circulo circulo1 = Circulo.getInstance();
        assertNotNull(circulo1); // Verificando se a instância não é nula

        // Verificando se o mesmo objeto é retornado ao chamar getInstance novamente
        Circulo circulo2 = Circulo.getInstance();
        assertEquals(circulo1, circulo2);
    }

    @Test
    public void testCreateInstance() {
        // Teste para garantir que uma exceção é lançada se tentarmos criar mais de uma instância
        assertThrows(SingletonException.class, () -> Circulo.createInstance(10.0));
    }

    @Test
    public void testGetPerimetro() {
        Circulo circulo = Circulo.getInstance();

        // Testando o cálculo do perímetro
        assertEquals(31.41592653589793, circulo.getPerimetro(), 0.0001);
    }

    @Test
    public void testGetArea() {
        Circulo circulo = Circulo.getInstance();

        // Testando o cálculo da área
        assertEquals(78.53981633974483, circulo.getArea(), 0.0001);
    }

    @Test
    public void testGetNome() {
        Circulo circulo = Circulo.getInstance();

        // Testando o método getNome
        assertEquals("Círculo", circulo.getNome());
    }

    @Test
    public void testToString() {
        Circulo circulo = Circulo.getInstance();

        // Testando o método toString
        assertEquals("Figura2D (Círculo) | [Área: 78,54] - [Perímetro: 31,42] | [Raio: 5,00]", circulo.toString());
    }

    @Test
    public void testRaioNegativo() {
        assertThrows(SingletonException.class, () -> {
            Circulo.createInstance(-5.0);
        });
    }
}
