package admin.entities;

import java.util.HashMap;
import java.util.Map;

public class Almoxarifado {
    private Map<String, Integer> estoque;
    private Map<String, Integer> pedidosCompra;

    public Almoxarifado() {
        this.estoque = new HashMap<>();
        this.pedidosCompra = new HashMap<>();
    }

    public void atualizarEstoque(String item, int quantidade) {
        if (this.estoque.containsKey(item)) {
            this.estoque.put(item, this.estoque.get(item) + quantidade);
        } else {
            this.estoque.put(item, quantidade);
        }
    }

    public void registrarPedidoCompra(String item, int quantidade) {
        this.pedidosCompra.put(item, quantidade);
    }

    public Map<String, Integer> getEstoque() {
        return this.estoque;
    }

    public Map<String, Integer> getPedidosCompra() {
        return this.pedidosCompra;
    }
}
