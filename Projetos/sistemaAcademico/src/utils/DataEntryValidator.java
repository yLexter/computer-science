package utils;

import general.AcademicSystem;
import general.EntranceExam;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A classe DataEntryValidator fornece métodos estáticos para validar dados de entrada.
 * Esses métodos verificam se os dados fornecidos estão corretos e lançam exceções caso contrário.
 */

public class DataEntryValidator {



  /**
     * Valida se o nome fornecido é válido.
     * O nome não pode estar vazio e não pode ser um número.
     *
     * @param name o nome a ser validado
     * @throws IllegalArgumentException se o nome estiver vazio ou for um número
     */

    public static void validName(String name) {

        if (name.trim().equals(""))
            throw new IllegalArgumentException("Nome Vazio");

        try {
            Integer.parseInt(name);
            throw new IllegalArgumentException("O nome fornecido é um numero");
        } catch (NumberFormatException ignored) {}
    }


    /**
     * Valida se o número fornecido é positivo.
     *
     * @param number o número a ser validado
     * @throws IllegalArgumentException se o número for negativo
     */
    public static void validNumberIsPositive(Number number) {
        if (number.doubleValue() < 0)
            throw new IllegalArgumentException("Número precisa ser positivo");
    }

   /**
     * Valida se a nota fornecida é válida.
     * A nota deve estar entre 0 e 10.
     *
     * @param note a nota a ser validada
     * @throws IllegalArgumentException se a nota for inválida
     */

    public static void validNote(float note) {
        if (note < 0 || note > 10)
            throw new IllegalArgumentException("Nota invalida");
    }

    /**
     * Valida se a string fornecida não está vazia.
     *
     * @param string a string a ser validada
     * @throws IllegalArgumentException se a string estiver vazia
     */

    public static void validStringIsNotEmpty(String string) {
        if (string.trim().equals(""))
            throw new IllegalArgumentException("A String não pode ser vazia");
    }

  /**
     * Valida se o CPF fornecido é válido e não está em uso por outra pessoa.
     *
     * @param cpf o CPF a ser validado
     * @throws IllegalArgumentException se o CPF não tiver 11 dígitos ou se já estiver em uso
     */

    public static void validCpf(String cpf) {
        AcademicSystem academicSystem = Global.getAcademicSystem();

        if (!cpf.matches("(\\d{11})"))
            throw new IllegalArgumentException("O CPF Precisa ter 11 digitos");

        if (academicSystem.db.getEmployeeByCpf(cpf) != null)
            throw new IllegalArgumentException("CPF já usado por outra pessoa");

    }

   /**
     * Valida se a data fornecida está no formato correto (XX/XX/XXXX) e a converte para LocalDate.
     *
     * @param date a data a ser validada
     * @return a data validada como um objeto LocalDate
     * @throws IllegalArgumentException se a data não estiver no formato correto
     */

    public static LocalDate validDate(String date) {
        Pattern regex = Pattern.compile("(0[0-9]|[1-2][0-9]|3[0-1])/(0?[1-9]|1[0-2])/(\\d{4})");
        Matcher match = regex.matcher(date);

        if (!match.find())
            throw new IllegalArgumentException("A Data não está no formato XX/XX/XXXX");

        try {
            return LocalDate.of(
                    Integer.parseInt(match.group(3)),
                    Integer.parseInt(match.group(2)),
                    Integer.parseInt(match.group(1))
            );
        } catch (DateTimeException err) {
            throw new IllegalArgumentException("Data inválida");
        }

    }

      /**
     * Valida se a data fornecida não é do futuro.
     * Além disso, converte a data para LocalDate.
     *
     * @param date a data a ser validada
     * @return a data validada como um objeto LocalDate
     * @throws Error se a data for no futuro
     * @see #validDate(String)
     */

    public static LocalDate validDateIsNotFromTheFuture(String date) {
        LocalDate dateValided = validDate(date);

        if (dateValided.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("A data não pode ser no futuro");

        return dateValided;
    }

   /**
     * Valida se a pontuação do exame de entrada é válida.
     * A pontuação deve estar dentro dos limites mínimo e máximo definidos.
     *
     * @param score a pontuação a ser validada
     * @throws IllegalArgumentException se a pontuação for inválida
     */

    public static void entranceExamCcore(double score) {
        if (score < EntranceExam.minimumScore || score > EntranceExam.maximumScore)
            throw new IllegalArgumentException("Nota invalida");
    }

   /**
     * Valida se o horário fornecido está no formato correto (XX:XX) e o converte para LocalTime.
     *
     * @param time o horário a ser validado
     * @return o horário validado como um objeto LocalTime
     * @throws IllegalArgumentException se o horário não estiver no formato correto
     */

    public static LocalTime validTime(String time) {

        if (!time.matches("(0?[0-9]|[0-1][0-9]|2[0-3]):([0-5][0-9])"))
            throw new IllegalArgumentException("o Horario não está no formato XX/XX/XXXX");

        List<Integer> times = Arrays.stream(time.split(":"))
                .map(Integer::parseInt)
                .toList();

        return LocalTime.of(
           times.get(0),
           times.get(1)
        );

    }

}


