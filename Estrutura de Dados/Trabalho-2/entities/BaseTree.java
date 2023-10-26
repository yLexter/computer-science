package entities;

public abstract class BaseTree <T extends Comparable<T>> {
    private final String name;

    public BaseTree(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public abstract void insert(T valor);
    public abstract boolean search(T valor);
    public abstract void clear();
    public abstract int getHeight();
    public abstract int getTotalRotations();
    public abstract int getTotalDoubleRotations();
}
