package entidades;

import erros.GloboPlayException;
import java.util.ArrayList;
import java.util.List;

public class GloboPlay {

    List<Filme> filmes;

    public GloboPlay() {
        this.filmes = new ArrayList<>();
    }

    public Filme cadastrarFilme(String titulo, int anoLancamento, List<String> trilhasSonoras) {
         Filme novoFilme = new Filme(
            titulo,
            anoLancamento,
            trilhasSonoras
         );

        for (Filme filme : filmes) {
            if (filme.getId().equals(novoFilme.getId())) {
                throw new GloboPlayException("Filme já cadastrado");
            }
        }

        filmes.add(novoFilme);

        return novoFilme;
    }

    public void mostrarFilmes() {

        for (Filme filme : filmes) {
            System.out.println("-".repeat(50));
            System.out.printf("Filme: %s\n", filme);

            var funcionarios = filme.getFuncionarios();
            var trilhasSonoras = filme.getTrilhasSonoras();

            System.out.println();
            System.out.println("-- Funcionários --");

            for (FuncionarioFilme funcionario : funcionarios) {
                System.out.println(funcionario);
            }

            System.out.println();
            System.out.println("-- Trilhas --");

            for (String trilhaSonora :  trilhasSonoras) {
                System.out.println(trilhaSonora);
            }

            System.out.println("-".repeat(50));
            System.out.println();
        }

    }

    public List<Filme> getFilmes() {
        return filmes;
    }

}
