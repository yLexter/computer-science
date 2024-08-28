package school.servicos;

import school.entities.Disciplina;
import school.entities.Turma;
import school.erros.DisciplinaServicoException;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaServico {

    private List<Disciplina> disciplinas;

    public DisciplinaServico() {
        this.disciplinas = new ArrayList<>();
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public Disciplina adicionarDisciplina(String codigo, String nome, int periodo) {
        Disciplina novaDisciplina = new Disciplina(codigo, nome, periodo);

        for (var disciplina : disciplinas) {
            if (disciplina.getCodigo().equals(novaDisciplina.getCodigo())) {
                throw new DisciplinaServicoException("Disciplina já existe");
            }
        }

        disciplinas.add(novaDisciplina);

        return novaDisciplina;
    }


}
