# Resolución — Tema 3 · 1ra Fecha Árboles y Heap (10/05/2025)

> Fuente: `tema 3 1er fecha 2025.jpeg`

---

## Ejercicio 1 (5 puntos)

### Enunciado
Implementar en `ParcialArboles`:

```java
public static List<Integer> primerCaminoAlternanteCeroNoCero(GeneralTree<Integer> arbol)
```

Devuelve los nodos del **primer camino válido** (el primero encontrado DFS de izq a der) desde la raíz hasta una hoja, donde los valores **alternan entre terminar en cero y no terminar en cero** en cada paso. **El método debe terminar ni bien encuentre un camino válido.** Sin camino válido → lista vacía.

### Ejemplo del enunciado
```
        20
       /  \
      3    7
     /|\    \
   42 10  6  50
         \
         80
```
- 20→3→42: (termina cero→no→no) ✗ No alterna
- 20→3→10: (termina cero→no→sí) ✓ **Primer camino válido → DEVUELVE [20,3,10]**

### Resolución

```java
public class ParcialArboles {

    public static List<Integer> primerCaminoAlternanteCeroNoCero(GeneralTree<Integer> arbol) {
        List<Integer> resultado = new LinkedList<>();
        if (arbol != null && !arbol.isEmpty()) {
            construirCamino(arbol, resultado, new LinkedList<>());
        }
        return resultado;
    }

    // Retorna true si encontró un camino válido (para detener la búsqueda)
    private static boolean construirCamino(GeneralTree<Integer> nodo,
                                           List<Integer> resultado,
                                           List<Integer> camino) {
        camino.add(nodo.getData());
        boolean terminaEnCero = nodo.getData() % 10 == 0;

        if (nodo.isLeaf()) {
            resultado.addAll(camino);
            camino.remove(camino.size() - 1);
            return true;  // ¡Encontrado! Detener búsqueda
        } else {
            for (GeneralTree<Integer> hijo : nodo.getChildren()) {
                boolean hijoTerminaEnCero = hijo.getData() % 10 == 0;
                if (terminaEnCero != hijoTerminaEnCero) {  // alterna
                    if (construirCamino(hijo, resultado, camino)) {
                        camino.remove(camino.size() - 1);
                        return true;  // propagar: encontrado arriba
                    }
                }
            }
        }

        camino.remove(camino.size() - 1);
        return false;
    }
}
```

**Diferencias clave con Tema 1 y 2:**
- Busca el **PRIMERO** (no el más largo ni el de mayor suma)
- El helper retorna **boolean**: `true` = encontrado, para cortar la búsqueda inmediatamente
- No necesita `sumaMax` ni comparación de tamaños
- Condición: `dato % 10 == 0` (termina en cero = último dígito es 0)

**Verificación con el ejemplo:**
- `20 % 10 = 0` → terminaEnCero = true
- Hijo `3`: `3 % 10 = 3 ≠ 0` → hijoTerminaEnCero = false → true ≠ false → recursivo en 3
- `3 % 10 = 3` → terminaEnCero = false
  - Hijo `42`: `42 % 10 = 2 ≠ 0` → false ≠ false → false = false → **NO recursa** (mismo signo)
  - Hijo `10`: `10 % 10 = 0` → hijoTerminaEnCero = true → false ≠ true → recursivo en 10
    - `10` es hoja → resultado = [20, 3, 10] → return true → **corta todo**

---

## Ejercicio 2 (1.5 puntos)

### a) Expresión postfija de: `A+(B-C)*D/(E*F)`

**Árbol de expresión:**
```
         +
        / \
       A   /
          / \
         *   *
        / \ / \
       -  D E  F
      / \
     B   C
```

**PostOrden** (izq→der→raíz): `A B C - D * E F * / +`

En notación concatenada: **`ABC-D*EF*/+`**

**Respuesta: (b) `ABC-D*EF*/+`**

### b) BuildHeap Min-Heap con `G P C B A E H I` (orden alfabético: A<B<C<E<G<H<I<P)

Array (0-indexed): `[G, P, C, B, A, E, H, I]`  N=8

