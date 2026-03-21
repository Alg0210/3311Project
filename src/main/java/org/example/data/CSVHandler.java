package org.example.data;
import java.io.*;
import java.util.*;

public class CSVHandler {
    private static CSVHandler instance;

    private CSVHandler() {}

    public static CSVHandler getInstance() {
        if (instance == null) {
            instance = new CSVHandler();
        }
        return instance;
    }

    // Read all rows from a CSV file
    public List<String[]> readCSV(String filePath) {
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) { firstLine = false; continue; } // skip header
                if (!line.trim().isEmpty()) {
                    rows.add(line.split(",", -1));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV: " + filePath);
        }
        return rows;
    }

    // Write all rows to a CSV file (overwrites existing content)
    public void writeCSV(String filePath, String header, List<String[]> rows) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, false))) {
            pw.println(header);
            for (String[] row : rows) {
                pw.println(String.join(",", row));
            }
        } catch (IOException e) {
            System.out.println("Error writing CSV: " + filePath);
        }
    }

    // Append a single row to a CSV file
    public void appendCSV(String filePath, String[] row) {
        try {
            File file = new File(filePath);
            // Ensure the file ends with a newline before appending
            if (file.exists() && file.length() > 0) {
                RandomAccessFile raf = new RandomAccessFile(file, "r");
                raf.seek(file.length() - 1);
                int lastByte = raf.read();
                raf.close();
                if (lastByte != '\n') {
                    try (FileWriter fw = new FileWriter(file, true)) {
                        fw.write(System.lineSeparator());
                    }
                }
            }
            try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) {
                pw.println(String.join(",", row));
            }
        } catch (IOException e) {
            System.out.println("Error appending to CSV: " + filePath);
        }
    }

    // Check if a CSV file exists, if not create it with a header
    public void initCSV(String filePath, String header) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
                    pw.println(header);
                }
            } catch (IOException e) {
                System.out.println("Error creating CSV: " + filePath);
            }
        }
    }
}
