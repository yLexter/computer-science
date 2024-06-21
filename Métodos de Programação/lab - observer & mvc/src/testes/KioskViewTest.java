package testes;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import view.KioskView;
import entities.Onibus;
import enums.AcentoStatus;
import observer.ObserverManger;

public class KioskViewTest {

    private ObserverManger observerManger;
    private KioskView kioskView;
    private Onibus onibus;

    @Before
    public void setUp() {
        observerManger = new ObserverManger();
        kioskView = new KioskView("Quiosque 1");
        onibus = new Onibus("300B", 10, observerManger);
        observerManger.addObserver(kioskView);
    }

    @Test
    public void testKioskUpdate() {
        // Atualiza o status de um assento e verifica se a atualização ocorre corretamente
        onibus.atualizarStatusAcento(5, AcentoStatus.INDISPONIVEL);
        assertEquals(AcentoStatus.INDISPONIVEL, onibus.getAcentos().get(4).getStatus());

        onibus.atualizarStatusAcento(3, AcentoStatus.RESERVADO);
        assertEquals(AcentoStatus.RESERVADO, onibus.getAcentos().get(2).getStatus());

        onibus.atualizarStatusAcento(5, AcentoStatus.DISPONIVEL);
        assertEquals(AcentoStatus.DISPONIVEL, onibus.getAcentos().get(4).getStatus());
    }

    @Test
    public void testMultipleKioskUpdates() {
        // Adiciona outro quiosque e verifica se ambos recebem as notificações
        KioskView anotherKioskView = new KioskView("Quiosque 2");
        observerManger.addObserver(anotherKioskView);

        onibus.atualizarStatusAcento(1, AcentoStatus.RESERVADO);
        assertEquals(AcentoStatus.RESERVADO, onibus.getAcentos().get(0).getStatus());

        onibus.atualizarStatusAcento(10, AcentoStatus.INDISPONIVEL);
        assertEquals(AcentoStatus.INDISPONIVEL, onibus.getAcentos().get(9).getStatus());
    }
}
