package produtosConcretos;

import produto.Queijo;

public class QueijoPrato implements Queijo {
    @Override
    public String getTipo() {
        return "Queijo Prato";
    }
}
