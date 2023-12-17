package Java.QuestoesLabAv1.Question4;

import java.util.Scanner; // Importação de classe Scanner na biblioteca Java
public class Question4 { // Criação da classe principal onde será executado o programa
    public static void main(String[] args) { // Criação do método main

        Scanner scan = new Scanner(System.in); // Criação do objeto scan

        System.out.println("Informe o número que você deseja verificar: "); // Impressão de mensagem para usuário fornecer informação necessário para execução do programa
        int number = scan.nextInt(); // Chamada do método nextInt para armazenar a informação fornecida pelo usuário na variável number

        System.out.printf("O número %d é par? %b", number, PairVerification.Pair(number)); // Chamada da funcão para que a mesma seja imprimida na tela
    } // Fim do método main
} // Fim da classe Question4

