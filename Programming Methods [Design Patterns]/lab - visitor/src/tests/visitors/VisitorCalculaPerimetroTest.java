package tests.visitors;

import entities.*;
import interfaces.IVisitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import visitors.*;

import static org.junit.jupiter.api.Assertions.*;

public class VisitorCalculaPerimetroTest {

    private VisitorCalculaPerimetro visitor;

    @BeforeEach
    public void setUp() {
        this.visitor = new VisitorCalculaPerimetro();
    }

    @Test
    public void testCalculaPerimetroCirculo() {
        Circulo circulo = new Circulo(5.0);
        double perimetro = circulo.aceitarVisitor(visitor);
        assertEquals(2 * Math.PI * 5.0, perimetro, 1e-6);
    }

    @Test
    public void testCalculaPerimetroTriangulo() {
        Triangle triangulo = new Triangle(3.0, 4.0, 5.0);
        double perimetro = triangulo.aceitarVisitor(visitor);
        assertEquals(12.0, perimetro, 1e-6);
    }

    @Test
    public void testCalculaPerimetroRetangulo() {
        Retangulo retangulo = new Retangulo(6.0, 8.0);
        double perimetro = retangulo.aceitarVisitor(visitor);
        assertEquals(28.0, perimetro, 1e-6);
    }

    @Test
    public void testCalculaPerimetroTrapezio() {
        Trapezio trapezio = new Trapezio(4, 6, 3, 4, 5);

        double perimetro = trapezio.aceitarVisitor(visitor);
        assertEquals(19.0, perimetro, 1e-6);
    }
}
