import controller.BusController;
import entities.Onibus;
import view.PainelCentral;
import enums.AcentoStatus;
import observer.ObserverManger;
import view.KioskView;

public class Main {
    public static void main(String[] args) {
        ObserverManger observerManger = new ObserverManger();
        PainelCentral painelCentral = new PainelCentral();
        KioskView kiosk1 = new KioskView("Quiosque 1");
        KioskView kiosk2 = new KioskView("Quiosque 2");

        observerManger.addObserver(painelCentral);
        observerManger.addObserver(kiosk1);
        observerManger.addObserver(kiosk2);

        Onibus onibus = new Onibus("300B", 10, observerManger);
        BusController controller = new BusController(onibus);

        controller.marcarAcentoIndisponivel(5);
        controller.reservarAcento(3);
        controller.liberarAcento(5);
    }
}
