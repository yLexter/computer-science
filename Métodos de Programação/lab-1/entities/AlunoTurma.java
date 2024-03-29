package entities;

import utils.Global;

public class AlunoTurma {
    private final String matricula;
    private int faltas = 0;
    private int notaPrimeiraUnidade = 0;
    private int notaSegundaUnidade = 0;

    public AlunoTurma(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    @Override
    public String toString() {
        return "AlunoTurma = [%s]".formatted(matricula);
    }
}
