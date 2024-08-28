package testes;

import entidades.FuncionarioFilme;
import entidades.funcoes.*;
import interfaces.IFuncaoFilme;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FuncionarioFilmeTest {

    @Test
    public void testCriarFuncionario() {
        // Arrange
        String cpf = "123456789";
        String nome = "Funcionário Teste";
        List<IFuncaoFilme> funcoes = List.of(new Ator(), new Diretor());

        // Act
        FuncionarioFilme funcionario = new FuncionarioFilme(cpf, nome, funcoes);

        // Assert
        assertEquals(cpf, funcionario.getCpf());
        assertEquals(nome, funcionario.getNome());
        assertEquals(funcoes, funcionario.getFuncoes());
    }
}
