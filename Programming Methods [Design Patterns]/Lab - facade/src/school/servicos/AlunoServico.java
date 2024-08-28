package school.servicos;

import school.entities.*;
import school.erros.AlunoServicoException;

import java.util.ArrayList;
import java.util.List;

public class AlunoServico {

    private List<Aluno> alunos;

    public AlunoServico() {
        this.alunos = new ArrayList<>();
    }

    public Aluno obterAlunoPorId(String id) {
        return alunos.stream()
                .filter(aluno -> aluno.getMatricula().equals(id))
                .findAny()
                .orElseThrow(() -> new AlunoServicoException("Aluno inexistente"));
    }

    public void adicionarTurmaAAluno(String alunoId, String turmaId) {
        var aluno = obterAlunoPorId(alunoId);

        aluno.getTurmasId().add(turmaId);
    }

    public Aluno adicionaAluno(String nome, String sobrenome, String cpf) {
        var novoAluno = new Aluno(nome, sobrenome, cpf);

        for (var aluno : alunos) {

            if (aluno.getCpf().equals(novoAluno.getCpf())) {
                throw new AlunoServicoException("Aluno já existe");
            }
        }

        alunos.add(novoAluno);

        return novoAluno;
    }

    public HistoricoAluno adicionarCadeiraNoHistoricoAluno(String idAluno, HistoricoAluno historicoAluno) {
        Aluno aluno = obterAlunoPorId(idAluno);
        var historico = aluno.getHistorico();

        for (var cadeira : historico) {
            if (cadeira.getAlunoTurma().getId().equals(historicoAluno.getAlunoTurma().getId())) {
                throw new AlunoServicoException("Cadeira já ta no historico");
            }
        }

        aluno.getHistorico().add(historicoAluno);

        return historicoAluno;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

}
