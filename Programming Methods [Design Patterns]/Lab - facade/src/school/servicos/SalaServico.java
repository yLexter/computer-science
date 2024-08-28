package school.servicos;

import school.entities.Sala;
import school.erros.SalaServicoException;

import java.util.ArrayList;
import java.util.List;

public class SalaServico {

    private List<Sala> salas;

    public SalaServico() {
        this.salas = new ArrayList<>();
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public Sala adicionarSala(String nome, int capacidade) {
        Sala novaSala = new Sala(nome, capacidade);

        for (var sala : salas) {
            if (sala.getNome().equals(novaSala.getNome())) {
                throw new SalaServicoException("Sala já existe");
            }
        }

        salas.add(novaSala);

        return novaSala;
    }

}
