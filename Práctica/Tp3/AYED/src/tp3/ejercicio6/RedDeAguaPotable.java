package tp3.ejercicio6;

import java.util.List;

import tp3.ejercicio1.GeneralTree;

public class RedDeAguaPotable {

    private GeneralTree<Character> red;

    public RedDeAguaPotable(GeneralTree<Character> red) {
        this.red = red;
    }

    // ===================== EJERCICIO 6 =====================

    public double minimoCaudal(double caudal) {
        if (this.red != null && !this.red.isEmpty()) {
            return 0.0;
        }

        return minimoCaudalAux(this.red, caudal);
    }

    private double minimoCaudalAux(GeneralTree<Character> nodo, double caudal) {
        if (nodo.isLeaf()) { return caudal;}
        // Lista que contiene a los hijos y de ahí puedo obtener la cantidad 
        List<GeneralTree<Character>> hijos = nodo.getChildren();
        double caudalDividido = caudal / hijos.size();
        // Inicializo min y recorro hijos
        double min = Double.MAX_VALUE;
        for (GeneralTree<Character> hijo: hijos) {
            double caudalHijo = minimoCaudalAux(hijo, caudalDividido);
            // Actualizo el minimo
            if (caudalHijo < min){ min = caudalHijo; }

        }
        return min;
    }

    // ===================== MAIN =====================

    public static void main(String[] args) {
        // Árbol del enunciado (simplificado):
        //           raiz (caudal: 1000)
        //          / | \ \
        //         A  B  C  D    (caudal: 250 cada uno)
        //        /|    \|
        //       E  F   G  H     ...
        // El resultado debe ser 25.0

        GeneralTree<Character> raiz = new GeneralTree<>('R');
        GeneralTree<Character> A    = new GeneralTree<>('A');
        GeneralTree<Character> B    = new GeneralTree<>('B');
        GeneralTree<Character> C    = new GeneralTree<>('C');
        GeneralTree<Character> D    = new GeneralTree<>('D');
        GeneralTree<Character> E    = new GeneralTree<>('E');
        GeneralTree<Character> F    = new GeneralTree<>('F');
        GeneralTree<Character> G    = new GeneralTree<>('G');
        GeneralTree<Character> H    = new GeneralTree<>('H');
        GeneralTree<Character> I    = new GeneralTree<>('I');
        GeneralTree<Character> J    = new GeneralTree<>('J');
        GeneralTree<Character> K    = new GeneralTree<>('K');
        GeneralTree<Character> L    = new GeneralTree<>('L');
        GeneralTree<Character> M    = new GeneralTree<>('M');
        GeneralTree<Character> N    = new GeneralTree<>('N');

        raiz.addChild(A);
        raiz.addChild(B);
        raiz.addChild(C);
        raiz.addChild(D);
        A.addChild(E);
        A.addChild(F);
        B.addChild(G);
        B.addChild(H);
        C.addChild(I);
        C.addChild(J);
        C.addChild(K);
        D.addChild(L);
        I.addChild(M);
        I.addChild(N);

        RedDeAguaPotable red = new RedDeAguaPotable(raiz);
        System.out.println("Mínimo caudal: " + red.minimoCaudal(1000.0));
        // Esperado: 25.0
    }
}
