package tp1.ejercicio7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TestArrayList {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> lista = new ArrayList<>();

        // Opción B: List<Integer> lista = new LinkedList<>();

        System.out.println("Ingrese numeros (Ctrl+D para terminar):");
        
        // Mientras el usuario escriba números...
        while (sc.hasNextInt()) {
            lista.add(sc.nextInt()); // Los guardo en mi bolsa 

        System.out.println("Contenido de la lista:");
        
        // RECORRIDO: Para cada entero 'n' que esté en la lista
        for (Integer n : lista) {
            System.out.print(n + " ");
        }

        sc.close(); // Cierro el scanner
    }
}

}