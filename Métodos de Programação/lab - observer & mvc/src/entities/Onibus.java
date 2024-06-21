package entities;

import enums.AcentoStatus;
import observer.ObserverManger;

import java.util.*;

public class Onibus {

    private String id;

    private List<AcentoOnibus> acentos;

    private ObserverManger observerManger;

    public Onibus(String id, int totalAcentos, ObserverManger observerManger) {
        this.id = id;
        this.acentos = new ArrayList<>();

        for (int i = 1; i <= totalAcentos; i++) {
            acentos.add(new AcentoOnibus(i));
        }

        this.observerManger = observerManger;
    }

    public void atualizarStatusAcento(int numeroAcento, AcentoStatus status) {
        for (AcentoOnibus acento : acentos) {
            if (acento.getNumero() == numeroAcento) {
                acento.setStatus(status);
                observerManger.notifyObservers(this);
                break;
            }
        }
    }

    public List<AcentoOnibus> getAcentos() {
        return acentos;
    }

    @Override
    public String toString() {
        return String.format("Onibus [%s] - (%d Acentos)", id, acentos.size());
    }

}