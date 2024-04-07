package lab3.entidades;

import lab3.erros.Figura2DException;

public class Retangulo extends Figura2D {
    private double altura;
    private double largura;

    public Retangulo(double altura, double largura) throws Figura2DException {
        if (altura <= 0 || largura <= 0)
            throw new Figura2DException("Altura e largura devem ser maiores que 0");

        this.altura = altura;
        this.largura = largura;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) throws Figura2DException {
        if (altura <= 0)
            throw new Figura2DException("Medida da altura inválida");

        this.altura = altura;
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) throws Figura2DException {
        if (largura <= 0)
            throw new Figura2DException("Medida da largura inválida");

        this.largura = largura;
    }

    @Override
    public double getPerimetro() {
        return 2 * (altura + largura);
    }

    @Override
    public double getArea() {
        return altura * largura;
    }

    @Override
    public String toString() {
        return String.format("Retângulo: com altura: %.3f e largura: %.3f", altura, largura);
    }
}