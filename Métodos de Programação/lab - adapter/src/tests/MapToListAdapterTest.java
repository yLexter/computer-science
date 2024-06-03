package tests;

import mapToList.MapToListAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MapToListAdapterTest {

    private MapToListAdapter<String, Integer> adapter;

    @BeforeEach
    void setUp() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);

        adapter = new MapToListAdapter<>(map);
    }

    @Test
    void testSize() {
        assertEquals(3, adapter.size());
    }

    @Test
    void testIsEmpty() {
        assertFalse(adapter.isEmpty());
        adapter.clear();
        assertTrue(adapter.isEmpty());
    }

    @Test
    void testContains() {
        assertTrue(adapter.contains(1));
        assertFalse(adapter.contains(4));
    }

    @Test
    void testIterator() {
        Iterator<Integer> iterator = adapter.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testToArray() {
        Object[] array = adapter.toArray();
        assertEquals(1, array[0]);
        assertEquals(2, array[1]);
        assertEquals(3, array[2]);
    }

    @Test
    void testToArray_WithGivenArray() {
        Integer[] array = new Integer[3];
        Integer[] result = adapter.toArray(array);
        assertArrayEquals(new Integer[]{1, 2, 3}, result);
    }

    @Test
    void testRemove() {
        assertEquals(1, adapter.remove(0));
        assertEquals(2, adapter.remove(0));
        assertEquals(3, adapter.remove(0));
        assertTrue(adapter.isEmpty());
    }

    @Test
    void testContainsAll() {
        assertTrue(adapter.containsAll(Arrays.asList(1, 2)));
        assertFalse(adapter.containsAll(Arrays.asList(1, 4)));
    }

    @Test
    void testRemoveAll() {
        assertTrue(adapter.removeAll(Arrays.asList(1, 2)));
        assertEquals(1, adapter.size());
        assertFalse(adapter.removeAll(Collections.singletonList(4)));
    }

    @Test
    void testRetainAll() {
        assertTrue(adapter.retainAll(Arrays.asList(1, 2)));
        assertEquals(2, adapter.size());
        assertFalse(adapter.retainAll(Arrays.asList(1, 2)));
    }

    @Test
    void testGet() {
        assertEquals(1, adapter.get(0));
        assertEquals(2, adapter.get(1));
        assertEquals(3, adapter.get(2));
    }

    @Test
    void testSet() {
        assertEquals(1, adapter.set(0, 10));
        assertEquals(10, adapter.get(0));
        assertEquals(2, adapter.set(1, 20));
        assertEquals(20, adapter.get(1));
    }

    @Test
    void testIndexOf() {
        assertEquals(0, adapter.indexOf(1));
        assertEquals(1, adapter.indexOf(2));
        assertEquals(2, adapter.indexOf(3));
        assertEquals(-1, adapter.indexOf(4));
    }



    @Test
    void testListIterator() {
        ListIterator<Integer> listIterator = adapter.listIterator();
        assertTrue(listIterator.hasNext());
        assertEquals(1, listIterator.next());
        assertTrue(listIterator.hasNext());
        assertEquals(2, listIterator.next());
        assertTrue(listIterator.hasNext());
        assertEquals(3, listIterator.next());
        assertFalse(listIterator.hasNext());
    }




    @Test
    void testToString() {
        assertEquals("MapToListAdapter", adapter.toString());
    }
}
