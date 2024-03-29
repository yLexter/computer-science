package labs;

import entities.Aluno;
import entities.ControleAcademico;
import entities.Disciplina;
import entities.Professor;
import interfaces.menu.IResponseLab;
import utils.Global;

import java.util.List;

public class Lab2 extends BaseLab {

    public List<IResponseLab> getOptions() {
        return List.of(new ResponseLab("Padrão Creator", this::mainLab2));
    }

    public void mainLab2() {
        // Instanciando controle academico
        ControleAcademico controleAcademico = Global.getControleAcademico();

        // Adicionando Disciplinas
        var disciplina1 =
                controleAcademico.adicionarDisciplina("CPT01093", "PARADIGMAS DE PROGRAMAÇÃO", 5);

        var disciplina2 =
                controleAcademico.adicionarDisciplina("CPT01092", "ENGENHARIA DE SOFTWARE I", 5);

        // Adicionando alunos
        var aluno1 = controleAcademico.adicionaAluno("João", "Silva", "4568484648468");
        var aluno2 = controleAcademico.adicionaAluno("Maria", "Santos", "5846846488468");

        // Adicionando Professores
        var prof1 = controleAcademico.adicionarProfessor("Carlos", "Silveira", "4484646");
        var prof2 = controleAcademico.adicionarProfessor("Renata", "Lima", "4854325230223");

        // Adicionando Turmas;
        var turma1 = controleAcademico.adicionarTurma(disciplina1);
        var turma2 = controleAcademico.adicionarTurma(disciplina2);

        // Adicionando horarios
        turma1.adicionarHorario("2V43");
        turma1.adicionarHorario("2V56");

        turma2.adicionarHorario("2M63");

        // Adicioanndo alunos a turmas
        turma1.adicionarAluno(aluno1);
        turma1.adicionarAluno(aluno2);

        turma2.adicionarAluno(aluno1);

        // Adicionar turma a professores
        prof1.addTurma(turma1);
        prof2.addTurma(turma2);

        System.out.println("Adicionando entidades...");
    }
}
