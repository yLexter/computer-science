package AbstractData.HashTable.DataProvider;

import java.security.SecureRandom;

/**
 * Classe utilitária para a geração de vetores de strings aleatórias.
 */

public class StringProvider {

    /**
     * Objeto SecureRandom para geração de números aleatórios seguros.
     */
    public static final SecureRandom random = new SecureRandom();

    /**
     * Gera um vetor de strings aleatórias com o comprimento especificado.
     *
     * @param stringLength O comprimento de cada string no vetor.
     * @param vectorLength O comprimento total do vetor.
     * @return Um vetor de strings aleatórias.
     */
    public static String[] generateRandomVector(int stringLength, int vectorLength) {
        String[] vector = new String[vectorLength];
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = null;

        System.out.println("- Iniciando geração de strings...");

        for (int i = 0; i < vectorLength; i++) {

            randomString = new StringBuilder();

            for (int j = 0; j < stringLength; j++) {
                int index = random.nextInt(characters.length());
                randomString.append(characters.charAt(index));
            }

            vector[i] = randomString.toString();
        }

        System.out.println("- Geração de String Finalizada\n");

        return vector;
    }

    /**
     * Gera um vetor de strings para operações de busca com uma porcentagem das strings do vetor de inserção.
     *
     * @param vectorInsertion O vetor de strings utilizado para operações de inserção.
     * @param percentage      A porcentagem de strings do vetor de inserção a serem incluídas no vetor de busca.
     * @param stringLength    O comprimento de cada string no vetor de busca.
     * @return Um vetor de strings para operações de busca.
     */
    public static String[] getVectorForSearch(String[] vectorInsertion, int percentage, int stringLength) {
        int total = vectorInsertion.length * (percentage / 100);
        String[] randomVector = generateRandomVector(stringLength, vectorInsertion.length);

        for (int i = 0; i < total; i++) {
            int position = random.nextInt(total);
            randomVector[position] = vectorInsertion[i];
        }

        return randomVector;
    }
}

