package visitors;

import entities.Circulo;
import entities.Retangulo;
import entities.Trapezio;
import entities.Triangle;
import interfaces.IVisitor;

public class VisitorCalculaPerimetro implements IVisitor<Double> {
    @Override
    public Double visitaCirculo(Circulo circulo) {
        return 2 * Math.PI * circulo.getRaio();
    }

    @Override
    public Double visitaTriangulo(Triangle triangle) {
        return triangle.getSide1() + triangle.getSide2() + triangle.getSide3();
    }

    @Override
    public Double visitaRetangulo(Retangulo retangulo) {
        return 2 * (retangulo.getLargura() + retangulo.getAltura());
    }

    @Override
    public Double visitaTrapezio(Trapezio trapezio) {
        return trapezio.getBaseMaior() + trapezio.getBaseMenor() + trapezio.getLado1() + trapezio.getLado2();
    }

    @Override
    public String toString() {
        return "Visitor de Calcular Perímetro";
    }
}
