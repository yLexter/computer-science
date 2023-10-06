package AbstractData;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

interface ILinkedList<T> {
    void insertAtBeginning(T element);
    void removeFromStart();
}

class LinkesListTest {

    private SingleLinkedList SingleLinkedList;

    @BeforeEach
    public void start() {
        this.SingleLinkedList = new SingleLinkedList<Integer>();
    }

    @Test
    public void testRemoveFromStart() {

        SingleLinkedList.insertAtBeginning(10);
        SingleLinkedList.insertAtBeginning(20);
        SingleLinkedList.insertAtBeginning(30);

        SingleLinkedList.removeFromStart();

        assertEquals(20, SingleLinkedList.getRoot().getElement());

    }

    @Test
    public  void testInsertAtBeninnig() {

        SingleLinkedList.insertAtBeginning(10);
        assertEquals(10, SingleLinkedList.getRoot().getElement());
        assertNull(SingleLinkedList.getRoot().getNext());

    }

}

public class SingleLinkedList<T> implements ILinkedList<T>{

    private CustomNode root;

    public class CustomNode {
        private CustomNode next;

        private T element;

        public CustomNode(T element) {
            this.element = element;
            this.next = null;
        }

        public CustomNode getNext() {
            return next;
        }

        public T getElement() {
            return element;
        }

    }

    public CustomNode getRoot() {
        return root;
    }

    @Override
    public void insertAtBeginning(T element) {

        CustomNode temp;

        if (root == null) {
            root = new CustomNode(element);
            return;
        }

        temp = new CustomNode(element);
        temp.next = root;

        root = temp;
    }

    @Override
    public void removeFromStart() {

        if (root == null) {
            return;
        }

        root = root.next;
    }
    
}

