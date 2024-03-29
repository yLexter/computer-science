package erros;

/*
 * A classe DatabaseException é responsável pela mensagem de erro que é exibida caso aconteça alguma exceção na database
 * do código.
 * DatabaseException herda da classe RuntimeException
 */
public class DatabaseException extends RuntimeException {

    /** O método DatabaseExpcetion exibe a mensagem de erro que é criada na classe RuntimeException
     * @param message o parâmetro é utilizado para capturar os erros da database e assim
     *                exibir a mensagem com o erro que ocorreu no sistema
     */
    public DatabaseException(String message) {
          super(message);
      }

}