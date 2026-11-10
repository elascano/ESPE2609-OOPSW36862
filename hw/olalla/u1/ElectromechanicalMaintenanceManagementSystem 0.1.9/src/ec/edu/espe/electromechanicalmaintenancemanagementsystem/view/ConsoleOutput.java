package ec.edu.espe.electromechanicalmaintenancemanagementsystem.view;

import java.util.List;

/** Small helpers to print record lists. */
public final class ConsoleOutput {

    private ConsoleOutput() {
    }

    public static void show(List<?> items) {
        if (items.isEmpty()) {
            System.out.println("No records.");
        }
        for (Object item : items) {
            System.out.println(item);
        }
    }

    public static void section(String title, List<?> items) {
        System.out.println("\n" + title);
        show(items);
    }
}
