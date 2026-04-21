# Ejercicio 2: Manejo de Arreglos y Scanner

## Enunciado
Escriba un método de clase que dado un número `n` devuelva un nuevo arreglo de tamaño `n` con los `n` primeros múltiplos enteros de `n` mayores o iguales que 1. 
Ejemplo: `f(5) = [5, 10, 15, 20, 25]`.
Debe permitir ingresar el valor de `n` por teclado utilizando `System.in` y la clase `Scanner`.

## Conceptos Técnicos Clave

### 1. System.in y Scanner
- **`System.in`**: Representa el flujo de entrada estándar (el teclado). Es el "cable" por donde viajan los datos crudos.
- **`Scanner`**: Es el "traductor". Envuelve a `System.in` para procesar los datos y transformarlos en tipos que Java entiende (`int`, `String`, etc.).

### 2. Métodos de Scanner
- **`nextInt()`**: Comando que le dice al programa: "Pausa la ejecución hasta que el usuario escriba un número entero y presione Enter".
- **`close()`**: Cierra el flujo de datos. Es como colgar el teléfono; libera los recursos del sistema operativo para evitar fugas de memoria.

### 3. Arreglos (`int[]`)
Un arreglo es una estructura de datos de **tamaño fijo**.
- **`new int[n]`**: Reserva un bloque exacto de memoria para `n` elementos. 
- **Índices**: En Java, los arreglos siempre empiezan en la posición `0`.



## Diagnóstico de Implementación

- **Punto Crítico:** Se utiliza la fórmula `n * (i + 1)` para asegurar que el primer múltiplo sea el mismo `n` (n * 1) y no cero.