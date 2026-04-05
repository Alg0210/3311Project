package org.example.AI_assistant.data;

import org.example.data.CSVHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CSVHandlerTest {

    @TempDir
    Path tempDir;

    private CSVHandler handler;

    @BeforeEach
    public void setUp() {
        handler = CSVHandler.getInstance();
    }

    @Test
    public void getInstanceReturnsSingleton() {
        assertSame(handler, CSVHandler.getInstance());
    }

    @Test
    public void initCsvCreatesMissingFileWithHeader() throws IOException {
        Path csvPath = tempDir.resolve("init.csv");

        handler.initCSV(csvPath.toString(), "id,name");

        assertTrue(Files.exists(csvPath));
        assertEquals("id,name" + System.lineSeparator(), new String(Files.readAllBytes(csvPath)));
    }

    @Test
    public void readCsvSkipsHeaderAndBlankLines() throws IOException {
        Path csvPath = tempDir.resolve("read.csv");
        Files.write(csvPath, Arrays.asList("id,name", "1,Alice", "", "2,Bob"));

        List<String[]> rows = handler.readCSV(csvPath.toString());

        assertEquals(2, rows.size());
        assertArrayEquals(new String[] { "1", "Alice" }, rows.get(0));
        assertArrayEquals(new String[] { "2", "Bob" }, rows.get(1));
    }

    @Test
    public void writeCsvOverwritesFileAndWritesHeader() throws IOException {
        Path csvPath = tempDir.resolve("write.csv");
        Files.write(csvPath, Arrays.asList("old,data", "legacy,row"));

        handler.writeCSV(csvPath.toString(), "id,name", Arrays.asList(
                new String[] { "1", "Alice" },
                new String[] { "2", "Bob" }));

        List<String> lines = Files.readAllLines(csvPath);
        assertEquals(Arrays.asList("id,name", "1,Alice", "2,Bob"), lines);
    }

    @Test
    public void appendCsvAddsMissingTrailingNewlineBeforeAppending() throws IOException {
        Path csvPath = tempDir.resolve("append.csv");
        Files.write(csvPath, "id,name\n1,Alice".getBytes());

        handler.appendCSV(csvPath.toString(), new String[] { "2", "Bob" });

        List<String> lines = Files.readAllLines(csvPath);
        assertEquals(Arrays.asList("id,name", "1,Alice", "2,Bob"), lines);
    }
}