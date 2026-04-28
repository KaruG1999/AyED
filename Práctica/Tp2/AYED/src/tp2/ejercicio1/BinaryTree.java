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
        // TODO: Devuelve la cantidad de hojas del árbol receptor.
        // Una hoja es un nodo sin hijos (isLeaf() == true).
        // Caso base: si es hoja, retornar 1.
        // Caso recursivo: sumar las hojas del subárbol izquierdo y derecho.
        return 0;
    }

    // ===================== EJERCICIO 2b =====================

    public BinaryTree<T> espejo() {
        // TODO: Devuelve un nuevo árbol binario espejo del receptor.
        // El espejo se obtiene intercambiando recursivamente los hijos
        // izquierdo y derecho en cada nodo.
        // Ejemplo: árbol con raíz 1, izq=2, der=3 → espejo tiene raíz 1, izq=3, der=2
        return null;
    }

    // ===================== EJERCICIO 2c =====================

    public void entreNiveles(int n, int m) {
        // TODO: Imprime por consola los elementos del árbol entre los niveles n y m (inclusive).
        // Usar recorrido por niveles (BFS con Queue).
        // Nivel 0 = raíz. Se recorre nivel por nivel y solo se imprimen los comprendidos entre n y m.
        // Tip: usar una Queue<BinaryTree<T>> y llevar un contador de nivel.
    }
}
