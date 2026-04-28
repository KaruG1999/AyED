# Ejercicio 6: Transformacion — Suma de Subárboles

## Enunciado

Cree una clase Java llamada **`Transformacion`** que tenga como variable de instancia un árbol binario de números enteros y un método de instancia:

```java
public BinaryTree<Integer> suma()
```

El método devuelve el árbol en el que se **reemplazó el valor de cada nodo por la suma de todos los elementos presentes en su subárbol izquierdo y derecho**. Los valores de los subárboles vacíos son ceros.

**Pregunta del enunciado:** ¿Su solución recorre una única vez cada subárbol? En caso que no, ¿puede mejorarla para que sí lo haga?

---

## Ejemplo

```
Árbol original:          Árbol resultado:
      1                        35
     / \                      /  \
    2   3                    4    26
   /   / \                  /    /  \
  4   5   6                0   15    0
     / \                      /  \
    7   8                    0    0
```

Nodo raíz (1): suma izquierdo (2+4=6) + suma derecho (3+5+6+7+8=29) = 35
Nodo 2: suma izquierdo (4) + suma derecho (0) = 4
Nodo 3: suma izquierdo (5+7+8=20) + suma derecho (6) = 26
Nodo 5: suma izquierdo (7) + suma derecho (8) = 15
Las hojas y sus hijos vacíos valen 0.

---

## Conceptos Técnicos Clave

### 1. Solución Naive (recorre más de una vez)

Una primera idea es:
1. Calcular la suma de cada subárbol con un método `sumaTotal(nodo)`
2. Usarla para construir el nuevo árbol

Pero esto recorrería cada subárbol **múltiples veces** porque `sumaTotal` se llama para cada nodo.

### 2. Solución Óptima: PostOrden con retorno doble

La clave es que en **PostOrden** (izq → der → raíz), cuando procesamos un nodo ya tenemos la suma de sus hijos calculada. Podemos combinar en un solo recorrido:

```
// Retorna: par (nuevoArbol, sumaTotal)
transformar(nodo):
    Si nodo es null → (null, 0)
    
    (nuevoIzq, sumaIzq) = transformar(hijo_izquierdo)
    (nuevoDer, sumaDer) = transformar(hijo_derecho)
    
    nuevoNodo = new BinaryTree<>(sumaIzq + sumaDer)
    si nuevoIzq != null → nuevoNodo.addLeftChild(nuevoIzq)
    si nuevoDer != null → nuevoNodo.addRightChild(nuevoDer)
    
    retornar (nuevoNodo, sumaIzq + sumaDer + nodo.getData())
```

Esto recorre cada nodo **exactamente una vez** → `O(n)`.

### 3. Implementación en Java

Para retornar dos valores, se puede usar un array `int[]` o una clase auxiliar:

```java
private int[] transformarAux(BinaryTree<Integer> nodo, BinaryTree<Integer> nuevo) {
    // retorna la suma total del subárbol
}
```

O crear una clase interna `Pair<BinaryTree<Integer>, Integer>`.

---

## Archivos
| Archivo | Descripción |
|---|---|
| `Transformacion.java` | Clase con el TODO a implementar |
