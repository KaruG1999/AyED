package tp2.ejercicio1;

public class TestBinaryTree {

    public static void main(String[] args) {

        // Árbol de ejemplo del Ejercicio 2b (espejo):
        //        1
        //       / \
        //      2   3
        //     / \   \
        //    4   5   6

        BinaryTree<Integer> tree = new BinaryTree<>(1);
        BinaryTree<Integer> n2 = new BinaryTree<>(2);
        BinaryTree<Integer> n3 = new BinaryTree<>(3);
        BinaryTree<Integer> n4 = new BinaryTree<>(4);
        BinaryTree<Integer> n5 = new BinaryTree<>(5);
        BinaryTree<Integer> n6 = new BinaryTree<>(6);

        tree.addLeftChild(n2);
        tree.addRightChild(n3);
        n2.addLeftChild(n4);
        n2.addRightChild(n5);
        n3.addRightChild(n6);

        System.out.println("=== Ejercicio 1 & 2 ===");
        System.out.println("Árbol original: " + tree);
        System.out.println();

        // Ejercicio 2a
        System.out.println("--- Ej 2a: contarHojas ---");
        System.out.println("Hojas: " + tree.contarHojas()); // esperado: 3 (nodos 4, 5, 6)

        // Ejercicio 2b
        System.out.println("\n--- Ej 2b: espejo ---");
        BinaryTree<Integer> mirror = tree.espejo();
        System.out.println("Espejo: " + mirror);
        // Esperado:
        //        1
        //       / \
        //      3   2
        //     /   / \
        //    6   5   4

        // Ejercicio 2c
        System.out.println("\n--- Ej 2c: entreNiveles(1, 2) ---");
        System.out.print("Nodos entre nivel 1 y 2: ");
        tree.entreNiveles(1, 2); // esperado: 2 3 4 5 6
    }
}
