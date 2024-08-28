package testes;

import entidades.funcoes.*;
import erros.FilmeException;
import erros.FuncionarioFilmeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import entidades.Filme;
import interfaces.IFuncaoFilme;

public class FilmeTest {

    private Filme filme;
    private List<String> trilhasSonoras;

    @BeforeEach
    public void setUp() {
        trilhasSonoras = new ArrayList<>();
        trilhasSonoras.add("Lerigouuuuuu");
        trilhasSonoras.add("Vc quer brincar na neve 😈");

        filme = new Filme("Frozen", 2014, trilhasSonoras);
    }

    @Test
    public void testCadastrarFuncionario() {
        List<IFuncaoFilme> funcoes = new ArrayList<>(
                List.of(
                   new Ator(),
                   new Roterista()
                )
        );

        filme.cadastrarFuncionario("1234", "João", funcoes);

        assertEquals(1, filme.getFuncionarios().size());

        assertThrows(FilmeException.class, () -> {
            filme.cadastrarFuncionario("1234", "Maria", funcoes);
        });
    }

}
