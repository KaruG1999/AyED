package tp2.ejercicio4;

import tp2.ejercicio1.BinaryTree;

public class RedBinariaLlena {

    private BinaryTree<Integer> red;

    public RedBinariaLlena(BinaryTree<Integer> red) {
        this.red = red;
    }

    // ===================== EJERCICIO 4b =====================

    public int retardoReenvio() {
        // TODO: Calcular el mayor retardo posible en el camino desde la raíz hasta una hoja.
        // Estrategia: recorrido en PROFUNDIDAD (DFS).
        // En cada camino raíz→hoja, acumular la suma de retardos.
        // Retornar el máximo de todas las sumas posibles.
        // Si hay más de un máximo, retornar el último hallado.
        // Tip: usar un método recursivo que acumule la suma del camino actual
        //      y actualice el máximo al llegar a una hoja.
        return 0;
    }

    // ===================== MAIN =====================

    public static void main(String[] args) {

        // Red del ejemplo del enunciado:
        //              10
        //            /    \
        //           2      3
        //          / \    / \
        //         5   4  9   8
        //        /\ /\ /\   /\
        //       7 8 5 6 12 8  2 1

        BinaryTree<Integer> root = new BinaryTree<>(10);

        BinaryTree<Integer> n2 = new BinaryTree<>(2);
        BinaryTree<Integer> n3 = new BinaryTree<>(3);

        BinaryTree<Integer> n5 = new BinaryTree<>(5);
        BinaryTree<Integer> n4 = new BinaryTree<>(4);
        BinaryTree<Integer> n9 = new BinaryTree<>(9);
        BinaryTree<Integer> n8 = new BinaryTree<>(8);

        BinaryTree<Integer> n7  = new BinaryTree<>(7);
        BinaryTree<Integer> n8b = new BinaryTree<>(8);
        BinaryTree<Integer> n5b = new BinaryTree<>(5);
        BinaryTree<Integer> n6  = new BinaryTree<>(6);
        BinaryTree<Integer> n12 = new BinaryTree<>(12);
        BinaryTree<Integer> n8c = new BinaryTree<>(8);
        BinaryTree<Integer> n2b = new BinaryTree<>(2);
        BinaryTree<Integer> n1  = new BinaryTree<>(1);

        root.addLeftChild(n2);
        root.addRightChild(n3);
        n2.addLeftChild(n5);
        n2.addRightChild(n4);
        n3.addLeftChild(n9);
        n3.addRightChild(n8);
        n5.addLeftChild(n7);
        n5.addRightChild(n8b);
        n4.addLeftChild(n5b);
        n4.addRightChild(n6);
        n9.addLeftChild(n12);
        n9.addRightChild(n8c);
        n8.addLeftChild(n2b);
        n8.addRightChild(n1);

        RedBinariaLlena r = new RedBinariaLlena(root);

        System.out.println("=== Ejercicio 4 ===");
        System.out.println("Mayor retardo: " + r.retardoReenvio());
        // Esperado: 34 (camino: 10 → 3 → 9 → 12 = 10+3+9+12)
    }
}
