# Ejercicio 10: ParcialArboles — Camino Filtrado de Valor Máximo (Parcial)

## Restricciones del Ejercicio (tipo parcial)

1. No puede agregar más variables de instancia ni de clase a `ParcialArboles`.
2. Debe respetar la firma del método indicado.
3. Puede definir todos los métodos y variables **locales** que considere necesarios.
4. Todo método no definido en la sinopsis debe ser implementado.
5. **Debe recorrer la estructura solo 1 vez.**
6. **No puede** generar la lista con 0/1 y en un segundo recorrido eliminar los 0.

---

## Enunciado

Implementar en `ParcialArboles`:

```java
public static List<Integer> resolver(GeneralTree<Integer> arbol)
```

El árbol solo contiene valores **0 o 1**. Retorna el **"camino filtrado de valor máximo"**:

- **"De valor máximo"**: el camino raíz→hoja cuya suma `Σ (valor_nodo × nivel_nodo)` es mayor.
- **"Filtrado"**: del camino ganador, solo se incluyen en la lista los nodos con valor **1** (los 0 se omiten).
- Si hay empate → retornar el **primero** encontrado.

Para el árbol del enunciado, el camino ganador tiene valor `1*0 + 0*1 + 1*2 + 1*3 = 5`.  
El resultado es `[1, 1, 1]` (los tres nodos con valor 1 de ese camino, sin el 0).

---

## Conceptos Técnicos Clave

### 1. El Valor de un Camino

Para el camino `r → n1 → n2 → ... → hoja`:
```
valor = r.getData()*0 + n1.getData()*1 + n2.getData()*2 + ... + hoja.getData()*altura
```
Los nodos con valor 0 no aportan nada (0 × nivel = 0), solo los 1 aportan.

### 2. Estrategia: DFS con Backtracking

Se mantiene el **camino actual (solo los 1s)** y el **valor acumulado** del camino actual:

```
DFS(nodo, nivel, valorAcumulado, caminoActual, mejorCamino, mejorValor):
    aporte = nodo.getData() * nivel
    nuevoValor = valorAcumulado + aporte
    
    si nodo.getData() == 1: caminoActual.add(1)   ← filtrar: agregar solo los 1
    
    si es hoja:
        si nuevoValor > mejorValor:
            mejorValor = nuevoValor
            mejorCamino = copia de caminoActual
    sino:
        para cada hijo:
            DFS(hijo, nivel+1, nuevoValor, caminoActual, mejorCamino, mejorValor)
    
    si nodo.getData() == 1: caminoActual.remove(último)   ← backtracking
```

### 3. ¿Por qué usar `int[] mejorValor` en lugar de `int`?

Los tipos primitivos en Java se pasan **por valor**, no por referencia. Para poder actualizar el mejor valor desde dentro de la recursión, se usa un arreglo de un elemento `int[]` que actúa como "variable mutable compartida".

```java
int[] mejorValor = {Integer.MIN_VALUE};
// Dentro de la recursión: mejorValor[0] = nuevoValor;
```

### 4. Inicializar con `Integer.MIN_VALUE`

Así el primer camino siempre actualiza el mejor, incluso si su valor es 0.

### 5. Restricción: filtrar en un solo recorrido

No se puede hacer:
```java
// PROHIBIDO: dos recorridos
List<Integer> conCeros = todosLosNodos(camino);
conCeros.removeIf(x -> x == 0);   // segundo recorrido
```

Debe filtrar **mientras construye** el camino (solo agregar cuando `nodo.getData() == 1`).

---

## Traza del Ejemplo

```
Camino 1: raíz(1)→n0a(0)→n1c(1)→n1f(1)
  valor = 1*0 + 0*1 + 1*2 + 1*3 = 5  → lista filtrada: [1,1,1]

Camino 2: raíz(1)→n1a(1)→n1e(1)→n0f(0)
  valor = 1*0 + 1*1 + 1*2 + 0*3 = 3  → no es el máximo

Resultado: [1, 1, 1]  (del camino 1)
```

## Archivos
| Archivo | Descripción |
|---|---|
| `ParcialArboles.java` | Clase con el TODO a implementar |
