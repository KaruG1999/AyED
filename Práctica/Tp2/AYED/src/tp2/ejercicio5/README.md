# Ejercicio 5: ProfundidadDeArbolBinario — Suma por Profundidad

## Enunciado

Implementar una clase Java llamada **`ProfundidadDeArbolBinario`** que tiene como variable de instancia un árbol binario de números enteros y un método de instancia:

```java
public int sumaElementosProfundidad(int p)
```

El método devuelve la **suma de todos los nodos del árbol que se encuentren a la profundidad `p`** pasada como argumento.

---

## Conceptos Técnicos Clave

### 1. Profundidad vs. Nivel vs. Altura

| Término | Definición |
|---|---|
| **Profundidad de un nodo** | Distancia (en aristas) desde la raíz hasta ese nodo |
| **Nivel** | Sinónimo de profundidad |
| **Altura del árbol** | Máxima profundidad de cualquier hoja |

La raíz siempre está en profundidad `0`.

### 2. Estrategia Recursiva

La idea clave es **decrementar el parámetro `p`** en cada llamada recursiva hasta llegar a `0`:

```
sumaEnProfundidad(nodo, p):
    Si nodo es null → 0
    Si p == 0       → retornar nodo.getData()   ← llegamos al nivel buscado
    Sino            → sumaEnProfundidad(izq, p-1) + sumaEnProfundidad(der, p-1)
```

Cuando `p` llega a `0`, estamos en el nivel deseado y sumamos el dato de ese nodo.

### 3. Alternativa BFS

También se puede resolver con BFS llevando un contador de nivel, similar a `entreNiveles` del Ejercicio 2c. Sin embargo, la solución recursiva es más elegante.

---

## Ejemplo

```
          1          ← profundidad 0
         / \
        2   3        ← profundidad 1
       / \   \
      4   5   6      ← profundidad 2
         / \
        7   8        ← profundidad 3

sumaElementosProfundidad(0) = 1
sumaElementosProfundidad(1) = 2 + 3 = 5
sumaElementosProfundidad(2) = 4 + 5 + 6 = 15
sumaElementosProfundidad(3) = 7 + 8 = 15
```

### 4. Casos Borde
- `p` mayor que la altura del árbol → suma = 0 (no hay nodos a esa profundidad)
- `p = 0` → devuelve el dato de la raíz
