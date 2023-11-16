package AbstractData.HashTable.Tests;

import AbstractData.HashTable.HashTable.HashTable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.function.Function;
import static org.junit.jupiter.api.Assertions.*;

public class HashTableTest {

        private HashTable hashTable;

        @BeforeEach
        public void start() {
            this.hashTable = new HashTable(10);
        }
        @Test
        public void testHashDivision() {
            testInsertionAndSearch("Hash Division", hashTable::hashDivision);
        }

        @Test
        public void testHashMultiplication() {
            testInsertionAndSearch("Hash Multiplication", hashTable::hashMultiplication);
        }

        @Test
        public void testHashWTF() {
            testInsertionAndSearch("Hash WTF", hashTable::hashWTF);
        }

        private void testInsertionAndSearch(String functionName, Function<String, Integer> hashFunction) {
            int hashValueTest1 = hashFunction.apply("Test1");
            int hashValueTest2 = hashFunction.apply("Test2");
            int hashValueTest3 = hashFunction.apply("Test3");

            hashTable.insert("Test1", hashFunction);
            hashTable.insert("Test2", hashFunction);
            hashTable.insert("Test3", hashFunction);

            assertEquals(hashValueTest1, hashFunction.apply("Test1"), functionName + ": Hash value for Test1 should be consistent");
            assertEquals(hashValueTest2, hashFunction.apply("Test2"), functionName + ": Hash value for Test2 should be consistent");
            assertEquals(hashValueTest3, hashFunction.apply("Test3"), functionName + ": Hash value for Test3 should be consistent");

            assertTrue(hashTable.search("Test1", hashFunction), functionName + ": Test1 should be found");
            assertTrue(hashTable.search("Test2", hashFunction), functionName + ": Test2 should be found");
            assertTrue(hashTable.search("Test3", hashFunction), functionName + ": Test3 should be found");
            assertFalse(hashTable.search("??", hashFunction), "Valor não presente na lista");

            hashTable.clear();
        }

}
