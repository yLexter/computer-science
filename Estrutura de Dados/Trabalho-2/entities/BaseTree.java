package entities;

public abstract class BaseTree <T extends Comparable<T>> {

    private final String name;

    protected int totalRotations;

    protected int totalDoubleRotation;

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

    public void clearRotations() {
        totalRotations = 0;
        totalDoubleRotation = 0;
    }

    public int getTotalRotations() {
        return totalRotations;
    }

    public int getTotalDoubleRotations() {
        return totalDoubleRotation;
    }


}
