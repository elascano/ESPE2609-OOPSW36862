package ec.edu.espe.provstock.utils;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;

/**
 * Writes the reports as CSV files that can be opened with Excel.
 * The fields are separated by ";" and the decimals use a point.
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class CsvWriter {

    public static final String REPORTS_FOLDER = "reports";
    private static final String SEPARATOR = ";";

    private CsvWriter() {
        //only static methods
    }

    /**
     * Writes all the rows in the file. Every row is one line; an empty row is a blank line.
     * The file starts with a BOM so Excel shows the accents (á, é, ñ) correctly.
     */
    public static void write(String filePath, List<String[]> rows) throws IOException {
        Path file = Paths.get(filePath);
        if (file.getParent() != null) {
            Files.createDirectories(file.getParent());
        }
        try (Writer writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            writer.write('﻿');
            for (String[] row : rows) {
                for (int i = 0; i < row.length; i++) {
                    if (i > 0) {
                        writer.write(SEPARATOR);
                    }
                    writer.write(escape(row[i]));
                }
                writer.write("\r\n");
            }
        }
    }

    /**
     * Formats an amount with 2 decimals and a point: 1234.5 -> "1234.50".
     */
    public static String money(double value) {
        return String.format(Locale.US, "%.2f", value);
    }

    /**
     * A text that has the separator, quotes or a line break is written between quotes,
     * and its quotes are doubled (CSV rule), so the columns are never broken.
     */
    private static String escape(String value) {
        if (value == null) {
            return "";
        }
        if (value.contains(SEPARATOR) || value.contains("\"") || value.contains("\n") || value.contains("\r")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}
