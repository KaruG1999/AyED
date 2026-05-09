package tp3.ejercicio10;

import tp3.ejercicio1.GeneralTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ParcialArboles {

    // ===================== EJERCICIO 10 =====================

    public static List<Integer> resolver(GeneralTree<Integer> arbol) {
        List<Integer> mejorCamino = new LinkedList<>();
        if (arbol != null && !arbol.isEmpty()){
            resolverAux(arbol, new ArrayList<>(), mejorCamino);
        }
        return mejorCamino;
    } 

    private static void resolverAux(GeneralTree<Integer> nodo,
                                    List<Integer> caminoActual,
                                    List<Integer> mejorCamino) {
        
         caminoActual.add(nodo.getData());
            // Si es una hoja (final) comparo con mejor camino y actualizo si es necesario
         if (nodo.isLeaf()) {
            if (caminoActual.size() > mejorCamino.size()){
                mejorCamino.clear();
                mejorCamino.addAll(caminoActual);
            }            
         } else {
            for (GeneralTree<Integer> hijo: nodo.getChildren()){
                resolverAux(hijo, caminoActual,mejorCamino);
            }
         }
         // Limpiar la lista caminoo actual
         caminoActual.remove(caminoActual.size()-1);
    }

    // ===================== MAIN =====================

    public static void main(String[] args) {
        // Árbol del enunciado (solo 0s y 1s):
        //                  1          nivel 0
        //               /  |  \
        //              0   1   1      nivel 1
        //             / \  |  / \
        //            1   1 1  0   0   nivel 2
        //           /|\    |
        //          0  0 1  0          nivel 3
        //                  |  \ \
        //                  1   0  0   nivel 4

        GeneralTree<Integer> r = new GeneralTree<>(1);
        GeneralTree<Integer> n0a = new GeneralTree<>(0);
        GeneralTree<Integer> n1a = new GeneralTree<>(1);
        GeneralTree<Integer> n1b = new GeneralTree<>(1);
        GeneralTree<Integer> n1c = new GeneralTree<>(1);
        GeneralTree<Integer> n1d = new GeneralTree<>(1);
        GeneralTree<Integer> n1e = new GeneralTree<>(1);
        GeneralTree<Integer> n0b = new GeneralTree<>(0);
        GeneralTree<Integer> n0c = new GeneralTree<>(0);
        GeneralTree<Integer> n0d = new GeneralTree<>(0);
        GeneralTree<Integer> n0e = new GeneralTree<>(0);
        GeneralTree<Integer> n1f = new GeneralTree<>(1);
        GeneralTree<Integer> n0f = new GeneralTree<>(0);
        GeneralTree<Integer> n1g = new GeneralTree<>(1);
        GeneralTree<Integer> n0g = new GeneralTree<>(0);
        GeneralTree<Integer> n0h = new GeneralTree<>(0);

        r.addChild(n0a);
        r.addChild(n1a);
        r.addChild(n1b);
        n0a.addChild(n1c);
        n0a.addChild(n1d);
        n1a.addChild(n1e);
        n1b.addChild(n0b);
        n1b.addChild(n0c);
        n1c.addChild(n0d);
        n1c.addChild(n0e);
        n1c.addChild(n1f);
        n1e.addChild(n0f);
        n1f.addChild(n1g);
        n1f.addChild(n0g);
        n1f.addChild(n0h);

        System.out.println("Camino filtrado máximo: " + resolver(r));
        // Esperado: [1, 1, 1]
        // (camino raíz→n0a→n1c→n1f: valor = 1*0 + 0*1 + 1*2 + 1*3 = 5; filtrado = [1,1,1])
    }
}
