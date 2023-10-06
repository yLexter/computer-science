package AbstractData;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class Stack<T> {

    private int size;

    private CustomNode last;

    private CustomNode root;

    public class CustomNode {
        private CustomNode next;

        private CustomNode previous;

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

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(T element) {

        size++;

        if (root == null) {
            root = new CustomNode(element);
            last = root;
            return;
        }

        last.next = new CustomNode(element);
        last.next.previous = last;
        last = last.next;

    }

    public T pop() {

        CustomNode node = last;

        if (root == null)
            throw new IllegalStateException("Pilha Vazia");

        size--;

        if (node.previous == null) {
            last = null;
            root = null;
            return node.element;
        }

        last.previous.next = null;
        last = last.previous;

        return node.element;
    }

    public void show() {

        CustomNode aux = root;

        while(aux != null) {
            System.out.printf("%s " + aux);
            aux = aux.next;
        }
        
    }

    public int getSize() {
        return size;
    }

    public CustomNode getRoot() {
        return root;
    }

    public static void main(String[] args) {

    }



}
