package LinkedList;

import java.nio.channels.IllegalSelectorException;
import java.util.HashMap;
import java.util.Map;

public class CustomLinkedList<T> {

    public CustomNode<T> root;

    private int size;

    public CustomLinkedList() {}

    private static class CustomNode<T> {
        private CustomNode<T> next;

        private T element;

        public CustomNode(T element) {
            this.element = element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public T getElement() {
            return element;
        }

    }

    public int getSize() {
        return size;
    }

    public CustomLinkedList<T> add(T element) {

        if (root == null) {
            root = new CustomNode<T>(element);
            size++;
            return this;
        }

        CustomNode<T> current = root;

        while(current.next != null)
            current = current.next;

        current.next = new CustomNode<>(element);

        size++;

        return this;
    }
    public void show() {

        CustomNode<T> current = root;

        if (current == null)
            throw new IllegalCallerException("Lista vazia");

        while (current != null) {
            System.out.printf("%s ", current.element);
            current = current.next;
        }


    }

    public T pop(T element) {

        CustomNode<T> current = root, prev = root;

        if (current == null)
            throw new IllegalCallerException("Lista vazia");

         while (current != null) {

             if (current.element.equals(element)) {

                 if (current.equals(prev)) {
                     root = root.next;
                 } else {
                     prev.next = current.next;
                 }

                 size--;

                 return current.element;
             }

             prev = current;
             current = current.next;
         }

        throw new IllegalArgumentException("Elemento não está na lista");
    }


    public void reverse() {




    }



}
