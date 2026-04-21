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
        // TODO
    }

    // i) suma de LinkedList recursivamente
    public int sumarLinkedList(LinkedList<Integer> lista) {
        // TODO
        return 0;
    }

    // j) combinar dos listas ordenadas en una lista ordenada
    public ArrayList<Integer> combinarOrdenado(ArrayList<Integer> lista1, ArrayList<Integer> lista2) {
        // TODO
        return null;
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
