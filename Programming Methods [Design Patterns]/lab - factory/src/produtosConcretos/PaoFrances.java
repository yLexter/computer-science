package produtosConcretos;

import produto.Pao;

public class PaoFrances implements Pao {
    @Override
    public String getTipo() {
        return "Pão Francês";
    }
}