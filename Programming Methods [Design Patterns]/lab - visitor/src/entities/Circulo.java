package entities;

import erros.Figura2DException;
import interfaces.IAceitaVisitor;
import interfaces.IVisitor;

public class Circulo extends Figura2D implements IAceitaVisitor {

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
    public String getNome() {
        return "Círculo";
    }

    @Override
    public String toString() {
        return String.format("%s [Raio: %.2f]", super.toString(), raio);
    }

    @Override
    public <T> T aceitarVisitor(IVisitor<T> visitor) {
        return visitor.visitaCirculo(this);
    }


}
