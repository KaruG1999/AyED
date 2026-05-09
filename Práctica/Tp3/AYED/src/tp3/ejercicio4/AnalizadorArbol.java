package tp3.ejercicio4;

import tp3.ejercicio1.GeneralTree;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AnalizadorArbol {

    // ===================== EJERCICIO 4b =====================

    public double devolverMaximoPromedio(GeneralTree<AreaEmpresa> arbol) {
        if (arbol != null && !arbol.isEmpty()) {
            return 0.0;
        }
        return recorridoNiveles(arbol);
    }

    private double recorridoNiveles (GeneralTree<AreaEmpresa> a){

        double maxProm = -1;
        Queue<GeneralTree<AreaEmpresa>> cola = new LinkedList<>();
        cola.add(a);

        if (!cola.isEmpty()) {
            int nodosEnNivel =cola.size();
            double sumaNivel=0;
            // Proceso nodos del mismo nivel
            for (int i=0; i<nodosEnNivel; i++) {
                GeneralTree<AreaEmpresa> nodoAux = cola.remove();

                // sumo tardanza de nivel actual
                sumaNivel += nodoAux.getData().getTardanza();
                // agrego hijos a la cola
                for (GeneralTree<AreaEmpresa> hijo: nodoAux.getChildren()){
                    cola.add(hijo);
                }
            }
            // Aca sumo y saco promedio de nivel
            double promedioActual = sumaNivel / nodosEnNivel;
            if (promedioActual > maxProm){
                maxProm = promedioActual;
            } 
        }
        return maxProm;
    }

    // ===================== MAIN =====================

    public static void main(String[] args) {
        // Árbol del enunciado:
        //          M(14)
        //        /   |   \
        //      J(13) K(25) L(10)
        //      /|   /|\    |\
        //    A(4)B(7)C(5)D(6)E(10)F(18)G(9)H(12)I(19)

        GeneralTree<AreaEmpresa> M = new GeneralTree<>(new AreaEmpresa("M", 14));
        GeneralTree<AreaEmpresa> J = new GeneralTree<>(new AreaEmpresa("J", 13));
        GeneralTree<AreaEmpresa> K = new GeneralTree<>(new AreaEmpresa("K", 25));
        GeneralTree<AreaEmpresa> L = new GeneralTree<>(new AreaEmpresa("L", 10));
        GeneralTree<AreaEmpresa> A = new GeneralTree<>(new AreaEmpresa("A", 4));
        GeneralTree<AreaEmpresa> B = new GeneralTree<>(new AreaEmpresa("B", 7));
        GeneralTree<AreaEmpresa> C = new GeneralTree<>(new AreaEmpresa("C", 5));
        GeneralTree<AreaEmpresa> D = new GeneralTree<>(new AreaEmpresa("D", 6));
        GeneralTree<AreaEmpresa> E = new GeneralTree<>(new AreaEmpresa("E", 10));
        GeneralTree<AreaEmpresa> F = new GeneralTree<>(new AreaEmpresa("F", 18));
        GeneralTree<AreaEmpresa> G = new GeneralTree<>(new AreaEmpresa("G", 9));
        GeneralTree<AreaEmpresa> H = new GeneralTree<>(new AreaEmpresa("H", 12));
        GeneralTree<AreaEmpresa> I = new GeneralTree<>(new AreaEmpresa("I", 19));

        M.addChild(J);
        M.addChild(K);
        M.addChild(L);
        J.addChild(A);
        J.addChild(B);
        K.addChild(C);
        K.addChild(D);
        K.addChild(E);
        K.addChild(F);
        K.addChild(G);
        L.addChild(H);
        L.addChild(I);

        AnalizadorArbol a = new AnalizadorArbol();
        System.out.println("Máximo promedio: " + a.devolverMaximoPromedio(M));
        // Esperado: 16.0  (nivel 1: (13+25+10)/3 = 16)
    }
}
