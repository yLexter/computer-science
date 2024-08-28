package Java.QuestoesLabAv1.Question10;
public class Summation { // Criação da classe para realizar o somatório
    public static double factorial(int number) { // Criação do método para calcular o fatorial de um número

        double factorial = 1.0; // Criação da variável factorial

        for (int i = 1; i <= number; i++) { // Criação do laço de repetição para fazer multiplicações consectivas
            factorial *= i; // O valor de i é multiplicado pelo valor de factorial e o resultado é armazenado em factorial
        } // Fim do laço do repetição
        return factorial; // retorna o valor armazenado em factorial
    } // Fim do método factorial

    public static void sum(int x) { // Criação do método para realizar o somatório
        double sum = 0; // Criação da variável sum
        System.out.print("s = "); // Print de mensagem "s =" sem quebrar linha
        for (int i = 1; i <= 10; i++) { // Criação do laço de repetição
            if (i % 2 != 0) { // Criação de estrutura de controle if para que seja feito somatório em caso do i for ímpar
                sum += x / factorial(i); // É feita a divisão do valor de x pelo valor do fatorial do valor de i por meio da chamada do método factorial e passando i como argumento. Assim, o resultado é somado a sum
                System.out.printf("%d/%d! - ", x, i); // Feita impressão do valor de x e i separados pelo caractere '/' e seguidos dos caracteres "! - "
            } // Fim da estrutura de controle if
            else{ // Criação da estrutura de controle else para que seja feito somatório em caso do i for par
                sum += (x / factorial(i)) * - 1; // É feito o somatório da mesma forma que no caso anterior, mas dessa ver o resultado é multiplicado por -1 para que haja a subtração
                if (i < 10) // Criação de estrutura de controle if para adaptar impressão final
                    System.out.printf("%d/%d! + ", x, i); // Feita impressão do valor de x e i separados pelo caractere '/' e seguidos dos caracteres "! + "
                else // Criação da estrutura de controle else para impressão final ser diferente das demais
                    System.out.printf("%d/%d! = %2f", x, i, sum); // Feita impressão do valor de x e i separados pelo caractere '/', seguidos dos caracteres "! = " e o valor de sum
            } // Fim da estrutura de controle else
        } // Fim do for
    } // Fim do método sum
} // Fim da classe Summation
