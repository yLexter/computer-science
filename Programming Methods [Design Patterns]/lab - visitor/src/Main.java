import entities.*;
import interfaces.IAceitaVisitor;
import interfaces.IVisitor;
import visitors.VisitorCalculaArea;
import visitors.VisitorCalculaPerimetro;
import visitors.VisitorDesenhar;
import visitors.VisitorMaximizarFigura;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<IVisitor<?>> visitors = List.of(
                new VisitorCalculaArea(),
                new VisitorDesenhar(),
                new VisitorCalculaPerimetro(),
                new VisitorMaximizarFigura()
        );

        List<IAceitaVisitor> figura2DS = List.of(
                new Circulo(5),
                new Circulo(8),
                new Triangle(5, 5, 5),
                new Triangle(5, 5, 9),
                new Retangulo(5, 6),
                new Retangulo(4, 5),
                new Trapezio(4, 6, 3, 4, 5),
                new Trapezio(3, 5, 2, 3, 4)
        );


        for (IVisitor<?> visitor : visitors) {
            System.out.printf("----------- [%s]-------------\n", visitor.toString().toUpperCase());
            System.out.println("-".repeat(50) + "\n");

            for (IAceitaVisitor figura : figura2DS) {
                System.out.println(figura);
                System.out.println("Valor: " + figura.aceitarVisitor(visitor));
                System.out.println();
            }

            System.out.println("-".repeat(50) + "\n");
            System.out.println();
        }


    }
}