package produtosConcretos;

import produto.Queijo;

public class QueijoMussarela implements Queijo {
    @Override
    public String getTipo() {
        return "Queijo Mussarela";
    }
}
