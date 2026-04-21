package tp1.ejercicio9;

import java.util.Stack;

public class TestBalanceo {

    // a) Estructura elegida: Pila (Stack) - LIFO
    // Al encontrar abridor se apila, al encontrar cerrador se verifica el tope

    public static boolean estaBalanceado(String s) {
        Stack<Character> pila = new Stack<>();
        // TODO: recorrer s.toCharArray() y usar la pila
        return false;
    }

    public static void main(String[] args) {
        System.out.println(estaBalanceado("{()[()]}"));  // true
        System.out.println(estaBalanceado("([)]"));      // false
        System.out.println(estaBalanceado(""));           // true
        System.out.println(estaBalanceado("{(}"));        // false
    }
}
