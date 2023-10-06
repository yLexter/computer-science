package AbstractData;

class DoublyLinkedListTest {

    private DoublyLinkedList doublyLinkedList;

    @BeforeEach
    public void start() {
        this.doublyLinkedList = new DoublyLinkedList<Integer>();
    }

    @Test
    public void testRemoveFromEnd() {

        doublyLinkedList.insert(10);
        assertEquals(10, doublyLinkedList.getRoot().getElement());

        assertNull(doublyLinkedList.getRoot().getPrevious());
        assertNull(doublyLinkedList.getRoot().getNext());

    }

    @Test
    public  void testInsertAtEnd() {

        doublyLinkedList.insertAtBeninnig(10);
        doublyLinkedList.insertAtBeninnig(20);
        doublyLinkedList.removeFromEnd();

        assertEquals(20, doublyLinkedList.getRoot().getElement());
        assertNull(doublyLinkedList.getRoot().getNext());

    }

}