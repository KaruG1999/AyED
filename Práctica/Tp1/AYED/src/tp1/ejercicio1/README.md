# Ejercicio 1: Iteración y Métodos de Clase

## Enunciado
Escriba tres métodos de clase (`static`) que reciban por parámetro dos números enteros `a` y `b` e impriman todos los números enteros comprendidos entre ellos (inclusive), uno por cada línea.
- **a.** Usando un bucle `for`.
- **b.** Usando un bucle `while`.
- **c.** Sin utilizar estructuras de control iterativas (Recursión).

## Conceptos Técnicos Clave

### 1. Métodos Estáticos (`static`)
Indica que el método pertenece a la **clase** y no a una instancia. 
- **Particularidad:** No requiere usar `new` para ser invocado. Se carga en memoria al inicio y es ideal para funciones de utilidad o algoritmos puros.

### 2. Estructuras de Control
- **`for`**: Ideal cuando el número de repeticiones es conocido (rango de `a` a `b`).
- **`while`**: Ejecuta el bloque de código mientras la condición sea verdadera. Requiere manejo manual del contador.

### 3. Recursión (Inciso C)
Es la técnica de resolver un problema haciendo que el método se llame a sí mismo con un estado modificado (`a + 1`).
- **Condición de parada:** Es fundamental para evitar un bucle infinito (en este caso, cuando `a > b`).