```
i=3: A[3]=B, hijo A[7]=I. B<I (alfabético) → sin cambio
i=2: A[2]=C, hijos A[5]=E, A[6]=H. C<E → sin cambio
i=1: A[1]=P, hijos A[3]=B, A[4]=A. mínimo=A en pos 4 → swap(1,4)
     [G, A, C, B, P, E, H, I]
     Sift-down desde 4: sin hijos → fin
i=0: A[0]=G, hijos A[1]=A, A[2]=C. mínimo=A en pos 1 → swap(0,1)
     [A, G, C, B, P, E, H, I]
     Sift-down desde 1: hijos A[3]=B, A[4]=P. mínimo=B en pos 3 → swap(1,3)
     [A, B, C, G, P, E, H, I]
     Sift-down desde 3: hijo A[7]=I. G<I → fin
```

**Min-Heap final (recorrido por niveles): `A B C G P E H I`**

**Respuesta: (a) `A B C G P E H I`**

### d) Sentencias sobre árboles

- **(i) Un árbol binario puede contener al menos 2^L nodos en el nivel L** → **FALSO** (un AB tiene como máximo 2^L nodos en el nivel L, no "al menos")
- **(ii) Un AB completo de altura H contiene 2^L nodos en cada nivel L, hasta la altura (H-1)** → **VERDADERO** (todos los niveles de 0 a H-1 están completamente llenos)
- **(iii) La altura de un AG lleno de grado K puede expresarse como H = log_k(N+1) - 1** → **FALSO** (esa fórmula es solo para AB lleno: N = 2^(h+1)-1 → h = log_2(N+1)-1; para k≠2 la fórmula es diferente)
- **(iv) Un AG lleno de grado K y altura H tiene k^L nodos en el nivel L** → **VERDADERO**

**Respuesta: (e) (ii) y (iv)**

---

## Ejercicio 3 (3.5 puntos)

### a) Max-Heap insertando uno a uno: `12 33 1 21 74 81 28 88 99`

```
Insert(12):  [12]
Insert(33):  [12,33] → 33>12 → swap → [33,12]
Insert(1):   [33,12,1] → 1<33 → stop. [33,12,1]
Insert(21):  [33,12,1,21] → 21>12→swap→[33,21,1,12]→21<33→stop
Insert(74):  [33,21,1,12,74] → 74>21→swap→[33,74,1,12,21]→74>33→swap→[74,33,1,12,21]
Insert(81):  [74,33,1,12,21,81] → 81>1→swap→[74,33,81,12,21,1]→81>74→swap→[81,33,74,12,21,1]
Insert(28):  [81,33,74,12,21,1,28] → 28<74→stop. Sin cambio
Insert(88):  [81,33,74,12,21,1,28,88] → 88>12→swap→[81,33,74,88,21,1,28,12]
             → 88>33→swap→[81,88,74,33,21,1,28,12]→88>81→swap→[88,81,74,33,21,1,28,12]
Insert(99):  [88,81,74,33,21,1,28,12,99] → 99>33→swap→[88,81,74,99,21,1,28,12,33]
             → 99>81→swap→[88,99,74,81,21,1,28,12,33]→99>88→swap→[99,88,74,81,21,1,28,12,33]
```

**Max-Heap final: `[99, 88, 74, 81, 21, 1, 28, 12, 33]`**

```
              99
            /    \
          88      74
         /  \    /  \
        81  21   1   28
       / \
      12  33
```

### b) DeleteMax

1. Sacar raíz (99), poner último (33) en la raíz:
   `[33, 88, 74, 81, 21, 1, 28, 12]`
2. Sift-down desde 0: hijos A[1]=88, A[2]=74. máximo=88 → swap(0,1)
   `[88, 33, 74, 81, 21, 1, 28, 12]`
3. Sift-down desde 1: hijos A[3]=81, A[4]=21. máximo=81 → swap(1,3)
   `[88, 81, 74, 33, 21, 1, 28, 12]`
4. Sift-down desde 3: hijo A[7]=12. 33>12 → stop

**Max-Heap final después de DeleteMax: `[88, 81, 74, 33, 21, 1, 28, 12]`**

```
              88
            /    \
          81      74
         /  \    /  \
        33  21   1   28
       /
      12
```
