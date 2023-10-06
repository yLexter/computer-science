package AbstractData;

class StackTest {
    
    private Stack<String> stack;
    
    @BeforeEach
    public void start() {
        this.stack = new Stack<>();
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
