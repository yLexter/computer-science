package tests.entities;

import entities.Trapezio;
import erros.Figura2DException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrapezioTest {

    private Trapezio trapezio;

    @BeforeEach
    public void setUp() {
        trapezio = new Trapezio(4, 6, 3, 4, 5);
    }

    @Test
    public void testConstrutorComValoresPositivos() {
        assertEquals(4, trapezio.getBaseMaior());
        assertEquals(6, trapezio.getBaseMenor());
        assertEquals(3, trapezio.getAltura());
        assertEquals(4, trapezio.getLado1());
        assertEquals(5, trapezio.getLado2());
    }

    @Test
    public void testConstrutorComBaseMaiorZero() {
        assertThrows(Figura2DException.class, () -> new Trapezio(0, 6, 3, 4, 5));
    }

    @Test
    public void testConstrutorComBaseMenorZero() {
        assertThrows(Figura2DException.class, () -> new Trapezio(4, 0, 3, 4, 5));
    }

    @Test
    public void testConstrutorComAlturaZero() {
        assertThrows(Figura2DException.class, () -> new Trapezio(4, 6, 0, 4, 5));
    }

    @Test
    public void testConstrutorComLado1Zero() {
        assertThrows(Figura2DException.class, () -> new Trapezio(4, 6, 3, 0, 5));
    }

    @Test
    public void testConstrutorComLado2Zero() {
        assertThrows(Figura2DException.class, () -> new Trapezio(4, 6, 3, 4, 0));
    }

    @Test
    public void testSetBaseMaiorComValorPositivo() {
        trapezio.setBaseMaior(8);
        assertEquals(8, trapezio.getBaseMaior());
    }

    @Test
    public void testSetBaseMaiorComValorZero() {
        assertThrows(Figura2DException.class, () -> trapezio.setBaseMaior(0));
    }

    @Test
    public void testSetBaseMenorComValorPositivo() {
        trapezio.setBaseMenor(10);
        assertEquals(10, trapezio.getBaseMenor());
    }

    @Test
    public void testSetBaseMenorComValorZero() {
        assertThrows(Figura2DException.class, () -> trapezio.setBaseMenor(0));
    }

    @Test
    public void testSetAlturaComValorPositivo() {
        trapezio.setAltura(5);
        assertEquals(5, trapezio.getAltura());
    }

    @Test
    public void testSetAlturaComValorZero() {
        assertThrows(Figura2DException.class, () -> trapezio.setAltura(0));
    }

    @Test
    public void testSetLado1ComValorPositivo() {
        trapezio.setLado1(7);
        assertEquals(7, trapezio.getLado1());
    }

    @Test
    public void testSetLado1ComValorZero() {
        assertThrows(Figura2DException.class, () -> trapezio.setLado1(0));
    }

    @Test
    public void testSetLado2ComValorPositivo() {
        trapezio.setLado2(9);
        assertEquals(9, trapezio.getLado2());
    }

    @Test
    public void testSetLado2ComValorZero() {
        assertThrows(Figura2DException.class, () -> trapezio.setLado2(0));
    }
}
