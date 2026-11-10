package ec.edu.espe.electromechanicalsystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Minimal JSON helpers (no external library): builds flat JSON objects and
 * reads their fields back.
 */
public final class JsonUtil {
    private static final String FIELD_INDENT = "    ";

    private JsonUtil() {
    }

    // ---------- writing ----------

    public static String string(String key, String value) {
        return FIELD_INDENT + "\"" + key + "\": \"" + escape(value) + "\"";
    }

    public static String number(String key, Number value) {
        return FIELD_INDENT + "\"" + key + "\": " + value;
    }

    public static String bool(String key, boolean value) {
        return FIELD_INDENT + "\"" + key + "\": " + value;
    }

    public static String object(String... fields) {
        return "  {\n" + String.join(",\n", fields) + "\n  }";
    }

    // ---------- reading ----------

    /** Returns the text between the braces of every top-level object in the JSON array. */
    public static List<String> splitObjects(String json) {
        List<String> objects = new ArrayList<>();
        int depth = 0;
        int start = -1;
        boolean inString = false;
        boolean escaped = false;
        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            if (inString) {
                if (escaped) {
                    escaped = false;
                } else if (c == '\\') {
                    escaped = true;
                } else if (c == '"') {
                    inString = false;
                }
            } else if (c == '"') {
                inString = true;
            } else if (c == '{') {
                if (depth++ == 0) {
                    start = i + 1;
                }
            } else if (c == '}' && depth > 0 && --depth == 0) {
                objects.add(json.substring(start, i));
            }
        }
        return objects;
    }

    public static String readString(String object, String key) {
        Matcher matcher = Pattern.compile(keyPrefix(key) + "\"((?:\\\\.|[^\"\\\\])*)\"").matcher(object);
        return matcher.find() ? unescape(matcher.group(1)) : null;
    }

    public static Integer readInt(String object, String key) {
        Matcher matcher = Pattern.compile(keyPrefix(key) + "(-?\\d+)").matcher(object);
        if (!matcher.find()) {
            return null;
        }
        try {
            return Integer.valueOf(matcher.group(1));
        } catch (NumberFormatException exception) {
            return null;
        }
    }

    public static Boolean readBoolean(String object, String key) {
        Matcher matcher = Pattern.compile(keyPrefix(key) + "(true|false)").matcher(object);
        return matcher.find() ? Boolean.valueOf(matcher.group(1)) : null;
    }

    // ---------- escaping ----------

    private static String keyPrefix(String key) {
        return "\"" + Pattern.quote(key) + "\"\\s*:\\s*";
    }

    static String escape(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\\", "\\\\").replace("\"", "\\\"")
                .replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
    }

    static String unescape(String value) {
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
        if (escaped) {
            result.append('\\');
        }
        return result.toString();
    }
}
