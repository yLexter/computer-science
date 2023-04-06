package Java.ExerciciosLab.Ap8;

public class Matriz {


    // Questão 10
    public static boolean isIndetityMatriz(int[][] matrix) {

        for(int i = 0; i < matrix.length; i++) {

            for(int j = 0; j < matrix[i].length; j++) {

                int currentElement = matrix[i][j];

                if (currentElement != 1 && i == j)
                    return false;

                if (currentElement != 0)
                    return false;
            }
        }

        return true;
    }
    // Questão 8
    public static int totalNullElements(int[][] matrix) {

        int total = 0;

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] == 0)
                    total++;
            }
        }

        return total;

    }

    public static void main(String[] args) {

    }




}
