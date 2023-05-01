package Java.ExerciciosLab.Ar4;
import java.util.*;

public class CustomArrayList {

    public static <T> ArrayList<T> joinArrays(ArrayList<T> list1, ArrayList<T> list2) {

        ArrayList<T> newArray = new ArrayList<>();

        newArray.addAll(list2);
        newArray.addAll(list1);

        return newArray;
    }


    public static <T> void showArray(ArrayList<T> list) {

        for(T value : list)
            System.out.printf("%s ", value.toString());

        System.out.println();
    }

    public static <T extends Number> ArrayList<T> removeDuplicates(ArrayList<T> list) {
       HashSet<T> setArray = new HashSet<T>(list);
       return new ArrayList<T>(setArray);
    }

    public static Integer sum(ArrayList<Integer> list) {
        return list.stream().reduce(0, Integer::sum);
    }

    public static void main(String[] args) {

        ArrayList<Double> a1 = new ArrayList<>(Arrays.asList(1.0009, 1.009, 1.00, 1.000));

        showArray(removeDuplicates(a1));


    }




}
