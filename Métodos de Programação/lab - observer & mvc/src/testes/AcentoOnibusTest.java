package testes;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import enums.AcentoStatus;
import entities.AcentoOnibus;

public class AcentoOnibusTest {

    private AcentoOnibus acentoOnibus;

    @Before
    public void setUp() {
        // Inicializa um assento de ônibus com número 1
        acentoOnibus = new AcentoOnibus(1);
    }

    @Test
    public void testConstructor() {
        // Verifica se o número do assento é o esperado
        assertEquals(1, acentoOnibus.getNumero());

        // Verifica se o status inicial do assento é DISPONIVEL
        assertEquals(AcentoStatus.DISPONIVEL, acentoOnibus.getStatus());
    }

    @Test
    public void testSetStatus() {
        // Define o status do assento para RESERVADO
        acentoOnibus.setStatus(AcentoStatus.RESERVADO);

        // Verifica se o status do assento foi atualizado corretamente
        assertEquals(AcentoStatus.RESERVADO, acentoOnibus.getStatus());

        // Define o status do assento para INDISPONIVEL
        acentoOnibus.setStatus(AcentoStatus.INDISPONIVEL);

        // Verifica se o status do assento foi atualizado corretamente
        assertEquals(AcentoStatus.INDISPONIVEL, acentoOnibus.getStatus());
    }
}
