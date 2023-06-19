package utils;

import general.EntranceExam;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataEntryValidator {

    public static void validStringInput(String string) {
        if (string.equals(""))
            throw new IllegalArgumentException("A String não pode ser vazia");
    }

    public static int validAge(String integer) {
        int toInt = Integer.parseInt(integer);

        if (toInt <= 0)
            throw new IllegalArgumentException("O número fornecido precisa ser positivo");

        return toInt;
    }

    public static void validCpf(String cpf) {
        if (!cpf.matches("(\\d{3}).(\\d{3}).(\\d{3})-(\\d{2})"))
            throw new IllegalArgumentException("O cpf não está no formato XXX.XXX.XXX-XX");
    }

    public static LocalDate validDate(String date) {
        Pattern regex = Pattern.compile("(0[0-9]|[1-2][1-9]|3[0-1])/(0[1-9]|1[0-2])/(\\d{4})");
        Matcher match = regex.matcher(date);

        if (!match.find())
            throw new IllegalArgumentException("A Data não está no formato XX/XX/XXXX");

        return LocalDate.of(
                Integer.parseInt(match.group(3)),
                Integer.parseInt(match.group(2)),
                Integer.parseInt(match.group(1))
        );

    }

    public static double entranceExamCcore(String scoreString) {
        double score = Double.parseDouble(scoreString);

        if (score < EntranceExam.minimumScore || score > EntranceExam.maximumScore)
            throw new IllegalArgumentException("Nota invalida");

        return score;
      }


}


