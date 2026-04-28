package tp2.ejercicio3;

import tp2.ejercicio1.BinaryTree;
import java.util.ArrayList;
import java.util.List;

public class ContadorArbol {

    private BinaryTree<Integer> arbol;

    public ContadorArbol(BinaryTree<Integer> arbol) {
        this.arbol = arbol;
    }

    // ===================== EJERCICIO 3a: InOrden =====================

    public List<Integer> numerosPares() {
        List<Integer> resultado = new ArrayList<>();
        // TODO: Recorrer el árbol en InOrden (izquierda → raíz → derecha)
        // y agregar al resultado todos los nodos cuyo valor sea divisible por 2.
        // Tip: usar un método privado auxiliar que reciba el nodo y la lista acumuladora.
        return resultado;
    }

    // ===================== EJERCICIO 3b: PostOrden =====================

    public List<Integer> numerosParesPostOrden() {
        List<Integer> resultado = new ArrayList<>();
        // TODO: Recorrer el árbol en PostOrden (izquierda → derecha → raíz)
        // y agregar al resultado todos los nodos cuyo valor sea divisible por 2.
        return resultado;
    }

    // ===================== MAIN =====================

    public static void main(String[] args) {

        // Árbol de ejemplo:
        //         1
        //        / \
        //       2   3
        //      / \   \
        //     4   6   8
        //    /
        //   10

        BinaryTree<Integer> tree = new BinaryTree<>(1);
        BinaryTree<Integer> n2 = new BinaryTree<>(2);
        BinaryTree<Integer> n3 = new BinaryTree<>(3);
        BinaryTree<Integer> n4 = new BinaryTree<>(4);
        BinaryTree<Integer> n6 = new BinaryTree<>(6);
        BinaryTree<Integer> n8 = new BinaryTree<>(8);
        BinaryTree<Integer> n10 = new BinaryTree<>(10);

        tree.addLeftChild(n2);
        tree.addRightChild(n3);
        n2.addLeftChild(n4);
        n2.addRightChild(n6);
        n3.addRightChild(n8);
        n4.addLeftChild(n10);

        ContadorArbol contador = new ContadorArbol(tree);

        System.out.println("=== Ejercicio 3 ===");
        System.out.println("Pares InOrden:    " + contador.numerosPares());
        // Esperado InOrden (10, 4, 2, 6, 1, 3, 8) → pares: [10, 4, 2, 6, 8]

        System.out.println("Pares PostOrden:  " + contador.numerosParesPostOrden());
        // Esperado PostOrden (10, 4, 6, 2, 8, 3, 1) → pares: [10, 4, 6, 2, 8]
    }
}
