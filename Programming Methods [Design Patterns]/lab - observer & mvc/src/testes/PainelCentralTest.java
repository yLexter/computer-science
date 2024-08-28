package testes;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import view.PainelCentral;
import entities.Onibus;
import enums.AcentoStatus;
import observer.ObserverManger;

public class PainelCentralTest {

    private ObserverManger observerManger;
    private PainelCentral painelCentral;
    private Onibus onibus;

    @Before
    public void setUp() {
        observerManger = new ObserverManger();
        painelCentral = new PainelCentral();
        onibus = new Onibus("300B", 10, observerManger);
        observerManger.addObserver(painelCentral);
    }

    @Test
    public void testPainelCentralUpdate() {
        // Atualiza o status de um assento e verifica se a atualização ocorre corretamente
        onibus.atualizarStatusAcento(5, AcentoStatus.INDISPONIVEL);
        assertEquals(AcentoStatus.INDISPONIVEL, onibus.getAcentos().get(4).getStatus());

        onibus.atualizarStatusAcento(3, AcentoStatus.RESERVADO);
        assertEquals(AcentoStatus.RESERVADO, onibus.getAcentos().get(2).getStatus());

        onibus.atualizarStatusAcento(5, AcentoStatus.DISPONIVEL);
        assertEquals(AcentoStatus.DISPONIVEL, onibus.getAcentos().get(4).getStatus());
    }

    @Test
    public void testMultiplePainelCentralUpdates() {
        // Adiciona outro painel central e verifica se ambos recebem as notificações
        PainelCentral anotherPainelCentral = new PainelCentral();
        observerManger.addObserver(anotherPainelCentral);

        onibus.atualizarStatusAcento(1, AcentoStatus.RESERVADO);
        assertEquals(AcentoStatus.RESERVADO, onibus.getAcentos().get(0).getStatus());

        onibus.atualizarStatusAcento(10, AcentoStatus.INDISPONIVEL);
        assertEquals(AcentoStatus.INDISPONIVEL, onibus.getAcentos().get(9).getStatus());
    }
}
