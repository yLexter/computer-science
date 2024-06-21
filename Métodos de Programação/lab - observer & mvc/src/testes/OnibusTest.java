package testes;

import entities.Onibus;
import enums.AcentoStatus;
import observer.ObserverManger;
import org.junit.jupiter.api.Test;
import view.PainelCentral;
import view.KioskView;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OnibusTest {

    @Test
    public void testAtualizarStatusAcento() {
        // Criação do ObserverManager
        ObserverManger observerManger = new ObserverManger();

        // Criação do ônibus com 5 assentos
        Onibus onibus = new Onibus("300B", 5, observerManger);

        // Verifica se o número de assentos está correto
        assertEquals(5, onibus.getAcentos().size());

        // Atualiza o status do primeiro assento para INDISPONIVEL
        onibus.atualizarStatusAcento(1, AcentoStatus.INDISPONIVEL);

        // Verifica se o status do primeiro assento foi atualizado corretamente
        assertEquals(AcentoStatus.INDISPONIVEL, onibus.getAcentos().get(0).getStatus());
    }

}
