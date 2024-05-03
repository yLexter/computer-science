package admin.tests;

import admin.facade.SistemaFacade;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

public class SistemaFacadeTest {

    @Test
    public void testFacade() {
        SistemaFacade facade = new SistemaFacade();

        // Testando o agendamento de reuniões
        facade.agendarReuniao("Reunião de teste 1");
        facade.agendarReuniao("Reunião de teste 2");
        List<String> reunioesAgendadas = facade.getReunioesAgendadas();
        Assertions.assertEquals(2, reunioesAgendadas.size());
        Assertions.assertTrue(reunioesAgendadas.contains("Reunião de teste 1"));
        Assertions.assertTrue(reunioesAgendadas.contains("Reunião de teste 2"));

        // Testando o agendamento de entrevistas
        facade.agendarEntrevista("Entrevista de teste 1");
        List<String> entrevistas = facade.getEntrevistas();
        Assertions.assertEquals(1, entrevistas.size());
        Assertions.assertTrue(entrevistas.contains("Entrevista de teste 1"));

        // Testando a atualização do balanço de contas
        facade.atualizarBalancoContas("Balanço de teste", 10000.0);
        Map<String, Double> balancoContas = facade.getBalancoContas();
        Assertions.assertTrue(balancoContas.containsKey("Balanço de teste"));
        Assertions.assertEquals(10000.0, balancoContas.get("Balanço de teste"));

        // Adicione mais testes conforme necessário para cobrir todos os métodos da fachada.
    }
}
