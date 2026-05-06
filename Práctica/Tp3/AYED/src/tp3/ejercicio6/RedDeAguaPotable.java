package tp3.ejercicio6;

import tp3.ejercicio1.GeneralTree;

public class RedDeAguaPotable {

    private GeneralTree<Character> red;

    public RedDeAguaPotable(GeneralTree<Character> red) {
        this.red = red;
    }

    // ===================== EJERCICIO 6 =====================

    public double minimoCaudal(double caudal) {
        // TODO: calcular el mínimo caudal que llega a una hoja
        // Si el nodo es hoja → retornar el caudal que recibió (es una casa)
        // Si tiene hijos → dividir caudal entre la cantidad de hijos
        //                  y llamar recursivamente con cada hijo
        // Retornar el mínimo entre los resultados de todos los hijos
        return minimoCaudalAux(this.red, caudal);
    }

    private double minimoCaudalAux(GeneralTree<Character> nodo, double caudal) {
        // TODO: implementar aquí la lógica recursiva
        return 0.0;
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
