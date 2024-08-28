package lab3.testes;

import lab3.entidades.Retangulo;
import lab3.erros.Figura2DException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RetanguloTest {

    private Retangulo retangulo;

    @BeforeEach
    public void setUp() throws Figura2DException {
        this.retangulo = new Retangulo(5.0, 10.0);
    }

    @Test
    public void testRetanguloComMedidasPositivas() {
        try {
            Retangulo retangulo = new Retangulo(2.0, 4.0);

            assertEquals(8.0, retangulo.getArea());
            assertEquals(12.0, retangulo.getPerimetro());

        } catch (Figura2DException e) {
            fail("Não é para lançar exceção");
        }

    }

    @Test
    public void testRetanguloComMedidasInvalidas() {
        assertThrows(Figura2DException.class, () -> {
            new Retangulo(-1.0, 2.0);
        });

        assertThrows(Figura2DException.class, () -> {
            new Retangulo(1.0, -2.0);
        });

        assertThrows(Figura2DException.class, () -> {
            new Retangulo(-1.0, -2.0);
        });
    }

    @Test
    public void testSetAltura() {
        assertThrows(Figura2DException.class, () -> {
            retangulo.setAltura(0.0);
        });

        assertThrows(Figura2DException.class, () -> {
            retangulo.setAltura(-1.0);
        });
    }

    @Test
    public void testSetLargura() {
        assertThrows(Figura2DException.class, () -> {
            retangulo.setLargura(0.0);
        });

        assertThrows(Figura2DException.class, () -> {
            retangulo.setLargura(-1.0);
        });
    }
}
