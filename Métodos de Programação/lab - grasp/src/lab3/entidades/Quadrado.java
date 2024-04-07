package lab3.entidades;

import lab3.erros.Figura2DException;

public class Quadrado extends Figura2D {
    private double lado;

    public Quadrado(double lado) throws Figura2DException {

        if (lado <= 0) {
            throw new Figura2DException("O lado deve ser maior que zero.");
        }

        this.lado = lado;
    }

    public double getLado() {
        return lado;
    }

    public void setLado(double lado) throws Figura2DException {
        if (lado <= 0) {
            throw new Figura2DException("O lado deve ser maior que zero.");
        }

        this.lado = lado;
    }

    public double getArea() {
        return lado * lado;
    }

    public double getPerimetro() {
        return 4 * lado;
    }

    @Override
    public String toString() {
        return String.format("Quadrado tem lados de: %.3f", lado);
    }
}
