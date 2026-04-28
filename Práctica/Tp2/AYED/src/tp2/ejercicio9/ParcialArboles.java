package tp2.ejercicio9;

import tp2.ejercicio1.BinaryTree;

public class ParcialArboles {

    private BinaryTree<Integer> arbol;

    public ParcialArboles(BinaryTree<Integer> arbol) {
        this.arbol = arbol;
    }

    // ===================== EJERCICIO 9 =====================

    public BinaryTree<?> sumAndDif(BinaryTree<Integer> arbol) {
        // TODO: Devuelve un NUEVO árbol donde cada nodo contiene dos valores (NodeInfo):
        //   - suma: acumulado de todos los valores desde la raíz hasta el nodo actual (inclusive)
        //   - diferencia: valor_del_nodo_actual - valor_del_nodo_padre
        //     (en la raíz, el padre vale 0, por lo que diferencia = valor_raíz - 0 = valor_raíz)
        //
        // Tip: usar un método auxiliar recursivo que reciba:
        //   - el nodo actual del árbol original
        //   - la suma acumulada hasta el padre
        //   - el valor del padre
        return null;
    }

    // ===================== MAIN =====================

    public static void main(String[] args) {

        // Árbol del ejemplo del enunciado:
        //          20
        //         /  \
        //        5    30
        //       / \   / \
        //      -5  10 50  -9
        //         /   \
        //         1    4
        //               \
        //                6

        BinaryTree<Integer> tree = new BinaryTree<>(20);
        BinaryTree<Integer> n5   = new BinaryTree<>(5);
        BinaryTree<Integer> n30  = new BinaryTree<>(30);
        BinaryTree<Integer> nm5  = new BinaryTree<>(-5);
        BinaryTree<Integer> n10  = new BinaryTree<>(10);
        BinaryTree<Integer> n50  = new BinaryTree<>(50);
        BinaryTree<Integer> nm9  = new BinaryTree<>(-9);
        BinaryTree<Integer> n1   = new BinaryTree<>(1);
        BinaryTree<Integer> n4   = new BinaryTree<>(4);
        BinaryTree<Integer> n6   = new BinaryTree<>(6);

        tree.addLeftChild(n5);
        tree.addRightChild(n30);
        n5.addLeftChild(nm5);
        n5.addRightChild(n10);
        n30.addLeftChild(n50);
        n30.addRightChild(nm9);
        n10.addLeftChild(n1);
        n50.addRightChild(n4);
        n4.addRightChild(n6);

        ParcialArboles pa = new ParcialArboles(tree);
        BinaryTree<?> resultado = pa.sumAndDif(tree);

        System.out.println("=== Ejercicio 9 ===");
        if (resultado != null) {
            System.out.println("Raíz: " + resultado.getData()); // esperado: 20|20
            // Árbol esperado:
            //        20|20
            //       /      \
            //    25|-15    50|10
            //    /   \     /    \
            // 20|-10 35|5 100|20 41|-39
            //        /    \
            //      36|-9  104|-46
            //                \
            //               110|2
        }
    }
}
