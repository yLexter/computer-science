package admin;

import admin.facade.SistemaFacade;

public class Main {
    public static void main(String[] args) {
        SistemaFacade facade = new SistemaFacade();

        // Agendar reuniões e entrevistas
        facade.agendarReuniao("Reunião de diretoria");
        facade.agendarReuniao("Reunião de equipe");
        facade.agendarEntrevista("Entrevista com candidato 1");
        facade.agendarEntrevista("Entrevista com candidato 2");

        // Atualizar balanço de contas e processar folha de pagamento
        facade.atualizarBalancoContas("Balanço mensal", 50000.0);
        facade.atualizarBalancoContas("Reservas", 20000.0);
        facade.processarFolhaPagamento("Funcionário 1", 3000.0);
        facade.processarFolhaPagamento("Funcionário 2", 2500.0);

        // Atualizar estoque e registrar pedido de compra
        facade.atualizarEstoque("Canetas", 100);
        facade.atualizarEstoque("Papéis", 200);
        facade.registrarPedidoCompra("Toner", 5);

        // Obter informações do sistema
        System.out.println("Reuniões agendadas:");
        facade.getReunioesAgendadas().forEach(System.out::println);

        System.out.println("\nEntrevistas agendadas:");
        facade.getEntrevistas().forEach(System.out::println);

        System.out.println("\nBalanço de contas:");
        facade.getBalancoContas().forEach((descricao, valor) ->
                System.out.println("- " + descricao + ": $" + valor));

        System.out.println("\nFolha de pagamento:");
        facade.getFolhaPagamento().forEach((funcionario, salario) ->
                System.out.println("- " + funcionario + ": $" + salario));

        System.out.println("\nEstoque atual:");
        facade.getEstoque().forEach((item, quantidade) ->
                System.out.println("- " + item + ": " + quantidade + " unidades"));

        System.out.println("\nPedidos de compra:");
        facade.getPedidosCompra().forEach((item, quantidade) ->
                System.out.println("- " + item + ": " + quantidade + " unidades"));
    }
}
