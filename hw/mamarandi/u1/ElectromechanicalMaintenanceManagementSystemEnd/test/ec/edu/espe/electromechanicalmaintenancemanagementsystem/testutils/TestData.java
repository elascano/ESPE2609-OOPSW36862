package ec.edu.espe.electromechanicalmaintenancemanagementsystem.testutils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Reads test data from CSV files (separator ';') stored in testdata/<DATA_SET>/.
 * Change DATA_SET to switch scenarios: "set25", "set75" or "set100".
 */
public class TestData {

    public static final String DATA_SET = "set75";

    public static String[] row(String fileName, int caseNumber) {
        Path file = Path.of("testdata", DATA_SET, fileName);
        try {
            List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
            for (int i = 1; i < lines.size(); i++) {
                String[] fields = lines.get(i).split(";", -1);
                if (fields[0].trim().equals(String.valueOf(caseNumber))) return fields;
            }
        } catch (IOException e) {
            throw new IllegalStateException("Could not read " + file.toAbsolutePath() + ": " + e.getMessage(), e);
        }
        throw new IllegalStateException("Case " + caseNumber + " not found in " + file);
    }
}
