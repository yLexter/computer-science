package labs;

import entities.*;
import interfaces.menu.*;
import utils.Global;

import java.util.List;

public class Lab1 extends BaseLab {

    @Override
    public List<IResponseLab> getOptions() {
        return List.of(
                new ResponseLab(
                        "Quais disciplinas que professores estão ministrando",
                        this::mostrarDisciplinasProfessores),
                new ResponseLab(
                        "Qual o horário de todos os professores",
                        this::mostrarHorarioDosProfessores),
                new ResponseLab(
                        "Quais os alunos de cada disciplina", this::mostrarAlunosPorDisciplina),
                new ResponseLab(
                        "Quais as disciplinas de todos os alunos",
                        this::mostrarDisciplinasDosAlunos),
                new ResponseLab("Qual o horário de todos os alunos", this::mostrarHorarioDosAlunos),
                new ResponseLab("Número de alunos p/ turma", this::mostrarNumeroAlunosDisciplina));
    }

    public void mostrarDisciplinasProfessores() {
        ControleAcademico controleAcademico = Global.getControleAcademico();
        List<Professor> professores = controleAcademico.getProfessores();

        for (Professor professor : professores) {
            System.out.println("Professor: " + professor.getNome());
            List<Turma> turmas = professor.getTurmas();

            for (Turma turma : turmas) {
                System.out.printf("Turma: [%s]\n", turma);
            }

            System.out.println();
        }
    }

    public void mostrarHorarioDosProfessores() {
        ControleAcademico controleAcademico = Global.getControleAcademico();
        List<Professor> professores = controleAcademico.getProfessores();

        for (Professor professor : professores) {
            System.out.println("Professor: " + professor.getNome());
            List<Horario> horarios = professor.getHorario();

            for (Horario horario : horarios) {
                System.out.printf("Horário: [%s]\n", horario);
            }

            System.out.println();
        }
    }

    public void mostrarAlunosPorDisciplina() {
        ControleAcademico controleAcademico = Global.getControleAcademico();
        List<Turma> turmas = controleAcademico.getTurmas();

        for (Turma turma : turmas) {
            System.out.println("Disciplina: " + turma.getNome());

            List<AlunoTurma> alunos = turma.getAlunos();

            for (AlunoTurma aluno : alunos) {
                System.out.printf("Aluno: [%s]\n", aluno);
            }

            System.out.println();
        }
    }

    public void mostrarDisciplinasDosAlunos() {
        ControleAcademico controleAcademico = Global.getControleAcademico();
        List<Aluno> alunos = controleAcademico.getAlunos();

        for (Aluno aluno : alunos) {
            System.out.println("Aluno: " + aluno.getNome());
            List<Turma> turmas = aluno.getTurmas();

            for (Turma turma : turmas) {
                System.out.printf("Turma: [%s]\n", turma);
            }

            System.out.println();
        }
    }

    public void mostrarHorarioDosAlunos() {
        ControleAcademico controleAcademico = Global.getControleAcademico();
        List<Aluno> alunos = controleAcademico.getAlunos();

        for (Aluno aluno : alunos) {
            System.out.println("Aluno: " + aluno.getNome());
            List<Horario> horarios = aluno.getHorario();

            for (Horario horario : horarios) {
                System.out.printf("Horario: [%s]\n", horario);
            }

            System.out.println();
        }
    }

    public void mostrarNumeroAlunosDisciplina() {
        ControleAcademico controleAcademico = Global.getControleAcademico();
        List<Turma> turmas = controleAcademico.getTurmas();

        for (Turma turma : turmas) {
            System.out.printf("Disciplina: [%s]\n", turma.getNome());
            System.out.println("- Número de Alunos: " + turma.getTotalDeAluno());
            System.out.println();
        }
    }
}
