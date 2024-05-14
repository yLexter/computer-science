package visitors;

import entities.*;
import interfaces.IVisitor;

public class VisitorMaximizarFigura implements IVisitor<Figura2D> {
    @Override
    public Figura2D visitaCirculo(Circulo circulo) {
        circulo.setRaio(circulo.getRaio() * 2);

        return circulo;
    }

    @Override
    public Figura2D visitaTriangulo(Triangle triangle) {
        triangle.setSides(triangle.getSide1() * 2, triangle.getSide2() * 2, triangle.getSide3() * 2 );

        return triangle;
    }

    @Override
    public Figura2D visitaRetangulo(Retangulo retangulo) {
        retangulo.setLargura(retangulo.getLargura() * 2);
        retangulo.setAltura(retangulo.getAltura() * 2);

        return retangulo;
    }

    @Override
    public Figura2D visitaTrapezio(Trapezio trapezio) {
        trapezio.setBaseMaior(trapezio.getBaseMaior() * 2);
        trapezio.setBaseMenor(trapezio.getBaseMenor() * 2);
        trapezio.setLado1(trapezio.getLado1() * 2);
        trapezio.setLado2(trapezio.getLado2() * 2);

        return trapezio;
    }

    @Override
    public String toString() {
        return "Visitor de Maximizar Figura";
    }

}
