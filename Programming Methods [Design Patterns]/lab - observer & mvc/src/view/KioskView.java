package view;

import entities.AcentoOnibus;
import entities.Onibus;
import interfaces.IObserver;

import java.util.List;

public class KioskView implements IObserver {
    private String id;

    public KioskView(String id) {
        this.id = id;
    }

    @Override
    public void update(Onibus onibus) {
        List<AcentoOnibus> acentos = onibus.getAcentos();

        System.out.println("-".repeat(50));
        System.out.println("---- Quiosque " + id + " Atualizado -------");
        System.out.println();
        System.out.println(onibus);
        System.out.println();

        for (AcentoOnibus acento : acentos) {
            System.out.printf("Acento (%d) - Status: %s \n", acento.getNumero(), acento.getStatus());
        }
        System.out.println("-".repeat(50));
    }
}
