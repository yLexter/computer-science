package entidades;

import erros.FilmeException;
import erros.GloboPlayException;
import interfaces.IFuncaoFilme;

import java.util.ArrayList;
import java.util.List;

public class Filme {
    public final String id;
    public String titulo;
    public int anoLancamento;
    List<FuncionarioFilme> funcionarios;
    List<String> trilhasSonoras;

    public Filme(String titulo, int anoLancamento, List<String> trilhasSonoras) {
        this.id = String.format("%s %d", titulo, anoLancamento);
        this.titulo = titulo;
        this.anoLancamento = anoLancamento;
        this.funcionarios = new ArrayList<>();
        this.trilhasSonoras = trilhasSonoras;
    }

    public void cadastrarFuncionario(String cpf, String nome, List<IFuncaoFilme> funcoes) {
        FuncionarioFilme novoFuncionario = new FuncionarioFilme(
                cpf,
                nome,
                funcoes
        );

        for (FuncionarioFilme funcionario : funcionarios) {
            if (funcionario.getCpf().equals(novoFuncionario.getCpf())) {
                throw new FilmeException("Funcionario já existe no filme");
            }
        }

        funcionarios.add(novoFuncionario);
    }

    public FuncionarioFilme cadastrarFuncionario(String cpf, String nome) {
        FuncionarioFilme novoFuncionario = new FuncionarioFilme(
                cpf,
                nome
        );

        for (FuncionarioFilme funcionario : funcionarios) {
            if (funcionario.getCpf().equals(novoFuncionario.getCpf())) {
                throw new FilmeException("Funcionario já existe no filme");
            }
        }

        funcionarios.add(novoFuncionario);

        return novoFuncionario;
    }



    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public List<FuncionarioFilme> getFuncionarios() {
        return funcionarios;
    }

    public List<String> getTrilhasSonoras() {
        return trilhasSonoras;
    }

    @Override
    public String toString() {
        return String.format("%s (%d)", titulo, anoLancamento);
    }
}
