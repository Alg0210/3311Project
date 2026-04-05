package org.example.AI_assistant.data;

import org.example.data.CSVHandler;
import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class CSVHandlerTest {
    private static final String TEST_CSV = "test_users.csv";
    private static final String HEADER = "id,name";

    @BeforeEach
    void setUp() {
        File file = new File(TEST_CSV);
        if (file.exists()) file.delete();
    }

    @AfterEach
    void tearDown() {
        File file = new File(TEST_CSV);
        if (file.exists()) file.delete();
    }

    @Test
    void testInitCSV() {
        CSVHandler.getInstance().initCSV(TEST_CSV, HEADER);
        assertTrue(new File(TEST_CSV).exists());
    }

    @Test
    void testWriteAndReadCSV() {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"1", "Alice"});
        rows.add(new String[]{"2", "Bob"});
        CSVHandler.getInstance().writeCSV(TEST_CSV, HEADER, rows);
        List<String[]> readRows = CSVHandler.getInstance().readCSV(TEST_CSV);
        assertEquals(2, readRows.size());
        assertArrayEquals(new String[]{"1", "Alice"}, readRows.get(0));
        assertArrayEquals(new String[]{"2", "Bob"}, readRows.get(1));
    }

    @Test
    void testAppendCSV() {
        CSVHandler.getInstance().initCSV(TEST_CSV, HEADER);
        CSVHandler.getInstance().appendCSV(TEST_CSV, new String[]{"1", "Alice"});
        CSVHandler.getInstance().appendCSV(TEST_CSV, new String[]{"2", "Bob"});
        List<String[]> readRows = CSVHandler.getInstance().readCSV(TEST_CSV);
        assertEquals(2, readRows.size());
        assertArrayEquals(new String[]{"1", "Alice"}, readRows.get(0));
        assertArrayEquals(new String[]{"2", "Bob"}, readRows.get(1));
    }
}
