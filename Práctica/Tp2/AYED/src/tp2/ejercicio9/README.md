# Ejercicio 9: ParcialArboles — sumAndDif (Parcial)

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
public BinaryTree<?> sumAndDif(BinaryTree<Integer> arbol)
```

El método recibe un árbol binario de enteros y devuelve un **nuevo árbol** donde cada nodo contiene **dos tipos de información** (encapsuladas en `NodeInfo`):

1. **suma**: acumulado de los valores desde la raíz hasta el nodo actual (inclusive).
2. **diferencia**: valor del nodo actual − valor del nodo padre.

**Nota:** En el nodo raíz, el valor del padre se considera `0`.

---

## Ejemplo del Enunciado

```
Árbol original:              Nuevo árbol (suma|diferencia):
       20                           20|20
      /  \                         /     \
     5    30                    25|-15   50|10
    / \   / \                   /  \     /    \
  -5  10 50  -9              20|-10 35|5 100|20 41|-39
      /   \                        /    \
      1    4                     36|-9  104|-46
            \                              \
             6                            110|2
```

**Verificación nodo 5:**
- suma: 20 (raíz) + 5 = 25
- diferencia: 5 - 20 (padre) = -15
→ `25|-15` ✓

**Verificación nodo 10:**
- suma: 20 + 5 + 10 = 35
- diferencia: 10 - 5 (padre) = 5
→ `35|5` ✓

---

## Conceptos Técnicos Clave

### 1. La clase NodeInfo

Cada nodo del nuevo árbol almacena un `NodeInfo(int suma, int diferencia)`.
El nuevo árbol es de tipo `BinaryTree<NodeInfo>`.

### 2. Estrategia: PreOrden con parámetros acumulados

Para calcular la suma acumulada y la diferencia necesitamos información del **padre**, que viene de arriba hacia abajo → **PreOrden** (raíz antes que hijos).

```
sumAndDifAux(nodo, sumaAcumulada, valorPadre):
    Si nodo es null → null
    
    nuevaSuma = sumaAcumulada + nodo.getData()
    nuevaDif  = nodo.getData() - valorPadre
    
    nuevoNodo = new BinaryTree<>(new NodeInfo(nuevaSuma, nuevaDif))
    
    si nodo tiene hijo izquierdo:
        nuevoNodo.addLeftChild(sumAndDifAux(nodo.getLeftChild(), nuevaSuma, nodo.getData()))
    si nodo tiene hijo derecho:
        nuevoNodo.addRightChild(sumAndDifAux(nodo.getRightChild(), nuevaSuma, nodo.getData()))
    
    retornar nuevoNodo
```

Llamada inicial: `sumAndDifAux(arbol, 0, 0)`

### 3. Firma del retorno `BinaryTree<?>`

El `?` (wildcard) indica que el tipo exacto del árbol retornado no se especifica en la firma. En la implementación, el tipo concreto es `BinaryTree<NodeInfo>`.

### 4. Un solo recorrido

Este algoritmo recorre el árbol original exactamente **una vez** (cada nodo se visita exactamente una vez en PreOrden) → complejidad `O(n)`.

---

## Archivos
| Archivo | Descripción |
|---|---|
| `NodeInfo.java` | Clase auxiliar que almacena suma y diferencia de un nodo |
| `ParcialArboles.java` | Clase con el TODO a implementar |
