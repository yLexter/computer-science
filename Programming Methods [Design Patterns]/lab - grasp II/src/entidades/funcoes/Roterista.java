package entidades.funcoes;

import interfaces.IFuncaoFilme;

public class Roterista implements IFuncaoFilme {

    @Override
    public void exercer() {
        System.out.println("Escrendo roteiro do filme...");
    }

    @Override
    public String getNome() {
        return "Roterista";
    }

    @Override
    public String toString() {
        return "Roterista";
    }
}
