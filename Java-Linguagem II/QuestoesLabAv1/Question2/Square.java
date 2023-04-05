package Java.QuestoesLabAv1.Question2;

public class Square {

    /**
     * Mostra um quadrado na tela
     *
     * @param size um inteiro para o tamanho do quadrado
     */
    public static void showSquare(int size) {

        if (size <= 0) {
            System.out.println("The size has to be greater than zero");
            return;
        }

        for (int x = 0; x < size; x++) {

            int topOrRigthSide = size - 1;
            int downOrLeftSide = 0;
            String line = "";

            for (int y = 0; y < size; y++) {

                String caractere =
                        (x == downOrLeftSide || y == downOrLeftSide) ||
                        (x == topOrRigthSide || y == topOrRigthSide) ? "* " : "  ";

                line += caractere;
            }

            System.out.println(line);
        }

    }



}
