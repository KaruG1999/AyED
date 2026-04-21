package tp1.ejercicio8;

import java.util.LinkedList;
import java.util.List;

public class Queue<T> extends Sequence {

    protected List<T> data;

    public Queue() {
        data = new LinkedList<>();
    }

    // Inserta al final
    public void enqueue(T dato) {
        // TODO
    }

    // Elimina y retorna el primero. Error si esta vacia
    public T dequeue() {
        // TODO
        return null;
    }

    // Retorna el primero sin eliminarlo. Error si esta vacia
    public T head() {
        // TODO
        return null;
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public int size() {
        return data.size();
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
