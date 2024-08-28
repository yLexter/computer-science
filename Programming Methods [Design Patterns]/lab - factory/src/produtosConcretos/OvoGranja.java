package produtosConcretos;

import produto.Ovo;

public class OvoGranja implements Ovo {
    @Override
    public String getTipo() {
        return "Ovo de Granja";
    }
}
