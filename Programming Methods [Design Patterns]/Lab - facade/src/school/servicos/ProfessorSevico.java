package school.servicos;

import school.entities.Professor;
import school.erros.ProfessorServicoException;

import java.util.ArrayList;
import java.util.List;

public class ProfessorSevico {

    List<Professor> professores;

    public ProfessorSevico() {
        this.professores = new ArrayList<>();
    }

    public Professor obterProfessorPorMatricula(String matricula) {
        return professores.stream()
                .filter(professor -> professor.getMatricula().equals(matricula))
                .findAny()
                .orElseThrow(() -> new ProfessorServicoException("Professor inexistente"));
    }

    public Professor adicionarProfessor(String nome, String sobrenome, String cpf) {
        var novoProfessor = new Professor(nome, sobrenome, cpf);

        for (var professor : professores) {
            if (professor.getCpf().equals(novoProfessor.getCpf())) {
                throw new ProfessorServicoException("Professor já existe");
            }
        }

        professores.add(novoProfessor);

        return novoProfessor;
    }

    public List<Professor> getProfessores() {
        return professores;
    }
}
