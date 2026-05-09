package tp3.ejercicio9;

import tp3.ejercicio1.GeneralTree;
import java.util.List;

public class ParcialArboles {

    // ===================== EJERCICIO 9 =====================

    public static boolean esDeSeleccion (GeneralTree<Integer> arbol){
        if (arbol == null || !arbol.isEmpty()){
            return false;
        }
        return recorrido(arbol);
    }   

    private static boolean recorrido (GeneralTree<Integer> nodo){
        // Si es hoja siempre cumple
        if (nodo.isLeaf()) {return true;}
        // Inicializo var que guarda valor de hijo min y lista que contiene los hijos
        int minHijos = Integer.MAX_VALUE;
        List<GeneralTree<Integer>> hijos = nodo.getChildren();

        // Recorro una sola vez los hijos
        for (GeneralTree<Integer> hijo: hijos){
            if (!recorrido(hijo)){
                return false;
            }

            if (hijo.getData() < minHijos){
                minHijos = hijo.getData();
            }   
        }
        return nodo.getData().equals(minHijos);  
    }


    // ===================== MAIN =====================

    public static void main(String[] args) {
        // Árbol 1 (debería retornar TRUE):
        //              12
        //            /    \
        //          12      25
        //         /  \      \
        //        35   12    25
        //            /  \
        //           35  14  12  33
        //                    \  | \ \
        //                    35 35 83 90 33

        GeneralTree<Integer> a12r = new GeneralTree<>(12);
        GeneralTree<Integer> a12i = new GeneralTree<>(12);
        GeneralTree<Integer> a25  = new GeneralTree<>(25);
        GeneralTree<Integer> a35a = new GeneralTree<>(35);
        GeneralTree<Integer> a12c = new GeneralTree<>(12);
        GeneralTree<Integer> a25b = new GeneralTree<>(25);
        GeneralTree<Integer> a35b = new GeneralTree<>(35);
        GeneralTree<Integer> a14  = new GeneralTree<>(14);
        GeneralTree<Integer> a12d = new GeneralTree<>(12);
        GeneralTree<Integer> a33  = new GeneralTree<>(33);
        GeneralTree<Integer> a35c = new GeneralTree<>(35);
        GeneralTree<Integer> a35d = new GeneralTree<>(35);
        GeneralTree<Integer> a83  = new GeneralTree<>(83);
        GeneralTree<Integer> a90  = new GeneralTree<>(90);
        GeneralTree<Integer> a33b = new GeneralTree<>(33);

        a12r.addChild(a12i);
        a12r.addChild(a25);
        a12i.addChild(a35a);
        a12i.addChild(a12c);
        a25.addChild(a25b);
        a12c.addChild(a35b);
        a12c.addChild(a14);
        a12c.addChild(a12d);
        a12c.addChild(a33);
        a12d.addChild(a35c);
        a12d.addChild(a35d);
        a12d.addChild(a83);
        a12d.addChild(a90);
        a12d.addChild(a33b);

        System.out.println("Árbol 1 (esperado true):  " + esDeSeleccion(a12r));

        // Árbol 2 (debería retornar FALSE):
        // Idéntico pero con raíz 18 en lugar de 12 interior → viola la propiedad
        GeneralTree<Integer> b12r = new GeneralTree<>(12);
        GeneralTree<Integer> b12i = new GeneralTree<>(12);
        GeneralTree<Integer> b25  = new GeneralTree<>(25);
        GeneralTree<Integer> b35a = new GeneralTree<>(35);
        GeneralTree<Integer> b18  = new GeneralTree<>(18); // <-- hijo mínimo es 14, no 18
        GeneralTree<Integer> b25b = new GeneralTree<>(25);
        GeneralTree<Integer> b35b = new GeneralTree<>(35);
        GeneralTree<Integer> b14  = new GeneralTree<>(14);
        GeneralTree<Integer> b18b = new GeneralTree<>(18);
        GeneralTree<Integer> b33  = new GeneralTree<>(33);

        b12r.addChild(b12i);
        b12r.addChild(b25);
        b12i.addChild(b35a);
        b12i.addChild(b18);
        b25.addChild(b25b);
        b18.addChild(b35b);
        b18.addChild(b14);  // min hijo de b18 es 14, pero b18.getData() = 18 → FALLA
        b18.addChild(b18b);
        b18b.addChild(b33);

        System.out.println("Árbol 2 (esperado false): " + esDeSeleccion(b12r));
    }
}
