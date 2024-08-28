package entities;

public abstract class Figura2D {
    public abstract double getPerimetro();
    public abstract double getArea();
    public abstract String getNome();

    @Override
    public String toString() {
        return "Figura2D (%s) | [Área: %.2f] - [Perímetro: %.2f] |".formatted(
                getNome(),
                getArea(),
                getPerimetro()
        );
    }
}
