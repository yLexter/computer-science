package admin.facade;

import admin.entities.*;

import java.util.List;
import java.util.Map;

public class SistemaFacade {
    private Administrativo administrativo;
    private Financeiro financeiro;
    private Almoxarifado almoxarifado;

    public SistemaFacade() {
        this.administrativo = new Administrativo();
        this.financeiro = new Financeiro();
        this.almoxarifado = new Almoxarifado();
    }

    public void agendarReuniao(String descricao) {
        this.administrativo.agendarReuniao(descricao);
    }

    public void agendarEntrevista(String descricao) {
        this.administrativo.agendarEntrevista(descricao);
    }

    public void atualizarBalancoContas(String descricao, double valor) {
        this.financeiro.atualizarBalancoContas(descricao, valor);
    }

    public void processarFolhaPagamento(String funcionario, double salario) {
        this.financeiro.processarFolhaPagamento(funcionario, salario);
    }

    public void atualizarEstoque(String item, int quantidade) {
        this.almoxarifado.atualizarEstoque(item, quantidade);
    }

    public void registrarPedidoCompra(String item, int quantidade) {
        this.almoxarifado.registrarPedidoCompra(item, quantidade);
    }

    public List<String> getReunioesAgendadas() {
        return this.administrativo.getReunioesAgendadas();
    }

    public List<String> getEntrevistas() {
        return this.administrativo.getEntrevistas();
    }

    public Map<String, Double> getBalancoContas() {
        return this.financeiro.getBalancoContas();
    }

    public Map<String, Double> getFolhaPagamento() {
        return this.financeiro.getFolhaPagamento();
    }

    public Map<String, Integer> getEstoque() {
        return this.almoxarifado.getEstoque();
    }

    public Map<String, Integer> getPedidosCompra() {
        return this.almoxarifado.getPedidosCompra();
    }
}
