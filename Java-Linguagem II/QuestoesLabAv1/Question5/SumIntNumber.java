package Java.QuestoesLabAv1.Question5;
public class SumIntNumber { // Criação da classe para realizar somatório de números inteiros menores que o número inteiro fornecido pelo usuário
    public static void Summation(int number) { // Criação do método para realizar o somatório
        int sum = 0; // criação da variável para armazenar os valores somados

        for (int i = 0; i < number; i++) { // criação de laço de repetição para realizar somas consecutivamente
            sum += i; // O valor de i é somado ao valor de sum e o resultado é atribuído a sum
            if (i < number - 1) // Criação de estrutura de controle if para adaptaro que será impresso na tela
                System.out.print(i + " + "); // Caso a condição seja satisfeita será impresso o valor de i concatenado com o caractere +
            else // Criação de estrutura de controle else para atender os caso onde a condição anterior não é satisfeita e por isso precisamos de uma impressão diferente
                System.out.println( i + " = " + sum); // Nesse caso, é impresso o valor de i concatenado com o caractere = que por sua vez é concatenado com o valor de sum
        } // Fim do laço de repetição
    } // Fim do método Summation
} // Fim da classe SumIntNumber
