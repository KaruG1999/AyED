package tp2.ejercicio6;

import tp2.ejercicio1.BinaryTree;

public class Transformacion {

    private BinaryTree<Integer> arbol;

    public Transformacion(BinaryTree<Integer> arbol) {
        this.arbol = arbol;
    }

    // ===================== EJERCICIO 6 =====================

    public BinaryTree<Integer> suma() {
        // TODO: Devuelve un NUEVO árbol donde cada nodo contiene
        // la suma de todos los elementos de su subárbol izquierdo y derecho.
        // (No incluye el valor del propio nodo, solo sus hijos.)
        // Los subárboles vacíos valen 0.
        //
        // Reflexión: ¿Tu solución recorre cada subárbol solo una vez?
        // Tip para hacerlo en un solo recorrido:
        //   Usar un método auxiliar que retorne la suma total del subárbol
        //   y construya el nuevo árbol al mismo tiempo (PostOrden).
        return null;
    }

    // ===================== MAIN =====================

    public static void main(String[] args) {

        // Árbol de ejemplo (enunciado):
        //        1
        //       / \
        //      2   3
        //     /   / \
        //    4   5   6
        //       / \
        //      7   8

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
        n3.addLeftChild(n5);
        n3.addRightChild(n6);
        n5.addLeftChild(n7);
        n5.addRightChild(n8);

        Transformacion t = new Transformacion(tree);
        BinaryTree<Integer> resultado = t.suma();

        System.out.println("=== Ejercicio 6 ===");
        if (resultado != null) {
            System.out.println("Raíz del árbol transformado: " + resultado.getData());
            // Esperado: 35 (suma de todos los elementos: 2+4+3+5+6+7+8 = 35, pero sin el 1)
            // Árbol resultado:
            //       35
            //      /  \
            //     4    26
            //    /    /  \
            //   0    15   0
            //       /  \
            //      0    0
        }
    }
}
