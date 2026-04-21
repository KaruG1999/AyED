# Ejercicio 7: Listas, Referencias y Recursión

## Enunciado
Exploración profunda de `ArrayList` y `LinkedList` mediante:
1. Comparación de implementación y rendimiento.
2. Análisis de **copia superficial vs. profunda**.
3. Algoritmos de verificación (Capicúa) y sucesiones (Collatz).
4. Manipulación recursiva de listas (Inversión y Suma).
5. Combinación ordenada de estructuras.

## Conceptos Técnicos Clave

### 1. Polimorfismo en Listas (7b)
Aunque usemos `ArrayList` o `LinkedList`, si declaramos la variable como la interfaz `List<Integer> lista = new ...`, el resto del código es **idéntico**. Esto se debe a que ambas implementan los mismos métodos (`add`, `get`, `size`). La diferencia es interna (gestión de memoria).

### 2. El Problema de la Copia (7d)
Este es el punto más crítico del ejercicio. Al copiar una lista de objetos (como `Estudiante`):
- **Copia Superficial (Shallow Copy)**: Se crea una nueva lista, pero los elementos dentro siguen apuntando a los **mismos objetos** en el Heap. Si modificás un estudiante en la Lista A, se verá modificado en la Lista B.
- **Copia Profunda (Deep Copy)**: Se deben crear nuevas instancias de cada estudiante para que sean totalmente independientes.



### 3. Formas de Recorrer una Lista (7c)
Existen tres caminos principales en Java:
1. **For clásico**: `for(int i=0; i < lista.size(); i++)` (eficiente en ArrayList).
2. **For-each**: `for(Integer n : lista)` (sintaxis más limpia).
3. **Iterator**: `Iterator<Integer> it = lista.iterator()` (seguro para eliminar elementos mientras recorrés).

### 4. Sucesión de Collatz (7g)
El algoritmo recursivo para generar la secuencia $n/2$ (par) o $3n+1$ (impar) demuestra cómo una lista puede pasarse como acumulador en llamadas recursivas hasta alcanzar el **caso base** (n=1).


## Enunciado
Implementar un método recursivo que, dado un número `n`, devuelva una `List` con la sucesión completa hasta llegar a 1.

## Conceptos Técnicos Clave

### 1. Caso Base
Es la condición que detiene la recursión. En este algoritmo, el caso base es `n == 1`. Sin él, el programa entraría en un bucle infinito y lanzaría un `StackOverflowError`.

### 2. Caso Recursivo
Es la parte donde el problema se reduce. El método calcula el siguiente paso de la sucesión y se invoca a sí mismo, confiando en que esa nueva llamada eventualmente llegará al caso base.

### 3. Propagación de Resultados
Cada llamada recursiva espera la respuesta de la siguiente. Una vez que se alcanza el 1, las listas se van combinando hacia arriba en la pila de llamadas hasta retornar la lista completa al `main`.



## Métodos Destacados

### `esCapicua` (7f)
Se resuelve comparando el elemento en la posición `i` con el de la posición `size - 1 - i`. Si todos coinciden hasta la mitad, la secuencia es simétrica.

### `combinarOrdenado` (7j)
Utiliza la lógica de **Merge Sort**. Compara las cabezas de ambas listas y va insertando el menor en una tercera lista, asegurando orden sin necesidad de usar `Collections.sort()`.

## Análisis del Stack
| Operación | ArrayList | LinkedList |
| :--- | :--- | :--- |
| **Acceso Aleatorio** | $O(1)$ | $O(n)$ |
| **Insertar/Borrar** | $O(n)$ | $O(1)$ (en extremos) |
| **Recursión** | Riesgo de StackOverflow en listas grandes. | Requiere manejo de punteros o sublistas. |

## Diagnóstico de Viabilidad

- **Conclusión de 7d:** "Modificar un objeto en una lista afecta a todas las listas que tengan una referencia a dicho objeto". 🟢 Confirmado mediante Debugger.