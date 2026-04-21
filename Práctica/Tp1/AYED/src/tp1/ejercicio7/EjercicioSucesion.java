package tp1.ejercicio7;

import java.util.ArrayList;
import java.util.List;

public class EjercicioSucesion {

    // f(n) = n/2 si n es par, 3n+1 si n es impar. Siempre llega a 1.
    public List<Integer> calcularSucesion(int n) {
    List<Integer> lista = new ArrayList<>();
    lista.add(n); // 1. Anoto mi número actual.

    if (n == 1) return lista; // 2. CASO BASE: Si soy el 1, devuelvo la lista y corto la cadena.

    // 3. LÓGICA: Calculo quién sigue según la regla.
    int siguiente = (n % 2 == 0) ? n / 2 : 3 * n + 1;

    // 4. RECURSIÓN: "Delego" el resto del trabajo al siguiente.
    List<Integer> resto = calcularSucesion(siguiente); 

    // 5. UNIÓN: Agrego todo lo que calculó el resto a mi lista.
    lista.addAll(resto); 

    return lista; // 6. Entrego el paquete completo al que me llamó.
}

    public static void main(String[] args) {
        EjercicioSucesion ej = new EjercicioSucesion();
        System.out.println("Sucesion(6): " + ej.calcularSucesion(6));
        // Esperado: [6, 3, 10, 5, 16, 8, 4, 2, 1]
    }
}
