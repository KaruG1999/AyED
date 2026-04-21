package tp1.ejercicio8;

public class TestQueue {

    public static void main(String[] args) {
        // Probar Queue
        Queue<Integer> cola = new Queue<>();
        cola.enqueue(1);
        cola.enqueue(2);
        cola.enqueue(3);
        System.out.println("Cola: " + cola);
        System.out.println("dequeue: " + cola.dequeue());
        System.out.println("head: " + cola.head());

        // Probar CircularQueue
        CircularQueue<String> circular = new CircularQueue<>();
        circular.enqueue("Parada A");
        circular.enqueue("Parada B");
        circular.enqueue("Parada C");
        System.out.println("Circular antes: " + circular);
        circular.shift();
        System.out.println("Circular despues de shift: " + circular);

        // Probar DoubleEndedQueue
        DoubleEndedQueue<Integer> deque = new DoubleEndedQueue<>();
        deque.enqueue(2);
        deque.enqueue(3);
        deque.enqueueFirst(1);
        System.out.println("DoubleEnded: " + deque);
    }
}
