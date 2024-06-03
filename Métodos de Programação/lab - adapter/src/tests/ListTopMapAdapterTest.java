package tests;

import listToMap.ElementAdapter;
import listToMap.ListToMapAdapter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListTopMapAdapterTest {

    private ListToMapAdapter<String, Integer> adapter;

    @BeforeEach
    void setUp() {
        adapter = new ListToMapAdapter<>();

        adapter.put("key1", 1);
        adapter.put("key2", 2);
    }

    @Test
    void testSize() {
        assertEquals(2, adapter.size());
    }

    @Test
    void testIsEmpty() {
        assertFalse(adapter.isEmpty());
        adapter.clear();
        assertTrue(adapter.isEmpty());
    }

    @Test
    void testContainsKey() {
        assertTrue(adapter.containsKey("key1"));
        assertFalse(adapter.containsKey("key3"));
    }

    @Test
    void testContainsValue() {
        assertTrue(adapter.containsValue(1));
        assertFalse(adapter.containsValue(3));
    }

    @Test
    void testGet() {
        assertEquals(1, adapter.get("key1"));
        assertNull(adapter.get("key3"));
    }

    @Test
    void testPut() {
        adapter.put("key3", 3);
        assertEquals(3, adapter.size());
        assertEquals(3, adapter.get("key3"));

        adapter.put("key1", 10);
        assertEquals(3, adapter.size());
        assertEquals(10, adapter.get("key1"));
    }

    @Test
    void testRemove() {
        assertEquals(1, adapter.remove("key1"));
        assertEquals(1, adapter.size());
        assertNull(adapter.remove("key3"));
    }

    @Test
    void testValues() {
        List<Integer> values = new ArrayList<>(adapter.values());
        assertTrue(values.contains(1));
        assertTrue(values.contains(2));
        assertEquals(2, values.size());
    }

    @Test
    void testClear() {
        adapter.clear();
        assertTrue(adapter.isEmpty());
    }


}
