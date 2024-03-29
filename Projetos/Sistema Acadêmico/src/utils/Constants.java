package utils;

/**
 * A classe Constants contém constantes utilizadas em várias partes do sistema.
 */

public class Constants {

   /**
     * String de entrada para sair do programa.
     */

    public static final String exitInputString = "-s";

      /**
     * Índice de início das opções.
     */

    public static final int startOptionsIndex = 1;

    /**
     * A classe ErrorMessage contém mensagens de erro relacionadas ao sistema.
     * Todas as mensagens são constantes e podem ser utilizadas em diversas partes do sistema.
     */

    public static final class ErrorMessage {

        public static final String EMPLOYEE_NOT_AUTHENTICATE = "Usuário ou senha incorretos";

      /**
         * Construtor privado para evitar a criação de instâncias da classe ErrorMessage.
         */
        private ErrorMessage() {}
    }



}
