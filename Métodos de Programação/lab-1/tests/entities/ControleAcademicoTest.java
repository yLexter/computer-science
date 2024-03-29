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
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class ControleAcademicoTest {

    private Aluno aluno;
    private Professor professor;
    private Turma turma;

    private ControleAcademico controleAcademico = Global.getControleAcademico();

    @BeforeEach
    public void setUp() {
        ControleAcademico.setarBancoDeDados();

        this.aluno = controleAcademico.getAlunos().get(0);
        this.professor = controleAcademico.getProfessores().get(0);
        this.turma = controleAcademico.getTurmas().get(0);
    }

    @Test
    public void testObterProfessorPorMatricula() {

        assertEquals(
                professor, controleAcademico.obterProfessorPorMatricula(professor.getMatricula()));
    }

    @Test
    public void testVerificarHorarioEmUso() {
        Horario horario = controleAcademico.getTurmas().get(0).getHorarios().get(0);

        assertTrue(controleAcademico.verificarHorarioEmUso(horario));
    }

    @Test
    public void testObterTurmasDeUmAluno() {
        assertEquals(aluno.getTurmas(), controleAcademico.obterTurmasDeUmAluno(aluno));
    }
}
