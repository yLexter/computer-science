package interfaces.general;

import java.util.List;

/**
 * Esta interface representa uma fábrica de objetos genéricos (T).
 * Ela define o método generate, que recebe uma lista de objetos atuais e retorna um novo objeto gerado.
 *
 */

public interface IObjectFactory<T> {
  /**
     * Gera um novo objeto com base na lista atual de objetos.
     *
     * @param currentList A lista de objetos atuais.
     * @return O objeto gerado.
     */
    T generate(List<T> currentList);
}