package school;

import school.entities.*;
import school.facades.FachadaControleAcademico;
import school.servicos.*;

public class Main {

    public static void main(String[] args) {

        // Fachada instancia
        FachadaControleAcademico fachada = new FachadaControleAcademico(
                new AlunoServico(),
                new ProfessorSevico(),
                new DisciplinaServico(),
                new TurmaServico(),
                new SalaServico()
        );

        // Adicionando disciplinas
        Disciplina matematica = fachada.adicionarDisciplina("MAT123", "Matemática", 1);
        Disciplina portugues = fachada.adicionarDisciplina("POR456", "Português", 1);

        // Adicionando professores
        Professor prof1 = fachada.adicionarProfessor("João", "Silva", "123456789");
        Professor prof2 = fachada.adicionarProfessor("Maria", "Souza", "987654321");

        // Adicionando alunos
        Aluno aluno1 = fachada.adicionaAluno("Pedro", "Santos", "111222333");
        Aluno aluno2 = fachada.adicionaAluno("Ana", "Ferreira", "444555666");

        // Adicionar historico de cadeira
        fachada.adicionarCadeiraNoHistoricoAluno(
             aluno1.getMatricula(),
             new AlunoTurma(aluno1.getMatricula(), 14, 10, 10),
             matematica
        );

        fachada.adicionarCadeiraNoHistoricoAluno(
                aluno1.getMatricula(),
                new AlunoTurma(aluno1.getMatricula(), 14, 10, 10),
                portugues
        );

        fachada.adicionarCadeiraNoHistoricoAluno(
                aluno2.getMatricula(),
                new AlunoTurma(aluno1.getMatricula(), 10, 10, 4),
                portugues
        );

        // Adicionando turmas
        Turma turmaMatematica = fachada.adicionarTurma(matematica);
        Turma turmaPortugues = fachada.adicionarTurma(portugues);

        // Adicionando alunos na turma
        fachada.adicionarAlunoEmTurma(turmaMatematica.getId(), aluno1.getMatricula());
        fachada.adicionarAlunoEmTurma(turmaMatematica.getId(), aluno2.getMatricula());

        fachada.adicionarAlunoEmTurma(turmaPortugues.getId(), aluno2.getMatricula());

        // Adicionar professor a turma
        fachada.adicionarTurmaAProfessor(prof1.getMatricula(), turmaMatematica.getId());
        fachada.adicionarTurmaAProfessor(prof2.getMatricula(), turmaPortugues.getId());

        // Adicionando salas
        Sala sala1 = fachada.adicionarSala("LAB V", 50);
        Sala sala2 = fachada.adicionarSala("LAB IV", 50);

        // Add horario
        fachada.adicionarHorarioEmTurma(
              turmaMatematica.getId(),
              "2M3M",
              sala1.getNome()
        );

        fachada.adicionarHorarioEmTurma(
                turmaMatematica.getId(),
                "5M6M",
                sala1.getNome()
        );

        fachada.adicionarHorarioEmTurma(
                turmaPortugues.getId(),
                "1M4M",
                sala2.getNome()
        );


        // Mostrar relatorio
        fachada.mostrarDadosControleAcademico();
    }


}