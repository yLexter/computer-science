package tests.entities;

import entities.Disciplina;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DisciplinaTest {

    @Test
    public void testCriacaoDisciplinaComHorasPositivas() {
        // Teste para criação de disciplina com horas positivas
        Disciplina disciplina = new Disciplina("MAT123", "Matemática", 60, 2);

        // Verificar se os valores foram definidos corretamente
        assertEquals("MAT123", disciplina.getCodigo());
        assertEquals("Matemática", disciplina.getNome());
        assertEquals(60, disciplina.getHoras());
    }

    @Test
    public void testCriacaoDisciplinaComHorasNegativas() {
        // Teste para criar disciplina com horas negativas
        assertThrows(IllegalArgumentException.class, () -> {
            new Disciplina("FIS456", "Física", -10, 1);
        });
    }

    @Test
    public void testCriacaoDisciplinaComNomeNulo() {
        // Teste para criar disciplina com nome nulo
        assertThrows(IllegalArgumentException.class, () -> {
            new Disciplina("QUI789", null, 2);
        });
    }

    @Test
    public void testCriacaoDisciplinaComCodigoNulo() {
        // Teste para criar disciplina com código nulo
        assertThrows(IllegalArgumentException.class, () -> {
            new Disciplina(null, "Química", 3);
        });
    }

    @Test
    public void testCriacaoDisciplinaComPeriodoNegativo() {
        // Teste para criar disciplina com período negativo
        assertThrows(IllegalArgumentException.class, () -> {
            new Disciplina("BIO101", "Biologia", -5);
        });
    }
}
