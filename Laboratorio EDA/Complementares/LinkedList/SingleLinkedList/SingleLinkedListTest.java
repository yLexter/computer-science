package AbstractData;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class SingleLinkedListTest {

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