package lab3.testes;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import lab3.entidades.Quadrado;
import lab3.erros.Figura2DException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuadradoTest {

    public Quadrado quadrado;

    @BeforeEach
    public void setUp() throws Figura2DException {
        this.quadrado = new Quadrado(5.0);
    }

    @Test
    public void testQuadradoComRaioPositivo() {
        try {
            Quadrado quadrado = new Quadrado(2.0);

            Assertions.assertEquals(2.0 * 2, quadrado.getArea());
            Assertions.assertEquals(4 * 2.0, quadrado.getPerimetro());

        } catch (Figura2DException e) {
            Assertions.fail("Não é para lançar exceção");
        }
    }

    @Test
    public void testQuadradoComLadoInvalido() {
        assertThrows(Figura2DException.class, () -> {
            new Quadrado(0.0);
        });

        assertThrows(Figura2DException.class, () -> {
            new Quadrado(-1.0);
        });
    }

    @Test
    public void testSetQuadrado() {
        assertThrows(Figura2DException.class, () -> {
            quadrado.setLado(0.0);
        });

        assertThrows(Figura2DException.class, () -> {
            quadrado.setLado(-1.0);
        });
    }

}
