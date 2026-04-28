# Ejercicio 2: Métodos adicionales de BinaryTree

## Nota Importante

Los métodos del Ejercicio 2 están implementados **directamente en `BinaryTree.java`** del Ejercicio 1, ya que son extensiones de esa clase.

Ir a: `../ejercicio1/BinaryTree.java`

Los métodos a implementar son:
- `contarHojas():int` — línea con `// TODO: Ejercicio 2a`
- `espejo():BinaryTree<T>` — línea con `// TODO: Ejercicio 2b`
- `entreNiveles(int n, int m)` — línea con `// TODO: Ejercicio 2c`

Los casos de prueba están en `../ejercicio1/TestBinaryTree.java`.

---

## Enunciado Completo

Agregue a la clase `BinaryTree` los siguientes métodos:

**a) `contarHojas():int`**
Devuelve la cantidad de árbol/subárbol hojas del árbol receptor.

**b) `espejo():BinaryTree<T>`**
Devuelve el árbol binario espejo del árbol receptor.

```
Dado el árbol:         Retorna:
       1                   1
      / \                 / \
     2   3               3   2
    / \   \             /   / \
   4   5   6           6   5   4
```

**c) `entreNiveles(int n, int m)`**
Imprime el recorrido por niveles de los elementos del árbol receptor entre los niveles `n` y `m` (ambos inclusive). `(0 ≤ n < m ≤ altura del árbol)`

---

## Estrategias de Resolución

### contarHojas
Recursión pura:
- Si el nodo **es hoja** → devolver `1`
- Si tiene **solo hijo izquierdo** → `1 + contarHojas(izquierdo)`
- Si tiene **solo hijo derecho** → `1 + contarHojas(derecho)`
- Si tiene **ambos hijos** → `contarHojas(izquierdo) + contarHojas(derecho)`

### espejo
Recursión constructiva:
1. Crear `BinaryTree<T> nuevo = new BinaryTree<>(this.data)`
2. Si tiene hijo derecho → `nuevo.addLeftChild(rightChild.espejo())`
3. Si tiene hijo izquierdo → `nuevo.addRightChild(leftChild.espejo())`
4. Retornar `nuevo`

### entreNiveles (BFS con control de nivel)
```java
Queue<BinaryTree<T>> cola = new LinkedList<>();
cola.add(this);
int nivelActual = 0;
while (!cola.isEmpty()) {
    int nodosEnNivel = cola.size(); // cuántos nodos hay en este nivel
    for (int i = 0; i < nodosEnNivel; i++) {
        BinaryTree<T> nodo = cola.poll();
        if (nivelActual >= n && nivelActual <= m) {
            System.out.print(nodo.getData() + " ");
        }
        if (nodo.hasLeftChild())  cola.add(nodo.getLeftChild());
        if (nodo.hasRightChild()) cola.add(nodo.getRightChild());
    }
    nivelActual++;
    if (nivelActual > m) break;
}
```
