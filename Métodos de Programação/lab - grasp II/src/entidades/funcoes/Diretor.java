package entidades.funcoes;

import interfaces.IFuncaoFilme;

public class Diretor implements IFuncaoFilme {

    @Override
    public void exercer() {
        System.out.println("Dirigindo filme...");
    }

    @Override
    public String getNome() {
        return "Diretor";
    }

    @Override
    public String toString() {
        return "Diretor";
    }
    
}
