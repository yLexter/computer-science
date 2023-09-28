package BinaryTree;

public class CustomBinaryTree<T extends Number> {

    private CustomNode<T> root;
    
    private class CustomNode<K extends Number> {

        private CustomNode<K> left;

        private CustomNode<K> rigth;

        private final K element;

        public CustomNode(K element) {
            this.element = element;
        }

        @Override
        public String toString() {
            return "Node { " +
                    "left = " + left +
                    ", rigth = " + rigth +
                    ", element = " + element + " } ";
        }
    }

    public void add(T element) {

        if (root == null) {
           root = new CustomNode<>(element);
           return;
        }

        root = insertMelhorado(element, root);
    }

    private CustomNode<T> insertMelhorado(T element, CustomNode<T> node) {

        if (node == null) {
            node = new CustomNode<T>(element);
            return node;
        }

        if (element.doubleValue() > node.element.doubleValue()) {
            node.rigth = insertMelhorado(element, node.rigth);
        } else {
            node.left = insertMelhorado(element, node.left);
        }

        return node;
    }

    private void insert(T element, CustomNode<T> node) {

        if (element.doubleValue() >= node.element.doubleValue() && node.rigth == null) {
            node.rigth = new CustomNode<>(element);
            return;
        }

        if (element.doubleValue() < node.element.doubleValue() && node.left == null) {
           node.left = new CustomNode<>(element);
           return;
        }

        if (element.doubleValue() > node.element.doubleValue()) {
           insert(element, node.rigth);
        } else {
           insert(element, node.left);
        }

    }

    public void show() {
        showTree(root);
    }

    private void showTree(CustomNode<T> node) {

        if (node == null) {
            return;
        }

        showTree(node.left);
        System.out.printf("%.2f | ", node.element.doubleValue());
        showTree(node.rigth);
    }

    public static void main(String[] args) {
        CustomBinaryTree<Integer> tree = new CustomBinaryTree<>();

        tree.add(2);
        tree.add(4);
        tree.add(0);
        tree.add(9);
        tree.add(10);

        tree.show();
        
    }

}
