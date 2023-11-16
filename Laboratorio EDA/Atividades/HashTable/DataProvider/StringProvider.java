package AbstractData.HashTable.DataProvider;

import java.security.SecureRandom;

public class StringProvider {

    public static final SecureRandom random = new SecureRandom();

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

    public static String[] getVectorForSearch(String[] vectorInsertion, int percetage, int stringLength) {
        int total = vectorInsertion.length * (percetage / 100);
        String[] randomVector = generateRandomVector(stringLength, vectorInsertion.length);

        for (int i = 0; i < total; i++) {
            int position = random.nextInt(total);
            randomVector[position] = vectorInsertion[i];
        }

        return randomVector;
    }

}
