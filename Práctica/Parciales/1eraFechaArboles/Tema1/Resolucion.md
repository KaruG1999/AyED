# Resolución — Tema 1 · 1ra Fecha Árboles y Heap (10/05/2025)

> Fuente: `AYED Primer Parcial 2025.pdf` + resolución manuscrita `1er fecha 2025.jpeg`

---

## Ejercicio 1 (5 puntos)

### Enunciado
Implementar en `ParcialArboles`:

```java
public static List<Integer> caminoParidadAlternante(GeneralTree<Integer> arbol)
```

Devuelve los nodos del **camino más largo** (más nodos) desde la raíz hasta una hoja, donde los valores **alternan entre par e impar** en cada paso. Si varios caminos tienen la misma longitud máxima, devolver el primero encontrado de izquierda a derecha. Sin camino válido → lista vacía. **Nota: 0 es par.**

### Ejemplo del enunciado
```
        2
       / \
      3   5
     /|\   \
    1  4  6  6
              \
               8
```
- 2→3→4 (par→impar→par) ✓ longitud=2
- 2→5→6 (par→impar→par) ✓ longitud=2  ← mismo largo, pero 2→3→4 se encuentra primero
- **Devuelve: [2, 3, 4]**

### Resolución

```java
public class ParcialArboles {

    public static List<Integer> caminoParidadAlternante(GeneralTree<Integer> arbol) {
        List<Integer> resultado = new LinkedList<>();
        if (arbol != null && !arbol.isEmpty()) {
            construirCamino(arbol, resultado, new LinkedList<>());
        }
        return resultado;
    }

    private static void construirCamino(GeneralTree<Integer> nodo,
                                        List<Integer> resultado,
                                        List<Integer> camino) {
        camino.add(nodo.getData());
        int resto = Math.abs(nodo.getData() % 2);  // 0=par, 1=impar

        if (nodo.isLeaf()) {
            if (camino.size() > resultado.size()) {
                resultado.clear();
                resultado.addAll(camino);
            }
        } else {
            for (GeneralTree<Integer> hijo : nodo.getChildren()) {
                if (Math.abs(hijo.getData() % 2) != resto) {  // alterna paridad
                    construirCamino(hijo, resultado, camino);
                }
            }
        }

        camino.remove(camino.size() - 1);  // backtracking
    }
}
```

**Puntos clave:**
- `Math.abs(dato % 2)` en lugar de solo `dato % 2` para manejar negativos correctamente (Java: -3 % 2 = -1)
- Sólo actualiza `resultado` cuando llega a una **hoja** (no a un nodo sin hijos válidos)
- Backtracking: `camino.remove(camino.size() - 1)` al salir de cada nodo
- Comparación por `camino.size() > resultado.size()` (número de nodos, no edges)

---

## Ejercicio 2 (1.5 puntos)

### a) Expresión postfija de: `A/(C-D)*(E+F)`

**Construir el árbol de expresión:**
```
        *
       / \
      /   +
     / \ / \
    A  - E  F
      / \
     C   D
```

**PostOrden** (izq → der → raíz):
`A C D - / E F + *`

### b) Primera fase del HeapSort para ordenar DESCENDENTEMENTE

**Respuesta: Convertir el arreglo en una Min-Heap**

- HeapSort ASCENDENTE → primero construir **Max-Heap** (se extrae máximo al final → queda ordenado ascendentemente)
- HeapSort DESCENDENTE → primero construir **Min-Heap** (se extrae mínimo al final → queda ordenado descendentemente)

### c) Sentencias correctas sobre AB Completo

- **(i) Cada nivel, excepto posiblemente el más profundo, está completamente lleno** ✓
- (ii) Cada nodo no-hoja tiene exactamente hijo izq y der no nulos ✗ (el último nodo puede tener solo hijo izquierdo)
- (iii) Cada nodo no-hoja tiene exactamente dos hijos o ninguno ✗ (eso es AB **lleno**)
- **(iv) Puede tener hojas en más de un nivel** ✓

**Respuesta: (e) (i) y (iv)**

---

## Ejercicio 3 (3.5 puntos)

### a) Construir Min-Heap con BuildHeap

Secuencia: `10 33 8 11 14 31 1 23 24`

Array inicial (0-indexed): `[10, 33, 8, 11, 14, 31, 1, 23, 24]`  N=9

**BuildHeap** empieza en i = N/2 - 1 = **3**, sift-down hacia atrás:

```
i=3: A[3]=11, hijos: A[7]=23, A[8]=24. 11 es mínimo → sin cambio
     [10, 33, 8, 11, 14, 31, 1, 23, 24]

i=2: A[2]=8, hijos: A[5]=31, A[6]=1. mínimo=1 en pos 6 → swap(2,6)
     [10, 33, 1, 11, 14, 31, 8, 23, 24]
     Sift-down desde 6: sin hijos → fin

i=1: A[1]=33, hijos: A[3]=11, A[4]=14. mínimo=11 en pos 3 → swap(1,3)
     [10, 11, 1, 33, 14, 31, 8, 23, 24]
     Sift-down desde 3: hijos A[7]=23, A[8]=24. mínimo=23 → swap(3,7)
     [10, 11, 1, 23, 14, 31, 8, 33, 24]
     Sift-down desde 7: sin hijos → fin

i=0: A[0]=10, hijos: A[1]=11, A[2]=1. mínimo=1 en pos 2 → swap(0,2)
     [1, 11, 10, 23, 14, 31, 8, 33, 24]
     Sift-down desde 2: hijos A[5]=31, A[6]=8. mínimo=8 en pos 6 → swap(2,6)
     [1, 11, 8, 23, 14, 31, 10, 33, 24]
     Sift-down desde 6: sin hijos → fin
```

**Min-Heap final: `[1, 11, 8, 23, 14, 31, 10, 33, 24]`**

```
           1
          / \
        11    8
       /  \  / \
     23  14 31  10
     / \
    33  24
```

### b) Insertar 7 en la Min-Heap

Array antes: `[1, 11, 8, 23, 14, 31, 10, 33, 24]`

1. Agregar 7 al final (índice 9): `[1, 11, 8, 23, 14, 31, 10, 33, 24, 7]`
2. Sift-up desde 9: padre = (9-1)/2 = 4 → A[4]=14. **7 < 14** → swap(9,4)
   `[1, 11, 8, 23, 7, 31, 10, 33, 24, 14]`
3. Sift-up desde 4: padre = (4-1)/2 = 1 → A[1]=11. **7 < 11** → swap(4,1)
   `[1, 7, 8, 23, 11, 31, 10, 33, 24, 14]`
4. Sift-up desde 1: padre = 0 → A[0]=1. **7 > 1** → stop

**Min-Heap final con 7: `[1, 7, 8, 23, 11, 31, 10, 33, 24, 14]`**

```
              1
            /   \
           7     8
          / \   / \
        23  11 31  10
       /  \ /
      33 24 14
```
