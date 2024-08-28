package tests.entities;

import entities.ControleAcademico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Global;
import static org.junit.jupiter.api.Assertions.*;

public class AdicionarTest {

    private ControleAcademico controleAcademico;

    @BeforeEach
    public void setUp() {
        this.controleAcademico = Global.getControleAcademico();

        ControleAcademico.resetarBancoDeDados();
    }

    @Test
    public void adicionarAlunoTest() {
        var aluno = controleAcademico.adicionaAluno("t", "t", "t");

        assertEquals(1, controleAcademico.getAlunos().size());

        assertThrows(IllegalArgumentException.class, () -> {
            controleAcademico.adicionaAluno(
                 aluno.getNome(),
                 aluno.getSobrenome(),
                 aluno.getCpf()
            );
        });
    }

    @Test
    public void adicionarProfessorTest() {
        var professor = controleAcademico.adicionarProfessor("t", "t", "t");

        assertEquals(1, controleAcademico.getProfessores().size());

        assertThrows(IllegalArgumentException.class, () -> {
            controleAcademico.adicionarProfessor(
                    professor.getNome(),
                    professor.getSobrenome(),
                    professor.getCpf()
            );
        });
    }

    @Test
    public void adicionarDisciplinaTest() {
        var disciplina = controleAcademico.adicionarDisciplina("t", "t", 5);

        assertEquals(1, controleAcademico.getDisciplinas().size());

        assertThrows(IllegalArgumentException.class, () -> {
            controleAcademico.adicionarDisciplina(
                    disciplina.getCodigo(),
                    disciplina.getNome(),
                    disciplina.getPeriodo()
            );
        });
    }

}
