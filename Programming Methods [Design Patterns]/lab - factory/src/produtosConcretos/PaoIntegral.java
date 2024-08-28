package produtosConcretos;

import produto.Pao;

public class PaoIntegral implements Pao {
    @Override
    public String getTipo() {
        return "Pão Integral";
    }
}
