package admin.tests;

import java.util.List;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import admin.entities.Administrativo;

public class AdministrativoTest {

    private Administrativo administrativo;

    @BeforeEach
    public void setUp() {
        administrativo = new Administrativo();
    }

    @Test
    public void testAgendarReuniao() {
        administrativo.agendarReuniao("Reunião de diretoria");
        administrativo.agendarReuniao("Reunião de equipe");
        administrativo.agendarReuniao("Reunião de planejamento");

        List<String> reunioesAgendadas = administrativo.getReunioesAgendadas();

        assertNotNull(reunioesAgendadas);
        assertEquals(3, reunioesAgendadas.size());
        assertEquals("Reunião de diretoria", reunioesAgendadas.get(0));
        assertEquals("Reunião de equipe", reunioesAgendadas.get(1));
        assertEquals("Reunião de planejamento", reunioesAgendadas.get(2));
    }

    @Test
    public void testAgendarEntrevista() {
        administrativo.agendarEntrevista("Entrevista com candidato 1");
        administrativo.agendarEntrevista("Entrevista com candidato 2");

        List<String> entrevistas = administrativo.getEntrevistas();

        assertNotNull(entrevistas);
        assertEquals(2, entrevistas.size());
        assertEquals("Entrevista com candidato 1", entrevistas.get(0));
        assertEquals("Entrevista com candidato 2", entrevistas.get(1));
    }
}

