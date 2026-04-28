package tp1.ejercicio7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import tp1.ejercicio3.Estudiante;

public class EjercicioListas {

    // d) crear lista con 3 estudiantes, copiarla, modificar y comparar
    public static void copiaYModificacion() {
        List<Estudiante> original = new ArrayList<>();

        Estudiante e1 = new Estudiante();
        e1.setNombre("Ana");
        original.add(e1);
        Estudiante e2 = new Estudiante();
        e2.setNombre("Luis");
        original.add(e2);
        Estudiante e3 = new Estudiante();
        e3.setNombre("Sol");
        original.add(e3);

        // Lista copia
        List<Estudiante> copia = new ArrayList<>(original);

        // imprimir
        System.out.println("Listas antes de modificación: ");
        original.forEach(e -> System.out.println("Original: "+ e.getNombre() ) );;
        copia.forEach(e-> System.out.println("Copia: " + e.getNombre() ) );
        
        
        // Modificar nombre Ana
        original.get(0).setNombre("Ana modificado");


        System.out.println(" Después de la modificación: ");
        original.forEach(e -> System.out.println("Original: "+ e.getNombre() ) );;
        copia.forEach(e-> System.out.println("Copia: " + e.getNombre() ) );

        // Ambas listas camban ya que la copia de una lista le pasa las referencias al objeto a la nueva y si modifico una, la otra igual 
    }

    // e) agregar estudiante solo si no existe (verificar por algun campo unico)
    public static void agregarSiNoExiste(List<Estudiante> lista, Estudiante nuevo) {
        if (!lista.contains(nuevo)) {
            lista.add(nuevo);
            System.out.println("Estudiante agregado.");
        } else {
            System.out.println("El estudiante ya existe.");
    }
}

    // f) verdadero si la lista es capicua
    public boolean esCapicua(ArrayList<Integer> lista) {
    boolean igual = true;
    int i = 0;
    int izq = 0;
    int der = lista.size() - 1;

    // Solo necesitamos recorrer hasta la mitad
    while (izq < der && igual) {
        // Comparamos los extremos hacia adentro
        if (!lista.get(izq).equals(lista.get(der))) {
            igual = false; // Encontramos una diferencia, se corta el bucle
        }
        izq++;
        der--;
        }
        return igual;
    }


    // h) invertir ArrayList recursivamente
    public void invertirArrayList(ArrayList<Integer> lista) {
    invertirAux(lista, 0, lista.size() - 1);
}

    private void invertirAux(ArrayList<Integer> lista, int inicio, int fin) {
        if (inicio < fin) {
            // Intercambio de valores (Swap)
            Integer temp = lista.get(inicio);
            lista.set(inicio, lista.get(fin));
            lista.set(fin, temp);
        
            // Llamada recursiva: achicamos el problema hacia el centro
            invertirAux(lista, inicio + 1, fin - 1);
    } 
    // Caso base implícito: si inicio >= fin, no hace nada y termina.
}

    // i) suma de LinkedList recursivamente
    public int sumarLinkedList(LinkedList<Integer> lista) {
    // Caso base: si la lista está vacía, la suma es 0
    if (lista.isEmpty()) {
        return 0;
    }
    
    // Paso recursivo:
    // 1. Extraigo el primer elemento
    Integer n = lista.removeFirst();
    
    // 2. Llamo recursivamente con lo que queda de la lista
    int sumaResto = sumarLinkedList(lista);
    
    // 3. Antes de devolver, vuelvo a poner el elemento para no romper la lista original
    lista.addFirst(n);
    
    // 4. Devuelvo la suma total
    return n + sumaResto;
}

    // j) combinar dos listas ordenadas en una lista ordenada
    public List<Integer> combinarOrdenado(List<Integer> lista1, List<Integer> lista2) {
        List<Integer> resultado = new ArrayList<>();
        combinarRecursivo(lista1, lista2, resultado, 0, 0);
        return resultado;
    }

    private void combinarRecursivo(List<Integer> l1, List<Integer> l2, List<Integer> res, int i, int j) {
        // Caso Base 1: Si terminó l1, agrego lo que queda de l2
        if (i == l1.size()) {
            while (j < l2.size()) res.add(l2.get(j++));
            return;
        }
        // Caso Base 2: Si terminó l2, agrego lo que queda de l1
        if (j == l2.size()) {
            while (i < l1.size()) res.add(l1.get(i++));
            return;
        }

        // Paso Recursivo: Comparo cabezas y elijo la menor
        if (l1.get(i) <= l2.get(j)) {
            res.add(l1.get(i));
            combinarRecursivo(l1, l2, res, i + 1, j);
        } else {
            res.add(l2.get(j));
            combinarRecursivo(l1, l2, res, i, j + 1);
        }
    }
}

    public static void main(String[] args) {
        EjercicioListas ej = new EjercicioListas();

        // Probar cada metodo aqui
        copiaYModificacion();

        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(2); nums.add(5); nums.add(2);
        System.out.println("esCapicua [2,5,2]: " + ej.esCapicua(nums));

        System.out.println("Collatz(6): " + ej.calcularSucesion(6));

        ArrayList<Integer> inv = new ArrayList<>();
        inv.add(1); inv.add(2); inv.add(3); inv.add(4);
        ej.invertirArrayList(inv);
        System.out.println("invertir [1,2,3,4]: " + inv);

        LinkedList<Integer> ll = new LinkedList<>();
        ll.add(1); ll.add(2); ll.add(3);
        System.out.println("suma [1,2,3]: " + ej.sumarLinkedList(ll));

        ArrayList<Integer> l1 = new ArrayList<>();
        l1.add(1); l1.add(3); l1.add(5);
        ArrayList<Integer> l2 = new ArrayList<>();
        l2.add(2); l2.add(4); l2.add(6);
        System.out.println("combinar: " + ej.combinarOrdenado(l1, l2));
    }
}
