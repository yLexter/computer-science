package utils;

import entities.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Examples {

    public static Map<String, List<?>> getAll() {
        Map<String, List<?>> mapa = new HashMap<>();
        List<Aluno> alunos = new ArrayList<>();
        List<Professor> professores = new ArrayList<>();
        List<Turma> turmas = new ArrayList<>();

        // Disciplinas
        Disciplina d1 = new Disciplina("CPT01093", "PARADIGMAS DE PROGRAMAÇÃO", 60, 5);
        Disciplina d2 = new Disciplina("CPT01092", "ENGENHARIA DE SOFTWARE I", 60, 5);

        // Alunos
        alunos.add(new Aluno("João", "Silva", "4568484648468"));
        alunos.add(new Aluno("Maria", "Santos", "5846846488468"));

        // Turmas
        Turma t1 = new Turma(d1);

        t1.getHorarios().add(new Horario("1D15", t1.getId()));
        t1.getHorarios().add(new Horario("1Z91", t1.getId()));

        // Criação de alunos turmas
        t1.getAlunos().addAll(
                alunos
                        .stream()
                        .map(aluno -> new AlunoTurma(aluno.getMatricula()))
                        .toList()
        );

        Turma t2 = new Turma(d2);

        t2.getHorarios().add(new Horario("1F45", t2.getId()));
        t2.getHorarios().add(new Horario("1F41", t2.getId()));

        // Criação de alunos turmas
        t2.getAlunos().addAll(
                alunos.subList(0, 1)
                        .stream()
                        .map(aluno -> new AlunoTurma(aluno.getMatricula()))
                        .toList()
        );

        turmas.add(t1);
        turmas.add(t2);

        // Professores
        Professor p1 = new Professor("Carlos", "Silveira", "4484646");
        Professor p2 = new Professor("Renata", "Lima", "4854325230223");

        p1.getTurmas().add(turmas.get(0));
        p1.getTurmas().add(turmas.get(1));

        professores.add(p1);
        professores.add(p2);

        // Map
        mapa.put("professores", professores);
        mapa.put("alunos", alunos);
        mapa.put("turmas", turmas);

        return mapa;
    }
}
