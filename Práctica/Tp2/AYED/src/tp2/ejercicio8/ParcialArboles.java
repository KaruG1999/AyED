package tp2.ejercicio8;

import tp2.ejercicio1.BinaryTree;

public class ParcialArboles {

    private BinaryTree<Integer> arbol;

    public ParcialArboles(BinaryTree<Integer> arbol) {
        this.arbol = arbol;
    }

    // ===================== EJERCICIO 8 =====================

    public boolean esPrefijo(BinaryTree<Integer> arbol1, BinaryTree<Integer> arbol2) {
        // TODO: Devuelve true si arbol1 es prefijo de arbol2.
        //
        // Un árbol arbol1 es PREFIJO de arbol2 cuando arbol1 coincide
        // con la parte inicial de arbol2 tanto en CONTENIDO como en ESTRUCTURA.
        //
        // Casos:
        //   - Si arbol1 es null (vacío) → true (árbol vacío es prefijo de cualquiera)
        //   - Si arbol1 no es null pero arbol2 es null → false
        //   - Si los datos no coinciden → false
        //   - Si arbol1 tiene hijo izquierdo pero arbol2 no → false
        //   - Si arbol1 tiene hijo derecho pero arbol2 no → false
        //   - Recursivamente: esPrefijo(izq1, izq2) && esPrefijo(der1, der2)
        return false;
    }

    // ===================== MAIN =====================

    public static void main(String[] args) {

        // arbol1 (el prefijo candidato):
        //      65
        //     /  \
        //    37   81
        //      \
        //      93

        BinaryTree<Integer> a1 = new BinaryTree<>(65);
        BinaryTree<Integer> a1n37 = new BinaryTree<>(37);
        BinaryTree<Integer> a1n81 = new BinaryTree<>(81);
        BinaryTree<Integer> a1n93 = new BinaryTree<>(93);
        a1.addLeftChild(a1n37);
        a1.addRightChild(a1n81);
        a1n37.addRightChild(a1n93);

        // arbol2:
        //        65
        //       /  \
        //      37   81
        //     /  \
        //    22   47    93
        //        /  \
        //       76   93
        //      /  \
        //     11   29    85  94

        BinaryTree<Integer> a2 = new BinaryTree<>(65);
        BinaryTree<Integer> a2n37 = new BinaryTree<>(37);
        BinaryTree<Integer> a2n81 = new BinaryTree<>(81);
        BinaryTree<Integer> a2n22 = new BinaryTree<>(22);
        BinaryTree<Integer> a2n47 = new BinaryTree<>(47);
        BinaryTree<Integer> a2n76 = new BinaryTree<>(76);
        BinaryTree<Integer> a2n93 = new BinaryTree<>(93);
        BinaryTree<Integer> a2n11 = new BinaryTree<>(11);
        BinaryTree<Integer> a2n29 = new BinaryTree<>(29);
        BinaryTree<Integer> a2n85 = new BinaryTree<>(85);
        BinaryTree<Integer> a2n94 = new BinaryTree<>(94);
        a2.addLeftChild(a2n37);
        a2.addRightChild(a2n81);
        a2n37.addLeftChild(a2n22);
        a2n37.addRightChild(a2n47);
        a2n47.addLeftChild(a2n76);
        a2n47.addRightChild(a2n93);
        a2n76.addLeftChild(a2n11);
        a2n76.addRightChild(a2n29);
        a2n93.addLeftChild(a2n85);
        a2n93.addRightChild(a2n94);

        ParcialArboles pa = new ParcialArboles(null);

        System.out.println("=== Ejercicio 8 ===");
        System.out.println("esPrefijo(arbol1, arbol2): " + pa.esPrefijo(a1, a2));
        // Esperado: true (arbol1 coincide con la parte inicial de arbol2)

        // Caso donde arbol1 NO es prefijo: nodo 37 tiene hijo izquierdo en arbol1
        // pero en arbol2 tiene hijos diferentes...
        // Usar ejemplos del PDF para más casos de prueba
    }
}
