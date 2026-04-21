# AyED — TP1: Estructuras de control, listas y recursión
`Algoritmos y Estructuras de Datos 2026 | UNLP Informática`

> 🔗 Hilo previo: Java OOP, clases, arreglos, pasaje de parámetros, ArrayList/LinkedList, recursión.

---

## Ejercicio 1 — Imprimir enteros entre a y b

**¿De qué concepto sale?** → Estructuras de control iterativas e iteración sin loop (recursión)

```java
package tp1.ejercicio1;

public class ImprimirRango {

    // a) Con for
    public static void imprimirFor(int a, int b) {
        for (int i = a; i <= b; i++) {
            System.out.println(i);
        }
    }

    // b) Con while
    public static void imprimirWhile(int a, int b) {
        int i = a;
        while (i <= b) {
            System.out.println(i);
            i++;
        }
    }

    // c) Sin estructuras iterativas → recursión
    public static void imprimirRecursivo(int a, int b) {
        if (a > b) return;          // caso base
        System.out.println(a);
        imprimirRecursivo(a + 1, b); // caso recursivo
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
```

**Por qué:** El inciso c) no puede usar `for`, `while` ni `do-while`, así que la única salida es la recursión. El caso base es `a > b` (cuando pasamos el límite, se corta).

---

## Ejercicio 2 — Primeros n múltiplos de n

**¿De qué concepto sale?** → Arrays, Scanner, métodos static

```java
package tp1.ejercicio2;

import java.util.Scanner;

public class Multiplos {

    public static int[] multiplosDeN(int n) {
        int[] resultado = new int[n];
        for (int k = 1; k <= n; k++) {
            resultado[k - 1] = n * k;
        }
        return resultado;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese n: ");
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int[] multiplos = multiplosDeN(n);
            System.out.print("f(" + n + ") = [");
            for (int i = 0; i < multiplos.length; i++) {
                System.out.print(multiplos[i]);
                if (i < multiplos.length - 1) System.out.print(", ");
            }
            System.out.println("]");
            System.out.print("Ingrese n (Ctrl+D para salir): ");
        }
        sc.close();
    }
}
```

**Por qué:** `resultado[k-1] = n * k` porque el array va de índice 0 a n-1, pero los múltiplos arrancan en k=1. `sc.hasNextInt()` permite ingresar varios valores hasta que se cierra la entrada.

---

## Ejercicio 3 — Estudiante, Profesor, Test

**¿De qué concepto sale?** → Clases, getters/setters, arreglos de objetos

```java
package tp1.ejercicio3;

// Estudiante.java
public class Estudiante {
    private String nombre;
    private String apellido;
    private String comision;
    private String email;
    private String direccion;

    // getters y setters (generados con Eclipse: Source → Generate Getters and Setters)
    public String getNombre()            { return nombre; }
    public void setNombre(String n)      { this.nombre = n; }
    public String getApellido()          { return apellido; }
    public void setApellido(String a)    { this.apellido = a; }
    public String getComision()          { return comision; }
    public void setComision(String c)    { this.comision = c; }
    public String getEmail()             { return email; }
    public void setEmail(String e)       { this.email = e; }
    public String getDireccion()         { return direccion; }
    public void setDireccion(String d)   { this.direccion = d; }

    public String tusDatos() {
        return "Estudiante: " + getNombre() + " " + getApellido()
             + " | Comisión: " + getComision()
             + " | Email: " + getEmail()
             + " | Dir: " + getDireccion();
    }
}
```

