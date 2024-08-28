package controller;

import entities.Onibus;
import enums.AcentoStatus;

public class BusController {
    private Onibus onibus;

    public BusController(Onibus onibus) {
        this.onibus = onibus;
    }

    public void reservarAcento(int numeroAcento) {
        onibus.atualizarStatusAcento(numeroAcento, AcentoStatus.RESERVADO);
    }

    public void marcarAcentoIndisponivel(int numeroAcento) {
        onibus.atualizarStatusAcento(numeroAcento, AcentoStatus.INDISPONIVEL);
    }

    public void liberarAcento(int numeroAcento) {
        onibus.atualizarStatusAcento(numeroAcento, AcentoStatus.DISPONIVEL);
    }
}
