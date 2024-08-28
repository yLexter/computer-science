import entidades.Filme;
import entidades.FuncionarioFilme;
import entidades.GloboPlay;
import entidades.funcoes.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GloboPlay globoPlay = new GloboPlay();

        List<String> trilhasSonoras = new ArrayList<>();
        trilhasSonoras.add("Lerigouuuuuuuuuu");
        trilhasSonoras.add("Vc quer brincar na neve? 😈");

        Filme f1 = globoPlay.cadastrarFilme("Frozen 1", 2014, trilhasSonoras);
        Filme f2 = globoPlay.cadastrarFilme("Frozen 2", 2018, trilhasSonoras);

        FuncionarioFilme func1 = f1.cadastrarFuncionario("Frozen", "584184646");
        FuncionarioFilme func3 = f1.cadastrarFuncionario("Boneco de neve", "5184646");
        FuncionarioFilme func2 = f2.cadastrarFuncionario("Zendaia", "485487452");

        func1
            .adicionarFuncao(new Diretor())
            .adicionarFuncao(new Roterista())
            .adicionarFuncao(new Ator());

        func2
            .adicionarFuncao(new Diretor())
            .adicionarFuncao(new Roterista());

        func3.adicionarFuncao(new Ator());

        globoPlay.mostrarFilmes();
    }
}
