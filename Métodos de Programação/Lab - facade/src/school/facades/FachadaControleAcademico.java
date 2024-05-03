package school.facades;

import school.entities.*;
import school.servicos.*;

import java.util.List;

public class FachadaControleAcademico {
    private final AlunoServico alunoServico;
    private final ProfessorSevico professorSevico;
    private final DisciplinaServico disciplinaServico;
    private final TurmaServico turmaServico;
    private final SalaServico salaServico;

    public FachadaControleAcademico(AlunoServico alunoServico, ProfessorSevico professorSevico, DisciplinaServico disciplinaServico, TurmaServico turmaServico, SalaServico salaServico) {
        this.alunoServico = alunoServico;
        this.professorSevico = professorSevico;
        this.disciplinaServico = disciplinaServico;
        this.turmaServico = turmaServico;
        this.salaServico = salaServico;
    }

    public FachadaControleAcademico() {
        this.alunoServico = new AlunoServico();
        this.professorSevico = new ProfessorSevico();
        this.disciplinaServico = new DisciplinaServico();
        this.turmaServico = new TurmaServico();
        this.salaServico = new SalaServico();
    }

    // - Getters
    public List<Turma> obterTurmasDeUmAluno(String alunoId) {
        Aluno aluno = alunoServico.obterAlunoPorId(alunoId);
        List<String> turmasId = aluno.getTurmasId();

        return turmaServico.obterTurmaDeUmAluno(turmasId);
    }

    public Professor adicionarProfessor(String nome, String sobrenome, String cpf) {
        return professorSevico.adicionarProfessor(nome, sobrenome, cpf);
    }

    public Aluno adicionaAluno(String nome, String sobrenome, String cpf) {
        return alunoServico.adicionaAluno(nome, sobrenome, cpf);
    }

    public Turma obterTurmaPorId(String id) {
        return turmaServico.obterTurmaPorId(id);
    }

    public Turma adicionarTurma(Disciplina disciplina) {
        return turmaServico.adicionarTurma(disciplina);
    }

    public Aluno obterAlunoPorId(String id) {
        return alunoServico.obterAlunoPorId(id);
    }

    public Professor obterProfessorPorMatricula(String id) {
        return professorSevico.obterProfessorPorMatricula(id);
    }

    // -- Adicionar Instancias
    public void adicionarAlunoEmTurma(String turmaId, String alunoId) {
        turmaServico.adicionarAlunoATurma(alunoId, turmaId);
        alunoServico.adicionarTurmaAAluno(alunoId, turmaId);
    }

    public HistoricoAluno adicionarCadeiraNoHistoricoAluno(String idAluno, AlunoTurma novaCadeira, Disciplina disciplina) {
        HistoricoAluno historicoAluno = new HistoricoAluno(disciplina, novaCadeira);

        return alunoServico.adicionarCadeiraNoHistoricoAluno(idAluno, historicoAluno);
    }

    public void adicionarTurmaAProfessor(String idProfessor, String idTurma) {
        turmaServico.adicionarTurmaAProfessor(idProfessor, idTurma);
    }

    public Disciplina adicionarDisciplina(String codigo, String nome, int periodo) {
        return disciplinaServico.adicionarDisciplina(codigo, nome, periodo);
    }

    public Horario adicionarHorarioEmTurma(String turmaId, String horarioTurma, String nomeSala) {
        return turmaServico.adicionarHorarioEmTurma(turmaId, horarioTurma, nomeSala);
    }

    public Sala adicionarSala(String nome, int capacidade) {
        return salaServico.adicionarSala(nome, capacidade);
    }

    // Relatorios
    public void mostrarDadosControleAcademico() {

        var alunos = alunoServico.getAlunos();
        var professores = professorSevico.getProfessores();
        var turmas = turmaServico.getTurmas();

        System.out.println("-".repeat(20) + " Alunos " + "-".repeat(20));
        System.out.println();

        for (var aluno : alunos) {
            var turmasAluno = obterTurmasDeUmAluno(aluno.getMatricula());

            System.out.println();
            System.out.println("---------------------------------");
            System.out.println(aluno);

            System.out.println();
            System.out.println("--------------- RDM ---------------");

            for (var turma : turmasAluno) {
                System.out.println(turma);
            }

            System.out.println("---------------------------------");
            System.out.println();
            System.out.println();

            System.out.println("--------------- Historico ---------------");

            for (var cadeira : aluno.getHistorico()) {
                System.out.println(cadeira);
            }

            System.out.println("---------------------------------");
            System.out.println();
            System.out.println();
        }

        System.out.println();

        System.out.println("-".repeat(20) + " Professores " + "-".repeat(20));
        System.out.println();

        for (var professor : professores) {
            System.out.println(professor);
        }

        System.out.println();
        System.out.println("-".repeat(50));
        System.out.println();

        for (var turma : turmas) {
            var professor = obterProfessorPorMatricula(turma.getIdProfessor());

            System.out.println();
            System.out.println("------------------------- Turma -------------------------");
            System.out.println();
            System.out.println(turma.getDisciplina());

            System.out.printf("Professor: %s\n", professor);

            System.out.println();
            System.out.println("------------ Alunos --------------------");

            for (var aluno : turma.getAlunos()) {
                System.out.println(aluno);
            }

            System.out.println();
            System.out.println("--------------- Horario ----------------------");

            for (var horario : turma.getHorarios()) {
                System.out.println(horario);
            }

            System.out.println("-------------------------------------------");
            System.out.println();
        }

    }

    // getters fachada
    public AlunoServico getAlunoServico() {
        return alunoServico;
    }

    public ProfessorSevico getProfessorSevico() {
        return professorSevico;
    }

    public DisciplinaServico getDisciplinaServico() {
        return disciplinaServico;
    }

    public TurmaServico getTurmaServico() {
        return turmaServico;
    }

    public SalaServico getSalaServico() {
        return salaServico;
    }
}
