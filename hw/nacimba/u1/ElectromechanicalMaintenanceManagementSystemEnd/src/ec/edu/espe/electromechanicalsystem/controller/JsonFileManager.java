package ec.edu.espe.electromechanicalsystem.controller;

import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.Client;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Saves and loads client records in JSON without external libraries. */
public class JsonFileManager {
    private final Path filePath;

    public JsonFileManager(String fileName) {
        this.filePath = Path.of(fileName);
    }

    public List<Client> loadClients() {
        List<Client> clients = new ArrayList<>();
        if (!Files.exists(filePath)) return clients;
        try {
            String json = Files.readString(filePath, StandardCharsets.UTF_8);
            Matcher objects = Pattern.compile("\\{([^{}]*)\\}").matcher(json);
            while (objects.find()) {
                String object = objects.group(1);
                String idText = findValue(object, "clientId", false);
                String name = findValue(object, "name", true);
                String contact = findValue(object, "contactInfo", true);
                String address = findValue(object, "address", true);
                if (idText != null && name != null && contact != null && address != null) {
                    try {
                        clients.add(new Client(Integer.parseInt(idText), unescape(name), unescape(contact), unescape(address)));
                    } catch (NumberFormatException ignored) {
                        System.out.println("A client record with an invalid ID was skipped.");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load clients.json: " + e.getMessage());
        }
        return clients;
    }

    public void saveClients(List<Client> clients) {
        StringBuilder json = new StringBuilder("[\n");
        for (int i = 0; i < clients.size(); i++) {
            Client client = clients.get(i);
            json.append("  {\n")
                .append("    \"clientId\": ").append(client.getClientId()).append(",\n")
                .append("    \"name\": \"").append(escape(client.getName())).append("\",\n")
                .append("    \"contactInfo\": \"").append(escape(client.getContactInfo())).append("\",\n")
                .append("    \"address\": \"").append(escape(client.getAddress())).append("\"\n")
                .append("  }");
            if (i < clients.size() - 1) json.append(',');
            json.append('\n');
        }
        json.append("]\n");
        try {
            Path parent = filePath.toAbsolutePath().getParent();
            if (parent != null) Files.createDirectories(parent);
            Files.writeString(filePath, json.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Could not save clients.json: " + e.getMessage());
        }
    }

    private static String findValue(String object, String key, boolean isString) {
        String prefix = "\"" + Pattern.quote(key) + "\"\\s*:\\s*";
        Pattern pattern = isString
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
            } else if (c == '\\') {
                escaped = true;
            } else {
                result.append(c);
            }
        }
        if (escaped) result.append('\\');
        return result.toString();
    }
}
