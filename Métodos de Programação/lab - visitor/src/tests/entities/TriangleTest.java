package tests.entities;

import static org.junit.jupiter.api.Assertions.*;

import entities.Triangle;
import erros.Figura2DException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TriangleTest {

    private Triangle triangle;

    @BeforeEach
    public void setUp() {
        triangle = new Triangle(3.0, 4.0, 5.0);
    }

    @Test
    public void testConstrutorComLadoNegativo() {
        assertThrows(Figura2DException.class, () -> new Triangle(-3.0, 4.0, 5.0));
    }

    @Test
    public void testConstrutorComTrianguloInvalido() {
        assertThrows(Figura2DException.class, () -> new Triangle(1.0, 2.0, 5.0));
    }
    @Test
    public void testSetSide1ComValorInvalido() {
        assertThrows(Figura2DException.class, () -> triangle.setSide1(-5.0));
        assertThrows(Figura2DException.class, () -> triangle.setSide1(50.0));
    }

    @Test
    public void testSetSide2ComValorInvalido() {
        assertThrows(Figura2DException.class, () -> triangle.setSide2(-4.0));
        assertThrows(Figura2DException.class, () -> triangle.setSide2(50.0));
    }

    @Test
    public void testSetSide3ComValorInvalido() {
        assertThrows(Figura2DException.class, () -> triangle.setSide3(-3.0));
        assertThrows(Figura2DException.class, () -> triangle.setSide3(50.0));
    }

}

