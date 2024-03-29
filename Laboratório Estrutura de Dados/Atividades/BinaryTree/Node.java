package AbstractData.BinaryTree;

public class Node {
    public Node esquerda;
    public Node direita;
    public int valor;

    public Node(int valor) {
        this.valor = valor;
        this.direita = null;
        this.esquerda = null;
    }

}
