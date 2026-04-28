# Ejercicio 7: ParcialArboles — isLeftTree (Parcial)

## Restricciones del Ejercicio (tipo parcial)

1. No puede agregar más variables de instancia ni de clase a `ParcialArboles`.
2. Debe respetar la firma del método indicado.
3. Puede definir todos los métodos y variables **locales** que considere necesarios.
4. Todo método no definido en la sinopsis debe ser implementado.
5. **Debe recorrer la estructura solo 1 vez.**

---

## Enunciado

Escribir en la clase **`ParcialArboles`** (con una única variable de instancia `BinaryTree<Integer>` de valores NO repetidos) el método:

```java
public boolean isLeftTree(int num)
```

El método devuelve `true` si el subárbol cuya raíz es `num` tiene en su **subárbol izquierdo una cantidad mayor estricta** de árboles con un **único hijo** que en su subárbol derecho.

**Consideraciones:**
- Si `num` no se encuentra en el árbol → `false`
- Si el árbol con raíz `num` no tiene una de sus ramas → esa rama tiene `-1` árboles con único hijo

---

## Ejemplos del Enunciado

```
Árbol:
        2
       / \
      7   -5
     / \    \
    23  6    19
   /  \  \     \
  -3  55  11    4
                 \
                 18

isLeftTree(7)   → true   (izq: 1 árbol con único hijo [23 NO, tiene 2], 
                          el único con 1 hijo es... espera)
```

**"árbol con único hijo"** = nodo que tiene exactamente uno de los dos hijos (ni ambos ni ninguno).

```
isLeftTree(7)  → true  (izq tiene 1 → nodo 23: NO tiene único hijo, tiene dos.
                         Espera, el árbol con raíz 23 tiene raíz con 2 hijos
                         pero sus descendientes: -3 y 55 son hojas.
                         El árbol con raíz 6 tiene un único hijo (11).
                         → izq del 7: árbol de raíz 23 → contar árboles con único hijo
                         En el subárbol de 23: ninguno tiene único hijo (23 tiene 2, -3 y 55 son hojas)
                         → izq cuenta: 0? NO...
                         
Según el enunciado:
isLeftTree(7) → true porque en la rama izquierda hay 1 árbol con único hijo (el de raíz 23)
```

Ojo: el enunciado dice que el árbol con raíz 23 **tiene** único hijo. Pero 23 tiene 2 hijos (-3 y 55). Revisando el árbol del PDF, parece que 23 solo tiene hijo izquierdo (-3) en ese ejemplo. El árbol de prueba del `main` puede diferir del PDF.

---

## Conceptos Técnicos Clave

### 1. Árbol con "Único Hijo"

Un nodo/árbol tiene **único hijo** cuando:
```java
(nodo.hasLeftChild() && !nodo.hasRightChild()) ||
(!nodo.hasLeftChild() && nodo.hasRightChild())
```
Es decir: `hasLeftChild() XOR hasRightChild()`

### 2. Estrategia: Un Solo Recorrido

La restricción de **un solo recorrido** implica que debemos hacer todo en una misma pasada recursiva. La técnica es devolver múltiples valores en la recursión.

**Paso 1:** Buscar el nodo con valor `num`.
**Paso 2:** Una vez encontrado, contar los nodos con único hijo en su rama izquierda y derecha.

Si se encapsula en un método auxiliar que retorna un objeto/array con la información necesaria, se puede hacer en un solo recorrido.

### 3. Esquema de Solución

```java
// Retorna: [contadorUnicoHijo, seEncontroNum, contIzqDeNum, contDerDeNum]
private int[] helper(BinaryTree<Integer> nodo, int num) {
    if (nodo == null) return new int[]{0, 0, -2, -2}; // -2 = no encontrado aún
    
    int esUnicoHijo = tieneUnicoHijo(nodo) ? 1 : 0;
    
    int[] izq = helper(nodo.hasLeftChild() ? nodo.getLeftChild() : null, num);
    int[] der = helper(nodo.hasRightChild() ? nodo.getRightChild() : null, num);
    
    // combinar resultados...
}
```

### 4. Valor -1 para Ramas Inexistentes

Si el nodo con valor `num` **no tiene hijo izquierdo**, su cuenta izquierda es `-1`.
Si **no tiene hijo derecho**, su cuenta derecha es `-1`.
Esto modifica el resultado: `(-1 > cualquier_cosa) → false` salvo que la derecha también sea `-1`.

---

## Tabla de Resultados Esperados

| num | izq | der | ¿izq > der? |
|---|---|---|---|
| 7 | 1 | 0 | true |
| 2 | 1 | 3 | false |
| -5 | 2 | -1 | true |
| 19 | -1 | 1 | false |
| -3 | -1 | -1 | false |
| 99 | no existe | — | false |
