package lab3.entidades;

import lab3.erros.Figura2DException;

public class Circulo extends Figura2D {
    private double raio;

    public Circulo(double raio) throws Figura2DException {

        if (raio <= 0)
            throw new Figura2DException("O raio tem que ser maior que 0");

        this.raio = raio;
    }

    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) throws Figura2DException {

        if (raio <= 0)
          throw new Figura2DException("Medida do raio invalida");

        this.raio = raio;
    }

    @Override
    public double getPerimetro() {
        return 2 * Math.PI * raio;
    }

    @Override
    public double getArea() {
        return Math.pow(raio, 2) * Math.PI;
    }

    @Override
    public String toString() {
        return String.format("Círculo tem raio %.3f", raio);
    }

}
