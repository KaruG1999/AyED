package tp2.ejercicio5;

import tp2.ejercicio1.BinaryTree;
import java.util.LinkedList;
import java.util.Queue;

public class ProfundidadDeArbolBinario {

    private BinaryTree<Integer> arbol;

    public ProfundidadDeArbolBinario(BinaryTree<Integer> arbol) {
        this.arbol = arbol;
    }

    // ===================== EJERCICIO 5 =====================

    public int sumaElementosProfundidad(int p) {
        // Verificación inicial de seguridad
    if (this.arbol == null || this.arbol.isEmpty() || p < 0) {
        return 0;
    }

    Queue<BinaryTree<Integer>> cola = new LinkedList<>();
    cola.add(this.arbol);
    int nivelActual = 0;
    int sumaFinal = 0;

    // Se recorre mientras haya nodos y no hayamos superado el nivel buscado
    while (!cola.isEmpty() && nivelActual <= p) {
        // Se obtiene la cantidad de nodos en el nivel actual antes de procesar
        int nodosEnNivel = cola.size();

        for (int i = 0; i < nodosEnNivel; i++) {
            BinaryTree<Integer> nodo = cola.poll();

            // Si llegamos a la profundidad p, sumamos el dato del nodo
            if (nivelActual == p) {
                sumaFinal += nodo.getData();
            }

            // Solo encolamos hijos si aún no llegamos al nivel p para ahorrar memoria
            if (nivelActual < p) {
                if (nodo.hasLeftChild()) cola.add(nodo.getLeftChild());
                if (nodo.hasRightChild()) cola.add(nodo.getRightChild());
            }
        }
        // Pasamos al siguiente nivel de profundidad
        nivelActual++;
    }
    
    return sumaFinal;
    
    }

    // ===================== MAIN =====================

    public static void main(String[] args) {

        // Árbol de ejemplo:
        //           1          ← profundidad 0
        //          / \
        //         2   3        ← profundidad 1
        //        / \   \
        //       4   5   6      ← profundidad 2
        //          / \
        //         7   8        ← profundidad 3

        BinaryTree<Integer> tree = new BinaryTree<>(1);
        BinaryTree<Integer> n2 = new BinaryTree<>(2);
        BinaryTree<Integer> n3 = new BinaryTree<>(3);
        BinaryTree<Integer> n4 = new BinaryTree<>(4);
        BinaryTree<Integer> n5 = new BinaryTree<>(5);
        BinaryTree<Integer> n6 = new BinaryTree<>(6);
        BinaryTree<Integer> n7 = new BinaryTree<>(7);
        BinaryTree<Integer> n8 = new BinaryTree<>(8);

        tree.addLeftChild(n2);
        tree.addRightChild(n3);
        n2.addLeftChild(n4);
        n2.addRightChild(n5);
        n3.addRightChild(n6);
        n5.addLeftChild(n7);
        n5.addRightChild(n8);

        ProfundidadDeArbolBinario prof = new ProfundidadDeArbolBinario(tree);

        System.out.println("=== Ejercicio 5 ===");
        System.out.println("Suma profundidad 0: " + prof.sumaElementosProfundidad(0)); // 1
        System.out.println("Suma profundidad 1: " + prof.sumaElementosProfundidad(1)); // 2+3 = 5
        System.out.println("Suma profundidad 2: " + prof.sumaElementosProfundidad(2)); // 4+5+6 = 15
        System.out.println("Suma profundidad 3: " + prof.sumaElementosProfundidad(3)); // 7+8 = 15
    }
}
