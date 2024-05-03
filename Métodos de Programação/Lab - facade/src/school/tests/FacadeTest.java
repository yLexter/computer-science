package school.tests;

import org.junit.jupiter.api.*;
import school.facades.*;
import school.entities.*;
import school.erros.*;
import school.servicos.*;

import static org.junit.jupiter.api.Assertions.*;

public class FacadeTest {

    private FachadaControleAcademico fachada;
    private Professor professor;
    private Aluno aluno;
    private Disciplina disciplina;
    private Turma turma;
    private Sala sala;
    private AlunoTurma alunoTurmaHistorico;

    @BeforeEach
    public void setUp() {
        this.fachada = new FachadaControleAcademico(
                new AlunoServico(),
                new ProfessorSevico(),
                new DisciplinaServico(),
                new TurmaServico(),
                new SalaServico()
        );

        this.disciplina = fachada.adicionarDisciplina("MAT123", "Matemática", 1);
        this.professor = fachada.adicionarProfessor("João", "Silva", "123456789");
        this.aluno = fachada.adicionaAluno("Pedro", "Santos", "111222333");
        this.alunoTurmaHistorico = new AlunoTurma(aluno.getMatricula(), 14, 10, 10);

        fachada.adicionarCadeiraNoHistoricoAluno(
                aluno.getMatricula(),
                alunoTurmaHistorico,
                disciplina
        );

        this.turma = fachada.adicionarTurma(disciplina);

        fachada.adicionarAlunoEmTurma(turma.getId(), aluno.getMatricula());
        fachada.adicionarTurmaAProfessor(professor.getMatricula(), turma.getId());

        this.sala = fachada.adicionarSala("LAB V", 50);

        fachada.adicionarHorarioEmTurma(
                turma.getId(),
                "2M3M",
                sala.getNome()
        );
    }

    @Test
    public void testAdicionarDisciplina() {
        assertEquals(1, fachada.getDisciplinaServico().getDisciplinas().size());

        assertThrows(DisciplinaServicoException.class, () -> {
            fachada.adicionarDisciplina("MAT123", "Matemática", 1);
        });
    }

    @Test
    public void testAdicionarProfessor() {
        assertEquals(1, fachada.getProfessorSevico().getProfessores().size());

        assertThrows(ProfessorServicoException.class, () -> {
            fachada.adicionarProfessor("João", "Silva", "123456789");
        });
    }

    @Test
    public void testAdicionaAluno() {
        assertEquals(1, fachada.getAlunoServico().getAlunos().size());

        assertThrows(AlunoServicoException.class, () -> {
            fachada.adicionaAluno("Pedro", "Santos", "111222333");
        });
    }

    @Test
    public void testAdicionarSala() {
        assertEquals(1, fachada.getSalaServico().getSalas().size());

        assertThrows(SalaServicoException.class, () -> {
            fachada.adicionarSala("LAB V", 50);
        });
    }

    @Test
    public void testAdicionarHorarioEmTurma() {
        assertEquals(1, turma.getHorarios().size());

        assertThrows(TurmaSevicoException.class, () -> {
            fachada.adicionarHorarioEmTurma(turma.getId(), "2M3M", "LAB V");
        });

    }

    @Test
    public void testAdicionarTurmaAProfessor() {
        assertThrows(TurmaSevicoException.class, () -> {
            fachada.adicionarTurmaAProfessor(professor.getMatricula(), turma.getId());
        });
    }

    @Test
    public void testAdicionarCadeiraNoHistoricoAluno() {
        assertEquals(1, aluno.getHistorico().size());

        assertThrows(AlunoServicoException.class, () -> {
            fachada.adicionarCadeiraNoHistoricoAluno(
                    aluno.getMatricula(),
                    alunoTurmaHistorico,
                    disciplina
            );
        });
    }

    @Test
    public void testAdicionarAlunoEmTurma() {
        assertTrue(aluno.getTurmasId().contains(turma.getId()));

        // Tentar adicionar o mesmo aluno à mesma turma deve lançar exceção
        assertThrows(TurmaSevicoException.class, () -> {
            fachada.adicionarAlunoEmTurma(turma.getId(), aluno.getMatricula());
        });
    }

    @Test
    public void testObterProfessorPorMatricula() {
        assertEquals(professor, fachada.obterProfessorPorMatricula(professor.getMatricula()));

        // Verifique se a exceção é lançada para um professor inexistente
        assertThrows(ProfessorServicoException.class, () -> {
            fachada.obterProfessorPorMatricula("999999999");
        });
    }

    @Test
    public void testObterTurmaPorId() {
        assertEquals(turma, fachada.obterTurmaPorId(turma.getId()));

        // Verifique se a exceção é lançada para uma turma inexistente
        assertThrows(TurmaSevicoException.class, () -> {
            fachada.obterTurmaPorId("999999999");
        });
    }

    @Test
    public void testObterAlunoPorId() {
        assertEquals(aluno, fachada.obterAlunoPorId(aluno.getMatricula()));

        // Verifique se a exceção é lançada para um aluno inexistente
        assertThrows(AlunoServicoException.class, () -> {
            fachada.obterAlunoPorId("999999999");
        });
    }
}