```java
// Profesor.java
public class Profesor {
    private String nombre;
    private String apellido;
    private String email;
    private String catedra;
    private String facultad;

    public String getNombre()           { return nombre; }
    public void setNombre(String n)     { this.nombre = n; }
    public String getApellido()         { return apellido; }
    public void setApellido(String a)   { this.apellido = a; }
    public String getEmail()            { return email; }
    public void setEmail(String e)      { this.email = e; }
    public String getCatedra()          { return catedra; }
    public void setCatedra(String c)    { this.catedra = c; }
    public String getFacultad()         { return facultad; }
    public void setFacultad(String f)   { this.facultad = f; }

    public String tusDatos() {
        return "Profesor: " + getNombre() + " " + getApellido()
             + " | Cátedra: " + getCatedra()
             + " | Facultad: " + getFacultad()
             + " | Email: " + getEmail();
    }
}
```

```java
// Test.java
public class Test {
    public static void main(String[] args) {
        // Arreglo de 2 estudiantes
        Estudiante[] estudiantes = new Estudiante[2];
        estudiantes[0] = new Estudiante();
        estudiantes[0].setNombre("Karen");
        estudiantes[0].setApellido("López");
        estudiantes[0].setComision("A");
        estudiantes[0].setEmail("karen@unlp.edu.ar");
        estudiantes[0].setDireccion("Calle 1 N°123");

        estudiantes[1] = new Estudiante();
        estudiantes[1].setNombre("Juan");
        estudiantes[1].setApellido("Pérez");
        estudiantes[1].setComision("B");
        estudiantes[1].setEmail("juan@unlp.edu.ar");
        estudiantes[1].setDireccion("Calle 2 N°456");

        // Arreglo de 3 profesores
        Profesor[] profesores = new Profesor[3];
        profesores[0] = new Profesor();
        profesores[0].setNombre("Alejandra");
        profesores[0].setApellido("Schiavoni");
        profesores[0].setCatedra("AyED");
        profesores[0].setFacultad("Informática UNLP");
        profesores[0].setEmail("ales@info.unlp.edu.ar");
        // (completar profesores[1] y [2] similarmente)

        // e) breakpoints acá ↓
        for (Estudiante e : estudiantes) {   // ← breakpoint aquí
            System.out.println(e.tusDatos());
        }
        for (Profesor p : profesores) {      // ← breakpoint aquí
            if (p != null) System.out.println(p.tusDatos());
        }
    }
}
```

**Por qué:** `new Estudiante[2]` crea el arreglo pero no los objetos — hay que hacer `new Estudiante()` para cada posición. El `if (p != null)` en profesores es por si no se cargaron todos.

---

## Ejercicio 4 — Pasaje de parámetros: análisis de SwapValores

**¿De qué concepto sale?** → Pasaje por valor, inmutabilidad de wrappers

**a) Análisis sin ejecutar:**

```
a=1  b=2   → swap1(a, b): trabaja con copias de int. a y b en main NO cambian.
c=3  d=4   → swap2(c, d): trabaja con copias de la referencia Integer.
             Integer es INMUTABLE: x = y dentro del método crea un nuevo objeto
             y lo asigna a la copia local. c y d en main NO cambian.
```

**Salida esperada:**
```
a=1 b=2
c=3 d=4
```

**Por qué:** `swap1` recibe copias de `int` — modificarlas no afecta al original. `swap2` recibe copias de referencias a `Integer`, pero `Integer` es inmutable: cuando hace `x = y`, no modifica el objeto `Integer` sino que reasigna la variable local. En ningún caso se modifica el estado de `c` y `d` en `main`.

**c) Debug — ¿coinciden x, y con lo impreso?** Los valores de `x` e `y` dentro de `swap1` y `swap2` SÍ cambian (el swap ocurre dentro del método), pero esos cambios no se propagan afuera. La consola imprime los valores originales de `a`, `b`, `c`, `d`.

---

## Ejercicio 5 — Máximo, mínimo y promedio de un arreglo

**¿De qué concepto sale?** → 3 formas de devolver múltiples resultados desde un método

