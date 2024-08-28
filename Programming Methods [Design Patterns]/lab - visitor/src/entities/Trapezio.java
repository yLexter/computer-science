package entities;

import erros.Figura2DException;
import interfaces.IAceitaVisitor;
import interfaces.IVisitor;

public class Trapezio extends Figura2D implements IAceitaVisitor {
    private double baseMaior;
    private double baseMenor;
    private double altura;
    private double lado1;
    private double lado2;

    public Trapezio(double baseMaior, double baseMenor, double altura, double lado1, double lado2) throws IllegalArgumentException {
        if (baseMaior <= 0 || baseMenor <= 0 || altura <= 0 || lado1 <= 0 || lado2 <= 0) {
            throw new Figura2DException("Os lados do trapézio devem ser maiores que zero.");
        }

        this.baseMaior = baseMaior;
        this.baseMenor = baseMenor;
        this.altura = altura;
        this.lado1 = lado1;
        this.lado2 = lado2;
    }

    public double getBaseMaior() {
        return baseMaior;
    }

    public void setBaseMaior(double baseMaior) {
        if (baseMaior <= 0) {
            throw new Figura2DException("A base maior do trapézio deve ser maior que zero.");
        }
        this.baseMaior = baseMaior;
    }

    public double getBaseMenor() {
        return baseMenor;
    }

    public void setBaseMenor(double baseMenor) {
        if (baseMenor <= 0) {
            throw new Figura2DException("A base menor do trapézio deve ser maior que zero.");
        }
        this.baseMenor = baseMenor;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        if (altura <= 0) {
            throw new Figura2DException("A altura do trapézio deve ser maior que zero.");
        }
        this.altura = altura;
    }

    public double getLado1() {
        return lado1;
    }

    public void setLado1(double lado1) {
        if (lado1 <= 0) {
            throw new Figura2DException("Os lados do trapézio devem ser maiores que zero.");
        }
        this.lado1 = lado1;
    }

    public double getLado2() {
        return lado2;
    }

    public void setLado2(double lado2) {
        if (lado2 <= 0) {
            throw new Figura2DException("Os lados do trapézio devem ser maiores que zero.");
        }
        this.lado2 = lado2;
    }

    @Override
    public String getNome() {
        return "Trapézio";
    }

    @Override
    public <T> T aceitarVisitor(IVisitor<T> visitor) {
        return visitor.visitaTrapezio(this);
    }

    @Override
    public String toString() {
        return "%s [Base 1: %.2f | Base 2: %.2f | Altura: %.2f | Lado 1: %.2f | Lado 2: %.2f]".formatted(
                super.toString(),
                baseMaior,
                baseMenor,
                altura,
                lado1,
                lado2
        );
    }
}

