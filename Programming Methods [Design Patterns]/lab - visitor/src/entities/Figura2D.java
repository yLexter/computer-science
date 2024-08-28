package entities;

public abstract class Figura2D {

    public abstract String getNome();

    @Override
    public String toString() {
        return "Figura2D (%s) | ".formatted(getNome());
    }
}
