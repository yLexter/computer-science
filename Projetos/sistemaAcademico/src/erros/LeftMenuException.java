package erros;

/* A classe LeftMenuException é responsável pela mensagem de erro que é exibida caso aconteça alguma exceção no menu
 *  LeftMenuException herda de RuntimeException
 */
public class LeftMenuException extends RuntimeException {

    public LeftMenuException() {
        super();
    }

    /** O método LeftMenuExpcetion exibe a mensagem de erro que é criada na classe RuntimeException
     * @param message o parâmetro é utilizado para capturar os erros do menu e assim
     *                exibir a mensagem com o erro que ocorreu no sistema
     */
    public LeftMenuException(String message) {
        super(message);
    }

}
