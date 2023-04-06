package Java.ExerciciosLab.Ap8;

// Questão 7
public class Palindrome {

    // To-Do Arrajar uma forma de tirar os espaços

    public static boolean isPalindrome(String word) {

        char[] wordFormated = word.trim().toLowerCase().toCharArray();

        if (wordFormated.length % 2 != 0)
            return false;

        for (int x = 0; x < wordFormated.length / 2; x++) {

            char char1 = wordFormated[x];
            char char2 = wordFormated[(wordFormated.length - 1) - x];

            if (char1 != char2)
                return false;

        }

        return true;

    }


    public static void main(String[] args) {

        System.out.println(Palindrome.isPalindrome("lucas"));

    }


}
