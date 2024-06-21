import sanduiche.Sanduiche;
import sanduicheFactory.*;


public class Main {
    public static void main(String[] args) {

        Sanduiche sanduiche1 = new SanduicheFrancesMussarelaPeruGranja();
        System.out.println(sanduiche1.criarSanduiche());

        Sanduiche sanduiche2 = new SanduicheBolaCheddarFrangoCapoeira();
        System.out.println(sanduiche2.criarSanduiche());

        Sanduiche sanduiche3 = new SanduicheIntegralCheddarPeruGranja();
        System.out.println(sanduiche3.criarSanduiche());
    }
}
