package ec.edu.espe.electromechanicalsystem.controller;

import java.util.Collection;
import java.util.function.ToIntFunction;

/** Generates the next free ID (highest existing ID + 1), so IDs are never reused after a deletion. */
public final class IdGenerator {

    private IdGenerator() {
    }

    public static <T> int nextId(Collection<T> items, ToIntFunction<T> idGetter) {
        int max = 0;
        for (T item : items) {
            max = Math.max(max, idGetter.applyAsInt(item));
        }
        return max + 1;
    }
}
