package ec.edu.espe.unistock.structure;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleList<T> {

    private static class Node<T> {

        private final T data;
        private Node<T> next;

        private Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private Node<T> cursor;
    private int size;

    public void add(T element) {
        Objects.requireNonNull(element, "El elemento no puede ser nulo.");
        Node<T> node = new Node<>(element);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        size++;
    }

    public void remove(T element) {
        Node<T> previous = null;
        Node<T> current = head;

        while (current != null && !current.data.equals(element)) {
            previous = current;
            current = current.next;
        }
        if (current == null) {
            return;
        }
        if (previous == null) {
            head = current.next;
        } else {
            previous.next = current.next;
        }
        if (current == tail) {
            tail = previous;
        }
        if (current == cursor) {
            cursor = current.next;
        }
        size--;
    }

    public void resetIteration() {
        cursor = head;
    }

    public boolean hasNext() {
        return cursor != null;
    }

    public T next() {
        if (cursor == null) {
            throw new NoSuchElementException("No hay más elementos.");
        }
        T data = cursor.data;
        cursor = cursor.next;
        return data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
