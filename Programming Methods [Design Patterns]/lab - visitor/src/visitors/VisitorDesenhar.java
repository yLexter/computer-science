package visitors;

import entities.Circulo;
import entities.Retangulo;
import entities.Triangle;
import entities.Trapezio;
import interfaces.IVisitor;

public class VisitorDesenhar implements IVisitor<Void> {
    @Override
    public Void visitaCirculo(Circulo circulo) {
        System.out.println("Desenhando Circulo...");
        return null;
    }

    @Override
    public Void visitaTriangulo(Triangle triangle) {
        System.out.println("Desenhando Triangulo...");
        return null;
    }

    @Override
    public Void visitaRetangulo(Retangulo retangulo) {
        System.out.println("Desenhando Retângulo...");
        return null;
    }

    @Override
    public Void visitaTrapezio(Trapezio trapezio) {
        System.out.println("Desenhando Trapézio...");
        return null;
    }

    @Override
    public String toString() {
        return "Visitor de Desenhar";
    }

}
