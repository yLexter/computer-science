package tests.entities;

import entities.AlunoTurma;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlunoTurmaTest {

    private AlunoTurma alunoTurma;

    @BeforeEach
    public void setUp() {
        // Configuração inicial
        alunoTurma = new AlunoTurma("123456");
    }

    @Test
    public void testGetMatricula() {
        // Verificar se o método getMatricula() retorna a matrícula correta
        assertEquals("123456", alunoTurma.getMatricula());
    }
}
