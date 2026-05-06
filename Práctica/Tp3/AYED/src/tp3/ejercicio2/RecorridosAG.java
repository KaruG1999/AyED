package tp3.ejercicio2;

import tp3.ejercicio1.GeneralTree;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RecorridosAG {

    // ===================== EJERCICIO 2a =====================

    public List<Integer> numerosImparesMayoresQuePreOrden(GeneralTree<Integer> a, Integer n) {
        // TODO: recorrido PreOrden — raíz primero, luego hijos
        List<Integer> resultado = new LinkedList<>();
        preOrden(a, n, resultado);
        return resultado;
    }

    private void preOrden(GeneralTree<Integer> nodo, Integer n, List<Integer> resultado) {
        // TODO: visitar raíz, luego recursión sobre cada hijo
        // Condición: dato es impar (% 2 != 0) Y mayor que n
    }

    public List<Integer> numerosImparesMayoresQueInOrden(GeneralTree<Integer> a, Integer n) {
        // TODO: recorrido InOrden — primer hijo, raíz, hijos restantes
        List<Integer> resultado = new LinkedList<>();
        inOrden(a, n, resultado);
        return resultado;
    }

    private void inOrden(GeneralTree<Integer> nodo, Integer n, List<Integer> resultado) {
        // TODO: recursión primer hijo → raíz → resto de hijos
    }

    public List<Integer> numerosImparesMayoresQuePostOrden(GeneralTree<Integer> a, Integer n) {
        // TODO: recorrido PostOrden — hijos primero, luego raíz
        List<Integer> resultado = new LinkedList<>();
        postOrden(a, n, resultado);
        return resultado;
    }

    private void postOrden(GeneralTree<Integer> nodo, Integer n, List<Integer> resultado) {
        // TODO: recursión sobre hijos → luego procesar raíz
    }

    public List<Integer> numerosImparesMayoresQuePorNiveles(GeneralTree<Integer> a, Integer n) {
        // TODO: recorrido por niveles con Queue
        List<Integer> resultado = new LinkedList<>();
        return resultado;
    }

    // ===================== EJERCICIO 2b — REFLEXIÓN =====================
    // Si estos métodos estuvieran en GeneralTree<T>, la firma cambiaría:
    //
    // El problema: GeneralTree<T> es genérico, no sabe que T es Integer.
    // Habría que restringir con: public List<T> imparesMayoresQue(T n)
    // y dentro no podríamos usar % ni > directamente.
    //
    // Solución habitual: usar Comparable<T> o castear, o pasar un Predicate<T>.
    // En la implementación habría que pedir que T extienda Number o Comparable.

    // ===================== MAIN =====================

    public static void main(String[] args) {
        //       1
        //      /|\
        //     3  2  7
        //    /|    \
        //   5  9    4

        GeneralTree<Integer> raiz = new GeneralTree<>(1);
        GeneralTree<Integer> n3   = new GeneralTree<>(3);
        GeneralTree<Integer> n2   = new GeneralTree<>(2);
        GeneralTree<Integer> n7   = new GeneralTree<>(7);
        GeneralTree<Integer> n5   = new GeneralTree<>(5);
        GeneralTree<Integer> n9   = new GeneralTree<>(9);
        GeneralTree<Integer> n4   = new GeneralTree<>(4);

        raiz.addChild(n3);
        raiz.addChild(n2);
        raiz.addChild(n7);
        n3.addChild(n5);
        n3.addChild(n9);
        n7.addChild(n4);

        RecorridosAG r = new RecorridosAG();
        int umbral = 2;

        System.out.println("PreOrden impares > " + umbral + ": " +
                r.numerosImparesMayoresQuePreOrden(raiz, umbral));
        System.out.println("InOrden impares > " + umbral + ": " +
                r.numerosImparesMayoresQueInOrden(raiz, umbral));
        System.out.println("PostOrden impares > " + umbral + ": " +
                r.numerosImparesMayoresQuePostOrden(raiz, umbral));
        System.out.println("PorNiveles impares > " + umbral + ": " +
                r.numerosImparesMayoresQuePorNiveles(raiz, umbral));
        // Todos deberían contener: 3, 5, 9, 7 (en distintos órdenes)
    }
}
