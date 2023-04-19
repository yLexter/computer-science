package Java.ExerciciosLab.Ap9;

import java.util.*;

public class TP092 {
    public int[][] matrix;
    public int size;

    TP092(int size) {
        this.size = size;
        this.matrix = new int[size][size];
    }
    
    public void fillMatrixByUser() {

        Scanner input = new Scanner(System.in);

        for (int i = 0; i < this.matrix.length; i++) {

            for (int j = 0; j < this.matrix[i].length; j++) {
                System.out.printf("Digite o elemento %dx%d ", i, j);
                this.matrix[i][j] = input.nextInt();
            }


        }

        input.close();

        System.out.println();
    }

    public int getTotalSumMatrix() {

        int sum = 0;

        for (int i = 0; i < this.matrix.length; i++) {

            for (int j = 0; j < this.matrix[i].length; j++)
                sum += this.matrix[i][j];

        }

        return sum;
    }

    public int[] getSumColumns() {

        int[] sumColumns = new int[this.matrix.length];

        for (int i = 0; i < this.matrix.length; i++) {

            int currentColumnSum = 0;

            for (int j = 0; j < this.matrix[i].length; j++)
                currentColumnSum += this.matrix[j][i];

            sumColumns[i] = currentColumnSum;
        }

        return sumColumns;
    }

    public static void showMatrix(int[][] customMatrix) {

        for (int i = 0; i < customMatrix.length; i++) {

            String line = "";

            for (int j = 0; j < customMatrix[i].length; j++)
                line += String.format("%d ", customMatrix[i][j]);

            System.out.println(line);
        }

    }

    public static int[][] getReverseMatrix(int [][]matrix) {

        int[][] reverseMatrix = new int[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++)
                reverseMatrix[i][j] = matrix[matrix.length - 1 - i][matrix.length - 1 - j];

        }

        return reverseMatrix;
    }

    public void showResults() {

        System.out.printf("Digite os elementos das %d linhas e as %d colunas da matriz %dx%d\n\n", this.size, this.size, this.size, this.size);

        this.fillMatrixByUser();

        int totalSum = this.getTotalSumMatrix();
        int[] sumColumns = this.getSumColumns();
        int[][] reverseMatrix = getReverseMatrix(this.matrix);

        System.out.printf("A soma dos elementos da matriz é igual a: %d\n\n", totalSum);

        for (int x = 0; x < sumColumns.length; x++)
            System.out.printf("Soma da coluna %d é: %d\n", x, sumColumns[x]);

        System.out.printf("\nA matriz é\n");

        showMatrix(this.matrix);

        System.out.printf("\nA matriz reversa é\n");

        showMatrix(reverseMatrix);
    }

    public static void main(String[] args) {

        TP092 matrix = new TP092(3);
        matrix.showResults();
        
    }


}
