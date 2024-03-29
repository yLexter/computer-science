package tests.entities;

import entities.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Examples;
import utils.Global;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TurmaTest {

    private Aluno aluno;

    private Turma turma;

    public Horario horario;

    private ControleAcademico controleAcademico = Global.getControleAcademico();

    @BeforeEach
    public void setUp() {
        ControleAcademico.setarBancoDeDados();

        this.turma = controleAcademico.getTurmas().get(0);
        this.aluno = new Aluno("July", "July", "shaushauashaa");
        this.horario = new Horario("1vB4", "ID_Turma");
    }

    @Test
    public void testAdicionarAlunoRepetido() {
        turma.adicionarAluno(aluno);

        assertThrows(IllegalArgumentException.class, () -> turma.adicionarAluno(aluno));
    }

    @Test
    public void testAdicionarHorario() {
        turma.adicionarHorario(horario.getHorario());

        assertEquals(turma.getHorarios().size(), 3);
    }

    @Test
    public void testAdicionarHorarioRepetido() {
        turma.adicionarHorario(horario.getHorario());

        assertThrows(
                IllegalArgumentException.class, () -> turma.adicionarHorario(horario.getHorario()));
    }
}
