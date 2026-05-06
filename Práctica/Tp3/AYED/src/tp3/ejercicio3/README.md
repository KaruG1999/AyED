# Ejercicio 3: altura(), nivel() y ancho() — en GeneralTree

## Enunciado

Implementar en la clase **`GeneralTree`** (ubicada en `tp3.ejercicio1`) los siguientes métodos:

**a) `public int altura()`**
Devuelve la altura del árbol, es decir, la longitud del camino más largo desde la raíz hasta una hoja.

**b) `public int nivel(T dato)`**
Devuelve la profundidad (nivel) del dato en el árbol. El nivel de un nodo es la longitud del único camino desde la raíz hasta ese nodo. Si el dato no existe, devuelve -1.

**c) `public int ancho()`**
La amplitud (ancho) del árbol es la cantidad de nodos que se encuentran en el nivel con la mayor cantidad de nodos.

> Todos los TODOs ya están marcados en `GeneralTree.java` del ejercicio 1.

---

## Conceptos Técnicos Clave

### 1. Altura — Estrategia Recursiva (PostOrden)

La altura de un árbol general se calcula **después** de conocer la altura de los hijos (PostOrden = procesar hijos antes que la raíz).

```
altura(nodo):
    Si es hoja → return 0
    maxHijo = max(altura(hijo1), altura(hijo2), ..., altura(hijoN))
    return 1 + maxHijo
```

```java
public int altura() {
    if (this.isLeaf()) return 0;
    int max = 0;
    for (GeneralTree<T> hijo : this.getChildren()) {
        int h = hijo.altura();
        if (h > max) max = h;
    }
    return 1 + max;
}
```

### 2. Nivel — Estrategia Recursiva (PreOrden)

Se sabe el nivel actual y se busca el dato. Cuando se encuentra, se retorna ese nivel.

```
nivel(nodo, dato, nivelActual):
    Si nodo.getData() == dato → return nivelActual
    Para cada hijo:
        res = nivel(hijo, dato, nivelActual + 1)
        Si res != -1 → return res    // lo encontró en ese subárbol
    return -1   // no encontrado
```

**Importante:** se usa un método privado auxiliar que recibe el nivel acumulado.

```java
public int nivel(T dato) {
    return nivelAux(this, dato, 0);
}

private int nivelAux(GeneralTree<T> nodo, T dato, int nivelActual) {
    if (nodo.getData().equals(dato)) return nivelActual;
    for (GeneralTree<T> hijo : nodo.getChildren()) {
        int res = nivelAux(hijo, dato, nivelActual + 1);
        if (res != -1) return res;
    }
    return -1;
}
```

### 3. Ancho — Estrategia BFS (Por Niveles)

Recorrido por niveles con Queue, contando cuántos nodos hay en cada nivel. Retorna el máximo.

```
Usar null como marcador de fin de nivel:
    encolar(raíz)
    encolar(null)
    contadorNivel = 0, maxAncho = 0
    mientras cola no vacía:
        actual = desencolar()
        si actual == null:
            si cola no vacía:
                maxAncho = max(maxAncho, contadorNivel)
                contadorNivel = 0
                encolar(null)
        sino:
            contadorNivel++
            encolar hijos de actual
    return maxAncho
```

Otra opción más sencilla: usar un `Map<Integer, Integer>` donde `mapa[nivel] = cantidad de nodos`.

---

## Ejemplos

```
Árbol:          1
              / | \
             2  3  4
            /|     |
           5  6    7

altura()    → 2  (camino más largo: 1→2→5 o 1→2→6 o 1→4→7)
nivel(6)    → 2  (raíz=0, hijos=1, nietos=2)
nivel(3)    → 1
nivel(99)   → -1
ancho()     → 3  (nivel 0: 1 nodo, nivel 1: 3 nodos ← máximo, nivel 2: 3 nodos)
```

> **Nota:** Los métodos se implementan directamente en `GeneralTree.java` del ejercicio 1, no en un archivo nuevo.
