package entities;

import java.util.ArrayList;
import java.util.List;

public class Professor extends Funcionario {
    private List<Turma> turmas;

    public Professor(String nome, String sobrenome, String cpf) {
        super(nome, sobrenome, cpf);
        this.turmas = new ArrayList<>();
    }

    public void addTurma(Turma novaTurma) {

        for (Turma turma : turmas) {
            if (turma.equals(novaTurma))
                throw new IllegalArgumentException("Professor já pertence a turma");
        }

        novaTurma.setIdProfessor(matricula);
        turmas.add(novaTurma);
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public List<Horario> getHorario() {
        List<Horario> horarios = new ArrayList<>();

        for (Turma turma : getTurmas()) {
            horarios.addAll(turma.getHorarios());
        }

        return horarios;
    }
}
