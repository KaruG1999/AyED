package tp3.ejercicio10;

import tp3.ejercicio1.GeneralTree;
import java.util.LinkedList;
import java.util.List;

public class ParcialArboles {

    // ===================== EJERCICIO 10 =====================

    public static List<Integer> resolver(GeneralTree<Integer> arbol) {
        // TODO: retornar el "camino filtrado de valor máximo"
        //
        // El "camino de valor máximo" se obtiene calculando para cada camino raíz→hoja:
        //    suma de (valor_del_nodo * nivel_del_nodo)   (nivel raíz = 0)
        //
        // "Filtrado" significa: agregar al resultado solo los nodos con valor 1 (los 0 se omiten)
        //
        // Si hay más de un camino con el mismo valor máximo → retornar el PRIMERO.
        //
        // Estrategia: DFS con backtracking, pasando el camino actual y el valor acumulado.
        // Comparar con el mejor valor encontrado hasta el momento.
        //
        // IMPORTANTE: no se puede hacer en dos pasadas (construir lista con 0/1 y luego filtrar).
        // Hay que filtrar directamente al construir el camino resultado.

        List<Integer> mejorCamino = new LinkedList<>();
        List<Integer> caminoActual = new LinkedList<>();
        resolverAux(arbol, 0, 0, caminoActual, mejorCamino, new int[]{Integer.MIN_VALUE});
        return mejorCamino;
    }

    private static void resolverAux(GeneralTree<Integer> nodo,
                                    int nivel,
                                    int valorAcumulado,
                                    List<Integer> caminoActual,
                                    List<Integer> mejorCamino,
                                    int[] mejorValor) {
        // TODO:
        // 1. Calcular el aporte de este nodo: nodo.getData() * nivel
        // 2. Actualizar el valor acumulado del camino
        // 3. Si el valor del nodo es 1: agregarlo al camino actual
        // 4. Si es hoja: comparar valorAcumulado con mejorValor[0]
        //               si es mayor → actualizar mejorValor y copiar caminoActual en mejorCamino
        // 5. Si no es hoja: recursión sobre cada hijo con nivel+1
        // 6. Backtracking: si había agregado el nodo, quitarlo
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