```java
package tp1.ejercicio5;

public class Calculadora {

    // a) Retorno por return → devuelve un objeto contenedor
    public static int[] maxMinPromedio(int[] datos) {
        int max = datos[0], min = datos[0], suma = 0;
        for (int d : datos) {
            if (d > max) max = d;
            if (d < min) min = d;
            suma += d;
        }
        // [max, min, promedio]
        return new int[]{max, min, suma / datos.length};
    }

    // b) Retorno por parámetro → el parámetro NO puede ser de tipo arreglo (dice el enunciado)
    //    → se crea una clase contenedora Resultado como objeto de salida
    static class Resultado {
        public int max;
        public int min;
        public int promedio;
    }

    public static void maxMinPromedioParam(int[] datos, Resultado r) {
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

    // c) Sin return ni parámetros → variables de clase (static)
    public static int maxStatic;
    public static int minStatic;
    public static int promedioStatic;

    public static void calcularStatic(int[] datos) {
        maxStatic = datos[0];
        minStatic = datos[0];
        int suma = 0;
        for (int d : datos) {
            if (d > maxStatic) maxStatic = d;
            if (d < minStatic) minStatic = d;
            suma += d;
        }
        promedioStatic = suma / datos.length;
    }

    public static void main(String[] args) {
        int[] datos = {3, 7, 1, 9, 4};

        // a)
        int[] res = maxMinPromedio(datos);
        System.out.println("a) max=" + res[0] + " min=" + res[1] + " prom=" + res[2]);

        // b)
        Resultado r = new Resultado();
        maxMinPromedioParam(datos, r);
        System.out.println("b) max=" + r.max + " min=" + r.min + " prom=" + r.promedio);

        // c)
        calcularStatic(datos);
        System.out.println("c) max=" + maxStatic + " min=" + minStatic + " prom=" + promedioStatic);
    }
}
```

**Por qué:** El inciso b) prohíbe explícitamente usar un arreglo como parámetro. La solución es crear un objeto `Resultado` — al pasarlo por parámetro, Java copia la referencia, y el método puede modificar el estado del objeto (sus campos). El inciso c) usa variables `static` como "canal de salida" — no recibe parámetros ni devuelve nada.

---

## Ejercicio 6 — Análisis ArrayList vs LinkedList

**¿De qué concepto sale?** → Teoría de estructuras de datos lineales

**a) ArrayList mejor rendimiento:**
Cuando se accede frecuentemente por índice (`get(i)`) — `ArrayList` lo hace en O(1) porque internamente es un array. `LinkedList` necesita recorrer desde el inicio → O(n).

**b) LinkedList más eficiente:**
Cuando se insertan o eliminan elementos al inicio o en el medio frecuentemente. `LinkedList` solo cambia punteros → O(1). `ArrayList` debe desplazar todos los elementos a la derecha → O(n).

**c) Diferencia en memoria:**
`ArrayList` almacena solo los elementos (más algo de capacidad extra reservada). `LinkedList` almacena cada elemento en un nodo con dos referencias extra (anterior y siguiente) → mayor overhead por elemento.

**d) Cuándo usar cada una:**
- `ArrayList`: lectura frecuente, acceso aleatorio por índice, pocas inserciones/eliminaciones en el medio.
- `LinkedList`: muchas inserciones/eliminaciones en los extremos (implementar colas, pilas), iteración secuencial sin acceso por índice.

---

## Ejercicio 7 — Uso de ArrayList y LinkedList

**¿De qué concepto sale?** → API de listas Java, recursión sobre listas

### a) TestArrayList

```java
package tp1.ejercicio7;

import java.util.ArrayList;
import java.util.Scanner;

public class TestArrayList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> lista = new ArrayList<>();

        System.out.println("Ingrese números (Ctrl+D para terminar):");
        while (sc.hasNextInt()) {
            lista.add(sc.nextInt());
        }

        System.out.println("Contenido de la lista:");
        for (Integer n : lista) {
            System.out.println(n);
        }
        sc.close();
    }
}
```

### b) LinkedList vs ArrayList en implementación

