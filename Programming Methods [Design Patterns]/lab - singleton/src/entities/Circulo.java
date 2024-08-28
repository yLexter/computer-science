package entities;

import erros.Figura2DException;
import erros.SingletonException;

import java.util.HashMap;

public class Circulo extends Figura2D {

    private static Circulo instancia;
    private double raio;

    private Circulo(double raio) throws Figura2DException {

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
    public String getNome() {
        return "Círculo";
    }

    @Override
    public String toString() {
        return String.format("%s [Raio: %.2f]", super.toString(), raio);
    }

    public static Circulo getInstance() {
        return instancia;
    }

    public static void resetInstance() {
        instancia = null;
    }

    public static void createInstance(double raio) {

        if (instancia != null) {
            throw new SingletonException("Já existe um circulo");
        }

        instancia = new Circulo(raio);
    }

}
