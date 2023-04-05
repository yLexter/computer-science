package Java.ExerciciosLab;

// Exercicio 6
public class Fibonachi {

    static long getFibonachi(long number) {
        if (number <= 2)
            return 1;
        return Fibonachi.getFibonachi(number - 1) +
                Fibonachi.getFibonachi(number - 2);
    }

    public static void test() {
        System.out.println(Fibonachi.getFibonachi(10));
    }


}
