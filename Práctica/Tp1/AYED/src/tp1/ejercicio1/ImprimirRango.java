package tp1.ejercicio1;

public class ImprimirRango {

    // a) Con for
    public static void imprimirFor(int a, int b) {
        // vamos a recorrer el rango desde a hasta b, inclusive
        for (int i = a; i <= b; i++) {
            System.out.println(i);
        }
    }

    // b) Con while
    public static void imprimirWhile(int a, int b) {
        int i = a;
        while ( i<=b) {
            System.out.println(i);
            i++;
        }
    }

    // c) Sin estructuras iterativas (recursion)
    public static void imprimirRecursivo(int a, int b) {
        if (a > b) return; // caso base: si es mayor sale de la recursion
        System.out.println(a);
        imprimirRecursivo ( a + 1, b ); // caso recursivo
    }

    public static void main(String[] args) {
        System.out.println("--- for ---");
        imprimirFor(3, 7);
        System.out.println("--- while ---");
        imprimirWhile(3, 7);
        System.out.println("--- recursivo ---");
        imprimirRecursivo(3, 7);
    }
}
