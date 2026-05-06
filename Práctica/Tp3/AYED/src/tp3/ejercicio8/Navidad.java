package tp3.ejercicio8;

import tp3.ejercicio1.GeneralTree;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Navidad {

    // Variable de instancia: el abeto ya construido
    private GeneralTree<String> abeto;

    public Navidad() {
        this.abeto = construirAbeto();
    }

    // ===================== EJERCICIO 8 =====================

    public String esAbetoNavidenio() {
        // TODO: verificar si el árbol tiene la forma de abeto navideño
        // Un árbol es "abeto navideño" si en cada nivel k hay exactamente k+1 nodos:
        //   nivel 0 → 1 nodo (raíz)
        //   nivel 1 → 2 nodos
        //   nivel 2 → 3 nodos
        //   nivel k → k+1 nodos
        // Retornar un String visual (árbol por niveles centrado) si lo es,
        // o "No es un abeto navideño" si no lo es.

        if (!cumpleFormaAbeto()) {
            return "No es un abeto navideño";
        }
        return generarRepresentacion();
    }

    private boolean cumpleFormaAbeto() {
        // TODO: verificar la propiedad nivel k → k+1 nodos usando BFS
        // Usar null como separador de niveles en la Queue
        return false;
    }

    private String generarRepresentacion() {
        // TODO: construir una representación visual por niveles
        // Cada nivel en una línea, con los datos de los nodos separados por espacios
        // Ejemplo:
        //      *
        //     * *
        //    * * *
        //   * * * *
        return "";
    }

    // ===================== CONSTRUCCIÓN DEL ABETO =====================

    private GeneralTree<String> construirAbeto() {
        // Abeto navideño: nivel 0→1 nodo, nivel 1→2, nivel 2→3, nivel 3→4
        // La raíz tiene 2 hijos
        // Los 2 hijos del nivel 1 suman 3 hijos entre ambos
        // etc.
        // Una forma sencilla: raíz tiene 1 hijo, ese tiene 2, esos tienen 3 en total, etc.
        //
        //      *               (nivel 0: 1 nodo)
        //     / \              (nivel 1: 2 nodos)
        //    * * *             (nivel 2: 3 nodos)
        //   * * * *            (nivel 3: 4 nodos)

        GeneralTree<String> raiz = new GeneralTree<>("*");

        GeneralTree<String> h1 = new GeneralTree<>("*");
        GeneralTree<String> h2 = new GeneralTree<>("*");
        raiz.addChild(h1);
        raiz.addChild(h2);

        GeneralTree<String> n1 = new GeneralTree<>("*");
        GeneralTree<String> n2 = new GeneralTree<>("*");
        GeneralTree<String> n3 = new GeneralTree<>("*");
        h1.addChild(n1);
        h1.addChild(n2);
        h2.addChild(n3);

        GeneralTree<String> l1 = new GeneralTree<>("*");
        GeneralTree<String> l2 = new GeneralTree<>("*");
        GeneralTree<String> l3 = new GeneralTree<>("*");
        GeneralTree<String> l4 = new GeneralTree<>("*");
        n1.addChild(l1);
        n2.addChild(l2);
        n3.addChild(l3);
        n3.addChild(l4);

        return raiz;
    }

    // ===================== MAIN =====================

    public static void main(String[] args) {
        Navidad nav = new Navidad();
        System.out.println(nav.esAbetoNavidenio());
    }
}
