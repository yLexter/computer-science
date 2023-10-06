import java.util.Arrays;

public class CoutingSort {

    static int[] countigSort(int[] arr) {

        int max = arr[0], auxCount = 0;
        
        for (int num : arr) {
            
         if (num > max) 
            max = num;
            
        }
        
        int[] count = new int[max + 1];
        int[] sortedArr = new int[arr.length];

        for (int num : arr) 
            count[num] += 1;
        
        for (int i = 0; i < max + 1; i++) {

            for (int j = count[i]; j > 0 ; j--) {
                sortedArr[auxCount] = i;
                auxCount++;
            }
            
        }


        return sortedArr;
     }


    public static void main(String[] args) {
        int[] values = {1,5,63,5,5,5,6,15,3,4,65, 0, 1515, 10, 23};

        System.out.println(Arrays.toString(countigSort(values)));
        
    }
    
}
