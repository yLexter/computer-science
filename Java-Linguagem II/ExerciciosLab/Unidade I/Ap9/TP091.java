package Java.ExerciciosLab.Ap9;

import java.util.*;

public class TP091 {

    public static void main(String[] args) {

        // Parte 1
        // Criar um array bidimensional 5x3 (cinco linhas, três colunas)
        int[][] array_1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11, 12},
                {13, 14, 15}
        };

        // Uso do length para exibir quantidade de arrays (linhas) do array 2D
        System.out.printf("O array 1 possui %d linhas%n", array_1.length);

        // Uso do length para exibir quantidade de colunas da primeira linha
        System.out.printf("A primeira linha do array 1 possui %d colunas%n", array_1[0].length);

        // Uso do length para exibir quantidade de colunas da segunda linha
        System.out.printf("A segunda linha do array 1 possui %d colunas%n", array_1[1].length);

        // Uso do length para exibir quantidade de colunas da terceira linha
        System.out.printf("A terceira linha do array 1 possui %d colunas%n", array_1[2].length);

        // Uso do length para exibir quantidade de colunas da quarta linha
        System.out.printf("A quarta linha do array 1 possui %d colunas%n", array_1[3].length);

        // Uso do length para exibir quantidade de colunas da quinta linha
        System.out.printf("A quinta linha do array 1 possui %d colunas%n", array_1[4].length);

        System.out.println();

        // Criar um array de inteiros bidimensional 3x6 (três linhas, seis colunas)
        int[][] array_1b = {
                {1, 2, 3, 4, 5, 6},
                {7, 8, 9, 10, 11, 12},
                {13, 14, 15, 16, 17, 18}
        };
        // Uso do length para exibir quantidade de arrays (linhas) do array 2D
        System.out.printf("O array possui 1b %d linhas%n", array_1b.length);

        // Uso do length para exibir quantidade de colunas da primeira linha
        System.out.printf("A primeira linha do array 1b possui %d colunas%n", array_1b[0].length);

        // Uso do length para exibir quantidade de colunas da segunda linha
        System.out.printf("A segunda linha do array 1b possui %d colunas%n", array_1b[1].length);

        // Uso do length para exibir quantidade de colunas da terceira linha
        System.out.printf("A terceira linha do array 1b possui %d colunas%n", array_1b[2].length);

        System.out.println();

        // Parte 2
        // Exemplo de array bidimensional diferente de matriz
        int[][] array_2 = {
                {1, 2, 3, 4, 5},
                {1, 2, 3, 4},
                {1, 2, 3},
                {1, 2},
                {1}
        };

        // Uso do length para exibir quantidade de arrays (linhas) do array 2D
        System.out.printf("O array 2 possui %d linhas%n", array_2.length);

        // Uso do length para exibir quantidade de colunas da primeira linha
        System.out.printf("A primeira linha do array 2 possui %d colunas%n", array_2[0].length);

        // Uso do length para exibir quantidade de colunas da segunda linha
        System.out.printf("A segunda linha do array 2 possui %d colunas%n", array_2[1].length);

        // Uso do length para exibir quantidade de colunas da terceira linha
        System.out.printf("A terceira linha do array 2 possui %d colunas%n", array_2[2].length);

        // Uso do length para exibir quantidade de colunas da quarta linha
        System.out.printf("A quarta linha do array 2 possui %d colunas%n", array_2[3].length);

        // Uso do length para exibir quantidade de colunas da quinta linha
        System.out.printf("A quinta linha do array 2 possui %d colunas%n", array_2[4].length);

        System.out.println();

        // Parte 3
        int array_3[][] = new int[4][];

        /** A partir do array_3, criar a seguinte estrutura 4x4:
         *  10 11 12 13 14
         *  15 16 17 18 19
         *  20 21 22 23 24
         *  25 26 27 28 29
         */

        // Criando a primeira linha:
        array_3[0] = new int[5];

        // Criando a segunda linha:
        array_3[1] = new int[5];

        // Criando a terceira linha:
        array_3[2] = new int[5];

        // Criando a quarta linha:
        array_3[3] = new int[5];

        // Preenchendo o array_3 elemento a elemento
        array_3[0][0] = 10;  array_3[0][1] = 11;  array_3[0][2] = 12;  array_3[0][3] = 13;  array_3[0][4] = 14;
        array_3[1][0] = 15;  array_3[1][1] = 16;  array_3[1][2] = 17;  array_3[1][3] = 18;  array_3[1][4] = 19;
        array_3[2][0] = 20;  array_3[2][1] = 21;  array_3[2][2] = 22;  array_3[2][3] = 23;  array_3[2][4] = 24;
        array_3[3][0] = 25;  array_3[3][1] = 26;  array_3[3][2] = 27;  array_3[3][3] = 28;  array_3[3][4] = 29;


        System.out.println("Elementos do Array 3");
        // Preechendo com laço for
        for(int i = 0, k = 10; i < array_3.length; i++){
            for(int j = 0; j < array_3[i].length; j++){
                array_3[i][j] = k++;
                // Exibindo o array elemento a elemento
                System.out.printf("%d ", array_3[i][j]);
            } // Fim do for interno
            // Quebra de linha
            System.out.println();
        } // For externo

        System.out.println();
        System.out.println("Array 3 Na ordem reversa");

        /** Exibir o array_3 na seguinte ordem:
         *
         *  29 28 27 26 25
         *  24 23 22 21 20
         *  19 18 17 16 15
         *  14 13 12 11 10
         */

        for(int i = array_3.length - 1; i >= 0; i--){
            for(int j = array_3[i].length - 1; j >= 0; j--){
                // Exibindo o array elemento a elemento
                System.out.printf("%d ", array_3[i][j]);
            } // Fim do for interno
            // Quebra de linha
            System.out.println();
        } // For externo

        System.out.println("\n");

        // Uso do length para exibir quantidade de arrays (linhas) do array 2D
        System.out.printf("O tamanho do array 3 é\n", array_3.length);

        // Uso do length para exibir quantidade de colunas da primeira linha
        System.out.printf("O tamanho da coluna 1 do array 3 é %d\n", array_3[0].length);


        // Uso do length para exibir quantidade de colunas da segunda linha
        System.out.printf("O tamanho da coluna 2  do array 3 é %d\n", array_3[1].length);


        // Uso do length para exibir quantidade de colunas da terceira linha
        System.out.printf("O tamanho da coluna 3 do array 3 é %d\n", array_3[2].length);


        // Uso do length para exibir quantidade de colunas da quarta linha
        System.out.printf("O tamanho da coluna 3 do array 3  é %d\n", array_3[3].length);

        System.out.println();

        // Parte 4
        int array_4[][] = new int[4][];

        /** A partir do array_4, criar a seguinte estrutura:
         *
         *  10 11 12 13
         *  14 15 16
         *  17 18 19 20 21
         *  22 23 24 25 26 27 28 29
         */

        // Criando a primeira linha:
        array_4[0] = new int[4];

        // Criando a segunda linha:
        array_4[1] = new int[3];

        // Criando a terceira linha:
        array_4[2] = new int[5];

        // Criando a quarta linha:
        array_4[3] = new int[8];

        // Preencher o array_4 elemento a elemento
        array_4[0][0] = 10;  array_4[1][0] = 14;  array_4[2][0] = 17;
        array_4[0][1] = 11;  array_4[1][1] = 15;  array_4[2][1] = 18;
        array_4[0][2] = 12;  array_4[1][2] = 16;  array_4[2][2] = 19;
        array_4[0][3] = 13;                       array_4[2][3] = 20;
        array_4[2][4] = 21;

        array_4[3][0] = 22;
        array_4[3][1] = 23;
        array_4[3][2] = 24;
        array_4[3][3] = 25;
        array_4[3][4] = 26;
        array_4[3][5] = 27;
        array_4[3][6] = 28;
        array_4[3][7] = 29;

        // Preecher e exibir o array_4 com laço for
        System.out.println("Elementos do Array 4");

        for(int i = 0, k = 10; i < array_4.length; i++){
            for(int j = 0; j < array_4[i].length; j++){
                array_4[i][j] = k++;
                // Exibindo o array elemento a elemento
                System.out.printf("%d ", array_4[i][j]);
            } // Fim do for interno
            // Quebra de linha
            System.out.println();
        } // For externo

        System.out.println();

        // Uso do length para exibir quantidade de arrays (linhas) do array 2D
        System.out.printf("O tamanho do array 4 é\n", array_4.length);

        // Uso do length para exibir quantidade de colunas da primeira linha
        System.out.printf("O tamanho da coluna 1 array 4 da é %d\n", array_4[0].length);


        // Uso do length para exibir quantidade de colunas da segunda linha
        System.out.printf("O tamanho da coluna 2 do array é %d\n", array_4[1].length);


        // Uso do length para exibir quantidade de colunas da terceira linha
        System.out.printf("O tamanho do da coluna 3  array 4 é %d\n", array_4[2].length);


        // Uso do length para exibir quantidade de colunas da quarta linha
        System.out.printf("O tamanho da coluna 4 do array 4 é %d\n", array_4[3].length);



    }

}
