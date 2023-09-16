package Algorithms;

import java.security.SecureRandom;

public class BubbleSort {

    public static void traditionalBubbleSort(int[] arr) {

        long start = System.currentTimeMillis(), end;
        int totalRuns = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                totalRuns++;
            }
        }

        end = System.currentTimeMillis();

        showResults(start, end, totalRuns, "Classico");
    }

    /**
     *  Bubble Sort com as seguintes melhorias:
     *
     *  Redução de interações do for interno pois a cada interação o maior número vai para ultima posição
     *  então não precisa ir até o final no for interno, podemos apenas subtrair o i
     *
     *  Caso não possuam nenhuma troca durante o for interno, isso significa que o vetor já esta ordenado e podemos
     *  simplismente finalizar o for externo com ajuda de uma variavel boleana
     *
     * */
    public static void optimizedBubbleSort(int[] arr) {

        boolean isOrdened = false;
        int totalRuns = 0;

        long start = System.currentTimeMillis(), end;

        for (int i = 0; i < arr.length && !isOrdened; i++) {

            isOrdened = true;

            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isOrdened = false;
                }
                totalRuns++;
            }

        }

        end = System.currentTimeMillis();

        showResults(start, end, totalRuns, "Otimizado");
    }

    public static void showResults(long start, long end, int executions, String name) {
        long time = end - start;

        System.out.printf("Algoritimo: %s\n", name);
        System.out.printf("Tempo de Execução: %dms\n", time);
        System.out.printf("Total de Comparações: %d\n\n", executions);
    }

    public static int[] getVetorDisordered(int size) {

        int[] vetor = new int[size];
        SecureRandom random = new SecureRandom();

        for(int x = 0; x < size; x++)
            vetor[x] = random.nextInt(500);

        return vetor;
    }

    public static void main(String[] args) {

        int size = 1_000;

        int [] vetor1 = getVetorDisordered(size),
               vetor2 = new int[size];

        System.arraycopy(vetor1, 0, vetor2, 0, size);

        System.out.println("Vetores Desordenados\n");
        traditionalBubbleSort(vetor1);
        optimizedBubbleSort(vetor2);

        System.out.println("Vetores Ordenados\n");
        traditionalBubbleSort(vetor1);
        optimizedBubbleSort(vetor2);
    }

}




