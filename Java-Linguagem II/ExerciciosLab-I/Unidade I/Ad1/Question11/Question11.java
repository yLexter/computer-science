package Java.QuestoesLabAv1.Question11;
import java.util.Scanner;

public class Question11 {

    public static void main(String args[]) {

        System.out.println("Digite a string: ");
        String input = new Scanner(System.in).nextLine();

        int totalVowels = Vowels.getTotalVowels(input);

        System.out.printf("O Total de vogais da palavra %s é %d", input, totalVowels);

    }
}
