package sanduicheFactory;

import produto.Pao;
import produto.Queijo;
import produtosConcretos.PaoBola;
import produtosConcretos.QueijoCheddar;
import sanduiche.Sanduiche;

public class SanduicheBolaCheddarFrangoCapoeira extends Sanduiche {
    @Override
    public Pao criarPao() {
        return new PaoBola();
    }

    @Override
    public Queijo criarQueijo() {
        return new QueijoCheddar();
    }

}
