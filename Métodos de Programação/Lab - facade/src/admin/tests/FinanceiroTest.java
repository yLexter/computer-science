package admin.tests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import admin.entities.Financeiro;

public class FinanceiroTest {

    private Financeiro financeiro;

    @BeforeEach
    public void setUp() {
        financeiro = new Financeiro();
    }

    @Test
    public void testAtualizarBalancoContas() {
        financeiro.atualizarBalancoContas("Receita", 10000.0);
        financeiro.atualizarBalancoContas("Despesa", -5000.0);

        Map<String, Double> balancoContas = financeiro.getBalancoContas();

        assertNotNull(balancoContas);
        assertEquals(2, balancoContas.size());
        assertEquals(Double.valueOf(10000.0), balancoContas.get("Receita"));
        assertEquals(Double.valueOf(-5000.0), balancoContas.get("Despesa"));
    }

    @Test
    public void testProcessarFolhaPagamento() {
        financeiro.processarFolhaPagamento("Funcionario A", 2500.0);
        financeiro.processarFolhaPagamento("Funcionario B", 3000.0);

        Map<String, Double> folhaPagamento = financeiro.getFolhaPagamento();

        assertNotNull(folhaPagamento);
        assertEquals(2, folhaPagamento.size());
        assertEquals(Double.valueOf(2500.0), folhaPagamento.get("Funcionario A"));
        assertEquals(Double.valueOf(3000.0), folhaPagamento.get("Funcionario B"));
    }

    @Test
    public void testProcessarFolhaPagamentoComSalarioNegativo() {
        try {
            financeiro.processarFolhaPagamento("Funcionario A", -2500.0);
        } catch (IllegalArgumentException e) {
            assertEquals("Salário não pode ser negativo", e.getMessage());
        }
    }
}
