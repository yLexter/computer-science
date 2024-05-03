package school.entities;

import java.util.ArrayList;
import java.util.List;

public class Aluno extends Funcionario {

    private List<String> turmasId;

    private List<HistoricoAluno> historico;

    public Aluno(String nome, String sobrenome, String cpf) {
        super(nome, sobrenome, cpf);
        this.turmasId = new ArrayList<>();
        this.historico = new ArrayList<>();
    }

    public List<HistoricoAluno> getHistorico() {
        return historico;
    }

    public void addTurma(String idTurma) {
        turmasId.add(idTurma);
    }

    public List<String> getTurmasId() {
        return turmasId;
    }



}
