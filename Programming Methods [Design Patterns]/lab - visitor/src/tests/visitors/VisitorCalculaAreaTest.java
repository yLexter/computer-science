package tests.visitors;

import entities.*;
import interfaces.IVisitor;
import org.junit.jupiter.api.BeforeEach;
import visitors.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class VisitorCalculaAreaTest {

    public VisitorCalculaArea visitor;
    @BeforeEach
    public void setUp() {
        this.visitor = new VisitorCalculaArea();
    }

    @Test
    public void testCalculaAreaCirculo() {
        Circulo circulo = new Circulo(5.0);
        Double area = circulo.aceitarVisitor(visitor);
        assertEquals(Math.PI * 25.0, area, 1e-6);
    }

    @Test
    public void testCalculaAreaTriangulo() {
        Triangle triangulo = new Triangle(3.0, 4.0, 5.0);
        double area = triangulo.aceitarVisitor(visitor);
        assertEquals(6.0, area, 1e-6);
    }

    @Test
    public void testCalculaAreaRetangulo() {
        Retangulo retangulo = new Retangulo(6.0, 8.0);
        double area = retangulo.aceitarVisitor(visitor);
        assertEquals(48.0, area, 1e-6);
    }

}
