# Ejercicio 5: Retorno de Datos y Estado de Clase

## Enunciado
Calcular el valor máximo, mínimo y promedio de un arreglo de enteros en un único método, explorando tres variantes de diseño en Java:
- **a.** Uso de la sentencia `return`.
- **b.** Interacción mediante parámetros (sin usar arreglos).
- **c.** Sin parámetros ni `return` (uso de estado global/clase).

## Conceptos Técnicos Clave

### 1. El problema del "Retorno Único"
En Java, un método solo puede devolver un valor o un objeto. Para devolver tres datos (máx, mín, prom), en el **Inciso A** debemos recurrir a un **Objeto Contenedor** o un **Wrapper**.
- No podemos devolver tres `int` sueltos. Necesitamos crear una clase (ej. `Resultado`) que guarde los tres valores y devolver una instancia de esa clase.

### 2. Pasaje por Referencia de Objetos (Inciso B)
Como el enunciado prohíbe usar arreglos como parámetros para devolver datos, se deben pasar objetos como argumentos.
- **Técnica:** Se pasan objetos de una clase personalizada. El método modifica los atributos internos de esos objetos. Como Java pasa la **referencia** al objeto, los cambios realizados dentro del método persisten en el `main`.



### 3. Variables de Clase / Estado Global (Inciso C)
Para devolver valores sin `return` ni parámetros, se deben utilizar variables **estáticas de clase**. 
- **Riesgo Técnico:** Esto convierte al método en uno con "efecto secundario" (side effect). El método no nos da nada, pero altera el estado de la clase. Es una práctica que debe usarse con cuidado porque dificulta el debugging y el testing.



### 4. Algoritmos de Cálculo
- **Máximo/Mínimo:** Se inicializan con el primer elemento del arreglo y se comparan mediante un bucle.
- **Promedio:** Sumatoria acumulada dividida por `arreglo.length`. 
  - *Nota:* Se debe castear a `double` para no perder precisión decimal.

## Análisis del Stack
| Estrategia | Ventaja | Desventaja |
| :--- | :--- | :--- |
| **Return Objeto** | Limpio y funcional. | Requiere crear una clase extra. |
| **Parámetros** | Permite reutilizar objetos existentes. | Puede ser confuso si el objeto es inmutable. |
| **Variables de Clase** | Acceso directo desde cualquier método. | **Race conditions** y acoplamiento fuerte. |

## Diagnóstico de Viabilidad

- **Punto Crítico:** En el Inciso C, si dos hilos de ejecución llaman al método al mismo tiempo, los valores de max/min podrían mezclarse. 🟢 Recomendado: Inciso A (Objetos).