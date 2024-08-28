package entities;

import erros.Figura2DException;

public class Quadrado extends Figura2D {
    private double lado;

    public Quadrado(double lado)  {

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

    @Override
    public String getNome() {
        return "Quadrado";
    }

    @Override
    public double getPerimetro() {
        return 4 * lado;
    }

    @Override
    public String toString() {
        return String.format("%s [Lado: %.2f]", super.toString(), lado);
    }

}
