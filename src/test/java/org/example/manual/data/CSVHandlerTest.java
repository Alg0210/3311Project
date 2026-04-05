package org.example.manual.data;

import org.example.data.CSVHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CSVHandlerTest {

    @TempDir
    Path tempDir;

    private CSVHandler handler;

    @BeforeEach
    public void setUp() {
        handler = CSVHandler.getInstance();
    }

    @Test
    public void testSingletonInstanceReturnsSameObject() {
        CSVHandler first = CSVHandler.getInstance();
        CSVHandler second = CSVHandler.getInstance();
        assertSame(first, second);
    }

    @Test
    public void testInitCSVCreatesFileWhenMissing() {
        String filePath = tempDir.resolve("new.csv").toString();
        assertFalse(new File(filePath).exists());

        handler.initCSV(filePath, "col1,col2");

        assertTrue(new File(filePath).exists());
    }

    @Test
    public void testInitCSVDoesNotOverwriteExistingFile() throws IOException {
        String filePath = tempDir.resolve("existing.csv").toString();
        try (PrintWriter pw = new PrintWriter(new FileWriter(new File(filePath)))) {
            pw.println("col1,col2");
            pw.println("row1,value1");
        }

        handler.initCSV(filePath, "different_header");

        List<String[]> rows = handler.readCSV(filePath);
        assertEquals(1, rows.size());
        assertEquals("row1", rows.get(0)[0]);
    }

    @Test
    public void testReadCSVSkipsHeaderRow() throws IOException {
        String filePath = tempDir.resolve("data.csv").toString();
        try (PrintWriter pw = new PrintWriter(new FileWriter(new File(filePath)))) {
            pw.println("id,name");
            pw.println("1,Alice");
            pw.println("2,Bob");
        }

        List<String[]> rows = handler.readCSV(filePath);

        assertEquals(2, rows.size());
        assertEquals("1", rows.get(0)[0]);
        assertEquals("2", rows.get(1)[0]);
    }

    @Test
    public void testReadCSVReturnsEmptyListForNonExistentFile() {
        String filePath = tempDir.resolve("nonexistent.csv").toString();

        List<String[]> rows = handler.readCSV(filePath);

        assertNotNull(rows);
        assertTrue(rows.isEmpty());
    }

    @Test
    public void testWriteCSVStoresDataReadableByReadCSV() {
        String filePath = tempDir.resolve("write.csv").toString();
        List<String[]> data = Arrays.asList(
                new String[] { "1", "Alice" },
                new String[] { "2", "Bob" });

        handler.writeCSV(filePath, "id,name", data);
        List<String[]> rows = handler.readCSV(filePath);

        assertEquals(2, rows.size());
        assertEquals("Alice", rows.get(0)[1]);
        assertEquals("Bob", rows.get(1)[1]);
    }

    @Test
    public void testWriteCSVOverwritesExistingContent() {
        String filePath = tempDir.resolve("overwrite.csv").toString();
        handler.writeCSV(filePath, "col1,col2",
                Collections.singletonList(new String[] { "old", "data" }));

        handler.writeCSV(filePath, "col1,col2",
                Collections.singletonList(new String[] { "new", "data" }));
        List<String[]> rows = handler.readCSV(filePath);

        assertEquals(1, rows.size());
        assertEquals("new", rows.get(0)[0]);
    }

    @Test
    public void testAppendCSVAddsRowToExistingFile() {
        String filePath = tempDir.resolve("append.csv").toString();
        handler.writeCSV(filePath, "id,name",
                Collections.singletonList(new String[] { "1", "Alice" }));

        handler.appendCSV(filePath, new String[] { "2", "Bob" });
        List<String[]> rows = handler.readCSV(filePath);

        assertEquals(2, rows.size());
        assertEquals("2", rows.get(1)[0]);
        assertEquals("Bob", rows.get(1)[1]);
    }

    @Test
    public void testAppendCSVPreservesAllExistingRows() {
        String filePath = tempDir.resolve("preserve.csv").toString();
        handler.writeCSV(filePath, "id,name", Arrays.asList(
                new String[] { "1", "Alice" },
                new String[] { "2", "Bob" }));

        handler.appendCSV(filePath, new String[] { "3", "Charlie" });
        List<String[]> rows = handler.readCSV(filePath);

        assertEquals(3, rows.size());
        assertEquals("1", rows.get(0)[0]);
        assertEquals("3", rows.get(2)[0]);
    }

    @Test
    public void testReadCSVSkipsEmptyLines() throws IOException {
        String filePath = tempDir.resolve("empty_lines.csv").toString();
        try (PrintWriter pw = new PrintWriter(new FileWriter(new File(filePath)))) {
            pw.println("id,name");
            pw.println("1,Alice");
            pw.println("");
            pw.println("2,Bob");
            pw.println("");
        }

        List<String[]> rows = handler.readCSV(filePath);

        assertEquals(2, rows.size());
    }
}

