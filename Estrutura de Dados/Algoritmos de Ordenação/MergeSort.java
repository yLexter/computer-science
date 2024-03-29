import java.util.Arrays;

public class MergeSort {

    static void merge(int[] arr, int[] aux, int left, int middle, int right) {

        for (int z = left; z < right + 1; z++) {
            aux[z] = arr[z];
        }

        int i = left, j = middle + 1;

        for (int k = left; k < right + 1 ; i++) {

            if (i > middle) {
                arr[k] = aux[j];
                j++;
            } else if (j > right) {
                arr[k] = aux[i];
                i++;
            } else if (aux[j] < aux[i]) {
                arr[k] = aux[j];
                j++;
            } else {
                arr[k] = aux[i];
                i++;
            }

        }

    }

    static void mergeSort(int[] arr, int left, int right, int[] aux) {

        if (right <= left)
            return;

        int middle = (left + right) / 2;

        mergeSort(arr, left, middle, aux);
        mergeSort(arr, middle + 1, right, aux);
        merge(arr, aux, left, middle, right);

    }

    public static void main(String[] args) {

        int[] values = { 1, 5, 63, 5, 5, 5, 6, 15, 3, 4, 65, 0, 1515, 10, 23 };
        int[] aux = new int[values.length];

        mergeSort(values, 0, values.length - 1, aux);

        System.out.println(Arrays.toString(values));

    }

}
