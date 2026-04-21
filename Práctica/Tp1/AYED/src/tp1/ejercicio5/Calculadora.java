package tp1.ejercicio5;

public class Calculadora {

    // Variables de clase para el Inciso C (Estado global)
    public static int[] datosGlobales; // El arreglo debe estar aquí para el inciso C
    public static int maxStatic;
    public static int minStatic;
    public static int promedioStatic;

    // a. Usando return: Devolvemos un objeto que contiene los 3 valores
    public static Resultado calcularConReturn(int[] datos) {
        Resultado r = new Resultado();
        r.max = datos[0];
        r.min = datos[0];
        int suma = 0;
        for (int d : datos) {
            if (d > r.max) r.max = d;
            if (d < r.min) r.min = d;
            suma += d;
        }
        r.promedio = suma / datos.length;
        return r; 
    }

    // b. Usando parámetro: El método es void, pero modifica el objeto que le pasamos
    public static void calcularConParametro(int[] datos, Resultado r) {
        r.max = datos[0];
        r.min = datos[0];
        int suma = 0;
        for (int d : datos) {
            if (d > r.max) r.max = d;
            if (d < r.min) r.min = d;
            suma += d;
        }
        r.promedio = suma / datos.length;
    }

    // c. Sin parámetros ni return: Lee de variables static y escribe en variables static
    public static void calcularSinNada() {
        maxStatic = datosGlobales[0];
        minStatic = datosGlobales[0];
        int suma = 0;
        for (int d : datosGlobales) {
            if (d > maxStatic) maxStatic = d;
            if (d < minStatic) minStatic = d;
            suma += d;
        }
        promedioStatic = suma / datosGlobales.length;
    }

    public static void main(String[] args) {
        int[] misDatos = {10, 20, 30, 40, 50};

        // Prueba A
        Resultado resA = calcularConReturn(misDatos);
        System.out.println("A (Return): Max=" + resA.max + ", Min=" + resA.min + ", Prom=" + resA.promedio);

        // Prueba B
        Resultado resB = new Resultado(); // Creamos la "caja" vacía
        calcularConParametro(misDatos, resB); // El método la llena
        System.out.println("B (Parámetro): Max=" + resB.max + ", Min=" + resB.min + ", Prom=" + resB.promedio);

        // Prueba C
        datosGlobales = misDatos; // Seteamos la estantería común
        calcularSinNada(); // No le pasamos nada, no devuelve nada
        System.out.println("C (Static): Max=" + maxStatic + ", Min=" + minStatic + ", Prom=" + promedioStatic);
    }
}