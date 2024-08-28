package Java.ExerciciosLab.Ap8;

public class PascalTriangle {

    public static int fatorial(int num) {
        if (num <= 1)
            return 1;
        return num * fatorial(num - 1);
    }


    public static void showTriangle(int size) {

        for (int x = 1; x <= size; x++) {

            StringBuilder line = new StringBuilder();

            for (int y = 0; y <= x; y++) {

                int result = fatorial(x) / (fatorial(y) * (fatorial(x - y)));

                line.append(String.format("%d ", result));
            }

            System.out.println(line);

        }

    }


    public static void main(String[] args) {
        showTriangle(6);
    }





}
