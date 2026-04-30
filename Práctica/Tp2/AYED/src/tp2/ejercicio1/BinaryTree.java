package tp2.ejercicio1;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<T> {

    private T data;
    private BinaryTree<T> leftChild;
    private BinaryTree<T> rightChild;

    public BinaryTree() {
        this.data = null;
        this.leftChild = null;
        this.rightChild = null;
    }

    public BinaryTree(T data) {
        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
    }

    // ===================== GETTERS / SETTERS =====================

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryTree<T> getLeftChild() {
        if (!hasLeftChild()) throw new RuntimeException("No tiene hijo izquierdo");
        return leftChild;
    }

    public BinaryTree<T> getRightChild() {
        if (!hasRightChild()) throw new RuntimeException("No tiene hijo derecho");
        return rightChild;
    }

    public void addLeftChild(BinaryTree<T> child) {
        this.leftChild = child;
    }

    public void addRightChild(BinaryTree<T> child) {
        this.rightChild = child;
    }

    public void removeLeftChild() {
        this.leftChild = null;
    }

    public void removeRightChild() {
        this.rightChild = null;
    }

    // ===================== CONSULTAS =====================

    public boolean isEmpty() {
        return data == null && leftChild == null && rightChild == null;
    }

    public boolean isLeaf() {
        return !hasLeftChild() && !hasRightChild();
    }

    public boolean hasLeftChild() {
        return leftChild != null;
    }

    public boolean hasRightChild() {
        return rightChild != null;
    }

    @Override
    public String toString() {
        // Recorrido PreOrden: raiz - izquierda - derecha
        StringBuilder sb = new StringBuilder();
        sb.append(data);
        if (hasLeftChild()) sb.append(" [L:" + leftChild.toString() + "]");
        if (hasRightChild()) sb.append(" [R:" + rightChild.toString() + "]");
        return sb.toString();
    }

    // ===================== EJERCICIO 2a =====================

    public int contarHojas() {
        // Si esta vacio
        if (isEmpty()) {
            return 0; 
        }
        // Si es hoja
        if (isLeaf()) {
            return 1; 
        }
        // si no es hoja, delego a mis hijos
        int hojasIzq =0;
        int hojasDer =0;
        // Si tiene hoja izquierda llamo a recuresion y se la paso llamando al mismo método
        if (this.hasLeftChild()){
            hojasIzq = this.getLeftChild().contarHojas();

        }
        // Si tiene hoja derecha llamo con recursion y le paso la hoja derecha
        if (this.hasRightChild()) {
            hojasDer = this.getRightChild().contarHojas();
        }
        
        return hojasIzq + hojasDer;
    }

    // ===================== EJERCICIO 2b =====================

    public BinaryTree<T> espejo() {
        // Caso base: si el árbol esta vacio 
        if (isEmpty()) {
            return new BinaryTree<>();
        }
        // creo nodo que sea el de la raiz del espejo
        BinaryTree<T> espejoRaiz = new BinaryTree<>(this.getData());
        // Recursion y volteo de datos de los hijos 
        if (this.hasLeftChild()) {
            espejoRaiz.addRightChild(this.getLeftChild().espejo());
        }
        if (this.hasRightChild()) {
            espejoRaiz.addLeftChild(this.getRightChild().espejo());
        }
        return espejoRaiz;
    }

    // ===================== EJERCICIO 2c =====================

    public void entreNiveles(int n, int m) {
    BinaryTree<T> ab = null; // "punter" vacio auxiliar 
    Queue<BinaryTree<T>> cola = new LinkedList<BinaryTree<T>>();
    cola.add(this); // Encolar la raíz (dirección en mem)
    int nivel = 0;

    while (!cola.isEmpty() && nivel <= m) {
        int cantNodos = cola.size(); // Foto del nivel actual

        for (int i = 0; i < cantNodos; i++) {
            ab = cola.poll(); // SACO al que está primero y lo aguardo en aux

            // Si el nivel actual me interesa, lo imprimo
            if (nivel >= n && nivel <= m) {
                System.out.println(ab.getData());
            }

            // ENCOLO a los hijos para que queden listos para el PRÓXIMO nivel
            if (ab.hasLeftChild()) {
                cola.add(ab.getLeftChild());
            }
            if (ab.hasRightChild()) {
                cola.add(ab.getRightChild());
            }
        }
        // Una vez que el for terminó, significa que ya procesé TODO el nivel
        nivel++; 
    }
}
}
