package AbstractData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class DoublyLinkedList<T>  {

    private CustomNode root;

    public class CustomNode {
        private CustomNode previous;

        private CustomNode next;

        private T element;

        public CustomNode(T element) {
            this.element = element;
            this.next = null;
            this.previous = null;
        }

        public CustomNode getPrevious() {
            return previous;
        }

        public CustomNode getNext() {
            return next;
        }

        public T getElement() {
            return element;
        }

    }

    public void insertAtBeninnig(T element) {

        CustomNode node;

        if (root == null) {
            root = new CustomNode(element);
            return;
        }

        node = new CustomNode(element);

        root.previous = node;
        node.next = root;
        root = node;
    }

    public void insert(T element) {

        CustomNode prev = root, node;

        if (root == null) {
            root =  new CustomNode(element);
            return;
        }

        while (prev.next != null) {
            prev = prev.next;
        }


        node = new CustomNode(element);
        node.previous = prev;
        prev.next = node;
    }

    public void removeFromEnd() {

        CustomNode prev = root, node;

        while (prev.next != null) {
            prev = prev.next;
        }

        prev.previous.next = null;
    }

    public CustomNode getRoot() {
        return root;
    }
}
