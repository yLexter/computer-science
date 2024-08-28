package lab3.testes;

import lab3.entidades.Circulo;
import lab3.entidades.Quadrado;
import lab3.erros.Figura2DException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CirculoTest {

    public Circulo circulo;

    @BeforeEach
    public void setUp() throws Figura2DException {
        this.circulo = new Circulo(5.0);
    }

    @Test
    public void testCirculoComRaioPositivo() {
        try {
            Circulo circulo = new Circulo(2.0);

            assertEquals(Math.PI * 2.0 * 2.0, circulo.getArea());
            assertEquals(2.0 * Math.PI * 2.0, circulo.getPerimetro());

        } catch (Figura2DException e) {
            fail("Não é para lançar exceção");
        }

    }

    @Test
    public void testCirculoComRaioInvalido() {
        assertThrows(Figura2DException.class, () -> {
            new Circulo(-1.0);
        });

        assertThrows(Figura2DException.class, () -> {
            new Circulo(0.0);
        });
    }

    @Test
    public void testSetQuadrado() {

        assertThrows(Figura2DException.class, () -> {
            circulo.setRaio(0.0);
        });


        assertThrows(Figura2DException.class, () -> {
            circulo.setRaio(-1.0);
        });
    }

}