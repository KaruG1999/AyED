package tp3.ejercicio11;

import tp3.ejercicio1.GeneralTree;
import java.util.LinkedList;
import java.util.Queue;

public class ParcialArboles {

    // ===================== EJERCICIO 11 =====================

    public static boolean resolver(GeneralTree<Integer> arbol) {
        // TODO: retornar true si el árbol es "creciente"
        //
        // Un árbol es creciente si para cada nivel k:
        //   cantidad_de_nodos_en_nivel_k == cantidad_de_nodos_en_nivel_(k-1) + 1
        //
        // El nivel 0 siempre tiene 1 nodo (la raíz), entonces:
        //   nivel 0 → 1 nodo
        //   nivel 1 → 2 nodos
        //   nivel 2 → 3 nodos
        //   nivel k → k+1 nodos
        //
        // Estrategia: recorrido por niveles (BFS) con null como separador de niveles
        //   - Para cada nivel: contar nodos
        //   - Verificar que el conteo es el esperado
        //   - Si algún nivel no coincide → return false
        //
        // Se recorre la estructura SOLO 1 vez.

        return false;
    }

    // ===================== MAIN =====================

    public static void main(String[] args) {
        // Árbol 1 (TRUE): nivel0=1, nivel1=2, nivel2=3, nivel3=4, nivel4=5
        //          2
        //         / \
        //        1   25
        //       /|\
        //      5  4  13
        //     /| / \
        //    18 7 11  3
        //    |    / \ \ \
        //   83  33 12 17  9

        GeneralTree<Integer> t1r  = new GeneralTree<>(2);
        GeneralTree<Integer> t1n1 = new GeneralTree<>(1);
        GeneralTree<Integer> t1n25= new GeneralTree<>(25);
        GeneralTree<Integer> t1n5 = new GeneralTree<>(5);
        GeneralTree<Integer> t1n4 = new GeneralTree<>(4);
        GeneralTree<Integer> t1n13= new GeneralTree<>(13);
        GeneralTree<Integer> t1n18= new GeneralTree<>(18);
        GeneralTree<Integer> t1n7 = new GeneralTree<>(7);
        GeneralTree<Integer> t1n11= new GeneralTree<>(11);
        GeneralTree<Integer> t1n3 = new GeneralTree<>(3);
        GeneralTree<Integer> t1n83= new GeneralTree<>(83);
        GeneralTree<Integer> t1n33= new GeneralTree<>(33);
        GeneralTree<Integer> t1n12= new GeneralTree<>(12);
        GeneralTree<Integer> t1n17= new GeneralTree<>(17);
        GeneralTree<Integer> t1n9 = new GeneralTree<>(9);

        t1r.addChild(t1n1);
        t1r.addChild(t1n25);
        t1n1.addChild(t1n5);
        t1n1.addChild(t1n4);
        t1n1.addChild(t1n13);
        t1n5.addChild(t1n18);
        t1n5.addChild(t1n7);
        t1n4.addChild(t1n11);
        t1n4.addChild(t1n3);
        t1n18.addChild(t1n83);
        t1n11.addChild(t1n33);
        t1n11.addChild(t1n12);
        t1n11.addChild(t1n17);
        t1n11.addChild(t1n9);

        System.out.println("Árbol 1 (esperado true):  " + resolver(t1r));

        // Árbol 2 (FALSE): en el nivel 3 hay 3 nodos en vez de 4
        GeneralTree<Integer> t2r  = new GeneralTree<>(2);
        GeneralTree<Integer> t2n1 = new GeneralTree<>(1);
        GeneralTree<Integer> t2n25= new GeneralTree<>(25);
        GeneralTree<Integer> t2n5 = new GeneralTree<>(5);
        GeneralTree<Integer> t2n4 = new GeneralTree<>(4);
        GeneralTree<Integer> t2n13= new GeneralTree<>(13);
        GeneralTree<Integer> t2n18= new GeneralTree<>(18);
        GeneralTree<Integer> t2n7 = new GeneralTree<>(7);
        GeneralTree<Integer> t2n3 = new GeneralTree<>(3);   // nivel 3: solo 3 nodos ← FALLA

        t2r.addChild(t2n1);
        t2r.addChild(t2n25);
        t2n1.addChild(t2n5);
        t2n1.addChild(t2n4);
        t2n1.addChild(t2n13);
        t2n5.addChild(t2n18);
        t2n5.addChild(t2n7);
        t2n4.addChild(t2n3);  // solo 3 nodos en nivel 3 → false

        System.out.println("Árbol 2 (esperado false): " + resolver(t2r));
    }
}
