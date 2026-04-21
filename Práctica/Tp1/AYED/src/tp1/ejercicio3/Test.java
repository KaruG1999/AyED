package tp1.ejercicio3;

public class Test {

    public static void main(String[] args) {

        // d) Arreglo de 2 estudiantes
        Estudiante[] estudiantes = new Estudiante[2];

        estudiantes[0] = new Estudiante();
        estudiantes[0].setNombre("Juan");
        estudiantes[0].setApellido("Perez");
        estudiantes[0].setComision("Comision 1");
        estudiantes[0].setEmail("juan@unlp.edu.ar");
        estudiantes[0].setDireccion("Calle 123");

        estudiantes[1] = new Estudiante();
        estudiantes[1].setNombre("Maria");
        estudiantes[1].setApellido("Gomez");
        estudiantes[1].setComision("Comision 2");
        estudiantes[1].setEmail("maria@unlp.edu.ar");
        estudiantes[1].setDireccion("Avenida 456");

        // d) Arreglo de 3 profesores
        Profesor[] profesores = new Profesor[3];

        profesores[0] = new Profesor();
        profesores[0].setNombre("Carlos");
        profesores[0].setApellido("Rodriguez");
        profesores[0].setEmail("carlos@unlp.edu.ar");
        profesores[0].setCatedra("AyED");
        profesores[0].setFacultad("Informatica UNLP");

        profesores[1] = new Profesor();
        profesores[1].setNombre("Ana");
        profesores[1].setApellido("Lopez");
        profesores[1].setEmail("ana@unlp.edu.ar");
        profesores[1].setCatedra("AyED");
        profesores[1].setFacultad("Informatica UNLP");

        profesores[2] = new Profesor();
        profesores[2].setNombre("Luis");
        profesores[2].setApellido("Martinez");
        profesores[2].setEmail("luis@unlp.edu.ar");
        profesores[2].setCatedra("AyED");
        profesores[2].setFacultad("Informatica UNLP");

        // e) breakpoint aqui
        for (Estudiante e : estudiantes) {
            System.out.println(e.tusDatos());
        }

        // e) breakpoint aqui
        for (Profesor p : profesores) {
            System.out.println(p.tusDatos());
        }
    }
}
