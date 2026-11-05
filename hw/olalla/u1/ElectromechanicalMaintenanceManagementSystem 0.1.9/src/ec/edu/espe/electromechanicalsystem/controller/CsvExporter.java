package ec.edu.espe.electromechanicalsystem.controller;

import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/** Exports every record list to its own CSV file (UTF-8 with BOM so Excel opens it correctly). */
public final class CsvExporter {

    private CsvExporter() {
    }

    /** Writes one CSV per record type into the folder and returns the total number of records written. */
    public static int exportAll(SystemState state, Path folder) throws IOException {
        Files.createDirectories(folder);
        List<MaintenanceTask> tasks = new ArrayList<>();
        for (WorkOrder order : state.orders) {
            tasks.addAll(order.getTasks());
        }
        int count = 0;
        count += exportFile(folder.resolve("clients.csv"), Client.class, state.clients);
        count += exportFile(folder.resolve("equipments.csv"), Equipment.class, state.equipments);
        count += exportFile(folder.resolve("technicians.csv"), Technician.class, state.technicians);
        count += exportFile(folder.resolve("maintenance_schedules.csv"), MaintenanceSchedule.class, state.schedules);
        count += exportFile(folder.resolve("work_orders.csv"), WorkOrder.class, state.orders);
        count += exportFile(folder.resolve("maintenance_tasks.csv"), MaintenanceTask.class, tasks);
        count += exportFile(folder.resolve("maintenance_history.csv"), MaintenanceHistory.class, state.histories);
        count += exportFile(folder.resolve("parts.csv"), Part.class, state.parts);
        count += exportFile(folder.resolve("safety_regulations.csv"), SafetyRegulation.class, state.regulations);
        count += exportFile(folder.resolve("alerts.csv"), Alert.class, state.alerts);
        count += exportFile(folder.resolve("reports.csv"), Report.class, state.reports);
        count += exportFile(folder.resolve("qr_codes.csv"), QrCode.class, state.qrCodes);
        return count;
    }

    private static int exportFile(Path output, Class<?> recordClass, List<?> records) throws IOException {
        List<Method> getters = simpleGetters(recordClass);
        try (BufferedWriter writer = Files.newBufferedWriter(output, StandardCharsets.UTF_8)) {
            writer.write("\uFEFF");
            writeRow(writer, headers(getters));
            for (Object record : records) {
                writeRow(writer, values(getters, record));
            }
        }
        return records.size();
    }

    private static List<Method> simpleGetters(Class<?> recordClass) {
        List<Method> getters = new ArrayList<>();
        for (Method method : recordClass.getMethods()) {
            String name = method.getName();
            boolean getter = (name.startsWith("get") && name.length() > 3)
                    || (name.startsWith("is") && name.length() > 2);
            if (getter && method.getParameterCount() == 0 && !name.equals("getClass")
                    && isSimpleType(method.getReturnType())) {
                getters.add(method);
            }
        }
        getters.sort((a, b) -> {
            String nameA = propertyName(a);
            String nameB = propertyName(b);
            boolean idA = nameA.toLowerCase().endsWith("id");
            boolean idB = nameB.toLowerCase().endsWith("id");
            if (idA != idB) {
                return idA ? -1 : 1;
            }
            return nameA.compareToIgnoreCase(nameB);
        });
        return getters;
    }

    private static boolean isSimpleType(Class<?> type) {
        return type.isPrimitive() || type == String.class || Number.class.isAssignableFrom(type)
                || type == Boolean.class || type == Character.class || type.isEnum()
                || java.util.Date.class.isAssignableFrom(type) || type.getName().startsWith("java.time.");
    }

    private static List<String> headers(List<Method> getters) {
        List<String> headers = new ArrayList<>();
        for (Method getter : getters) {
            String spaced = propertyName(getter).replaceAll("([a-z])([A-Z])", "$1 $2");
            headers.add(Character.toUpperCase(spaced.charAt(0)) + spaced.substring(1));
        }
        return headers;
    }

    private static List<String> values(List<Method> getters, Object record) throws IOException {
        List<String> values = new ArrayList<>();
        for (Method getter : getters) {
            try {
                Object value = getter.invoke(record);
                values.add(value == null ? "" : String.valueOf(value));
            } catch (ReflectiveOperationException exception) {
                throw new IOException("Could not read field from " + record.getClass().getSimpleName(), exception);
            }
        }
        return values;
    }

    private static void writeRow(BufferedWriter writer, List<String> cells) throws IOException {
        for (int i = 0; i < cells.size(); i++) {
            if (i > 0) {
                writer.write(',');
            }
            writer.write(cell(cells.get(i)));
        }
        writer.newLine();
    }

    private static String propertyName(Method method) {
        String name = method.getName();
        name = name.startsWith("get") ? name.substring(3) : name.substring(2);
        if (name.startsWith("Is") && name.length() > 2) {
            name = name.substring(2);
        }
        return Character.toLowerCase(name.charAt(0)) + name.substring(1);
    }

    /** Quotes a cell and neutralises spreadsheet formulas (CSV injection). */
    private static String cell(String value) {
        if (!value.isEmpty() && "=+-@\t\r".indexOf(value.charAt(0)) >= 0) {
            value = "'" + value;
        }
        return "\"" + value.replace("\"", "\"\"") + "\"";
    }
}
