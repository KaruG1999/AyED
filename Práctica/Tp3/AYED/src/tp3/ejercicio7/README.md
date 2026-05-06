# Ejercicio 7: Caminos — Camino a la Hoja Más Lejana

## Enunciado

Dada una clase `Caminos` con una variable de instancia `GeneralTree<Integer>`, implementar:

```java
public List<Integer> caminoAHojaMasLejana()
```

Retorna el camino desde la raíz hasta la **hoja más lejana** (camino más largo). Si hay más de uno, retornar el **primero** que se encuentre.

Para el árbol del enunciado, el resultado es: **[12, 17, 6, 1]** (longitud 3).

---

## Conceptos Técnicos Clave

### 1. ¿Qué significa "camino de longitud máxima"?

La longitud de un camino = cantidad de **aristas** (conexiones), no de nodos.
- Camino [12, 17, 6, 1] → 3 aristas (3 pasos desde la raíz).
- Equivale a: el nodo al que se llega está en el nivel 3 del árbol.

### 2. Técnica: Backtracking + Camino Actual

Se mantiene el **camino actual** como una lista mutable que crece al bajar y se reduce al subir:

```
DFS(nodo, caminoActual, mejorCamino):
    caminoActual.add(nodo.getData())           ← entrar al nodo
    
    si es hoja:
        si caminoActual.size() > mejorCamino.size():
            mejorCamino = copia de caminoActual     ← nuevo mejor
    sino:
        para cada hijo (en orden):
            DFS(hijo, caminoActual, mejorCamino)
    
    caminoActual.remove(último elemento)       ← salir del nodo (backtracking)
```

> El backtracking es clave: al regresar de un subárbol, se **deshace** el paso para explorar el siguiente hermano desde el mismo estado.

### 3. ¿Por qué retorna el PRIMERO?

Al recorrer los hijos en orden (el orden en que fueron agregados con `addChild`), el primer camino máximo encontrado se guarda. Los siguientes caminos de la misma longitud NO actualizan el mejor (condición `>` estricto, no `>=`).

### 4. Esquema en Java

```java
private void caminoAHojaMasLejanaAux(GeneralTree<Integer> nodo,
                                      List<Integer> caminoActual,
                                      List<Integer> mejorCamino) {
    caminoActual.add(nodo.getData());

    if (nodo.isLeaf()) {
        if (caminoActual.size() > mejorCamino.size()) {
            mejorCamino.clear();
            mejorCamino.addAll(caminoActual);
        }
    } else {
        for (GeneralTree<Integer> hijo : nodo.getChildren()) {
            caminoAHojaMasLejanaAux(hijo, caminoActual, mejorCamino);
        }
    }

    caminoActual.remove(caminoActual.size() - 1);  // backtracking
}
```

### 5. Traza del ejemplo

```
Explorando 12 → camino: [12]
  Explorando 17 → camino: [12, 17]
    Explorando 10 → camino: [12, 17, 10] → HOJA, longitud 3 → mejorCamino = [12,17,10]
    Explorando 6  → camino: [12, 17, 6]
      Explorando 1 → camino: [12, 17, 6, 1] → HOJA, longitud 4 > 3 → mejorCamino = [12,17,6,1]
  Explorando 9  → camino: [12, 9]
    Explorando 8  → camino: [12, 9, 8] → HOJA, longitud 3, NO > 4
  Explorando 15 → camino: [12, 15]
    Explorando 14 → camino: [12, 15, 14]
      Explorando 16 → camino: [12,15,14,16] → longitud 4, NO > 4 (igual, no actualiza)
      ...

Resultado: [12, 17, 6, 1]
```

## Archivos
| Archivo | Descripción |
|---|---|
| `Caminos.java` | Clase con variable de instancia, TODO y main de prueba |
