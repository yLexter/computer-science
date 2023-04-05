package Java.QuestoesLabAv1.Question11;

import java.util.Arrays;
import java.util.HashSet;

public class Vowels {

    /**
     * Contabiliza o total de vogais numa string
     *
     * @param word uma string qualquer
     * @return Um Inteiro com o total de vogais
     */
    public static Integer getTotalVowels(String word) {

        if (word == null || word == "") {
            System.out.println("String cannot be null or void");
            return null;
        }

        char[] stringArray = word.toLowerCase().toCharArray();
        HashSet<Character> vowels = new HashSet<>(Arrays.asList(
                'a', 'e', 'i', 'o', 'u', 'á', 'â', 'é', 'ê', 'í', 'ó', 'ô', 'ú'
        ));
        int total = 0;

        for (Character character : stringArray) {
            if (vowels.contains(character)) total++;
        }

        return total;

    }

}
