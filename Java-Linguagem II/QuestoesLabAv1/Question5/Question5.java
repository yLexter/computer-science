package Java.QuestoesLabAv1.Question5;

import java.util.Scanner; // Importação de classe Scanner na biblioteca Java
public class Question5 { // Criação da classe principal onde será executado o programa
    public static void main(String[] args) { // Criação do método main

        Scanner scan = new Scanner(System.in); // Criação do objeto scan

        System.out.println("Informe um número inteiro não negativo: "); // Impressão de mensagem para usuário fornecer informação necessário para execução do programa
        int number = scan.nextInt(); // Chamada do método nextInt para armazenar a informação fornecida pelo usuário na variável number

        System.out.printf("A soma dos números inteiros não negativos menores que %d é: ", number); // Impressão de mensagem para usuário junto com o valor de number
        System.out.println(); // Pula uma linha
        SumIntNumber.Summation(number); // Chamada do método Summation com o valor de number fornecido como argumento
    } // Fim do método main
} // Fim da classe Question5
