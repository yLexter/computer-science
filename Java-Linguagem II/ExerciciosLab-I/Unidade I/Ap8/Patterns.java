package Java.ExerciciosLab.Ap8;

// Questão 4
public class Patterns {
    public static void pattern1(int size) {

        for(int x = 1; x <= size; x++) {

            String line = "";

            for (int y = 1; y <= x; y++)
                line += String.format("%d ", y);

            System.out.println(line);

        }

        System.out.println();

    }
    public static void pattern2(int size) {

        for(int x = 1; x <= size; x++) {

            String line = "";

            for (int y = 1; y <= size; y++) {

                if (y <= size - (x - 1)) {
                    line += String.format("%d ", y);
                } else {
                    line += " ";
                }

            }

            System.out.println(line);

        }

        System.out.println();

    }
    public static void pattern3(int size) {

        for(int x = 1; x <= size; x++) {

            String line = "";

            for (int y = size; y >= 1; y--) {

                if (y <= x) {
                   line += String.format("%d ", y);
                } else {
                    line += "  ";
                }

            }

            System.out.println(line);

        }

        System.out.println();

    }
    public static void pattern4(int size) {

        for(int x = 1; x <= size; x++) {

            String line = "";
            int temp = 1;

            for(int y = 1; y <= size; y++) {

                if (y >= x) {
                    line += String.format("%d ", temp);
                    temp++;
                } else {
                    line += "  ";
                }
            }

            System.out.println(line);

        }

        System.out.println();


    }

    public static void main(String[] args) {
        Patterns.pattern1(9);
        Patterns.pattern2(9);
        Patterns.pattern3(9);
        Patterns.pattern4(9);
    }
}




