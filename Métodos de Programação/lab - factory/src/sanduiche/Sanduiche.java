package sanduiche;

import produto.*;
import produtosConcretos.*;

public class Sanduiche {


    public Pao criarPao() {
        return new PaoIntegral();
    }

    public Queijo criarQueijo() {
        return new QueijoPrato();
    }

    public Presunto criarPresunto() {
        return new PresuntoFrango();
    }

    public Ovo criarOvo() {
        return new OvoCapoeira();
    }

    public Tomate criarTomate() {
        return new TomateComum();
    }


    public String criarSanduiche() {
        return "Sanduíche com " + criarPao().getTipo() + ", " + criarQueijo().getTipo() + ", " + criarPresunto().getTipo() + ", " + criarOvo().getTipo() + " e " + criarTomate().getTipo();
    }
}
