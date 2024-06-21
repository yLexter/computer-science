package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import sanduiche.Sanduiche;
import sanduicheFactory.*;

public class SanduicheFactoryTest {

    @Test
    public void testSanduicheBolaCheddarFrangoCapoeira() {
        Sanduiche sanduiche = new SanduicheBolaCheddarFrangoCapoeira();
        assertEquals("Pão Bola", sanduiche.criarPao().getTipo());
        assertEquals("Queijo Cheddar", sanduiche.criarQueijo().getTipo());
        assertEquals("Presunto de Frango", sanduiche.criarPresunto().getTipo());
        assertEquals("Ovo de Capoeira", sanduiche.criarOvo().getTipo());
        assertEquals("Tomate", sanduiche.criarTomate().getTipo());
    }

    @Test
    public void testSanduicheFrancesMussarelaPeruGranja() {
        Sanduiche sanduiche = new SanduicheFrancesMussarelaPeruGranja();
        assertEquals("Pão Francês", sanduiche.criarPao().getTipo());
        assertEquals("Queijo Mussarela", sanduiche.criarQueijo().getTipo());
        assertEquals("Presunto de Peru", sanduiche.criarPresunto().getTipo());
        assertEquals("Ovo de Granja", sanduiche.criarOvo().getTipo());
        assertEquals("Tomate", sanduiche.criarTomate().getTipo());
    }

    @Test
    public void testSanduicheIntegralCheddarPeruGranja() {
        Sanduiche sanduiche = new SanduicheIntegralCheddarPeruGranja();
        assertEquals("Pão Integral", sanduiche.criarPao().getTipo());
        assertEquals("Queijo Cheddar", sanduiche.criarQueijo().getTipo());
        assertEquals("Presunto de Peru", sanduiche.criarPresunto().getTipo());
        assertEquals("Ovo de Granja", sanduiche.criarOvo().getTipo());
        assertEquals("Tomate", sanduiche.criarTomate().getTipo());
    }
}
