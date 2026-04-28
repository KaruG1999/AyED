# Ejercicio 8: ParcialArboles — esPrefijo (Parcial)

## Restricciones del Ejercicio (tipo parcial)

1. No puede agregar más variables de instancia ni de clase a `ParcialArboles`.
2. Debe respetar la firma del método indicado.
3. Puede definir todos los métodos y variables **locales** que considere necesarios.
4. Todo método no definido en la sinopsis debe ser implementado.
5. **Debe recorrer la estructura solo 1 vez.**

---

## Enunciado

Escribir en la clase **`ParcialArboles`** el método:

```java
public boolean esPrefijo(BinaryTree<Integer> arbol1, BinaryTree<Integer> arbol2)
```

Devuelve `true` si `arbol1` es **prefijo** de `arbol2`, `false` en caso contrario.

**Definición:** `arbol1` es prefijo de `arbol2` cuando `arbol1` coincide con la **parte inicial** de `arbol2`, tanto en el **contenido de los elementos** como en su **estructura**.

---

## Ejemplos del Enunciado

**Caso 1 — ES prefijo:**
```
arbol1:          arbol2:
    65               65
   /  \             /  \
  37   81          37   81
    \             /  \
    93           22   47
                     /  \
                    76   93
                   /  \
                  11   29    85  94
```
arbol1 ES prefijo de arbol2 ✓

**Caso 2 — NO es prefijo (falta un nodo en arbol2):**
arbol1 tiene nodo 93 como hijo de 81, pero arbol2 no tiene ese subárbol → NO es prefijo.

**Caso 3 — NO es prefijo (contenido diferente):**
arbol1 tiene raíz 37 en la izquierda, arbol2 tiene 62 → NO es prefijo.

---

## Conceptos Técnicos Clave

### 1. Definición Recursiva de Prefijo

arbol1 es prefijo de arbol2 si y solo si:
1. `arbol1` es vacío/null → **siempre true** (un árbol vacío es prefijo de cualquiera)
2. `arbol2` es vacío/null pero `arbol1` no → **false**
3. Los datos raíz coinciden: `arbol1.getData().equals(arbol2.getData())`
4. **Y** el hijo izquierdo de arbol1 es prefijo del hijo izquierdo de arbol2
5. **Y** el hijo derecho de arbol1 es prefijo del hijo derecho de arbol2

### 2. Esquema de Implementación

```java
public boolean esPrefijo(BinaryTree<Integer> a1, BinaryTree<Integer> a2) {
    if (a1 == null) return true;            // árbol vacío es prefijo de todo
    if (a2 == null) return false;           // a1 tiene nodos, a2 no
    if (!a1.getData().equals(a2.getData())) return false; // datos distintos
    
    BinaryTree<Integer> izq1 = a1.hasLeftChild()  ? a1.getLeftChild()  : null;
    BinaryTree<Integer> der1 = a1.hasRightChild() ? a1.getRightChild() : null;
    BinaryTree<Integer> izq2 = a2.hasLeftChild()  ? a2.getLeftChild()  : null;
    BinaryTree<Integer> der2 = a2.hasRightChild() ? a2.getRightChild() : null;
    
    return esPrefijo(izq1, izq2) && esPrefijo(der1, der2);
}
```

### 3. Complejidad

- Recorre como máximo todos los nodos de `arbol1` → `O(|arbol1|)`
- En el peor caso también recorre parte de `arbol2` → `O(min(|arbol1|, |arbol2|))`

### 4. Diferencia con "subárbol"

Un **prefijo** es más restrictivo que contener un subárbol: la coincidencia debe empezar **desde la raíz** y seguir la misma estructura top-down, sin saltos.
