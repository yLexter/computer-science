import lab3.entidades.*;
import lab3.entidades.Figura2D;
import lab3.erros.Figura2DException;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            List<Figura2D> figuras = List.of(
                    new Circulo(5.0),
                    new Retangulo(4.0, 6.0),
                    new Quadrado(3.0)
            );

            for (Figura2D figura : figuras) {
                System.out.println(figura.toString());
                System.out.println("Área: " + figura.getArea());
                System.out.println("Perímetro: " + figura.getPerimetro());
                System.out.println();
            }

        } catch (Figura2DException error) {
            System.out.printf("Erro de Figura2D: %s\n", error.getMessage());
        } catch (Exception error) {
            System.out.printf("Erro desconhecido: %s\n", error.getMessage());
        }


    }
}