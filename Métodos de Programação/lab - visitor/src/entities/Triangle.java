package entities;


import erros.Figura2DException;
import interfaces.IAceitaVisitor;
import interfaces.IVisitor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Triangle extends Figura2D implements IAceitaVisitor {

    private double side1;
    private double side2;
    private double side3;

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;

        if (allSidesAreZero() || hasImpossibleSides() || violatesTriangleInequality()) {
            throw new Figura2DException();
        }

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


    public double getSide1() {
        return side1;
    }

    public double getSide2() {
        return side2;
    }

    public double getSide3() {
        return side3;
    }



    public void setSide1(double side1) {
        new Triangle(side1, this.side2, this.side3);

        this.side1 = side1;
    }

    public void setSide2(double side2) {
        new Triangle(this.side1, side2, this.side3);

        this.side2 = side2;
    }

    public void setSide3(double side3) {
        new Triangle(this.side1, this.side2, side3);

        this.side3 = side3;
    }
    public void setSides(double side1, double side2, double side3) {
        new Triangle(side1, side2, side3);

        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }


    @Override
    public String getNome() {
        return "Triângulo";
    }

    @Override
    public <T> T aceitarVisitor(IVisitor<T> visitor) {
        return visitor.visitaTriangulo(this);
    }

    @Override
    public String toString() {
        return "%s [Lados: %.2f, %.2f, %.2f]".formatted(
            super.toString(),
            side1,
            side2,
            side3
        );
    }

}