# Ejercicio 3: ContadorArbol — Recorridos InOrden y PostOrden

## Enunciado

Defina una clase Java denominada **`ContadorArbol`** cuya función principal es proveer métodos de validación sobre árboles binarios de enteros. La clase tiene como variable de instancia un `BinaryTree<Integer>`.

Implemente el método **`numerosPares()`** que devuelve en una estructura adecuada (sin ningún criterio de orden) todos los elementos pares del árbol (divisibles por 2).

- **a)** Implemente el método realizando un recorrido **InOrden**.
- **b)** Implemente el método realizando un recorrido **PostOrden**.

---

## Conceptos Técnicos Clave

### 1. Los Tres Recorridos en Profundidad (DFS)

| Recorrido | Orden de visita | Cuándo se procesa la raíz |
|---|---|---|
| **PreOrden** | raíz → izquierda → derecha | Antes de los hijos |
| **InOrden** | izquierda → raíz → derecha | Entre los hijos |
| **PostOrden** | izquierda → derecha → raíz | Después de los hijos |

### 2. InOrden — Esquema
```java
private void inOrden(BinaryTree<Integer> nodo, List<Integer> lista) {
    if (nodo == null) return;
    if (nodo.hasLeftChild())  inOrden(nodo.getLeftChild(), lista);   // izquierda
    if (nodo.getData() % 2 == 0) lista.add(nodo.getData());          // raíz
    if (nodo.hasRightChild()) inOrden(nodo.getRightChild(), lista);  // derecha
}
```

### 3. PostOrden — Esquema
```java
private void postOrden(BinaryTree<Integer> nodo, List<Integer> lista) {
    if (nodo == null) return;
    if (nodo.hasLeftChild())  postOrden(nodo.getLeftChild(), lista);  // izquierda
    if (nodo.hasRightChild()) postOrden(nodo.getRightChild(), lista); // derecha
    if (nodo.getData() % 2 == 0) lista.add(nodo.getData());           // raíz
}
```

### 4. ¿Por qué el resultado es el mismo sin importar el recorrido?
La consigna dice "sin ningún criterio de orden", por lo que ambos métodos deben devolver los **mismos elementos** pero en **diferente orden**. Ambos visitan todos los nodos exactamente una vez → `O(n)`.

### 5. Usar método auxiliar privado
Para pasar la lista acumuladora a las llamadas recursivas, se recomienda crear un método privado helper:
```java
public List<Integer> numerosPares() {
    List<Integer> resultado = new ArrayList<>();
    inOrden(this.arbol, resultado);
    return resultado;
}
```

---

## Ejemplo de Ejecución

```
Árbol:         1
              / \
             2   3
            / \   \
           4   6   8
          /
         10

InOrden visita:    10, 4, 2, 6, 1, 3, 8
PostOrden visita:  10, 4, 6, 2, 8, 3, 1

Pares InOrden:   [10, 4, 2, 6, 8]
Pares PostOrden: [10, 4, 6, 2, 8]
```
