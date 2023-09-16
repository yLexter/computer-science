package BinaryTree;

public class CustomBinaryTree<T extends Number> {

    private CustomNode<T> root;
    
    private static class CustomNode<K extends Number> {

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

        insert(element, root);
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

        if (element.doubleValue() >= node.element.doubleValue()) {
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

}
