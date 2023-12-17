package Java.ExerciciosLab.Ap8;

import java.util.Arrays;

// Questão 7
public class Palindrome {

    // ToDo Arrajar uma forma de tirar os espaços e acentos

    // Não recursivo
    public static boolean isPalindrome(String word, int num) {

        char[] wordFormated = word.trim().toLowerCase().toCharArray();

        for (int x = 0; x < wordFormated.length / 2; x++) {

            char char1 = wordFormated[x];
            char char2 = wordFormated[(wordFormated.length - 1) - x];

            if (char1 != char2)
                return false;

        }

        return true;

    }

    // Recursivo
    public static boolean isPalindrome(String word) {

        char[] wordFormatted = word.toLowerCase().toCharArray();
        char firstChar = wordFormatted[0];
        char lastChar = wordFormatted[wordFormatted.length - 1];

        if (firstChar != lastChar)
            return false;

        if (wordFormatted.length <= 2)
            return true;

        String subWord = new String(Arrays.copyOfRange(wordFormatted, 1, wordFormatted.length - 1));

        return isPalindrome(subWord);
    }

    public static void main(String[] args) {

        System.out.println(isPalindrome("macaco"));
        System.out.println(isPalindrome("subinoonibus"));
        System.out.println(isPalindrome("radar"));
        System.out.println(isPalindrome("renner"));

    }


}
