package Java.QuestoesLabAv1.Question2;
import java.util.Scanner;

public class Question2 {

     public static void main(String[] args) {

         System.out.println("Digite o tamanho do quadrado: ");
         int sizeSquare = new Scanner(System.in).nextInt();

         Square.showSquare(sizeSquare);

    }
}
