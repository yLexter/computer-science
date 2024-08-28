package sanduicheFactory;

import produto.Ovo;
import produto.Pao;
import produto.Presunto;
import produto.Queijo;
import produtosConcretos.OvoGranja;
import produtosConcretos.PaoIntegral;
import produtosConcretos.PresuntoPeru;
import produtosConcretos.QueijoCheddar;
import sanduiche.Sanduiche;

public class SanduicheIntegralCheddarPeruGranja extends Sanduiche {
    @Override
    public Pao criarPao() {
        return new PaoIntegral();
    }

    @Override
    public Queijo criarQueijo() {
        return new QueijoCheddar();
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