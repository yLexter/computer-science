package sanduicheFactory;

import produto.Ovo;
import produto.Pao;
import produto.Presunto;
import produto.Queijo;
import produtosConcretos.OvoGranja;
import produtosConcretos.PaoFrances;
import produtosConcretos.PresuntoPeru;
import produtosConcretos.QueijoMussarela;
import sanduiche.Sanduiche;

public class SanduicheFrancesMussarelaPeruGranja extends Sanduiche {
    @Override
    public Pao criarPao() {
        return new PaoFrances();
    }

    @Override
    public Queijo criarQueijo() {
        return new QueijoMussarela();
    }

    @Override
    public Presunto criarPresunto() {
        return new PresuntoPeru();
    }

    @Override
    public Ovo criarOvo() {
        return new OvoGranja();
    }
}

