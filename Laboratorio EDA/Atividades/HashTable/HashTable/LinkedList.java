package AbstractData.HashTable.HashTable;

/**
 * Implementação de uma lista duplamente encadeada em Java.
 * A lista mantém uma referência para o início (root) e o final (tail).
 * Cada nó na lista contém um elemento genérico.
 *
 * @param <K,V> O tipo dos elementos armazenados na lista.
 */

public class LinkedList<K,V> {

    /**
     * Nó inicial (cabeça) da lista encadeada.
     */
    public Node<K,V> root;

    /**
     * Nó final (cauda) da lista encadeada.
     */
    public Node<K,V> tail;

    /**
     * Classe interna que representa um nó na lista encadeada.
     *
     * @param <K> é a key
     * @param <V> é o valor
     */
    public static class Node<K,V> {

        /**
         * Referência para o nó anterior.
         */
        private Node<K,V> previous;

        /**
         * Referência para o próximo nó.
         */
        private Node<K,V> next;

        /**
         * Elemento armazenado no nó.
         */
        private V value;

        private K key;

        /**
         * Construtor que cria um nó com o elemento fornecido.
         *
         * @param value O elemento a ser armazenado no nó.
         */
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.previous = null;
        }

    }

    /**
     * Insere um novo elemento no final da lista encadeada.
     *
     * @param value O elemento a ser inserido.
     */
    public void insert(K key, V value) {

        Node<K,V> node = new Node<>(key,value);

        if (root == null) {
            root = node;
            tail = node;
        } else {
            tail.next = node;
            node.previous = tail;
            tail = node;
        }

    }

    /**
     * Verifica se um determinado elemento está presente na lista encadeada.
     *
     * @param key O elemento a ser pesquisado.
     * @return true se o elemento estiver presente, false caso contrário.
     */
    public boolean search(K key) {
        Node<K,V> current = root;

        while (current != null) {

            if (current.key.equals(key))
                return true;

            current = current.next;
        }

        return false;
    }

    /**
     * Obtém o nó inicial (cabeça) da lista encadeada.
     *
     * @return O nó inicial da lista encadeada.
     */
    public Node<K,V> getRoot() {
        return root;
    }

    /**
     * Obtém o nó final (cauda) da lista encadeada.
     *
     * @return O nó final da lista encadeada.
     */
    public Node<K,V> getTail() {
        return tail;
    }

}
