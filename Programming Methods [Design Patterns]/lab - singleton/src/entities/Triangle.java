package entities;

import enums.TriangleKind;
import erros.Figura2DException;
import erros.SingletonException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Triangle extends Figura2D {
    private static HashMap<TriangleKind, Triangle> instances = new HashMap<>();
    public static final int MAXIMUN_TRIANGLES = 3;

    private double side1;
    private double side2;
    private double side3;

    private Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;

        if (allSidesAreZero() || hasImpossibleSides() || violatesTriangleInequality()) {
            throw new Figura2DException();
        }

    }

    public TriangleKind getKind() {
        int uniqueSides = getNumberOfUniqueSides();
        double[] sizes = {side1, side2, side3};

        Arrays.sort(sizes);

        if (Math.pow(sizes[0], 2) + Math.pow(sizes[1], 2) == Math.pow(sizes[2], 2)) {
            return TriangleKind.RECTANGLE;
        }

        if (uniqueSides == 1) {
            return TriangleKind.EQUILATERAL;
        }

        if (uniqueSides == 2) {
            return TriangleKind.ISOSCELES;
        }

        return TriangleKind.SCALENE;
    }

    private boolean allSidesAreZero() {
        return side1 == 0 && side2 == 0 && side3 == 0;
    }

    private boolean hasImpossibleSides() {
        return side1 < 0 || side2 < 0 || side3 < 0;
    }

    private boolean violatesTriangleInequality() {
        return side1 + side2 <= side3 || side1 + side3 <= side2 || side2 + side3 <= side1;
    }

    public int getNumberOfUniqueSides() {
        Set<Double> sides = new HashSet<>();

        sides.add(side1);
        sides.add(side2);
        sides.add(side3);

        return sides.size();
    }

    public static Triangle getInstance(TriangleKind tipoTriangulo) {
        return instances.get(tipoTriangulo);
    }

    public static Triangle createInstance(double side1, double side2, double side3) {

        if (instances.size() == MAXIMUN_TRIANGLES) {
            throw new SingletonException("Já possui %d triangulos".formatted(MAXIMUN_TRIANGLES));
        }

        Triangle triangle = new Triangle(side1, side2, side3);

        TriangleKind triangleKind = triangle.getKind();

        if (instances.containsKey(triangleKind)) {
            throw new SingletonException("Não é possivel triangulo com o mesmo tipo 2x");
        }

        if (triangle.getKind() == TriangleKind.SCALENE) {
            throw new SingletonException("Triangulo escaleno não é permitido");
        }

        instances.put(triangleKind, triangle);

        return triangle;
    }

    public static void resetInstances() {
        instances = new HashMap<>();
    }

    public static void showTriangles() {
        System.out.println("Triângulos");
        System.out.println();

        for (Triangle triangle : instances.values()) {
            System.out.println(triangle);
        }

        System.out.println();
    }

    @Override
    public double getPerimetro() {
        return side1 + side2 + side3;
    }

    @Override
    public double getArea() {
        double s = getPerimetro() / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    @Override
    public String getNome() {
        return "Triângulo";
    }

    @Override
    public String toString() {
        return "%s [Lados: %.2f, %.2f, %.2f] | [Tipo: %s]".formatted(
            super.toString(),
            side1,
            side2,
            side3,
            getKind()
        );
    }
}