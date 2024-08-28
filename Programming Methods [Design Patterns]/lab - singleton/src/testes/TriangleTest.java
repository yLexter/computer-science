package testes;

import entities.Triangle;
import enums.TriangleKind;
import erros.Figura2DException;
import erros.SingletonException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TriangleTest {


    @AfterEach
    public void reset() {
        Triangle.resetInstances();
    }

    @Test
    public void createValidTriangle() {
        Triangle.createInstance(3, 4, 5);

        var triangle = Triangle.getInstance(TriangleKind.RECTANGLE);
        assertNotNull(triangle);
        assertEquals(TriangleKind.RECTANGLE, triangle.getKind());
    }

    @Test
    public void insertMoreThenAllowed() {
        Triangle.createInstance(3, 4, 5);
        Triangle.createInstance(6, 6, 6);
        Triangle.createInstance(6, 6, 8);

        assertThrows(SingletonException.class, () -> Triangle.createInstance(1, 2, 3));
    }

    @Test
    public void insertDuplicate() {
        Triangle.createInstance(3, 4, 5);
        assertThrows(SingletonException.class, () -> Triangle.createInstance(3, 4, 5));

        Triangle.createInstance(6, 6, 6);
        assertThrows(SingletonException.class, () -> Triangle.createInstance(6, 6, 6));

        Triangle.createInstance(7, 7, 9);
        assertThrows(SingletonException.class, () -> Triangle.createInstance(7, 7, 9));
    }

    @Test
    public void testInsertEscaleneTriangle() {
        assertThrows(SingletonException.class, () -> Triangle.createInstance(7, 8, 9));
    }

    @Test
    public void getEquilateralTriangle() {
        Triangle.createInstance(6, 6, 6);

        var triangle = Triangle.getInstance(TriangleKind.EQUILATERAL);
        assertNotNull(triangle);
        assertEquals(TriangleKind.EQUILATERAL, triangle.getKind());
    }

    @Test
    public void getTriangleIsosceles() {
        Triangle.createInstance(7, 7, 9);

        var triangle = Triangle.getInstance(TriangleKind.ISOSCELES);
        assertNotNull(triangle);
        assertEquals(TriangleKind.ISOSCELES, triangle.getKind());
    }

    @Test
    public void createTriangleWithNegativeSide() {
        assertThrows(Figura2DException.class, () -> Triangle.createInstance(-1, 4, 4));
    }

    @Test
    public void createTriangleWithZeroSide() {
        assertThrows(Figura2DException.class, () -> Triangle.createInstance(0, 2, 2));
    }

    @Test
    public void createTriangleWithImpossibleSides() {
        assertThrows(Figura2DException.class, () -> Triangle.createInstance(1, 1, 10));
    }

}
