package AbstractData;


// ToDo Implementar percuso em profundidade
public class BinaryTree<T extends Number> {

    private CustomNode<T> root;

    private static class CustomNode<K extends Number> {

        private CustomNode<K> left;
        private CustomNode<K> rigth;

        private final K data;

        public CustomNode(K data) {
            this.data = data;
            this.rigth = null;
            this.left = null;
        }

    }

    public void insert(T data) {

        if (root == null) {
            root = new CustomNode<>(data);
            return;
        }

        root = insertNode(data, root);
    }

    private CustomNode<T> insertNode(T data, CustomNode<T> node) {

        if (node == null) {
            node = new CustomNode<>(data);
            return node;
        }

        if (data.doubleValue() > node.data.doubleValue()) {
            node.rigth = insertNode(data, node.rigth);
        } else {
            node.left = insertNode(data, node.left);
        }

        return node;
    }

    public boolean has(T data) {
        CustomNode<T> aux = root;

        return hasData(data, aux);
    }

    private boolean hasData(T data, CustomNode<T> node) {

        if (node == null)
            return false;

        if (node.data.equals(data))
            return true;

        if (data.doubleValue() > node.data.doubleValue())
            return hasData(data, node.rigth);

        return hasData(data, node.left);
    }

    public void preOrder(Consumer<T> callback) {
        CustomNode<T> aux = root;

        traversePreOrder(aux, callback);
    }

    private void traversePreOrder(CustomNode<T> node, Consumer<T> callback) {
        if (node != null) {
            callback.accept(node.data);
            traversePreOrder(node.left, callback);
            traversePreOrder(node.rigth, callback);
        }
    }

    public void inOrder(Consumer<T> callback) {
        CustomNode<T> aux = root;

        traverseInOrder(aux, callback);
    }

    private void traverseInOrder(CustomNode<T> node, Consumer<T> callback) {
        if (node != null) {
           traverseInOrder(node.left, callback);
           callback.accept(node.data);
           traverseInOrder(node.rigth, callback);
        }
    }

    public void postOrder(Consumer<T> callback) {
        CustomNode<T> aux = root;

        traversePostOrder(aux, callback);
    }

    private void traversePostOrder(CustomNode<T> node, Consumer<T> callback) {
        if (node != null) {
            traversePostOrder(node.left, callback);
            traversePostOrder(node.rigth, callback);
            callback.accept(node.data);
        }
    }


}
