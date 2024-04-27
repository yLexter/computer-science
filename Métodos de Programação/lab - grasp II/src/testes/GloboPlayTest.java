package testes;

import entidades.GloboPlay;
import erros.GloboPlayException;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GloboPlayTest {

    @Test
    public void testCadastrarFilme() {
        // Arrange
        GloboPlay globoPlay = new GloboPlay();
        String titulo = "Filme Teste";
        int anoLancamento = 2022;
        List<String> trilhasSonoras = List.of("Trilha Sonora 1", "Trilha Sonora 2");

        // Act
        globoPlay.cadastrarFilme(titulo, anoLancamento, trilhasSonoras);

        // Assert
        assertEquals(1, globoPlay.getFilmes().size());
        assertEquals(titulo + " " + anoLancamento, globoPlay.getFilmes().get(0).getId());
    }


    @Test
    public void testCadastrarFilmeExistente() {
        // Arrange
        GloboPlay globoPlay = new GloboPlay();
        String titulo = "Filme Teste";
        int anoLancamento = 2022;
        List<String> trilhasSonoras = List.of("Trilha Sonora 1", "Trilha Sonora 2");

        // Act
        globoPlay.cadastrarFilme(titulo, anoLancamento, trilhasSonoras);

        // Assert
        assertThrows(GloboPlayException.class, () -> {
            globoPlay.cadastrarFilme(titulo, anoLancamento, trilhasSonoras);
        });
    }

}
