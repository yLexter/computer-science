package entidades;

import erros.FuncionarioFilmeException;
import interfaces.IFuncaoFilme;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FuncionarioFilme {
    private String cpf;
    private String nome;
    private List<IFuncaoFilme> funcoes;

    public FuncionarioFilme(String cpf, String nome, List<IFuncaoFilme> funcoes) {
        this.cpf = cpf;
        this.nome = nome;
        this.funcoes = funcoes;
    }

    public FuncionarioFilme(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
        this.funcoes = new ArrayList<>();
    }

    public FuncionarioFilme adicionarFuncao(IFuncaoFilme funcao) {

        for(IFuncaoFilme funcaoFilme : funcoes) {

            if (funcaoFilme.getNome().equals(funcao.getNome()))
                throw new FuncionarioFilmeException("Funcionario já tem esta função");
        }

        funcoes.add(funcao);

        return this;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public List<IFuncaoFilme> getFuncoes() {
        return funcoes;
    }

    @Override
    public String toString() {
        String funcoesToString = funcoes
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        return String.format("Funcionário: %s (%s) | Funções: [%s]", nome, cpf, funcoesToString);
    }

}
