import java.util.Arrays;

public class QuickSort {

    public static int partition(int[] arr, int left, int right) {

        int i = left, temp;

        for (int j = left; j < right; j++) {

            if(arr[right] >= arr[j]) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;    
            }
        }

        temp = arr[i];
        arr[i] = arr[right];
        arr[right] = temp;   

        return i;
    }

    public static void quickSort(int[] arr, int left, int right) {

         if (right > left) {
            int pivot = partition(arr, left, right);    
            
            quickSort(arr, left, pivot - 1);    
            quickSort(arr, pivot + 1, right);    
         }   


    }

    public static void run(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int[] elements = {50, 42, 8, 23, 74, 91, 13, 26, 66, 3};

        System.out.println(Arrays.toString(elements));

        run(elements);

        System.out.println(Arrays.toString(elements));

        
    }


}