La implementación sería idéntica — ambas implementan `List<Integer>`, así que solo cambiaría la línea de declaración:
```java
LinkedList<Integer> lista = new LinkedList<>();
```
La diferencia es interna (estructura de nodos vs array) pero la API es la misma. Para este caso de uso (agregar al final e iterar) el rendimiento es similar.

### c) Otras formas de recorrer

```java
// 1. For-each (más común)
for (Integer n : lista) { ... }

// 2. For con índice
for (int i = 0; i < lista.size(); i++) { lista.get(i); }

// 3. Iterator explícito
Iterator<Integer> it = lista.iterator();
while (it.hasNext()) { it.next(); }

// 4. forEach con lambda (Java 8+)
lista.forEach(n -> System.out.println(n));
```

### d) Copia de lista y shallow copy

```java
public static void copiaYModificacion() {
    ArrayList<Estudiante> original = new ArrayList<>();
    Estudiante e1 = new Estudiante(); e1.setNombre("Ana"); original.add(e1);
    Estudiante e2 = new Estudiante(); e2.setNombre("Luis"); original.add(e2);
    Estudiante e3 = new Estudiante(); e3.setNombre("Sol"); original.add(e3);

    // Copia superficial
    ArrayList<Estudiante> copia = new ArrayList<>(original);

    System.out.println("--- Antes de modificar ---");
    original.forEach(e -> System.out.println("original: " + e.getNombre()));
    copia.forEach(e -> System.out.println("copia:    " + e.getNombre()));

    // Modificar un objeto
    original.get(0).setNombre("Ana Modificada");

    System.out.println("--- Después de modificar ---");
    original.forEach(e -> System.out.println("original: " + e.getNombre()));
    copia.forEach(e -> System.out.println("copia:    " + e.getNombre()));
    // Conclusión: copia.get(0) también cambió → shallow copy comparte objetos
}
```

**Formas de copiar una lista:**
- `new ArrayList<>(original)` → shallow copy (comparte objetos)
- Copiar elemento por elemento creando nuevos objetos → deep copy (independiente)
- `Collections.copy(dest, src)` → shallow copy, requiere que dest ya tenga el tamaño

**Conclusión:** con shallow copy, ambas listas apuntan a los **mismos objetos** en heap. Modificar el estado de un objeto se ve en ambas listas.

### e) Agregar estudiante con control de unicidad

```java
public static void agregarSiNoExiste(ArrayList<Estudiante> lista, Estudiante nuevo) {
    // Verificar por email (o cualquier campo único)
    boolean existe = false;
    for (Estudiante e : lista) {
        if (e.getEmail().equals(nuevo.getEmail())) {
            existe = true;
            break;
        }
    }
    if (!existe) {
        lista.add(nuevo);
        System.out.println("Estudiante agregado.");
    } else {
        System.out.println("El estudiante ya existe en la lista.");
    }
}
```

### f) ¿Es capicúa?

```java
public boolean esCapicua(ArrayList<Integer> lista) {
    int inicio = 0;
    int fin = lista.size() - 1;
    while (inicio < fin) {
        if (!lista.get(inicio).equals(lista.get(fin))) return false;
        inicio++;
        fin--;
    }
    return true;
}
// [2, 5, 2] → true | [4, 5, 6, 3, 4] → false
```

### g) Sucesión de Collatz (recursiva)

**El enunciado exige la clase `EjercicioSucesion` con ese nombre exacto** — no va dentro de `EjercicioListas`.

La función f(n) = n/2 si par, 3n+1 si impar. Siempre llega a 1.

```java
package tp1.ejercicio7;

import java.util.ArrayList;
import java.util.List;

public class EjercicioSucesion {

    public List<Integer> calcularSucesion(int n) {
        List<Integer> lista = new ArrayList<>();
        lista.add(n);
        if (n == 1) return lista;                        // caso base
        int siguiente;
        if (n % 2 == 0) {
            siguiente = n / 2;
        } else {
            siguiente = 3 * n + 1;
        }
        List<Integer> resto = calcularSucesion(siguiente); // caso recursivo
        for (Integer elem : resto) {
            lista.add(elem);
        }
        return lista;
    }
}
// calcularSucesion(6) → [6, 3, 10, 5, 16, 8, 4, 2, 1]
```

