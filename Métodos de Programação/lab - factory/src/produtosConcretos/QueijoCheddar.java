package produtosConcretos;

import produto.Queijo;

public class QueijoCheddar implements Queijo {
    @Override
    public String getTipo() {
        return "Queijo Cheddar";
    }
}
