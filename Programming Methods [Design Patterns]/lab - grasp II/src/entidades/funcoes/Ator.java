package entidades.funcoes;

import interfaces.IFuncaoFilme;

public class Ator implements IFuncaoFilme {

    @Override
    public void exercer() {
        System.out.println("Atuando no filme...");
    }

    @Override
    public String getNome() {
        return "Ator";
    }

    @Override
    public String toString() {
        return "Ator";
    }
}