**Por qué el for en vez de addAll:** `addAll` no está en la API vista en teoría. El for-each recorre la lista recursiva y agrega elemento a elemento — mismo resultado, solo con lo visto en clase.

### h) Invertir ArrayList recursivamente

```java
public void invertirArrayList(ArrayList<Integer> lista) {
    if (lista.size() <= 1) return;      // caso base
    Integer primero = lista.remove(0);  // saca el primero
    invertirArrayList(lista);           // invierte el resto
    lista.add(primero);                 // pone el primero al final
}
// [1, 2, 3, 4] → [4, 3, 2, 1]
```

### i) Suma de LinkedList recursivamente

```java
public int sumarLinkedList(LinkedList<Integer> lista) {
    if (lista.isEmpty()) return 0;          // caso base
    int primero = lista.removeFirst();      // extrae el primero
    int resto = sumarLinkedList(lista);     // suma el resto
    lista.addFirst(primero);               // restaura la lista
    return primero + resto;
}
```

**Por qué restaurar la lista:** `removeFirst` modifica la lista. Si el llamador la necesita intacta después, hay que devolverla a su estado original. Si no importa, se puede omitir el `addFirst`.

### j) Combinar dos listas ordenadas

```java
public ArrayList<Integer> combinarOrdenado(ArrayList<Integer> lista1,
                                            ArrayList<Integer> lista2) {
    ArrayList<Integer> resultado = new ArrayList<>();
    int i = 0, j = 0;
    while (i < lista1.size() && j < lista2.size()) {
        if (lista1.get(i) <= lista2.get(j)) {
            resultado.add(lista1.get(i++));
        } else {
            resultado.add(lista2.get(j++));
        }
    }
    // agregar los elementos restantes de la lista que no terminó
    while (i < lista1.size()) resultado.add(lista1.get(i++));
    while (j < lista2.size()) resultado.add(lista2.get(j++));
    return resultado;
}
// [1,3,5] + [2,4,6] → [1,2,3,4,5,6]
```

---

## Ejercicio 8 — Cola, Cola Circular, Cola Doble

**¿De qué concepto sale?** → Herencia, uso de LinkedList como estructura interna

### Sequence (clase abstracta — base del diagrama)

El diagrama muestra que `Queue` extiende `Sequence`. Hay que crearla primero.

```java
package tp1.ejercicio8;

public abstract class Sequence {
    public abstract int size();
    public abstract boolean isEmpty();
}
```

### a) Queue\<T\>

```java
package tp1.ejercicio8;

import java.util.LinkedList;
import java.util.List;

public class Queue<T> extends Sequence {
    protected List<T> data;

    public Queue() {
        data = new LinkedList<>();
    }

    public void enqueue(T dato) {
        data.add(dato);                     // agrega al final
    }

    public T dequeue() {
        if (isEmpty()) throw new RuntimeException("Cola vacía");
        return data.remove(0);             // elimina y retorna el primero
    }

    public T head() {
        if (isEmpty()) throw new RuntimeException("Cola vacía");
        return data.get(0);
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public int size() {
        return data.size();
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
```

### b) CircularQueue\<T\>

```java
public class CircularQueue<T> extends Queue<T> {

    // shift: saca el elemento del frente y lo encola al final (rota)
    public T shift() {
        T elemento = dequeue();    // saca del frente
        enqueue(elemento);         // lo pone al final
        return elemento;
    }
}
```

### c) DoubleEndedQueue\<T\>

```java
public class DoubleEndedQueue<T> extends Queue<T> {

    // Encolar al inicio (además del enqueue normal que es al final)
    public void enqueueFirst(T dato) {
        data.add(0, dato);
    }
}
```

