package AbstractData.BinaryTree;
import java.util.function.Consumer;

public class ArvoreBinaria {

    private Node root;

    public Node getRaiz() {
        return root;
    }

    public String percursoProfundidade() {
	 if (root == null)
            throw new IllegalStateException("Árvore Vazia");

        return percursoPreOrdem();
    }

    public void removerNo(Integer valor) {
        if (root == null)
            throw new IllegalStateException("Árvore Vazia");

        root = removerNo(valor, root);
    }

    private Node removerNo(Integer valor, Node node) {

        if (valor > node.valor) {
            node.direita = removerNo(valor, node.direita);
        } else if (valor < node.valor) {
            node.esquerda = removerNo(valor, node.esquerda);
        } else {

            if (node.esquerda == null && node.direita == null) {
                return null;
            } else if (node.esquerda != null && node.direita != null) {
                Node sucessor = minimo(node.direita);
                node.valor = sucessor.valor;
                node.direita = removerNo(sucessor.valor, node.direita);
            } else {
                return node.esquerda != null ? node.esquerda : node.direita;
            }

        }

        return node;
    }

    private Node minimo(Node node) {
        Node aux = node;

        while (aux.esquerda != null)
            aux = aux.esquerda;

        return aux;
    }

    public void adicionarNo(Integer valor) {

        if (root == null) {
            root = new Node(valor);
            return;
        }

        root = adicionarNo(valor, root);
    }

    private Node adicionarNo(Integer valor, Node node) {

        if (node == null) {
            node = new Node(valor);
            return node;
        }

        if (valor > node.valor) {
            node.direita = adicionarNo(valor, node.direita);
        } else {
            node.esquerda = adicionarNo(valor, node.esquerda);
        }

        return node;
    }

    public boolean buscar(Integer valor) {
        Node aux = root;

	 if (root == null)
            throw new IllegalStateException("Árvore Vazia");

        return buscar(valor, aux);
    }

    private boolean buscar(Integer valor, Node node) {

        if (node == null)
            return false;

        if (node.valor == valor)
            return true;

        if (valor > node.valor)
            return buscar(valor, node.direita);

        return buscar(valor, node.esquerda);
    }

    public String percursoPreOrdem() {
        Node aux = root;
        StringBuilder percusoToString = new StringBuilder();

        if (root == null)
            throw new IllegalStateException("Árvore Vazia");

        Consumer<Integer> callback = (Integer valor) -> {
            percusoToString
                    .append(valor)
                    .append(" ");
        };

        percursoPreOrdem(aux, callback);
        percusoToString.deleteCharAt(percusoToString.length() - 1);

        return percusoToString.toString();
    }

    private void percursoPreOrdem(Node node, Consumer<Integer> callback) {

        if (node != null) {
            callback.accept(node.valor);
            percursoPreOrdem(node.esquerda, callback);
            percursoPreOrdem(node.direita, callback);
        }

    }


    public String percursoInOrdem() {
        Node aux = root;
        StringBuilder percusoToString = new StringBuilder();

        if (root == null)
            throw new IllegalStateException("Árvore Vazia");

        Consumer<Integer> callback = (Integer valor) -> {
            percusoToString
                    .append(valor)
                    .append(" ");
        };

        percursoInOrdem(aux, callback);
        percusoToString.deleteCharAt(percusoToString.length() - 1);

        return percusoToString.toString();
    }

    private void percursoInOrdem(Node node, Consumer<Integer> callback) {
        if (node != null) {
            percursoInOrdem(node.esquerda, callback);
            callback.accept(node.valor);
            percursoInOrdem(node.direita, callback);
        }
    }


    public String percursoPosOrdem() {
        Node aux = root;
        StringBuilder percusoToString = new StringBuilder();

        if (root == null)
            throw new IllegalStateException("Árvore Vazia");

        Consumer<Integer> callback = (Integer valor) -> {
            percusoToString
                    .append(valor)
                    .append(" ");
        };

        percursoPosOrdem(aux, callback);
        percusoToString.deleteCharAt(percusoToString.length() - 1);

        return percusoToString.toString();
    }

    private void percursoPosOrdem(Node node, Consumer<Integer> callback) {
        if (node != null) {
            percursoPosOrdem(node.esquerda, callback);
            percursoPosOrdem(node.direita, callback);
            callback.accept(node.valor);
        }
    }


}