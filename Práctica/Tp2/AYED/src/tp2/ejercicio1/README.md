# Ejercicio 1 & 2: Clase BinaryTree

## Enunciado — Ejercicio 1

Considere la siguiente especificación de la clase Java **BinaryTree\<T\>** (con representación hijo izquierdo e hijo derecho):

- `BinaryTree(T data)`: inicializa un árbol con el dato pasado y ambos hijos nulos.
- `getLeftChild()` / `getRightChild()`: retornan los hijos izquierdo/derecho. Si no existe, lanza error.
- `addLeftChild(BinaryTree<T>)` / `addRightChild(BinaryTree<T>)`: agrega un hijo izquierdo o derecho.
- `removeLeftChild()` / `removeRightChild()`: eliminan el hijo correspondiente.
- `isEmpty()`: indica si el árbol está vacío.
- `isLeaf()`: indica si no tiene hijos.
- `hasLeftChild()` / `hasRightChild()`: devuelve boolean indicando si tiene dicho hijo.

**a)** Analice la implementación en `BinaryTree.java` brindada.

---

## Enunciado — Ejercicio 2

Agregue a la clase `BinaryTree` los siguientes métodos (están en `BinaryTree.java` como TODOs):

**a) `contarHojas():int`**
Devuelve la cantidad de hojas del árbol receptor. Una hoja es un nodo sin hijos.

**b) `espejo():BinaryTree<T>`**
Devuelve el árbol binario espejo del árbol receptor.

```
Dado:        Retorna:
    1             1
   / \           / \
  2   3         3   2
 / \   \       /   / \
4   5   6     6   5   4
```

**c) `entreNiveles(int n, int m)`**
Imprime el recorrido por niveles de los elementos entre los niveles `n` y `m` (ambos inclusive). `(0 ≤ n < m ≤ altura del árbol)`

---

## Conceptos Técnicos Clave

### 1. Estructura de un Árbol Binario
Cada nodo tiene:
- Un **dato** (`data`)
- Una referencia al **hijo izquierdo** (`leftChild`)
- Una referencia al **hijo derecho** (`rightChild`)

Si un hijo no existe, la referencia es `null`. Esto define la estructura recursiva del árbol.

### 2. Recursión en Árboles
La mayoría de los métodos sobre árboles se implementan recursivamente:
- **Caso base:** nodo hoja (`isLeaf()`) o árbol vacío.
- **Caso recursivo:** aplicar la lógica al subárbol izquierdo y al derecho.

### 3. contarHojas — Estrategia Recursiva
```
Si es hoja → retorna 1
Si no      → retorna contarHojas(izq) + contarHojas(der)
```

### 4. espejo — Estrategia Recursiva
```
Crear nuevo nodo con el mismo dato
Asignar como hijo izquierdo → espejo del hijo DERECHO original
Asignar como hijo derecho  → espejo del hijo IZQUIERDO original
```

### 5. entreNiveles — Recorrido BFS (Anchura / Por Niveles)
Se usa una `Queue` para procesar nodo por nodo en orden de nivel:
1. Encolar la raíz.
2. Mientras la cola no esté vacía: desencolar, procesar si el nivel está en [n, m], encolar sus hijos.
3. Llevar un contador de nivel usando el tamaño de la cola al inicio de cada "ronda".

### 6. Tipos Genéricos `<T>`
`BinaryTree<T>` puede almacenar cualquier tipo: `Integer`, `String`, objetos propios, etc. El tipo se define al instanciar: `new BinaryTree<Integer>(5)`.

---

## Archivos
| Archivo | Descripción |
|---|---|
| `BinaryTree.java` | Clase principal con los TODOs de ej1 y ej2 |
| `TestBinaryTree.java` | Tests y ejemplos para verificar las implementaciones |
