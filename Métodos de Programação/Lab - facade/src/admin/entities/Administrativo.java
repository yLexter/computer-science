package admin.entities;

import java.util.ArrayList;
import java.util.List;

public class Administrativo {
    private List<String> reunioesAgendadas;
    private List<String> entrevistas;

    public Administrativo() {
        this.reunioesAgendadas = new ArrayList<>();
        this.entrevistas = new ArrayList<>();
    }

    public void agendarReuniao(String descricao) {
        this.reunioesAgendadas.add(descricao);
    }

    public void agendarEntrevista(String descricao) {
        this.entrevistas.add(descricao);
    }

    public List<String> getReunioesAgendadas() {
        return this.reunioesAgendadas;
    }

    public List<String> getEntrevistas() {
        return this.entrevistas;
    }
}
