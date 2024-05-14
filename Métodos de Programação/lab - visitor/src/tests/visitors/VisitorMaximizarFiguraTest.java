package tests.visitors;

import entities.Circulo;
import entities.Retangulo;
import entities.Trapezio;
import entities.Triangle;
import interfaces.IVisitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import visitors.VisitorMaximizarFigura;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VisitorMaximizarFiguraTest {

    private VisitorMaximizarFigura visitor;

    @BeforeEach
    public void setUp() {
        this.visitor = new VisitorMaximizarFigura();
    }

    @Test
    public void testMaximizaCirculo() {
        Circulo circulo = new Circulo(5); // Raio inicial de 5
        visitor.visitaCirculo(circulo);
        assertEquals(10, circulo.getRaio()); // Espera-se que o raio seja duplicado
    }

    @Test
    public void testMaximizaTriangulo() {
        Triangle triangle = new Triangle(3, 4, 5); // Lados iniciais de 3, 4 e 5

        visitor.visitaTriangulo(triangle);
        assertEquals(6, triangle.getSide1()); // Espera-se que cada lado seja multiplicado por 2
        assertEquals(8, triangle.getSide2());
        assertEquals(10, triangle.getSide3());
    }

    @Test
    public void testMaximizaRetangulo() {
        Retangulo retangulo = new Retangulo(4, 6); // Largura inicial de 4 e altura de 6

        visitor.visitaRetangulo(retangulo);
        assertEquals(8, retangulo.getLargura()); // Espera-se que a largura e a altura sejam duplicadas
        assertEquals(12, retangulo.getAltura());
    }

    @Test
    public void testMaximizaTrapezio() {
        Trapezio trapezio = new Trapezio(3, 5, 4, 4, 3); // Bases iniciais de 3 e 5, lados de 4 e 3

        visitor.visitaTrapezio(trapezio);
        assertEquals(6, trapezio.getBaseMaior()); // Espera-se que as bases e os lados sejam multiplicados por 2
        assertEquals(10, trapezio.getBaseMenor());
        assertEquals(8, trapezio.getLado1());
        assertEquals(6, trapezio.getLado2());
    }
}
