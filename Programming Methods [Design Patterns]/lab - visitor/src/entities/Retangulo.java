package entities;

import erros.Figura2DException;
import interfaces.IAceitaVisitor;
import interfaces.IVisitor;

public class Retangulo extends Figura2D implements IAceitaVisitor {
    private double largura;
    private double altura;

    public Retangulo(double largura, double altura) throws IllegalArgumentException {
        if (largura <= 0 || altura <= 0) {
            throw new Figura2DException("Os lados do retângulo devem ser maiores que zero.");
        }

        this.largura = largura;
        this.altura = altura;
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        if (largura <= 0) {
            throw new Figura2DException("Os lados do retângulo devem ser maiores que zero.");
        }

        this.largura = largura;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        if (altura <= 0) {
            throw new Figura2DException("Os lados do retângulo devem ser maiores que zero.");
        }
        this.altura = altura;
    }

    @Override
    public String getNome() {
        return "Retângulo";
    }

    @Override
    public <T> T aceitarVisitor(IVisitor<T> visitor) {
        return visitor.visitaRetangulo(this);
    }

    @Override
    public String toString() {
        return "%s [Largura: %.2f | Altura: %.2f]".formatted(
                super.toString(),
                largura,
                altura
        );
    }


}