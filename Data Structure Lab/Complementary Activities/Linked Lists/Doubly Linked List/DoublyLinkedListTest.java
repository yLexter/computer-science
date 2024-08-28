package AbstractData;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class DoublyLinkedListTest {

    private DoublyLinkedList<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new DoublyLinkedList<>();
    }

    @Test
    public void testInsertAtBeginning() {
        list.insertAtBeginning(1);
        assertEquals(1, (int) list.getRoot().getElement());
        assertEquals(1, (int) list.getTail().getElement());

        list.insertAtBeginning(2);
        assertEquals(2, (int) list.getRoot().getElement());
        assertEquals(1, (int) list.getTail().getElement());
        assertEquals(1, (int) list.getRoot().getNext().getElement());
        assertNull(list.getRoot().getPrevious());

        list.insertAtBeginning(3);
        assertEquals(3, (int) list.getRoot().getElement());
        assertEquals(1, (int) list.getTail().getElement());
        assertEquals(2, (int) list.getRoot().getNext().getElement());
        assertNull(list.getRoot().getPrevious());
    }

    @Test
    public void testInsertAtEnd() {
        list.insertAtEnd(1);
        assertEquals(1, (int) list.getRoot().getElement());
        assertEquals(1, (int) list.getTail().getElement());

        list.insertAtEnd(2);
        assertEquals(1, (int) list.getRoot().getElement());
        assertEquals(2, (int) list.getTail().getElement());
        assertEquals(2, (int) list.getRoot().getNext().getElement());
        assertNull(list.getTail().getNext());

        list.insertAtEnd(3);
        assertEquals(1, (int) list.getRoot().getElement());
        assertEquals(3, (int) list.getTail().getElement());
        assertEquals(2, (int) list.getTail().getPrevious().getElement());
        assertNull(list.getRoot().getPrevious());
    }

    @Test
    public void testRemoveFromEnd() {
        list.insertAtBeginning(1);
        list.insertAtBeginning(2);
        list.insertAtBeginning(3);

        list.removeFromEnd();
        assertEquals(3, (int) list.getRoot().getElement());
        assertEquals(2, (int) list.getTail().getElement());
        assertNull(list.getTail().getNext());

        list.removeFromEnd();
        assertEquals(3, (int) list.getRoot().getElement());
        assertEquals(3, (int) list.getTail().getElement());
        assertNull(list.getTail().getNext());

        list.removeFromEnd();
        assertNull(list.getRoot());
        assertNull(list.getTail());
    }

    @Test
    public void testRemoveFromStart() {
        list.insertAtBeginning(1);
        list.insertAtBeginning(2);
        list.insertAtBeginning(3);

        list.removeFromStart();
        assertEquals(2, (int) list.getRoot().getElement());
        assertEquals(1, (int) list.getTail().getElement());
        assertNull(list.getRoot().getPrevious());

        list.removeFromStart();
        assertEquals(1, (int) list.getRoot().getElement());
        assertEquals(1, (int) list.getTail().getElement());
        assertNull(list.getRoot().getPrevious());
        assertNull(list.getRoot().getNext());

        list.removeFromStart();
        assertNull(list.getRoot());
        assertNull(list.getTail());
    }

    @Test
    public void testeRemove() {
        list.insertAtBeginning(1);
        list.insertAtBeginning(2);
        list.insertAtEnd(3);
        list.remove(2);
        assertFalse(list.search(2));
        list.remove(3);
        assertFalse(list.search(3));
    }

    @Test
    public void testeInsert() {
        list.insertAtBeginning(1);
        list.insertAtBeginning(2);
        list.insert(3, 1);
        assertEquals(3, list.root.next.element);
        list.insert(4, 0);
        assertEquals(4, list.root.element);
        list.insert(5, 10);
        assertEquals(5, list.cauda.element);
    }

}
