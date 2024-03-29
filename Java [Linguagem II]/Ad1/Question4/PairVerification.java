package Java.QuestoesLabAv1.Question4;
public class PairVerification { // Criação da classe para verificação se o número fornecido pelo usuário é par
    public static boolean Pair(int number) { // Criação do método para realizar a verificação

        char[] StringNumber = String.format("%d", number).toCharArray(); // É feita a chamada do método String.format para converter o número inteiro para string e do método toCharArray para converter a string para um array de caracteres
        char lastAlgorithm = StringNumber[StringNumber.length -1]; // É feita a chamada do método length para obter o tamanho do array e é feita a diminuição de 1 unidade para que seja obtido o último caractere. Assim, o mesmo é armazenado na variável lastAlgorithm
        return  lastAlgorithm =='0' || lastAlgorithm == '2' || lastAlgorithm == '4' || lastAlgorithm == '6' || lastAlgorithm == '8'; // O método retorna true caso o valor da variável seja igual a algum dos números listados e false caso contrário
    } // fim do método Pair
} // fim da classe PairVerification
