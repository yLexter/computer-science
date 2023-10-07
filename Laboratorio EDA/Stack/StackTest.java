package AbstractData;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class StackTest {

    private Stack<String> stack;

    @BeforeEach
    public void start() {
        this.stack = new Stack<>();
    }

    @Test
    public void testPushAndPop() {
        Stack<Integer> stack = new Stack<>();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.getSize());

        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertFalse(stack.isEmpty());
        assertEquals(3, stack.getSize());
        assertEquals(Integer.valueOf(3), stack.pop());
        assertEquals(Integer.valueOf(2), stack.pop());
        assertEquals(Integer.valueOf(1), stack.pop());

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.getSize());
    }

    @Test
    public void testIsEmpty() {
        Stack<String> stack = new Stack<>();
        assertTrue(stack.isEmpty());
        stack.push("A");
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, stack.getSize());
        stack.push("a");
        assertEquals(1, stack.getSize());
        stack.push("b");
        assertEquals(2, stack.getSize());
        stack.pop();
        assertEquals(1, stack.getSize());
    }
    
    @Test
    public void teststacklistsEncadeade() {

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.getSize());

        stack.push("Alice");
        stack.push("Bob");
        stack.push("Charlie");

        assertFalse(stack.isEmpty());
        assertEquals(3, stack.getSize());
        assertEquals("Charlie", stack.pop());

        stack.pop();
        stack.pop();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.getSize());
        
    }

}


