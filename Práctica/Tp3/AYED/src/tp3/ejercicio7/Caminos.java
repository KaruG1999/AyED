package tp3.ejercicio7;

import tp3.ejercicio1.GeneralTree;
import java.util.LinkedList;
import java.util.List;

public class Caminos {

    private GeneralTree<Integer> arbol;

    public Caminos(GeneralTree<Integer> arbol) {
        this.arbol = arbol;
    }

    // ===================== EJERCICIO 7 =====================

    public List<Integer> caminoAHojaMasLejana() {
        List<Integer> mejorCamino = new LinkedList<>();
        List<Integer> caminoActual = new LinkedList<>();
        if (this.arbol != null && !this.arbol.isEmpty()){
            caminoAHojaMasLejanaAux(this.arbol, caminoActual, mejorCamino);
        }
        return mejorCamino;
    }

    private void caminoAHojaMasLejanaAux(GeneralTree<Integer> nodo,
                                          List<Integer> caminoActual,
                                          List<Integer> mejorCamino) {
        
        // 1. Agregar el dato del nodo al camino actual
        caminoActual.add(nodo.getData());
        // 2. Si es hoja: comparar longitud con el mejor camino → actualizar si es más largo
        if (nodo.isLeaf()){
            if (caminoActual.size() > mejorCamino.size()) {
                mejorCamino.clear();
                mejorCamino.addAll(caminoActual);
            }
        } else {
            // 3. Si no es hoja: recursión sobre cada hijo
            for (GeneralTree<Integer> hijo: nodo.getChildren()){
                caminoAHojaMasLejanaAux(hijo, caminoActual, mejorCamino);
            }
        }
        // 4. Al volver de la recursión: quitar el dato del camino actual (backtracking)
        caminoActual.remove(caminoActual.size()-1);
    }

    // ===================== MAIN =====================

    public static void main(String[] args) {
        // Árbol del enunciado:
        //            12
        //          /  |  \
        //        17   9   15
        //       / \   |   |\
        //      10  6  8  14 18
        //          |    / \
        //          1  16   7

        GeneralTree<Integer> n12 = new GeneralTree<>(12);
        GeneralTree<Integer> n17 = new GeneralTree<>(17);
        GeneralTree<Integer> n9  = new GeneralTree<>(9);
        GeneralTree<Integer> n15 = new GeneralTree<>(15);
        GeneralTree<Integer> n10 = new GeneralTree<>(10);
        GeneralTree<Integer> n6  = new GeneralTree<>(6);
        GeneralTree<Integer> n8  = new GeneralTree<>(8);
        GeneralTree<Integer> n14 = new GeneralTree<>(14);
        GeneralTree<Integer> n18 = new GeneralTree<>(18);
        GeneralTree<Integer> n1  = new GeneralTree<>(1);
        GeneralTree<Integer> n16 = new GeneralTree<>(16);
        GeneralTree<Integer> n7  = new GeneralTree<>(7);

        n12.addChild(n17);
        n12.addChild(n9);
        n12.addChild(n15);
        n17.addChild(n10);
        n17.addChild(n6);
        n9.addChild(n8);
        n15.addChild(n14);
        n15.addChild(n18);
        n6.addChild(n1);
        n14.addChild(n16);
        n14.addChild(n7);

        Caminos c = new Caminos(n12);
        System.out.println("Camino a hoja más lejana: " + c.caminoAHojaMasLejana());
        // Esperado: [12, 17, 6, 1]  (el primero de longitud 3)
    }
}
