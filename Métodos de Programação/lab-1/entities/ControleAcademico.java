package entities;

import utils.Examples;
import utils.Global;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ControleAcademico {
    private List<Aluno> alunos;
    private List<Professor> professores;
    private List<Turma> turmas;
    private List<Disciplina> disciplinas;

    public ControleAcademico() {
        this.alunos = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.turmas = new ArrayList<>();
        this.disciplinas = new ArrayList<>();
    }

    public Professor obterProfessorPorMatricula(String matricula) {
        return professores.stream()
                .filter(professor -> professor.getMatricula().equals(matricula))
                .findAny()
                .orElse(null);
    }

    public List<Turma> obterTurmasDeUmAluno(Aluno aluno) {
        List<String> turmasId = aluno.getTurmasId();

        return turmas.stream()
                .filter(
                        turma ->
                                turmasId.stream()
                                        .anyMatch(turmaId -> turma.getId().equals(turmaId)))
                .collect(Collectors.toList());
    }

    public boolean verificarHorarioEmUso(Horario novoHorario) {

        for (Turma turma : turmas) {

            for (Horario horario : turma.getHorarios()) {
                if (novoHorario.equals(horario)) return true;
            }
        }

        return false;
    }

    // -- Adicionar Instancias
    public Professor adicionarProfessor(String nome, String sobrenome, String cpf) {
        var professor = new Professor(nome, sobrenome, cpf);

        professores.add(professor);

        return professor;
    }

    public Aluno adicionaAluno(String nome, String sobrenome, String cpf) {
        var aluno = new Aluno(nome, sobrenome, cpf);

        alunos.add(aluno);

        return aluno;
    }

    public Turma adicionarTurma(Disciplina disciplina) {
        var turma = new Turma(disciplina);

        turmas.add(turma);

        return turma;
    }

    public Disciplina adicionarDisciplina(String codigo, String nome, int periodo) {
        Disciplina disciplina = new Disciplina(codigo, nome, periodo);

        disciplinas.add(disciplina);

        return disciplina;
    }

    // ------------------ Getters e Setters

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    // ------------------ Métodos estaticos
    public static void setarBancoDeDados() {
        ControleAcademico controleAcademico = Global.getControleAcademico();

        var examples = Examples.getAll();

        List<Professor> professores = (List<Professor>) examples.get("professores");
        List<Aluno> alunos = (List<Aluno>) examples.get("alunos");
        List<Turma> turmas = (List<Turma>) examples.get("turmas");

        controleAcademico.setAlunos(alunos);
        controleAcademico.setTurmas(turmas);
        controleAcademico.setProfessores(professores);
    }
}
