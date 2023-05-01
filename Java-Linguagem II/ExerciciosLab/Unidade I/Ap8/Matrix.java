package Java.ExerciciosLab.Ap8;

import java.lang.reflect.Array;

public class Matrix {

    // Questão 2
    public static int[][] generateLowerTriangularMatrix(int size) {

        int[][] auxiliaryMatrix = new int[size][size];

        for(int i = 0; i < size; i++) {

            for(int j = 0; j < size; j++) {

                if(i >= j)
                    auxiliaryMatrix[i][j] = 1;

            }
        }

        return auxiliaryMatrix;
    }

    // Questão 2
    public static int[][] generateUpperTriangularMatrix(int size) {

        int[][] auxiliaryMatrix = new int[size][size];

        for(int i = 0; i < size; i++) {

            for(int j = 0; j < size; j++) {

                if(i <= j)
                    auxiliaryMatrix[i][j] = 1;

            }
        }

        return auxiliaryMatrix;
    }

    // Questão 2
    public static int[][] generateIndetityMatrix(int size) {

        int[][] auxiliaryMatrix = new int[size][size];

        for(int i = 0; i < size; i++) {

            for(int j = 0; j < size; j++) {

                if(i == j)
                    auxiliaryMatrix[i][j] = 1;

            }
        }

        return auxiliaryMatrix;
    }

    // Questão 2
    public static int[][] generateReverseIndetityMatrix(int size) {

        int[][] auxiliaryMatrix = new int[size][size];

        for(int i = 0; i < size; i++) {

            for(int j = 0; j < size; j++) {

                if(i + j == size - 1)
                    auxiliaryMatrix[i][j] = 1;

            }
        }

        return auxiliaryMatrix;
    }

    // Questão 8
    public static <T extends Number> int totalNullElements(T[][] matrix) {

        int total = 0;

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j].doubleValue() == 0)
                    total++;
            }
        }

        return total;
    }

    // Questão 9
    public static <T extends Number> T[][] getTransposedMatrix(T[][] matrix) {

        T[][] auxiliaryMatrix = (T[][]) new Object[matrix.length][matrix.length];

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
               auxiliaryMatrix[j][i] = matrix[i][j];
            }
        }

        return auxiliaryMatrix;
    }

    // Questão 10
    public static <T extends Number> boolean isIndetityMatriz(T[][] matrix) {

        for(int i = 0; i < matrix.length; i++) {

            for(int j = 0; j < matrix[i].length; j++) {

                double currentElement = matrix[i][j].doubleValue();

                if (currentElement != 1 && i == j)
                    return false;

                if (currentElement != 0)
                    return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {




    }




}
