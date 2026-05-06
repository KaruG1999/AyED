# Ejercicio 1: Clase GeneralTree

## Enunciado

Considere la siguiente especificación de la clase **`GeneralTree<T>`** con la representación de **Lista de Hijos**:

- `GeneralTree(T data)`: inicializa un árbol con el dato dado y lista de hijos vacía.
- `GeneralTree(T data, List<GeneralTree<T>> children)`: inicializa con dato y lista de hijos dada.
- `getData(): T` / `setData(T)`: getter y setter del dato de la raíz.
- `getChildren(): List<GeneralTree<T>>` / `setChildren(...)`: getter y setter de la lista de hijos.
- `addChild(GeneralTree<T> child)`: agrega un hijo al final de la lista.
- `removeChild(GeneralTree<T> child)`: elimina el hijo pasado como parámetro.
- `hasChildren(): boolean`: verdadero si la lista de hijos no es null y no está vacía.
- `isLeaf(): boolean`: verdadero si no tiene hijos.
- `isEmpty(): boolean`: verdadero si el dato es null y no tiene hijos.
- Los métodos `altura()`, `nivel(T)`, `ancho()` y `esAncestro(T,T)` se implementarán en los ejercicios 3 y 5.

**Tarea:** Analice la implementación en `GeneralTree.java` brindada.

---

## Conceptos Técnicos Clave

### 1. Árbol General vs. Árbol Binario

| Árbol Binario | Árbol General |
|---|---|
| Exactamente 0, 1 o 2 hijos | Cualquier cantidad de hijos |
| `leftChild`, `rightChild` | `List<GeneralTree<T>> children` |
| InOrden: izq → raíz → der | InOrden: 1er hijo → raíz → resto de hijos |

### 2. Representación: Lista de Hijos

Cada nodo almacena:
- Un **dato** (`data`)
- Una **lista** de referencias a sus hijos (`children`)

Si un nodo no tiene hijos, la lista está vacía (nunca `null` después del constructor).

### 3. Los Cuatro Recorridos

| Recorrido | Estrategia |
|---|---|
| **PreOrden** | Raíz → luego cada hijo en orden |
| **PostOrden** | Cada hijo en orden → luego raíz |
| **InOrden** | Primer hijo → raíz → hijos restantes |
| **Por niveles (BFS)** | Cola: encolar raíz, desencolar → procesar → encolar sus hijos |

### 4. InOrden en AG — Detalle
En AG, el "inorden" es una convención:
```
inOrden(nodo):
    si tiene hijos → inOrden(hijos.get(0))   // primer hijo
    visitar(nodo.getData())                   // raíz
    para i=1..hijos.size()-1:                 // hijos restantes
        inOrden(hijos.get(i))
```

### 5. BFS con Queue

```java
Queue<GeneralTree<T>> queue = new LinkedList<>();
queue.add(raiz);
while (!queue.isEmpty()) {
    GeneralTree<T> actual = queue.poll();
    procesar(actual);
    for (GeneralTree<T> hijo : actual.getChildren()) {
        queue.add(hijo);
    }
}
```

---

## Árbol de Ejemplo (de la teoría)

```
          180
        /  |  \
      18   50   19
      |   /|\   |\
      15 21 9 4  6 10
            |
           11  7
```

Código para construirlo:
```java
GeneralTree<Integer> raiz = new GeneralTree<>(180);
GeneralTree<Integer> n18  = new GeneralTree<>(18);
GeneralTree<Integer> n50  = new GeneralTree<>(50);
GeneralTree<Integer> n19  = new GeneralTree<>(19);
raiz.addChild(n18);
raiz.addChild(n50);
raiz.addChild(n19);
n18.addChild(new GeneralTree<>(15));
// etc.
```

## Archivos
| Archivo | Descripción |
|---|---|
| `GeneralTree.java` | Implementación completa con TODOs para Ej3 y Ej5 |
