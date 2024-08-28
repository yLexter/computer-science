package admin.entities;

import java.util.HashMap;
import java.util.Map;

public class Financeiro {
    private Map<String, Double> balancoContas;
    private Map<String, Double> folhaPagamento;

    public Financeiro() {
        this.balancoContas = new HashMap<>();
        this.folhaPagamento = new HashMap<>();
    }

    public void atualizarBalancoContas(String descricao, double valor) {
        this.balancoContas.put(descricao, valor);
    }

    public void processarFolhaPagamento(String funcionario, double salario) {
        this.folhaPagamento.put(funcionario, salario);
    }

    public Map<String, Double> getBalancoContas() {
        return this.balancoContas;
    }

    public Map<String, Double> getFolhaPagamento() {
        return this.folhaPagamento;
    }
}

