package tp2.ejercicio7;

import tp2.ejercicio1.BinaryTree;

public class ParcialArboles {

    private BinaryTree<Integer> arbol;

    public ParcialArboles(BinaryTree<Integer> arbol) {
        this.arbol = arbol;
    }

    // ===================== EJERCICIO 7 =====================

    public boolean isLeftTree(int num) {
        // TODO: Devuelve true si el subárbol con raíz "num" tiene en su subárbol
        // izquierdo una cantidad MAYOR ESTRICTA de árboles con un único hijo
        // que en su subárbol derecho. False en caso contrario.
        //
        // Consideraciones:
        //   - Si "num" no está en el árbol → false
        //   - Si el árbol con raíz "num" no tiene una de sus ramas → esa rama tiene -1
        //   - Un árbol tiene "único hijo" si tiene exactamente uno de los dos hijos (no ambos, no ninguno)
        //
        // Estrategia: recorrer UNA SOLA VEZ el árbol completo para:
        //   1. Encontrar el nodo con valor "num"
        //   2. Una vez encontrado, contar los de único hijo en cada subárbol
        return false;
    }

    // ===================== MAIN =====================

    public static void main(String[] args) {

        // Árbol del ejemplo del enunciado:
        //         2
        //        / \
        //       7   -5
        //      / \    \
        //     23  6    19
        //    /  \   \    \
        //   -3  55  11    4
        //                  \
        //                  18

        BinaryTree<Integer> tree = new BinaryTree<>(2);
        BinaryTree<Integer> n7   = new BinaryTree<>(7);
        BinaryTree<Integer> nm5  = new BinaryTree<>(-5);
        BinaryTree<Integer> n23  = new BinaryTree<>(23);
        BinaryTree<Integer> n6   = new BinaryTree<>(6);
        BinaryTree<Integer> n19  = new BinaryTree<>(19);
        BinaryTree<Integer> nm3  = new BinaryTree<>(-3);
        BinaryTree<Integer> n55  = new BinaryTree<>(55);
        BinaryTree<Integer> n11  = new BinaryTree<>(11);
        BinaryTree<Integer> n4   = new BinaryTree<>(4);
        BinaryTree<Integer> n18  = new BinaryTree<>(18);

        tree.addLeftChild(n7);
        tree.addRightChild(nm5);
        n7.addLeftChild(n23);
        n7.addRightChild(n6);
        nm5.addRightChild(n19);
        n23.addLeftChild(nm3);
        n23.addRightChild(n55);
        n6.addRightChild(n11);
        n19.addRightChild(n4);
        n4.addRightChild(n18);

        ParcialArboles pa = new ParcialArboles(tree);

        System.out.println("=== Ejercicio 7 ===");
        System.out.println("isLeftTree(7):   " + pa.isLeftTree(7));    // true  (izq:1 > der:0)
        System.out.println("isLeftTree(2):   " + pa.isLeftTree(2));    // false (izq:1 > der:3 → NO)
        System.out.println("isLeftTree(-5):  " + pa.isLeftTree(-5));   // true  (izq:2 > der:-1)
        System.out.println("isLeftTree(19):  " + pa.isLeftTree(19));   // false (izq:-1 > der:1 → NO)
        System.out.println("isLeftTree(-3):  " + pa.isLeftTree(-3));   // false (-1 > -1 → NO)
        System.out.println("isLeftTree(99):  " + pa.isLeftTree(99));   // false (no existe)
    }
}
