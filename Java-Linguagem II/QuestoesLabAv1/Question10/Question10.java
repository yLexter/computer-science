package Java.QuestoesLabAv1.Question10;

import java.util.Scanner; // Importação de classe Scanner na biblioteca Java
public class Question10 { // Criação da classe principal onde será executado o programa
    public static void main(String[] args) { // Criação do método main
        Scanner scan = new Scanner(System.in); // Criação do objeto scan

        System.out.println("Informe o valor de x: "); // Impressão de mensagem para usuário fornecer informação necessário para execução do programa
        int x = scan.nextInt(); // Chamada do método nextInt para armazenar a informação fornecida pelo usuário na variável x

        Summation.sum(x); // Chamada do método sum com o valor de x sendo fornecido como argumento
    } // Fim do método main
} // Fim da classe Question10
