package main;

public class Node {
	
    public Node proximo;

    public Node anterior;

    public int informacao;

    public Node(int element) {
        this.informacao = element;
        this.proximo = null;
        this.anterior = null;
    }

  
}
