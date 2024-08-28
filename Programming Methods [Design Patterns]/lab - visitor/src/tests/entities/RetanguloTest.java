package tests.entities;

import static org.junit.jupiter.api.Assertions.*;

import entities.Retangulo;
import erros.Figura2DException;
import org.junit.jupiter.api.Test;

public class RetanguloTest {

    @Test
    public void testConstrutorComLadosPositivos() {
        Retangulo retangulo = new Retangulo(6.0, 8.0);
        assertEquals(6.0, retangulo.getLargura(), 1e-6);
        assertEquals(8.0, retangulo.getAltura(), 1e-6);
    }

    @Test
    public void testConstrutorComLadoNegativo() {
        assertThrows(Figura2DException.class, () -> new Retangulo(-3.0, 4.0));
        assertThrows(Figura2DException.class, () -> new Retangulo(3.0, -4.0));
    }

    @Test
    public void testSetLadoAComValorPositivo() {
        Retangulo retangulo = new Retangulo(6.0, 8.0);
        retangulo.setAltura(10.0);
        assertEquals(10.0, retangulo.getAltura(), 1e-6);

        retangulo.setLargura(10.0);
        assertEquals(10.0, retangulo.getLargura(), 1e-6);
    }

    @Test
    public void testSetLadoAComValorNegativo() {
        Retangulo retangulo = new Retangulo(6.0, 8.0);
        assertThrows(Figura2DException.class, () -> retangulo.setAltura(-5.0));
    }

}
