package school.entities;

import java.util.UUID;

public class AlunoTurma {
    private final String matricula;
    private int faltas = 0;
    private int notaPrimeiraUnidade = 0;
    private int notaSegundaUnidade = 0;
    private final String id;

    public AlunoTurma(String matricula) {
        this.matricula = matricula;
        this.id = UUID.randomUUID().toString();
    }

    public AlunoTurma(String matricula, int faltas, int notaPrimeiraUnidade, int notaSegundaUnidade) {
        this.matricula = matricula;
        this.faltas = faltas;
        this.notaPrimeiraUnidade = notaPrimeiraUnidade;
        this.notaSegundaUnidade = notaSegundaUnidade;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getMatricula() {
        return matricula;
    }

    @Override
    public String toString() {
        return "AlunoTurma [%s] | Faltas: [%d] | Nota1: [%d] | Nota2: [%d] ".formatted(
                matricula,
                faltas,
                notaPrimeiraUnidade,
                notaSegundaUnidade
        );
    }
}
