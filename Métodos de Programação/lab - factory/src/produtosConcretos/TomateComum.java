package produtosConcretos;

import produto.Tomate;

public class TomateComum implements Tomate {
    @Override
    public String getTipo() {
        return "Tomate";
    }
}
