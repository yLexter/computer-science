package tests.entities;

import static org.junit.jupiter.api.Assertions.*;

import entities.Circulo;
import erros.Figura2DException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CirculoTest {

    private Circulo circulo;

    @BeforeEach
    public void setUp() {
        circulo = new Circulo(5.0);
    }

    @Test
    public void testConstrutorComRaioPositivo() {
        assertEquals(5.0, circulo.getRaio(), 1e-6);
    }

    @Test
    public void testConstrutorComRaioNegativo() {
        assertThrows(Figura2DException.class, () -> new Circulo(-3.0));
    }

    @Test
    public void testSetRaioComValorPositivo() {
        circulo.setRaio(8.0);
        assertEquals(8.0, circulo.getRaio(), 1e-6);
    }

    @Test
    public void testSetRaioComValorNegativo() {
        assertThrows(Figura2DException.class, () -> circulo.setRaio(-5.0));
    }


}
