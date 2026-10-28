package ec.edu.espe.electromechanicalsystem.controller;

import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.Report;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Loads and saves report records as JSON using only the Java standard library. */
public class ReportJsonFileManager {
    private final Path filePath;

    public ReportJsonFileManager(String fileName) {
        this.filePath = Path.of(fileName);
    }

    public List<Report> loadReports() {
        List<Report> reports = new ArrayList<>();
        if (!Files.exists(filePath)) return reports;
        try {
            String json = Files.readString(filePath, StandardCharsets.UTF_8);
            Matcher objects = Pattern.compile("\\{([^{}]*)\\}").matcher(json);
            while (objects.find()) {
                String object = objects.group(1);
                String id = findValue(object, "reportId", false);
                String type = findValue(object, "reportType", true);
                String date = findValue(object, "generatedDate", true);
                String content = findValue(object, "dataContent", true);
                if (id == null || type == null || date == null || content == null) continue;
                try {
                    reports.add(new Report(Integer.parseInt(id), unescape(type), unescape(date), unescape(content)));
                } catch (NumberFormatException ignored) {
                    System.out.println("A report with an invalid ID was skipped.");
                }
            }
        } catch (IOException exception) {
            System.out.println("Could not load reports.json: " + exception.getMessage());
        }
        return reports;
    }

    public void saveReports(List<Report> reports) {
        StringBuilder json = new StringBuilder("[\n");
        for (int i = 0; i < reports.size(); i++) {
            Report report = reports.get(i);
            json.append("  {\n")
                .append("    \"reportId\": ").append(report.getReportId()).append(",\n")
                .append("    \"reportType\": \"").append(escape(report.getReportType())).append("\",\n")
                .append("    \"generatedDate\": \"").append(escape(report.getGeneratedDate())).append("\",\n")
                .append("    \"dataContent\": \"").append(escape(report.getDataContent())).append("\"\n")
                .append("  }");
            if (i < reports.size() - 1) json.append(',');
            json.append('\n');
        }
        json.append("]\n");
        try {
            Path parent = filePath.toAbsolutePath().getParent();
            if (parent != null) Files.createDirectories(parent);
            Files.writeString(filePath, json.toString(), StandardCharsets.UTF_8);
        } catch (IOException exception) {
            System.out.println("Could not save reports.json: " + exception.getMessage());
        }
    }

    private static String findValue(String object, String key, boolean stringValue) {
        String prefix = "\"" + Pattern.quote(key) + "\"\\s*:\\s*";
        Pattern pattern = stringValue
                ? Pattern.compile(prefix + "\"((?:\\\\.|[^\"\\\\])*)\"")
                : Pattern.compile(prefix + "(-?\\d+)");
        Matcher matcher = pattern.matcher(object);
        return matcher.find() ? matcher.group(1) : null;
    }

    private static String escape(String value) {
        if (value == null) return "";
        return value.replace("\\", "\\\\").replace("\"", "\\\"")
                .replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
    }

    private static String unescape(String value) {
        StringBuilder result = new StringBuilder();
        boolean escaped = false;
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (escaped) {
                switch (c) {
                    case 'n': result.append('\n'); break;
                    case 'r': result.append('\r'); break;
                    case 't': result.append('\t'); break;
                    default: result.append(c); break;
                }
                escaped = false;
            } else if (c == '\\') escaped = true;
            else result.append(c);
        }
        if (escaped) result.append('\\');
        return result.toString();
    }
}
