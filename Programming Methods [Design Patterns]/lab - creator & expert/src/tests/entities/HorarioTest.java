package tests.entities;

import entities.Horario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HorarioTest {

    private Horario horario;

    @BeforeEach
    public void setUp() {
        // Configuração inicial
        horario = new Horario("08:00", "ID_TURMA");
    }

    @Test
    public void testGetHorario() {
        // Verificar se o método getHorario() retorna o horário correto
        assertEquals("08:00", horario.getHorario());
    }

    @Test
    public void testGetIdTurma() {
        // Verificar se o método getIdTurma() retorna o ID da turma correto
        assertEquals("ID_TURMA", horario.getIdTurma());
    }
}
