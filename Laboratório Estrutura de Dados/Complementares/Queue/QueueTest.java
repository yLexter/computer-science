package AbstractData;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class QueueTest {

    private Queue<Integer> queue;

    @BeforeEach
    public void start() {
        this.queue = new Queue<>();
    }

    @Test
    public void testqueuelistaEncadeada () {
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.getSize());

        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);

        assertFalse(queue.isEmpty());

        assertEquals(3, queue.getSize());
        assertEquals(1, queue.deQueue());

        queue.deQueue();
        queue.deQueue();

        assertTrue(queue.isEmpty());

        assertEquals(0, queue.getSize());
    }

    @Test
    public void testEnQueueAndDeQueue() {
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.getSize());

        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);

        assertFalse(queue.isEmpty());
        assertEquals(3, queue.getSize());
        assertEquals(1, queue.getRoot().getData());
        assertEquals(3, queue.getLast().getData());

        assertEquals(Integer.valueOf(1), queue.deQueue());
        assertEquals(Integer.valueOf(2), queue.deQueue());
        assertEquals(Integer.valueOf(3), queue.deQueue());

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.getSize());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(queue.isEmpty());
        queue.enQueue(10);
        assertFalse(queue.isEmpty());
        queue.deQueue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testGetSize() {
        assertEquals(0, queue.getSize());
        queue.enQueue(10);
        assertEquals(1, queue.getSize());
        queue.enQueue(20);
        assertEquals(2, queue.getSize());
        queue.deQueue();
        assertEquals(1, queue.getSize());
    }

    @Test
    public void testDoubleLinkedList() {
        
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);

        assertEquals(1, queue.getRoot().getData());
        assertEquals(3, queue.getLast().getData());

        queue.deQueue();

        assertEquals(2, queue.getRoot().getData());
        assertEquals(3, queue.getLast().getData());
    }

}
