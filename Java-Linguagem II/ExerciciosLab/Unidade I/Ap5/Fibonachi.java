package Java.ExerciciosLab.Ap5;

// Exercicio 6
public class Fibonachi {

    static long getFibonachi(long number) {
        if (number <= 2)
            return 1;
        return Fibonachi.getFibonachi(number - 1) +
               Fibonachi.getFibonachi(number - 2);
    }


}