---

## Ejercicio 9 — Balanceo de paréntesis

**¿De qué concepto sale?** → Pila (Stack), procesamiento de strings carácter por carácter

### a) Estructura de datos: Pila (Stack)

Se usa una **pila** (LIFO). Al encontrar un abridor (`(`, `[`, `{`) se apila. Al encontrar un cerrador (`)`, `]`, `}`), se verifica que el tope de la pila sea el abridor correspondiente y se desapila. Si al final la pila queda vacía, el string está balanceado.

```
"{( ) [ ( ) ] }" → procesar cada char:
  { → apilar   stack: ['{']
  ( → apilar   stack: ['{', '(']
  ) → desapilar y verificar '(' == tope ✓  stack: ['{']
  [ → apilar   stack: ['{', '[']
  ( → apilar   stack: ['{', '[', '(']
  ) → desapilar '(' ✓  stack: ['{', '[']
  ] → desapilar '[' ✓  stack: ['{']
  } → desapilar '{' ✓  stack: []
  Resultado: stack vacío → BALANCEADO ✓
```

### b) Implementación

```java
package tp1.ejercicio9;

import java.util.Stack;

public class TestBalanceo {

    public static boolean estaBalanceado(String s) {
        Stack<Character> pila = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                pila.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (pila.isEmpty()) return false;
                char tope = pila.pop();
                if (c == ')' && tope != '(') return false;
                if (c == ']' && tope != '[') return false;
                if (c == '}' && tope != '{') return false;
            }
        }
        return pila.isEmpty();  // balanceado solo si no quedó nada en la pila
    }

    public static void main(String[] args) {
        System.out.println(estaBalanceado("{()[()]}"));  // true
        System.out.println(estaBalanceado("([)]"));      // false
        System.out.println(estaBalanceado(""));           // true (string vacío)
    }
}
```

---

## Ejercicio 10 — Cola de banco con prioridad

**¿De qué concepto sale?** → Selección de estructura de datos según el problema

Se usaría una **`DoubleEndedQueue` (cola con dos extremos)** o una **cola de prioridad** (`PriorityQueue`).

La razón: la ley obliga a atender primero a embarazadas, personas con movilidad reducida y mayores de 70 años. Una cola FIFO simple no sirve porque no distingue prioridades. La solución es una cola donde los casos prioritarios se insertan al frente (`enqueueFirst`) y los normales al final (`enqueue`). Al atender, siempre se toma del frente (`dequeue`), garantizando que los prioritarios sean atendidos antes.

Alternativamente, `PriorityQueue` de Java con un `Comparator` que ordene por categoría de prioridad.

---

## Ejercicio 11 — Paradas de colectivo

**¿De qué concepto sale?** → Selección de estructura de datos según el problema

Se usaría una **`CircularQueue`** (cola circular).

La razón: cada línea de colectivos recorre sus paradas de forma **repetida y cíclica** durante el día — llega al final del recorrido y vuelve a empezar desde el principio. Una `CircularQueue` modela exactamente esto: al hacer `shift()`, la parada que acaba de ser "atendida" (el colectivo pasó por ella) se mueve al final de la cola, lista para la próxima vuelta.

---

## 🔑 Patrones que se repiten en este TP

| Situación | Patrón |
|---|---|
| Iteración sin `for`/`while` | Recursión con caso base explícito |
| Devolver múltiples valores | a) objeto/arreglo de retorno, b) arreglo parámetro, c) variables static |
| Swap en Java | Nunca funciona con primitivos ni wrappers — siempre se trabaja con copias |
| Copiar lista | `new ArrayList<>(original)` es shallow — modifica objetos compartidos |
| Balanceo de paréntesis | Pila: apilar abridores, desapilar y verificar al encontrar cerradores |
| Elegir ArrayList vs LinkedList | Acceso por índice → ArrayList; inserción en extremos → LinkedList |
