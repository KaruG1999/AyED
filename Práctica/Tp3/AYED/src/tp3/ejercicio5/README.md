# Ejercicio 5: esAncestro() — en GeneralTree

## Enunciado

Se dice que un nodo `n` es **ancestro** de un nodo `m` si existe un camino directo desde `n` hasta `m`.

Implementar en la clase `GeneralTree` (en `tp3.ejercicio1`) el método:

```java
public boolean esAncestro(T a, T b)
```

Devuelve `true` si el valor `"a"` es ancestro del valor `"b"`.

> El TODO ya está marcado en `GeneralTree.java` del ejercicio 1.

---

## Conceptos Técnicos Clave

### 1. Definición de Ancestro

`a` es ancestro de `b` si:
- `b` es un **descendiente** de `a` (está en el subárbol cuya raíz es el nodo con dato `a`)
- El nodo `a` mismo **no es ancestro de sí mismo** (a menos que el enunciado lo indique)

Por ejemplo:
```
       1
      /|\
     2  3  4
    /|
   5  6
```
- 1 es ancestro de 5 → true
- 2 es ancestro de 6 → true
- 3 es ancestro de 5 → false (no hay camino de 3 a 5)
- 5 es ancestro de 2 → false (va en dirección contraria)

### 2. Estrategia en Dos Pasos

1. **Encontrar el nodo con dato `a`** recorriendo el árbol.
2. **Buscar `b` en el subárbol** cuya raíz es ese nodo (excluyendo la raíz misma).

```
esAncestro(a, b):
    nodoA = buscarNodo(raíz, a)
    si nodoA == null → false    // 'a' no existe en el árbol
    return existeEnSubarbol(nodoA, b)
```

### 3. buscarNodo — PreOrden

```java
private GeneralTree<T> buscarNodo(GeneralTree<T> nodo, T dato) {
    if (nodo.getData().equals(dato)) return nodo;
    for (GeneralTree<T> hijo : nodo.getChildren()) {
        GeneralTree<T> res = buscarNodo(hijo, dato);
        if (res != null) return res;
    }
    return null;
}
```

### 4. existeEnSubarbol — Busca b en los HIJOS de nodoA

```java
private boolean existeEnSubarbol(GeneralTree<T> nodo, T dato) {
    for (GeneralTree<T> hijo : nodo.getChildren()) {
        if (existeEnNodo(hijo, dato)) return true;
    }
    return false;
}

private boolean existeEnNodo(GeneralTree<T> nodo, T dato) {
    if (nodo.getData().equals(dato)) return true;
    for (GeneralTree<T> hijo : nodo.getChildren()) {
        if (existeEnNodo(hijo, dato)) return true;
    }
    return false;
}
```

### 5. Versión compacta en un solo recorrido

Si el árbol tiene datos únicos, se puede hacer en un solo método:

```
esAncestro(a, b):
    buscar 'a' con PreOrden
    cuando se encuentra 'a': buscar 'b' en sus hijos
    si se encuentra 'b' antes de encontrar 'a': no era ancestro
```

---

## Tabla de Resultados (árbol del ejemplo)

| a | b | ¿es ancestro? | Por qué |
|---|---|---|---|
| 1 | 5 | true | camino 1→2→5 |
| 2 | 6 | true | camino 2→6 |
| 3 | 5 | false | no existe camino de 3 a 5 |
| 5 | 2 | false | el camino va en sentido contrario |
| 5 | 5 | false | un nodo no es ancestro de sí mismo |
| 99 | 5 | false | el nodo 99 no existe |

> **Nota:** El método se implementa directamente en `GeneralTree.java` del ejercicio 1.
