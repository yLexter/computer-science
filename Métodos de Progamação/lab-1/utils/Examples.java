package utils;

import entities.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        t1.adicionarHorario("2W12");
        t1.adicionarHorario("3D45");

        for (Aluno aluno : alunos) {
            t1.adicionarAluno(aluno);
        }

        Turma t2 = new Turma(d2);

        t2.adicionarHorario("1F45");
        t2.adicionarHorario("5N45");

        for (Aluno aluno : alunos.subList(0, 1)) {
            t2.adicionarAluno(aluno);
        }

        turmas.add(t1);
        turmas.add(t2);

        // Professores
        Professor p1 = new Professor("Carlos", "Silveira", "4484646");
        Professor p2 = new Professor("Renata", "Lima", "4854325230223");

        p1.addTurma(turmas.get(0));
        p1.addTurma(turmas.get(1));

        professores.add(p1);
        professores.add(p2);

        // Map
        mapa.put("professores", professores);
        mapa.put("alunos", alunos);
        mapa.put("turmas", turmas);

        return mapa;
    }
}
