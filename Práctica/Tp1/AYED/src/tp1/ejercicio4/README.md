# Ejercicio 4: Pasaje de Parámetros en Java

## Enunciado
Analizar y ejecutar un programa de intercambio de valores (`swap`) utilizando tipos primitivos (`int`) y objetos envueltos (`Integer`) para comprender cómo se comporta la memoria en Java.

## Conceptos Técnicos Clave

### 1. Pasaje por Valor (Pass-by-value)
En Java, **siempre** se pasa una copia.
- **Primitivos (`int`)**: Se pasa una copia del valor numérico.
- **Objetos (`Integer`, `String`)**: Se pasa una copia de la **referencia** (la dirección de memoria). No se pasa el objeto real.



### 2. Memoria Stack vs. Heap
- **Stack (Pila)**: Aquí viven las variables locales y las llamadas a métodos. Cuando `swap1` se ejecuta, crea sus propias variables `x` e `y` en su propio bloque de la pila. Al terminar el método, ese bloque desaparece.
- **Heap (Montículo)**: Aquí viven los objetos (como los `Integer`). Aunque el método tenga una copia de la dirección de memoria, si reasignamos la variable local (`x = y`), solo estamos moviendo un puntero local, no el objeto en el Heap.



### 3. Inmutabilidad
Los objetos como `Integer` y `String` son **inmutables**. No existe un método `setValue()` para cambiarlos. La única forma de "cambiarlos" es crear un objeto nuevo, lo que refuerza que el `swap` no afecte a la variable original.

## Resumen del Diagnóstico
- **Problema:** El método `swap` intenta modificar variables externas reasignando variables locales.
- **Resultado:** El cambio es local al método y se pierde al retornar al `main`.
- **Estado:** 🟢 Comportamiento esperado según la especificación de la JVM.