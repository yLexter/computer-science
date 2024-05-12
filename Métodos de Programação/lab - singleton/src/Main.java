import entities.Circulo;
import entities.Quadrado;
import entities.Triangle;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Criando instancias
        var listaQuadrados = List.of(
            new Quadrado(5),
            new Quadrado(4),
            new Quadrado(25)
        );

        Triangle.createInstance(5, 5, 5);
        Triangle.createInstance(5, 5, 9);
        Triangle.createInstance(8, 15, 17);

        Circulo.createInstance(10);

        // Prints
        System.out.println("-".repeat(50));
        System.out.println("Quadrados");

        System.out.println();
        listaQuadrados.forEach(System.out::println);
        System.out.println();

        System.out.println("-".repeat(50));

        System.out.println("Circulo");
        System.out.println();

        System.out.println(Circulo.getInstance());
        System.out.println();

        System.out.println("-".repeat(50));

        Triangle.showTriangles();
        System.out.println("-".repeat(50));
    }

}